package com.digidinos.springbootadvance.service;

import com.digidinos.springbootadvance.entity.Cart;
import com.digidinos.springbootadvance.repository.CartReprository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CartService {

    @Autowired
    private CartReprository cartReprository;

    public Cart createCart(Cart cart) {
        cart.setCreateAt(LocalDateTime.now());
        cart.setUpdateAt(LocalDateTime.now());
        return cartReprository.save(cart);
    }

}
