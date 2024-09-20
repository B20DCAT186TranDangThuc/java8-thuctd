package com.digidinos.springbootadvance.repository;

import com.digidinos.springbootadvance.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE " +
            "(?1 IS NULL OR p.code LIKE %?1% OR p.name LIKE %?1% OR p.description LIKE %?1%) AND " +
            "p.isDeleted = false")
    Page<Product> searchAndFilterProducts(String keyword, Pageable pageable);

    boolean existsByCode(String code);

}
