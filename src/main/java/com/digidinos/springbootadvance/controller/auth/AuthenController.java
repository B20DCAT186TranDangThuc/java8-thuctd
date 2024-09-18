package com.digidinos.springbootadvance.controller.auth;

import com.digidinos.springbootadvance.form.RegisterForm;
import com.digidinos.springbootadvance.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterForm());
        return "auth/register";
    }

    @PostMapping("/register")
    public String handRegister(
            @ModelAttribute("registerUser") @Valid RegisterForm registerForm,
            BindingResult bindingResult) {

        // validate
        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        // save account
        accountService.register(registerForm);
        return "redirect:/login";
    }
}
