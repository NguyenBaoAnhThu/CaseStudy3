package com.example.casestudy.entity;

public class ProductSize {
    private int id;
    private int productId;
    private String size;

    public ProductSize(int id, int productId, String size) {
        this.id = id;
        this.productId = productId;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public int getProductId() {
        return productId;
    }

    public String getSize() {
        return size;
    }
}
