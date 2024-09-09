package com.dd.thuctd.product;

import java.util.*;
import java.util.stream.Collectors;

public class ProductMangager {

    // bai 10
    public static void addTenProduct(List<Product> products) {
        // Tạo danh sách các ngày bán sản phẩm
        Date[] saleDates = new Date[10];
        Calendar calendar = Calendar.getInstance();

        // Khởi tạo các ngày bán sản phẩm
        for (int i = 0; i < 10; i++) {
            calendar.set(2024, Calendar.SEPTEMBER, i + 1); // Tạo ngày từ 1 đến 10 tháng 9 năm 2024
            saleDates[i] = calendar.getTime();
        }

        // Thêm các sản phẩm vào danh sách
        for (int i = 0; i < 10; i++) {
            boolean isActive = i % 2 == 0; // Ví dụ: Sản phẩm lẻ là không hoạt động, chẵn là hoạt động
            int price = (i + 1) * 10; // Giá sản phẩm
            products.add(new Product(isActive, price, saleDates[i], i + 1, "product " + (i + 1), i + 1));
        }
    }

    // bai 11
    public static String filterProductById(List<Product> products, Integer idProduct) {
        // using stream
        Optional<Product> product = products.stream()
                .filter(p -> p.getId().equals(idProduct))
                .findFirst();

        return product.map(Product::getName).orElse("no product found");

        // no stream
//        for(Product p : products) {
//            if (p.getId() == idProduct) {
//                return p.getName();
//            }
//        }
//        return "no product found";
    }

    // bai 12
    public static List<Product> filterProductByQuantity(List<Product> products) {
        // using stream
//        return products.stream()
//                .filter(x -> x.getquantity() > 0 && !x.isDelete())
//                .collect(Collectors.toList());
        // no stream
        List<Product> res = new ArrayList<>();
        for (Product product : products) {
            if (product.getquantity() > 0 && !product.isDelete()) {
                res.add(product);
            }
        }
        return res;
    }

    // bai 13
    public static List<String> filterProductBySaleDate(List<Product> products) {
        // no stream
//        List<String> productNames = new ArrayList<>();
//        Date today = new Date();
//        for (Product product : products) {
//            if (product.getSaleDate().after(today)) {
//                productNames.add(product.getName());
//            }
//        }
//
//        return productNames;

        // using stream
        return products.stream()
                .filter(x -> {
                    Date today = new Date();
                    return x.getSaleDate().after(today);
                })
                .map(Product::getName)
                .collect(Collectors.toList());
    }

    // bai 14
    public static int totalProducts(List<Product> products) {
        // no stream
//        int total = 0;
//        for (Product product : products) {
//            if(!product.isDelete()) {
//                total += product.getquantity();
//            }
//        }
//        return total;

        // using stream
        return products.stream()
                .filter(p -> !p.isDelete())
                .map(Product::getquantity)
                .reduce(0, Integer::sum);

    }

    // bai 15
    public static boolean isHaveProductInCategory(List<Product> products, Integer categoryId) {
        // using stream
//        return products.stream()
//                .anyMatch(p -> p.getCategoryId().equals(categoryId));

        // no stream
        for (Product product : products) {
            if (product.getCategoryId().equals(categoryId)) {
                return true;
            }
        }
        return false;
    }

    // bai 16
    public static String[][] filterProductBySaleDateAndQuanTity(List<Product> products) {
        // using stream
        Date currentDate = new Date();
//
//        return products.stream()
//                .filter(product -> product.getSaleDate().after(currentDate) && product.getquantity() > 0)
//                .map(product -> new String[]{String.valueOf(product.getId()), product.getName()})
//                .toArray(String[][]::new);

        // no stream
        List<String[]> result = new ArrayList<>();

        for (Product product : products) {
            if (product.getSaleDate().after(currentDate) && product.getquantity() > 0) {
                result.add(new String[]{String.valueOf(product.getId()), product.getName()});
            }
        }

        return result.toArray(new String[0][]);
    }

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();

        addTenProduct(products);
        System.out.println(filterProductById(products, 1));

        List<Product> res = filterProductByQuantity(products);
        res.forEach(System.out::println);

        List<String> res1 = filterProductBySaleDate(products);
        res1.forEach(System.out::println);

        System.out.println(totalProducts(products));

        System.out.println(isHaveProductInCategory(products, 99));

        String[][] res2 = filterProductBySaleDateAndQuanTity(products);

        for (int i = 0; i < res2.length; i++) {
            for (int j = 0; j < res2[i].length; j++) {
                System.out.print(res2[i][j] + " ");
            }
        }
    }

}
