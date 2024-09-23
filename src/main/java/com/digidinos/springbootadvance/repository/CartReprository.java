package com.digidinos.springbootadvance.repository;

import com.digidinos.springbootadvance.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartReprository extends JpaRepository<Cart, Long> {
}
