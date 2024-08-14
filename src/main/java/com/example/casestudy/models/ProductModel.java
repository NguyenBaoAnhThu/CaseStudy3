package com.example.casestudy.models;

import com.example.casestudy.entity.Product;
import com.example.casestudy.databases.DatabaseConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductModel {
    private int noOfRecords;

    // Các phương thức khác trong lớp

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM products";

        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                int categoryId = rs.getInt("category_id");
                products.add(new Product(id, name, price, image, categoryId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Phương thức phân trang và lọc theo category
    public List<Product> getProductsByPage(int offset, int noOfRecords, String category) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM products";
        if (category != null && !category.isEmpty()) {
            query += " WHERE category_id = (SELECT id FROM categories WHERE name = ?)";
        }
        query += " LIMIT ?, ?";
        try (Connection connection = DatabaseConnect.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            if (category != null && !category.isEmpty()) {
                statement.setString(1, category);
                statement.setInt(2, offset);
                statement.setInt(3, noOfRecords);
            } else {
                statement.setInt(1, offset);
                statement.setInt(2, noOfRecords);
            }
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                product.setImage(resultSet.getString("image"));
                product.setCategoryId(resultSet.getInt("category_id"));
                products.add(product);
            }
            resultSet.close();

            resultSet = statement.executeQuery("SELECT FOUND_ROWS()");
            if (resultSet.next()) {
                this.noOfRecords = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    // Phương thức thêm sản phẩm
    public void addProduct(Product product) {
        String query = "INSERT INTO products (name, price, image, category_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setString(3, product.getImage());
            pstmt.setInt(4, product.getCategoryId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức cập nhật sản phẩm
    public void updateProduct(Product product) {
        String query = "UPDATE products SET name = ?, price = ?, image = ?, category_id = ? WHERE id = ?";

        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, product.getName());
            pstmt.setDouble(2, product.getPrice());
            pstmt.setString(3, product.getImage());
            pstmt.setInt(4, product.getCategoryId());
            pstmt.setInt(5, product.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Phương thức xóa sản phẩm
    public void deleteProduct(int id) {
        String query = "DELETE FROM products WHERE id = ?";

        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Phương thức tìm sản phẩm theo ID
    public Product getProductById(int id) {
        Product product = null;
        String query = "SELECT * FROM products WHERE id = ?";

        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setImage(rs.getString("image"));
                product.setCategoryId(rs.getInt("category_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    // Phương thức tìm kiếm sản phẩm theo tên
    public List<Product> searchProductsByName(String query) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name LIKE ?";

        try (Connection conn = DatabaseConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + query + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String image = rs.getString("image");
                int categoryId = rs.getInt("category_id");
                products.add(new Product(id, name, price, image, categoryId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }
}
