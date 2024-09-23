package com.digidinos.springbootadvance.controller.admin;


import com.digidinos.springbootadvance.form.AccountForm;
import com.digidinos.springbootadvance.model.AccountInfo;
import com.digidinos.springbootadvance.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/accounts")
public class AccountManagementController {

    @Autowired
    private AccountService accountService;

    @GetMapping()
    public String getListAccount(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                 @RequestParam(name = "keyword", required = false) String keyword,
                                 @RequestParam(name = "role", required = false) String role,
                                 Model model) {

        Pageable pageable = (Pageable) PageRequest.of(page, 5, Sort.by("updateAt").descending());
        Page<AccountInfo> userPage = accountService.handleSearchAndFilterAccount(keyword, role, pageable);

        model.addAttribute("listRole", List.of("ADMIN", "CUSTOMER", "EMPLOYEE"));
        model.addAttribute("userPage", userPage);
        model.addAttribute("keyword", keyword);
        model.addAttribute("role", role);
        model.addAttribute("page", userPage.getNumber() + 1);
        model.addAttribute("pageSize", userPage.getTotalPages());

        return "admin/account/list";
    }

    @GetMapping("/create")
    public String createAccount(Model model) {
        model.addAttribute("newUser", new AccountForm());
        return "admin/account/create";
    }

    @PostMapping("/create")
    public String createAccount(@ModelAttribute("newUser") @Valid AccountForm accountForm,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {
        // validate
        if (bindingResult.hasErrors()) {
            return "admin/account/create";
        }

        if (accountService.getAccountByUsername(accountForm.getUsername()) != null) {
            redirectAttributes.addAttribute("error", "Account already exists");
            return "redirect:/admin/accounts/create";
        }

        if (accountService.createAccount(accountForm) == null) {
            redirectAttributes.addAttribute("error", "Account creation failed");
            return "redirect:/admin/accounts/create";
        }

        redirectAttributes.addAttribute("success", "Account created");
        return "redirect:/admin/accounts";

    }

    @GetMapping("/update/{userId}")
    public String updateAccount(@PathVariable("userId") Long userId,
                                RedirectAttributes redirectAttributes,
                                Model model) {

        AccountForm accountForm = accountService.getAccountById(userId);
        if (accountForm == null) {
            redirectAttributes.addAttribute("error", "Account not found");
            return "redirect:/admin/accounts";
        }

        model.addAttribute("accountForm", accountForm);
        return "admin/account/update";
    }

    @PostMapping("/update")
    public String updateAccount(@Valid @ModelAttribute("accountForm") AccountForm accountForm,
                                BindingResult bindingResult,
                                RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "admin/account/update";
        }

        if (accountService.updateAccount(accountForm) == null) {
            redirectAttributes.addAttribute("error", "Account creation failed");
            return "redirect:/admin/accounts/create";
        }
        redirectAttributes.addAttribute("success", "Account updated");
        return "redirect:/admin/accounts";
    }

    @GetMapping("/detail/{userId}")
    public String getAccountDetail(@PathVariable("userId") Long userId,
                                   Model model) {

        model.addAttribute("accountInfo", accountService.getAccountInfoById(userId));

        return "admin/account/detail";
    }

    @GetMapping("/delete/{userId}")
    public String deleteAccount(@PathVariable("userId") Long userId,
                                RedirectAttributes redirectAttributes) {

        boolean check = accountService.deleteAccount(userId);

        if (!check) {
            redirectAttributes.addAttribute("errorDelete", "Can't delete account");
            return "redirect:/detail/" + userId;
        }

        redirectAttributes.addAttribute("success", "Account deleted");
        return "redirect:/admin/accounts";
    }

}
