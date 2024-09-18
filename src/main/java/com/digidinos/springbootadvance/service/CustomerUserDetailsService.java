package com.digidinos.springbootadvance.service;

import com.digidinos.springbootadvance.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    private final AccountService accountService;

    public CustomerUserDetailsService(AccountService accountService) {
        this.accountService = accountService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountService.getAccountByUsername(username);

        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return new User(
                account.getUsername(),
                account.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + account.getRole())));
    }
}
