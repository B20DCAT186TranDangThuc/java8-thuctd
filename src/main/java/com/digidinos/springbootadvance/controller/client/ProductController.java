package com.digidinos.springbootadvance.controller.client;

import com.digidinos.springbootadvance.entity.Cart;
import com.digidinos.springbootadvance.form.CommentForm;
import com.digidinos.springbootadvance.form.OrderDetailInfo;
import com.digidinos.springbootadvance.model.ProductInfo;
import com.digidinos.springbootadvance.service.CartService;
import com.digidinos.springbootadvance.service.CommentService;
import com.digidinos.springbootadvance.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;
    @Autowired
    private CommentService commentService;

    @GetMapping()
    public String getPageProduct(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                 Model model) {

        Pageable pageable = (Pageable) PageRequest.of(page, 4, Sort.by("updateAt").descending());
        Page<ProductInfo> productPage = productService.handleSearchAndFilterAccount("", pageable);

        model.addAttribute("productPage", productPage);
        model.addAttribute("page", productPage.getNumber() + 1);
        model.addAttribute("pageSize", productPage.getTotalPages());
        return "client/product/list";
    }

    @GetMapping("/detail/{productId}")
    public String productDetail(@PathVariable("productId") Long productId, Model model) {

        model.addAttribute("product", productService.findProduct(productId));

        model.addAttribute("orderItem", new OrderDetailInfo());

        model.addAttribute("listComment", commentService.findAllByProductId(productId));

        model.addAttribute("comment", new CommentForm());

        return "client/product/detail";
    }

    @PostMapping("/comment")
    public String submitComment(@Valid @ModelAttribute("commentForm") CommentForm commentForm,
                                BindingResult result,
                                Model model) {
        // Tìm sản phẩm theo ID
        model.addAttribute("product", productService.findProduct(commentForm.getProductId()));

        // Kiểm tra lỗi trong form
        if (result.hasErrors()) {

            return "client/product/detail";
        }

        // Xử lý lưu comment cho sản phẩm
        commentService.saveComment(commentForm);

        // Thêm thông báo thành công
        model.addAttribute("message", "Comment đã được gửi thành công!");
        return "redirect:/products/detail/" + commentForm.getProductId();
    }

    @PostMapping("/switch")
    public String handleOrderItem(@ModelAttribute("orderItem") OrderDetailInfo orderDetailInfo,
                                  @RequestParam("submit") String action,
                                  HttpSession session) {

        if ("addtocart".equals(action)) {
            // Lưu đối tượng orderItem vào session
            session.setAttribute("orderItem", orderDetailInfo);
            return "redirect:/products/addToCart";
        } else if ("buy".equals(action)) {
            // Lưu đối tượng orderItem vào session
            session.setAttribute("productId", orderDetailInfo.getProductId());
            session.setAttribute("quantity", orderDetailInfo.getQuantity());
            return "redirect:/orders";
        }
        return "redirect:/error";
    }


    // add product in product detail to cart
    @GetMapping("/addToCart")
    public String addToCart(HttpSession session, Model model, HttpServletRequest request,
                            RedirectAttributes redirectAttributes) {
        // Lấy userId từ session
        Long userId = (Long) session.getAttribute("userIdSS");

        if (userId == null) {
            return "redirect:/login";
        }

        // Lấy OrderDetailInfo từ session
        OrderDetailInfo orderDetailInfo = (OrderDetailInfo) session.getAttribute("orderItem");

        if (orderDetailInfo != null && cartService.addCartItem(orderDetailInfo.getProductId(), orderDetailInfo.getQuantity(), userId)) {
            redirectAttributes.addAttribute("success", "Add to cart success");
            session.removeAttribute("orderItem");
        }

        // Lấy trang trước đó để redirect lại
        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

}

