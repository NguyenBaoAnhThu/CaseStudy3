package com.example.casestudy.models;

import com.example.casestudy.entity.Category;
import com.example.casestudy.databases.DatabaseConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryModel {

    public List<Category> getAllCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        String query = "SELECT * FROM categories";

        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                categories.add(new Category(id, name));
            }
        }
        return categories;
    }

    public void addCategory(Category category) throws SQLException {
        String query = "INSERT INTO categories (name) VALUES (?)";

        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, category.getName());
            pstmt.executeUpdate();
        }
    }

    public void updateCategory(Category category) throws SQLException {
        String query = "UPDATE categories SET name = ? WHERE id = ?";

        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, category.getName());
            pstmt.setInt(2, category.getId());
            pstmt.executeUpdate();
        }
    }
    public void deleteCategory(int id) throws SQLException {
        String query = "DELETE FROM categories WHERE id = ?";

        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
