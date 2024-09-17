package com.digidinos.shopping.controller.auth;

import com.digidinos.shopping.dao.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {

    @Autowired
    private AccountDAO accountDAO;

    // GET: Hiển thị trang login
    @RequestMapping(value = { "/login" }, method = RequestMethod.GET)
    public String login(Model model) {
        System.out.println("login");
        return "/auth/login";
    }

    @GetMapping("/register")
    public String getPageRegister(Model model) {
        return "/auth/register";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("username");
        return "redirect:/login?logout";
    }
}
