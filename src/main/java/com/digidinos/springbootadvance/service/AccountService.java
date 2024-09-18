package com.digidinos.springbootadvance.service;

import com.digidinos.springbootadvance.entity.Account;
import com.digidinos.springbootadvance.form.AccountForm;
import com.digidinos.springbootadvance.form.RegisterForm;
import com.digidinos.springbootadvance.model.AccountInfo;
import com.digidinos.springbootadvance.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Account getAccountByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    public Account register(RegisterForm registerForm) {
        Account account = new Account();
        account.setUsername(registerForm.getUsername());
        account.setPassword(passwordEncoder.encode(registerForm.getPassword()));
        account.setRole("USER");
        account.setCreateAt(LocalDateTime.now());
        account.setUpdateAt(LocalDateTime.now());
        return accountRepository.save(account);
    }

    public Page<AccountInfo> handleSearchAndFilterAccount(String keyword, String role, Pageable pageable) {
        Page<Account> userPage = accountRepository.searchAndFilterUsers(keyword, role, pageable);
        List<AccountInfo> accountInfoList = new ArrayList<>();

        for (Account account : userPage.getContent()) {
            AccountInfo accountInfo = new AccountInfo();
            accountInfo.setUsername(account.getUsername());
            accountInfo.setId(account.getId());
            accountInfo.setRole(account.getRole());
            accountInfoList.add(accountInfo);
        }

        return new PageImpl<>(accountInfoList, pageable, userPage.getTotalElements());
    }

    public Account createAccount(AccountForm accountForm) {
        Account account = Account.builder()
                .username(accountForm.getUsername())
                .password(passwordEncoder.encode(accountForm.getPassword()))
                .role(accountForm.getRole())
                .build();

        account.setCreateAt(LocalDateTime.now());
        account.setUpdateAt(LocalDateTime.now());

        return accountRepository.save(account);
    }

    public AccountForm getAccountById(Long id) {
        Account account = accountRepository.findById(id).get();
        if (account == null) {
            return null;
        }
        AccountForm accountForm = new AccountForm();
        accountForm.setId(id);
        accountForm.setUsername(account.getUsername());
        accountForm.setRole(account.getRole());

        return accountForm;
    }

    public Account updateAccount(AccountForm accountForm) {

        Account account = accountRepository.findById(accountForm.getId()).get();

        if (account == null) {
            return null;
        }

        account.setUsername(accountForm.getUsername());
        account.setPassword(passwordEncoder.encode(accountForm.getPassword()));
        account.setRole(accountForm.getRole());
        account.setUpdateAt(LocalDateTime.now());
        return accountRepository.save(account);

    }

    public AccountInfo getAccountInfoById(Long userId) {
        Account account = accountRepository.findById(userId).get();
        if (account == null) {
            return null;
        }
        AccountInfo accountInfo = AccountInfo.builder()
                .username(account.getUsername())
                .role(account.getRole())
                .build();
        accountInfo.setId(account.getId());
        accountInfo.setCreateAt(account.getCreateAt());
        accountInfo.setUpdateAt(account.getUpdateAt());

        return accountInfo;
    }

    public boolean deleteAccount(Long userId) {
        Account account = accountRepository.findById(userId).get();

        account.setDeleted(true);

        account.setUpdateAt(LocalDateTime.now());
        account.setDeleteAt(LocalDateTime.now());

        return accountRepository.save(account) != null;
    }
}
