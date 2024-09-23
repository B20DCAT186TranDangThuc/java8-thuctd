package com.digidinos.springbootadvance.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerForm {

    private String name;
    private String email;
    private String phone;
    private String address;
    private List<String> productIds;

}
