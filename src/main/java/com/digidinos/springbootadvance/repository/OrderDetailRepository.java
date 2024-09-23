package com.digidinos.springbootadvance.repository;

import com.digidinos.springbootadvance.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
