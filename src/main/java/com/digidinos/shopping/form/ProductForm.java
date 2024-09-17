package com.digidinos.shopping.form;

import com.digidinos.shopping.entity.Product;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductForm {
    private String code;
    private String name;
    private double price;
    private boolean newProduct = false;
    // Upload file.
    private MultipartFile fileData;
}