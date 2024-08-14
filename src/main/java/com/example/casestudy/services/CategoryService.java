package com.example.casestudy.services;

import com.example.casestudy.entity.Category;
import com.example.casestudy.models.CategoryModel;

import java.sql.SQLException;
import java.util.List;

public class CategoryService {
    private CategoryModel categoryModel;

    public CategoryService() {
        this.categoryModel = new CategoryModel();
    }

    public List<Category> getAllCategories() throws SQLException {
        return categoryModel.getAllCategories();
    }

    public void addCategory(Category category) throws SQLException {
        categoryModel.addCategory(category);
    }

    public void updateCategory(Category category) throws SQLException {
        categoryModel.updateCategory(category);
    }

    public Category getCategoryById(int id) throws SQLException {
        List<Category> categories = categoryModel.getAllCategories();
        return categories.stream().filter(category -> category.getId() == id).findFirst().orElse(null);
    }

    public void deleteCategory(int id) throws SQLException {
        categoryModel.deleteCategory(id);
    }
}
