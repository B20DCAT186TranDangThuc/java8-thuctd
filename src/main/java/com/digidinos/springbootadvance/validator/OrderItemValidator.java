package com.digidinos.springbootadvance.validator;

import com.digidinos.springbootadvance.entity.Product;
import com.digidinos.springbootadvance.form.OrderItem;
import com.digidinos.springbootadvance.repository.ProductRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderItemValidator implements ConstraintValidator<ValidOrderItem, List<OrderItem>> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public boolean isValid(List<OrderItem> orderItems, ConstraintValidatorContext context) {
        if (orderItems == null || orderItems.isEmpty()) {
            return true; // Allow empty list, validation happens in other parts
        }

        for (OrderItem orderItem : orderItems) {
            // Lấy sản phẩm từ database dựa vào productId
            Product product = productRepository.findById(orderItem.getProductId()).orElse(null);

            if (product == null || orderItem.getQuantity() > product.getQuantity()) {
                return false;
            }
        }

        return true;
    }
}
