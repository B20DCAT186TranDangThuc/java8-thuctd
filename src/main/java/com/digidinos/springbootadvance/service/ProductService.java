package com.digidinos.springbootadvance.service;

import com.digidinos.springbootadvance.entity.Account;
import com.digidinos.springbootadvance.entity.Product;
import com.digidinos.springbootadvance.form.ProductForm;
import com.digidinos.springbootadvance.model.AccountInfo;
import com.digidinos.springbootadvance.model.ProductInfo;
import com.digidinos.springbootadvance.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UploadService uploadService;

    public Product createProduct(ProductForm form) {
        Product product = Product.builder()
                .code(form.getCode())
                .name(form.getName())
                .price(form.getPrice())
                .description(form.getDescription())
                .build();

        if (!form.getImage().isEmpty()) {
            product.setImage(uploadService.upload(form.getImage()));
        }

        product.setCreateAt(LocalDateTime.now());
        product.setUpdateAt(LocalDateTime.now());

        return productRepository.save(product);
    }

    public Product updateProduct(ProductForm form) {
        Product product = productRepository.findById(form.getId()).orElse(null);
        if (product == null) {
            return null;
        }
        product.setCode(form.getCode());
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setDescription(form.getDescription());
        product.setUpdateAt(LocalDateTime.now());
        return productRepository.save(product);
    }


    public boolean doesProductExist(String code) {
        return productRepository.existsByCode(code);
    }

    public Page<ProductInfo> handleSearchAndFilterAccount(String keyword, Pageable pageable) {
        Page<Product> productPage = productRepository.searchAndFilterProducts(keyword, pageable);

        return new PageImpl<>(productPage.getContent().stream()
                .map(ProductInfo::new)
                .collect(Collectors.toList()), pageable, productPage.getTotalElements());
    }

    public Product findProduct(Long id) {
        return productRepository.findById(id).get();
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<ProductInfo> getAllProducts() {
        return productRepository.findAll().stream().map(ProductInfo::new).collect(Collectors.toList());
    }

}
