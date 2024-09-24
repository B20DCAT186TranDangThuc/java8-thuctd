package com.digidinos.springbootadvance.controller.client;

import com.digidinos.springbootadvance.entity.OrderDetail;
import com.digidinos.springbootadvance.entity.Product;
import com.digidinos.springbootadvance.form.OrderDetailInfo;
import com.digidinos.springbootadvance.form.OrderForm;
import com.digidinos.springbootadvance.form.OrderItem;
import com.digidinos.springbootadvance.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public String orders(HttpSession session, Model model, HttpServletRequest request,
                         RedirectAttributes redirectAttributes) {

        Long productId = (Long) session.getAttribute("productId");
        int quantity = (Integer) session.getAttribute("quantity");

        Product product = productService.findProduct(productId);
        OrderItem orderItem = OrderItem.builder()
                .productId(productId)
                .quantity(quantity)
                .nameProduct(product.getName())
                .price(product.getPrice())
                .amount(product.getPrice() * quantity)
                .build();

        OrderForm orderForm = new OrderForm();
        orderForm.setOrderDetailInfos(List.of(orderItem));
        orderForm.setOrderAmount(product.getPrice() * quantity);

        model.addAttribute("orderForm", orderForm);

        return "client/order/checkout";
    }

}
