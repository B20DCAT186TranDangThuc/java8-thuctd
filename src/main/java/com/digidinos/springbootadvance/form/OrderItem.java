package com.digidinos.springbootadvance.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {
    private Long productId;
    private String nameProduct;
    private int quantity;
    private double price;
    private double amount;
}
