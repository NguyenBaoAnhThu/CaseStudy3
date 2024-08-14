package com.example.casestudy.services;

import com.example.casestudy.entity.Product;
import com.example.casestudy.models.ProductModel;
import com.example.casestudy.models.ProductSizeModel;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private ProductModel productModel;
    private ProductSizeModel productSizeModel;

    public ProductService() {
        this.productModel = new ProductModel();
        this.productSizeModel = new ProductSizeModel();
    }

    public List<Product> getAllProducts() {
        return productModel.getAllProducts();
    }

    public List<Product> getProductsByPage(int offset, int noOfRecords, String category) {
        return productModel.getProductsByPage(offset, noOfRecords, category);
    }

    public int getNoOfRecords() {
        return productModel.getNoOfRecords();
    }

    public void addProduct(Product product) {
        productModel.addProduct(product);
    }

    public void updateProduct(Product product) {
        productModel.updateProduct(product);
    }

    public void deleteProduct(int id) throws SQLException {
        this.productSizeModel.deleteByProductId(id);
        this.productModel.deleteProduct(id);
    }

    public Product getProductById(int id) {
        return productModel.getProductById(id);
    }
    public List<Product> searchProductsByName(String query) {
        return productModel.searchProductsByName(query);
    }
}
