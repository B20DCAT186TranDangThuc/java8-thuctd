package com.digidinos.springbootadvance.controller;

import com.digidinos.springbootadvance.entity.Order;
import com.digidinos.springbootadvance.entity.OrderDetail;
import com.digidinos.springbootadvance.entity.Product;
import com.digidinos.springbootadvance.form.CustomerForm;
import com.digidinos.springbootadvance.form.OrderDetailInfo;
import com.digidinos.springbootadvance.model.ProductInfo;
import com.digidinos.springbootadvance.service.OrderService;
import com.digidinos.springbootadvance.service.ProductService;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping()
    public String home(Model model) {
        List<ProductInfo> productInfos = productService.getAllProducts();
        model.addAttribute("products", productInfos);
        return "home";
    }

    @GetMapping("/uploads/{productCode}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable("productCode") String productCode) {
        Product product = productService.findProduct(Long.parseLong(productCode));
        if (product != null && product.getImage() != null) {
            // Tạo headers cho phản hồi
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "image/jpeg"); // hoặc tùy thuộc vào định dạng ảnh của bạn (jpeg, png, etc.)
            return new ResponseEntity<>(product.getImage(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/cart/view/{productId}")
    public String viewCart(@PathVariable("productId") Long productId, Model model) {
        Product product = productService.findProduct(productId);

        OrderDetailInfo orderDetailInfo = OrderDetailInfo.builder()
                .productId(product.getId())
                .nameProduct(product.getName())
                .price(product.getPrice())
                .quantity(1)
                .amount(product.getPrice())
                .build();

        model.addAttribute("orderDetailInfo", orderDetailInfo);

        return "client/product/cart";
    }

    @PostMapping("/cart/checkout")
    public String checkout(@ModelAttribute("orderDetailInfo") OrderDetailInfo orderDetailInfo,
                           Model model) {

        orderService.save(orderDetailInfo);

        return "redirect:/products";
    }

    @GetMapping("/admin")
    public String getPageAdmin() {

        return "admin/home";
    }

}
