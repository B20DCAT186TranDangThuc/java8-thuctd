package com.digidinos.springbootadvance.service;

import com.digidinos.springbootadvance.entity.Order;
import com.digidinos.springbootadvance.entity.OrderDetail;
import com.digidinos.springbootadvance.entity.Product;
import com.digidinos.springbootadvance.form.OrderForm;
import com.digidinos.springbootadvance.form.OrderItem;
import com.digidinos.springbootadvance.form.StatusOrderForm;
import com.digidinos.springbootadvance.model.OrderInfo;
import com.digidinos.springbootadvance.repository.AccountRepository;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Page<OrderInfo> searchOrders(String keyword, Pageable pageable) {
        Page<Order> orders = orderRepository.searchOrdersByKeyword(keyword, pageable);
        List<OrderInfo> res = new ArrayList<>();

        for (Order order : orders) {
            OrderInfo orderInfo = OrderInfo.builder()
                    .orderId(order.getId())
                    .customerName(order.getCustomerName())
                    .amount(order.getAmount())
                    .orderDate(order.getOrderDate())
                    .status(order.getStatus())
                    .build();

            res.add(orderInfo);
        }
        return new PageImpl<>(res, pageable, orders.getTotalElements());
    }

    public boolean save(OrderForm orderForm, Long accountId) {

        Order order = Order.builder()
                .account(accountRepository.findById(accountId).get())
                .customerName(orderForm.getCustomerName())
                .customerPhone(orderForm.getCustomerPhone())
                .customerAddress(orderForm.getCustomerAddress())
                .customerEmail(orderForm.getCustomerEmail())
                .amount(orderForm.getOrderDetailInfos().stream()
                        .mapToDouble(OrderItem::getAmount)
                        .sum())
                .orderDate(LocalDateTime.now())
                .status("PENDING")
                .build();

        order.setCreateAt(LocalDateTime.now());
        order.setUpdateAt(LocalDateTime.now());

        Order finalOrder = orderRepository.save(order);

        List<OrderDetail> orderDetails = orderForm.getOrderDetailInfos().stream()
                .map(orderItem -> {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setProduct(productRepository.findById(orderItem.getProductId()).get());
                    orderDetail.setOrder(finalOrder);
                    orderDetail.setQuantity(orderItem.getQuantity());
                    orderDetail.setPrice(orderItem.getPrice());
                    orderDetail.setAmount(orderItem.getAmount());

                    orderDetail.setCreateAt(LocalDateTime.now());
                    orderDetail.setUpdateAt(LocalDateTime.now());

                    // Giảm số lượng tồn kho của sản phẩm
                    Product product = orderDetail.getProduct();
                    product.setQuantity(product.getQuantity() - orderItem.getQuantity());
                    productRepository.save(product); // Lưu lại sản phẩm với số lượng đã cập nhật

                    return orderDetail;
                })
                .collect(Collectors.toList());

        orderDetailRepository.saveAll(orderDetails);

        return true;

    }

    public List<Order> getAllOrderOfAccountById(Long id) {
        return orderRepository.findAllByAccount_Id(id);
    }

    public Optional<OrderInfo> handleFindByOrderId(Long orderId) {
        return orderRepository.findById(orderId).map(OrderInfo::new);
    }

    public Order updateStatus(StatusOrderForm form) {
        Order order = orderRepository.findById(form.getOrderId()).get();

        order.setStatus(form.getStatus());
        return orderRepository.save(order);

    }

    public boolean removeOrderItemFromOrder(Long orderItemId) {
        if (orderDetailRepository.existsById(orderItemId)) {
            orderDetailRepository.deleteById(orderItemId);
            return true;
        }
        return false;
    }

    public boolean deleteOrder(Long orderId) {
        // Lấy tất cả các OrderDetail liên quan đến orderId
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);

        // Xóa tất cả các OrderDetail
        if (!orderDetails.isEmpty()) {
            orderDetailRepository.deleteAll(orderDetails);
        }

        // Kiểm tra xem đơn hàng có tồn tại không
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId); // Xóa đơn hàng
            return true; // Trả về true nếu xóa thành công
        }

        return false; // Trả về false nếu không tìm thấy đơn hàng để xóa
    }
}
