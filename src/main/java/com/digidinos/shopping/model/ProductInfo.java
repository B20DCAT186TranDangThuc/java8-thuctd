package com.digidinos.shopping.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductInfo {
    private String code;
    private String name;
    private double price;
    private byte[] imageData;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}