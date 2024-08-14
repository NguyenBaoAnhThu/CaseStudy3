package com.example.casestudy.controllers;

import com.example.casestudy.entity.Category;
import com.example.casestudy.services.CategoryService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/categories")
public class CategoryController extends HttpServlet {
    private CategoryService categoryService;

    @Override
    public void init() throws ServletException {
        this.categoryService = new CategoryService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            renderListCategoryPage(req, resp);
        } else if (action.equals("edit")) {
            renderAddOrEditCategoryPage(req, resp, action);
        } else if (action.equals("add")) {
            renderAddOrEditCategoryPage(req, resp, action);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("add".equals(action)) {
            handleAddCategory(req, resp);
        } else if ("edit".equals(action)) {
            handleEditCategory(req, resp);
        } else if ("delete".equals(action)) {
            handleDeleteCategory(req, resp);
        }
    }

    private void renderListCategoryPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Category> categories = this.categoryService.getAllCategories();
            req.setAttribute("categories", categories);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/addCategory.jsp");
            dispatcher.forward(req, resp);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void renderAddOrEditCategoryPage(HttpServletRequest req, HttpServletResponse resp, String action) throws ServletException, IOException {
        if ("edit".equals(action)) {
            try {
                int categoryId = Integer.parseInt(req.getParameter("id"));
                Category category = this.categoryService.getCategoryById(categoryId);
                req.setAttribute("category", category);
            } catch (SQLException e) {
                throw new ServletException(e);
            }
        }
        renderListCategoryPage(req, resp);
    }

    private void handleAddCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            Category category = new Category(0, name);
            this.categoryService.addCategory(category);
            resp.sendRedirect(req.getContextPath() + "/categories");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void handleEditCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            Category category = new Category(id, name);
            this.categoryService.updateCategory(category);
            resp.sendRedirect(req.getContextPath() + "/categories");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    private void handleDeleteCategory(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            this.categoryService.deleteCategory(id);
            resp.sendRedirect(req.getContextPath() + "/categories");
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}
