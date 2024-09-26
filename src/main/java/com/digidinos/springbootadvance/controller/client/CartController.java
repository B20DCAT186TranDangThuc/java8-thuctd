package com.digidinos.springbootadvance.controller.client;

import com.digidinos.springbootadvance.entity.Cart;
import com.digidinos.springbootadvance.entity.Order;
import com.digidinos.springbootadvance.form.OrderDetailInfo;
import com.digidinos.springbootadvance.form.OrderForm;
import com.digidinos.springbootadvance.form.OrderItem;
import com.digidinos.springbootadvance.repository.CartItemRepository;
import com.digidinos.springbootadvance.service.CartService;
import com.digidinos.springbootadvance.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.naming.Binding;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/carts")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CartItemRepository cartItemRepository;


    @GetMapping()
    public String getCartPage(HttpSession session, Model model) {

        Long accountId = (Long) session.getAttribute("userIdSS");

        if (accountId == null) {
            return "redirect:/login";
        }

        Cart cart = cartService.findCartByAccountId(accountId);
        List<OrderItem> orderItems = cart.getCartItems().stream()
                .map(cartItem -> {
                    OrderItem orderItem = OrderItem.builder()
                            .productId(cartItem.getProduct().getId())
                            .quantity(cartItem.getQuantity())
                            .nameProduct(cartItem.getProduct().getName())
                            .price(cartItem.getProduct().getPrice())
                            .amount(cartItem.getAmount())
                            .build();
                    return orderItem;
                })
                .collect(Collectors.toList());

        OrderForm orderForm = new OrderForm();
        orderForm.setOrderDetailInfos(orderItems);
        orderForm.setOrderAmount(orderItems.stream().map(OrderItem::getAmount).reduce(Double.valueOf(0), (a, b) -> a + b));

        model.addAttribute("orderForm", orderForm);


        return "client/order/checkout";
    }


    // add product in list product to cart
    @GetMapping("/add/{productId}")
    public String addToCartFromListProduct(@PathVariable("productId") Long productId,
                                           Model model, HttpSession session,
                                           HttpServletRequest request) {

        Long userId = (Long) session.getAttribute("userIdSS");

        if (userId == null) {
            return "redirect:/login";
        }

        if (cartService.addCartItem(productId, 1, userId)) {
            model.addAttribute("success", "Add to cart success");
            request.getSession().setAttribute("numberCartItem", cartItemRepository.countByAccountId(userId));
        }

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;

    }

    @PostMapping("/checkout")
    public String checkout(@Valid @ModelAttribute("orderForm") OrderForm orderForm,
                           BindingResult bindingResult,
                           Model model, HttpSession session) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("orderForm", orderForm);
            return "client/order/checkout";
        }

        Long accountId = (Long) session.getAttribute("userIdSS");

        orderService.save(orderForm, accountId);

        if (orderForm.getOrderDetailInfos().size() > 1) {
            //logic xoa het product trong cart
            orderForm.getOrderDetailInfos().forEach(orderDetailInfo -> {
                cartItemRepository.deleteByAccountIdAndProductId(accountId, orderDetailInfo.getProductId());
            });
            session.setAttribute("numberCartItem", 0);
        }

        return "redirect:/products";
    }


}
