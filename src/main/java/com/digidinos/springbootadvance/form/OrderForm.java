package com.digidinos.springbootadvance.form;

import com.digidinos.springbootadvance.entity.OrderDetail;
import com.digidinos.springbootadvance.model.OrderInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderForm {

    private String customerName;
    private String customerAddress;
    private String customerPhone;
    private String customerEmail;
    private double orderAmount;

    private List<OrderItem> orderDetailInfos;

}
