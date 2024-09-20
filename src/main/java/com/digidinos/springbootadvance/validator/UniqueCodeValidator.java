package com.digidinos.springbootadvance.validator;

import com.digidinos.springbootadvance.service.ProductService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueCodeValidator implements ConstraintValidator<UniqueCode, String> {

    @Autowired
    private ProductService productService;


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !productService.doesProductExist(value);
    }
}