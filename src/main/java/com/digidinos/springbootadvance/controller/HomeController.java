package com.digidinos.springbootadvance.controller;

import com.digidinos.springbootadvance.entity.Product;
import com.digidinos.springbootadvance.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public String home() {
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
}
