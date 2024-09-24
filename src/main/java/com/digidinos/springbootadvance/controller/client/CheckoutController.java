package com.digidinos.springbootadvance.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("checkout")
public class CheckoutController {

    @GetMapping("/cart")
    public String checkout(Model model) {

        return "client/order/checkout";
    }
}
