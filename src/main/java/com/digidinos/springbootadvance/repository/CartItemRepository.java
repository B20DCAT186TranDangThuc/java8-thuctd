package com.digidinos.springbootadvance.repository;

import com.digidinos.springbootadvance.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCartId(Long cartId);

    Optional<CartItem> findByCartIdAndProductId(Long id, Long productId);

    @Modifying
    @Transactional
    @Query("DELETE FROM CartItem ci WHERE ci.cart.account.id = :accountId AND ci.product.id = :productId")
    void deleteByAccountIdAndProductId(@Param("accountId") Long accountId, @Param("productId") Long productId);

    @Query("SELECT COUNT(ci) FROM CartItem ci WHERE ci.cart.account.id = :accountId")
    long countByAccountId(@Param("accountId") Long accountId);
}
