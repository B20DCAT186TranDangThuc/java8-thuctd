package com.digidinos.springbootadvance.controller.client;

import com.digidinos.springbootadvance.service.AccountService;
import com.digidinos.springbootadvance.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/info")
    public String info(Model model, HttpSession session) {

        Long id = (Long) session.getAttribute("userIdSS");

        model.addAttribute("accountInfo", accountService.getAccountById(id));

        model.addAttribute("listOrder", orderService.getAllOrderOfAccountById(id));

        return "client/account/info";
    }
}
