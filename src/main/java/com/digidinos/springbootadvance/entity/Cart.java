package com.digidinos.springbootadvance.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private double totalPrice;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

    public Cart(Account account, double totalPrice) {
        this.account = account;
        this.totalPrice = totalPrice;
    }

}
