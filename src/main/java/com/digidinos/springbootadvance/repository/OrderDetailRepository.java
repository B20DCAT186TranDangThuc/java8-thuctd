package com.digidinos.springbootadvance.repository;

import com.digidinos.springbootadvance.entity.Order;
import com.digidinos.springbootadvance.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}
