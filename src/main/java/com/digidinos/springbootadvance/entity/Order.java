package com.digidinos.springbootadvance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order extends BaseEntity {
    private static final long serialVersionUID = 1L;

    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private String customerEmail;

    private LocalDateTime orderDate;

    private double amount;

    private String status;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
