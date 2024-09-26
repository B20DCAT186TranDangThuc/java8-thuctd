package com.digidinos.springbootadvance.model;


import com.digidinos.springbootadvance.entity.Order;
import com.digidinos.springbootadvance.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderInfo {

    private Long orderId;
    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private String customerEmail;

    private double amount;

    private String status;

    private LocalDateTime orderDate;

    private List<OrderDetail> orderDetailList;

    public OrderInfo(Order order) {
        this.orderId = order.getId();
        this.customerName = order.getCustomerName();
        this.customerAddress = order.getCustomerAddress();
        this.customerPhone = order.getCustomerPhone();
        this.customerEmail = order.getCustomerEmail();
        this.amount = order.getAmount();
        this.status = order.getStatus();
        this.orderDate = order.getOrderDate();
        this.orderDetailList = order.getOrderDetails();
    }
}