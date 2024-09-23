package com.digidinos.springbootadvance.service;

import com.digidinos.springbootadvance.entity.Order;
import com.digidinos.springbootadvance.entity.OrderDetail;
import com.digidinos.springbootadvance.entity.Product;
import com.digidinos.springbootadvance.form.OrderDetailInfo;
import com.digidinos.springbootadvance.model.OrderInfo;
import com.digidinos.springbootadvance.repository.OrderDetailRepository;
import com.digidinos.springbootadvance.repository.OrderRepository;
import com.digidinos.springbootadvance.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    public Page<OrderInfo> searchOrders(String keyword, Pageable pageable) {
        Page<Order> orders = orderRepository.searchOrdersByKeyword(keyword, pageable);
        List<OrderInfo> res = new ArrayList<>();

        for (Order order : orders) {
            OrderInfo orderInfo = OrderInfo.builder()
                    .orderId(order.getId())
                    .customerName(order.getCustomerName())
                    .amount(order.getAmount())
                    .orderDate(order.getOrderDate())
                    .build();

            res.add(orderInfo);
        }
        return new PageImpl<>(res, pageable, orders.getTotalElements());
    }

    public boolean save(OrderDetailInfo orderDetailInfo) {

        Product product = productRepository.findById(orderDetailInfo.getProductId()).get();

        Order order = Order.builder()
                .customerName(orderDetailInfo.getCustomerName())
                .customerPhone(orderDetailInfo.getCustomerPhone())
                .customerAddress(orderDetailInfo.getCustomerAddress())
                .customerEmail(orderDetailInfo.getCustomerEmail())
                .orderDate(LocalDateTime.now())
                .amount(orderDetailInfo.getPrice() * orderDetailInfo.getQuantity())
                .build();
        order.setCreateAt(LocalDateTime.now());
        order.setUpdateAt(LocalDateTime.now());

        Order newOrder = orderRepository.save(order);
        if (newOrder != null) {
            OrderDetail orderDetail = OrderDetail.builder()
                    .price(orderDetailInfo.getPrice())
                    .quantity(orderDetailInfo.getQuantity())
                    .amount(orderDetailInfo.getPrice() * orderDetailInfo.getQuantity())
                    .product(product)
                    .order(newOrder)
                    .build();
            orderDetail.setCreateAt(LocalDateTime.now());
            orderDetail.setUpdateAt(LocalDateTime.now());
            orderDetailRepository.save(orderDetail);
            return true;
        }
        return false;
    }
}
