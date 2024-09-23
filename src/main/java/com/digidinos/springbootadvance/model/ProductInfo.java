package com.digidinos.springbootadvance.model;


import com.digidinos.springbootadvance.entity.BaseEntity;
import com.digidinos.springbootadvance.entity.Product;
import lombok.Data;

@Data
public class ProductInfo extends BaseEntity {

    private String code;
    private String name;
    private double price;
    private String description;
    private long quantity;

    public ProductInfo(Product product) {
        this.id = product.getId();
        this.code = product.getCode();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.quantity = product.getQuantity();
    }
}
