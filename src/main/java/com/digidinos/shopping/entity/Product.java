package com.digidinos.shopping.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "Products")
@Data
public class Product implements Serializable {
    private static final long serialVersionUID = -1000119078147252957L;
    @Id
    @Column(name = "Code", length = 20, nullable = false)
    private String code;

    @Column(name = "Name", length = 255, nullable = false)
    private String name;
    @Column(name = "Price", nullable = false)
    private double price;

    @Column(name = "Image")
    private byte[] imageData;

    @Column(name = "Is_Deleted", nullable = false)
    private boolean isDeleted = false;

    @Column(name = "Create_At")
    private LocalDateTime createAt;

    @Column(name = "Update_At")
    private LocalDateTime updateAt;

    @Column(name = "Delete_At")
    private LocalDateTime deleteAt;
}