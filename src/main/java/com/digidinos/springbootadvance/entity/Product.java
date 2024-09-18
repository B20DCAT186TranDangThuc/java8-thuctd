package com.digidinos.springbootadvance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "image")
    private byte[] image;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

}
