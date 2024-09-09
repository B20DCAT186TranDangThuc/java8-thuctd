package com.dd.thuctd.product;

import java.util.Date;

public class Product {
    private Integer id;
    private String name;
    private Integer categoryId;
    private Date saleDate;
    private Integer quantity;
    private boolean isDelete;

    public Product() {

    }

    public Product(boolean isDelete, Integer quantity, Date saleDate, Integer categoryId, String name, Integer id) {
        this.isDelete = isDelete;
        this.quantity = quantity;
        this.saleDate = saleDate;
        this.categoryId = categoryId;
        this.name = name;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getquantity() {
        return quantity;
    }

    public void setquantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", saleDate=" + saleDate +
                ", quantity=" + quantity +
                ", isDelete=" + isDelete +
                '}';
    }
}
