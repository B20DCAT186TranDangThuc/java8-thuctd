package com.digidinos.springbootadvance.form;

import com.digidinos.springbootadvance.entity.OrderDetail;
import com.digidinos.springbootadvance.model.OrderInfo;
import com.digidinos.springbootadvance.validator.ValidOrderItem;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @NotBlank(message = "Customer name is required.")
    private String customerName;

    @NotBlank(message = "Customer address is required.")
    private String customerAddress;

    @NotBlank(message = "Customer address is required.")
    private String customerPhone;

    @Email(message = "Email should be valid and in the correct format.")
    private String customerEmail;

    @Min(value = 0, message = "Order amount must be positive.")
    private double orderAmount;

    @NotEmpty(message = "Order details cannot be empty.")
    @ValidOrderItem // Custom validator to check stock availability
    private List<OrderItem> orderDetailInfos;

}
