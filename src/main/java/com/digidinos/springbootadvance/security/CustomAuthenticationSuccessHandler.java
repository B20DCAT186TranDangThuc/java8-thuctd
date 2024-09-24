package com.digidinos.springbootadvance.security;


import com.digidinos.springbootadvance.entity.Account;
import com.digidinos.springbootadvance.repository.CartItemRepository;
import com.digidinos.springbootadvance.service.AccountService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private AccountService accountService;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        Account account = accountService.getAccountByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException(username);
        }

        request.getSession().setAttribute("userIdSS", account.getId());
        request.getSession().setAttribute("userNameSS", account.getUsername());
        request.getSession().setAttribute("roleNameSS", account.getRole());

        request.getSession().setAttribute("numberCartItem", cartItemRepository.countByAccountId(account.getId()));

        if (account.getRole().equals("ADMIN") || account.getRole().equals("EMPLOYEE")) {
            response.sendRedirect(request.getContextPath() + "/admin");
        } else {
            response.sendRedirect(request.getContextPath() + "/");
        }

    }
}