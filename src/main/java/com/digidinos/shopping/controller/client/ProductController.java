package com.digidinos.shopping.controller.client;

import com.digidinos.shopping.dao.ProductDAO;
import com.digidinos.shopping.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

    @Autowired
    ProductDAO productDAO;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("products", productDAO.findAllProductInfo());
        return "client/home";
    }

    @GetMapping("/product/image/{productCode}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable("productCode") String productCode) {
        Product product = productDAO.findProduct(productCode);
        if (product != null && product.getImageData() != null) {
            // Tạo headers cho phản hồi
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "image/jpeg"); // hoặc tùy thuộc vào định dạng ảnh của bạn (jpeg, png, etc.)
            return new ResponseEntity<>(product.getImageData(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
