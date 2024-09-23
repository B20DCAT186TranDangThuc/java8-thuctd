package com.digidinos.springbootadvance.repository;

import com.digidinos.springbootadvance.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE " +
            "(?1 IS NULL OR o.customerName LIKE %?1% OR " +
            "CAST(o.amount AS string) LIKE %?1% OR " +
            "CAST(o.id AS string) LIKE %?1%)")
    Page<Order> searchOrdersByKeyword(String keyword, Pageable pageable);

}
