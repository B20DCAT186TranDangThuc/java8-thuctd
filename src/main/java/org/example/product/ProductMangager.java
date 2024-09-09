package org.example.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ProductMangager {

    public static void addTenProduct(List<Product> products) {
        products.add(new Product(false, 10, new Date(), 1, "product 1", 1));
        products.add(new Product(false, 9, new Date(), 1, "product 2", 2));
        products.add(new Product(false, 111, new Date(), 2, "product 3", 3));
        products.add(new Product(false, 330, new Date(), 2, "product 4", 4));
        products.add(new Product(false, 13, new Date(), 4, "product 5", 5));
        products.add(new Product(false, 343, new Date(), 3, "product 6", 6));
        products.add(new Product(false, 20, new Date(), 5, "product 6", 7));
        products.add(new Product(false, 400, new Date(), 6, "product 6", 8));
        products.add(new Product(false, 90, new Date(), 4, "product 6", 9));
        products.add(new Product(false, 2, new Date(), 6, "product 6", 10));
    }

    public static String filterProductById(List<Product> products, Integer idProduct) {
        Optional<Product> product = products.stream()
                .filter(p -> p.getId().equals(idProduct))
                .findFirst();

        return product.map(Product::getName).orElse("no product found");
    }

//    public static String filterProductById(List<Product> products, Integer idProduct) {
//        for (Product p : products) {
//            if (p.getId() == idProduct) {
//                return p.getName();
//            }
//        }
//        return "no product found";
//    }

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        addTenProduct(products);
        System.out.println(filterProductById(products, 12));
    }

}
