package com.digidinos.springbootadvance.controller;

import com.digidinos.springbootadvance.entity.CartItem;
import com.digidinos.springbootadvance.entity.Product;
import com.digidinos.springbootadvance.form.OrderDetailInfo;
import com.digidinos.springbootadvance.model.ProductInfo;
import com.digidinos.springbootadvance.service.CartService;
import com.digidinos.springbootadvance.service.OrderService;
import com.digidinos.springbootadvance.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

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
    public String viewCartByProductId(@PathVariable("productId") Long productId, Model model) {
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

    @GetMapping("cart/view/{accountId}")
    public String viewCartByAccountId(@PathVariable("accountId") Long accountId, Model model) {



        return "client/product/cart";
    }

    @GetMapping("/items/{accountId}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable Long accountId) {
        List<CartItem> cartItems = cartService.getAllCartItemByAccountId(accountId);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }


    @GetMapping("/admin")
    public String getPageAdmin() {

        return "admin/home";
    }

}
