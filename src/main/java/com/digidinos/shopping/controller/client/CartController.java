package com.digidinos.shopping.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class CartController {

    @GetMapping("/add")
    public String add() {

        return "/client/add-order";
    }
}
