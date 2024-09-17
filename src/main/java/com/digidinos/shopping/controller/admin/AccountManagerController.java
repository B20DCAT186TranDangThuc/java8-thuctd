package com.digidinos.shopping.controller.admin;

import com.digidinos.shopping.dao.AccountDAO;
import com.digidinos.shopping.entity.Account;
import com.digidinos.shopping.form.AccountCreateForm;
import com.digidinos.shopping.form.AccountUpdateForm;
import com.digidinos.shopping.pagination.PaginationResult;
import com.digidinos.shopping.validator.AccountCreateFormValidator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;

@Controller
@RequestMapping("/admin/accounts")
public class AccountManagerController {

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private AccountCreateFormValidator accountCreateFormValidator;

    @GetMapping()
    public String getPageUsers(Model model) {
        model.addAttribute("accounts", accountDAO.findAllAccounts());
        model.addAttribute("roles", Arrays.asList("MANAGER", "EMPLOYEE"));
        return "/admin/account/user-list";
    }

//    @GetMapping()
//    public String getAccounts(
//            @RequestParam(defaultValue = "1") int page,
//            @RequestParam(defaultValue = "3") int maxResult,
//            @RequestParam(defaultValue = "5") int maxNavigationPage,
//            Model model) {
//
//        PaginationResult<Account> paginationResult = accountDAO.getAccounts(page, maxResult, maxNavigationPage);
//        model.addAttribute("paginationResult", paginationResult);
//
//        System.out.println(paginationResult);
//
//        return "/admin/account/user-list"; // Trả về tên file Thymeleaf (accounts.html)
//    }

    @GetMapping("/add")
    public String getPageAdd(Model model) {
        model.addAttribute("account", new AccountCreateForm());
        model.addAttribute("roles", Arrays.asList("MANAGER", "EMPLOYEE"));
        return "/admin/account/add";
    }

    @PostMapping("/add")
    public String addAccount(@ModelAttribute("account") AccountCreateForm accountCreateForm,
                             BindingResult bindingResult, Model model,
                             RedirectAttributes redirectAttributes) {

        accountCreateFormValidator.validate(accountCreateForm, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", Arrays.asList("MANAGER", "EMPLOYEE"));
            return "/admin/account/add";
        }
        boolean status = accountDAO.createAccount(accountCreateForm);
        if (!status) {
            System.out.println("Username is exits");
            redirectAttributes.addFlashAttribute("errorCreate", "Username already exists.");
            return "redirect:/admin/accounts/add"; // Redirect đến trang thêm tài khoản
        }
        return "redirect:/admin/accounts";
    }


    @GetMapping("/detail")
    public String getAccountDetail(@ModelAttribute("username") String username, Model model) {
        model.addAttribute("accountDetail",accountDAO.findAccount(username));
        return "/admin/account/detail";
    }

    @GetMapping("/update")
    public String getAccountUpdate(@ModelAttribute("username") String username, Model model) {
        model.addAttribute("account", accountDAO.findAccountUpdateForm(username));
        model.addAttribute("roles", Arrays.asList("MANAGER", "EMPLOYEE"));
        return "/admin/account/update";
    }

    @PostMapping("/update")
    public String updateAccount(@ModelAttribute("account") AccountCreateForm accountCreateForm,
                                BindingResult bindingResult, Model model) {
        accountCreateFormValidator.validate(accountCreateForm, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", Arrays.asList("MANAGER", "EMPLOYEE"));
            return "/admin/account/update";
        }

        boolean status = accountDAO.updateAccount(accountCreateForm);
        if (!status) {
            model.addAttribute("Update fail");
            return "redirect:/admin/accounts/add";
        }
        return "redirect:/admin/accounts";
    }

    @GetMapping("/delete")
    public String deleteAccount(@RequestParam("username") String username, RedirectAttributes redirectAttributes) {
        // Gọi phương thức xóa tài khoản
        if (accountDAO.deleteAccount(username)) {
            redirectAttributes.addFlashAttribute("infoDelete", "Account deleted successfully.");
        } else {
            redirectAttributes.addFlashAttribute("errorDelete", "Failed to delete account.");
        }

        // Chuyển hướng về trang danh sách tài khoản
        return "redirect:/admin/accounts";
    }

}
