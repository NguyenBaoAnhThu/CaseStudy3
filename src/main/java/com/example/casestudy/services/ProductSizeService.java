package com.example.casestudy.services;

import com.example.casestudy.models.ProductSizeModel;

import java.sql.SQLException;

public class ProductSizeService {
    private ProductSizeModel productSizeModel;

    public ProductSizeService() {
        this.productSizeModel = new ProductSizeModel();
    }

    public void deleteByProductId(int productId) throws SQLException {
        productSizeModel.deleteByProductId(productId);
    }
}
