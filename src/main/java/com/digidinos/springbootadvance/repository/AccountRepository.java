package com.digidinos.springbootadvance.repository;

import com.digidinos.springbootadvance.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByUsername(String username);

    @Query("SELECT a FROM Account a WHERE " +
                  "(?1 IS NULL OR a.username LIKE %?1% OR a.role LIKE %?1%) AND " +
                  "(?2 IS NULL OR ?2 = '' OR a.role = ?2) AND " +
                  "a.isDeleted = false")
    Page<Account> searchAndFilterUsers(String keyword, String role, Pageable pageable);
}
