package com.digidinos.shopping.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {

    @GetMapping("/cart")
    public String getPageCart() {
        return "/client/cart";
    }
}
