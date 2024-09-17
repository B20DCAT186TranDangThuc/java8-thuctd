package com.digidinos.shopping.dao;

import com.digidinos.shopping.entity.Account;
import com.digidinos.shopping.form.AccountCreateForm;
import com.digidinos.shopping.form.AccountUpdateForm;
import com.digidinos.shopping.model.AccountInfo;
import com.digidinos.shopping.pagination.PaginationResult;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Repository
public class AccountDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Account findAccount(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.find(Account.class, username);
    }

    public AccountUpdateForm findAccountUpdateForm(String username) {
        Account account = findAccount(username);
        return AccountUpdateForm.builder()
                .username(account.getUserName())
                .roleName(account.getUserRole())
                .active(account.isActive())
                .build();
    }

    public List<AccountInfo> findAllAccounts() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Account> accounts = session.createCriteria(Account.class).list();

        List<AccountInfo> accountInfos = new ArrayList<AccountInfo>();

        for (Account account : accounts) {
            if (!account.isDeleted()) {
                accountInfos.add(AccountInfo.builder()
                        .username(account.getUserName())
                        .roleName(account.getUserRole())
                        .active(account.isActive())
                        .createAt(account.getCreateAt())
                        .updateAt(account.getUpdateAt())
                        .build());
            }
        }
        return accountInfos;
    }

    public PaginationResult<Account> getAccounts(int page, int maxResult, int maxNavigationPage) {
        try (Session session = sessionFactory.openSession()) {
            Query<Account> query = session.createQuery("FROM Account WHERE isDeleted = false", Account.class);

            // Tạo đối tượng PaginationResult dựa trên truy vấn
            PaginationResult<Account> paginationResult = new PaginationResult<>(query, page, maxResult, maxNavigationPage);

            return paginationResult;
        }
    }


    public boolean createAccount(AccountCreateForm accountCreateForm) {

        if (findAccount(accountCreateForm.getUsername()) != null) {
            return false;
        }
        Account newAccount = Account.builder()
                .userName(accountCreateForm.getUsername())
                .userRole("ROLE_" + accountCreateForm.getRoleName())
                .encrytedPassword(bCryptPasswordEncoder.encode(accountCreateForm.getPassword()))
                .active(true)
                .isDeleted(false)
                .createAt(LocalDateTime.now())
                .updateAt(LocalDateTime.now())
                .build();

        // Lưu đối tượng Account vào cơ sở dữ liệu
        Session session = this.sessionFactory.getCurrentSession();
        try {
            session.save(newAccount);
            return true; // Lưu thành công
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có (có thể log hoặc ném lại)
            e.printStackTrace();
            return false; // Lưu không thành công
        }
    }

    public boolean updateAccount(AccountCreateForm accountUpdateForm) {
        // Lấy tài khoản hiện có từ cơ sở dữ liệu dựa trên username
        Session session = this.sessionFactory.getCurrentSession();
        try {
            // Tìm tài khoản dựa trên username
            Account existingAccount = session.get(Account.class, accountUpdateForm.getUsername());

            if (existingAccount != null) {
                // Cập nhật các trường cần thiết
                existingAccount.setUserName(accountUpdateForm.getUsername());
                existingAccount.setUserRole(accountUpdateForm.getRoleName());
                existingAccount.setEncrytedPassword(bCryptPasswordEncoder.encode(accountUpdateForm.getPassword()));
                existingAccount.setUpdateAt(LocalDateTime.now());

                // Lưu thay đổi
                session.update(existingAccount);
                return true; // Cập nhật thành công
            } else {
                return false; // Tài khoản không tồn tại
            }
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu có (có thể log hoặc ném lại)
            e.printStackTrace();
            return false; // Cập nhật không thành công
        }
    }

    public boolean deleteAccount(String username) {
        Session session = this.sessionFactory.getCurrentSession();

        // tim kiem tai khoan theo username
        Account existingAccount = session.get(Account.class, username);
        if (existingAccount != null) {
            existingAccount.setDeleted(true);
            existingAccount.setDeleteAt(LocalDateTime.now());
            session.update(existingAccount);
            return true;
        }
        return false;
    }

}
