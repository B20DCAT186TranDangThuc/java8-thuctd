package com.digidinos.shopping.dao;

import com.digidinos.shopping.entity.Product;
import com.digidinos.shopping.form.ProductForm;
import com.digidinos.shopping.model.ProductInfo;
import com.digidinos.shopping.service.UploadService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Repository
public class ProductDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    UploadService uploadService;

    public Product findProduct(String code) {
        try {
            String sql = "Select e from " + Product.class.getName() + " e Where e.code =:code ";
            Session session = this.sessionFactory.getCurrentSession();
            Query<Product> query = session.createQuery(sql, Product.class);
            query.setParameter("code", code);
            return (Product) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public ProductForm findProductForm(String code) {
        Product product = this.findProduct(code);
        if (product == null) {
            return null;
        }

        return ProductForm.builder()
                .code(product.getCode())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

    public ProductInfo findProductInfo(String code) {
        Product product = this.findProduct(code);
        if (product == null) {
            return null;
        }
        return ProductInfo.builder()
                .code(product.getCode())
                .name(product.getName())
                .price(product.getPrice())
                .imageData(product.getImageData())
                .createAt(product.getCreateAt())
                .updateAt(product.getUpdateAt())
                .build();
    }

    public List<Product> findAllProducts() {
        Session session = sessionFactory.getCurrentSession();

        String hql = "FROM Product p WHERE p.isDeleted = false";

        Query<Product> query = session.createQuery(hql, Product.class);

        return query.getResultList();
    }

    public List<ProductInfo> findAllProductInfo() {
        // Lấy danh sách tất cả các sản phẩm từ cơ sở dữ liệu
        List<Product> products = this.findAllProducts(); // Giả sử phương thức này lấy tất cả sản phẩm

        // Chuyển đổi danh sách sản phẩm thành danh sách ProductInfo
        return products.stream()
                .map(product -> ProductInfo.builder()
                        .code(product.getCode())
                        .name(product.getName())
                        .price(product.getPrice())
                        .build())
                .collect(Collectors.toList());
    }

    public boolean createProduct(ProductForm productForm) throws IOException {
        // Kiểm tra tính hợp lệ của productForm
        if (productForm == null) {
            return false; // Hoặc có thể ném ra ngoại lệ
        }

        // Chuyển từ ProductForm sang Product entity
        Product product = new Product();
        product.setCode(productForm.getCode());
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());

        // Lưu hình ảnh dưới dạng byte[] vào imageData
        MultipartFile imageFile = productForm.getFileData();
        if (imageFile != null && !imageFile.isEmpty()) {
            product.setImageData(imageFile.getBytes()); // Lưu ảnh vào imageData
        }

        product.setCreateAt(LocalDateTime.now());
        product.setUpdateAt(LocalDateTime.now());
        product.setDeleted(false);

        // Tạo một Session từ SessionFactory
        Session session = this.sessionFactory.getCurrentSession();

        try {
            // Lưu product vào database
            session.save(product);
            return true;  // Trả về true nếu lưu thành công
        } catch (Exception e) {
            e.printStackTrace();
            return false;  // Trả về false nếu có lỗi xảy ra
        }
    }

    public boolean updateProduct(ProductForm productForm) throws IOException {
        if (productForm == null || productForm.getCode() == null) {
            return false;
        }

        Session session = this.sessionFactory.getCurrentSession();
        Product product = session.get(Product.class, productForm.getCode());

        if (product == null) {
            return false;
        }

        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());

        MultipartFile imageFile = productForm.getFileData();
        if (imageFile != null && !imageFile.isEmpty()) {
            product.setImageData(imageFile.getBytes());
        }

        product.setUpdateAt(LocalDateTime.now());

        session.update(product);

        return true;
    }

    public boolean deleteAccount(String code) {
        Session session = this.sessionFactory.getCurrentSession();

        Product existProduct = session.get(Product.class, code);
        if (existProduct != null) {
            existProduct.setDeleted(true);
            existProduct.setDeleteAt(LocalDateTime.now());
            session.update(existProduct);
            return true;
        }

        return false;

    }
}