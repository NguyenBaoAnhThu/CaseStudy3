package com.example.casestudy.models;

import com.example.casestudy.entity.ProductSize;
import com.example.casestudy.databases.DatabaseConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductSizeModel {

    public List<ProductSize> getProductSizesByProductId(int productId) {
        List<ProductSize> productSizes = new ArrayList<>();
        String query = "SELECT * FROM product_sizes WHERE product_id = ?";

        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    int prodId = rs.getInt("product_id");
                    String size = rs.getString("size");
                    productSizes.add(new ProductSize(id, prodId, size));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productSizes;
    }

    public void addProductSize(ProductSize productSize) {
        String query = "INSERT INTO product_sizes (product_id, size) VALUES (?, ?)";

        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, productSize.getProductId());
            pstmt.setString(2, productSize.getSize());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByProductId(int productId) throws SQLException {
        String query = "DELETE FROM product_sizes WHERE product_id = ?";

        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, productId);
            pstmt.executeUpdate();
        }
    }
}
