package com.digidinos.springbootadvance.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusOrderForm {

    private Long orderId;
    private String status;
}
