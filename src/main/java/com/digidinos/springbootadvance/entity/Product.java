package com.digidinos.springbootadvance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private long quantity;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "product")
    private List<Comment> comments;

}
