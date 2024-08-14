package com.example.casestudy.controllers;

import com.example.casestudy.entity.Product;
import com.example.casestudy.services.ProductService;
import com.example.casestudy.services.ProductSizeService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductController extends HttpServlet {
    private ProductService productService;
    private ProductSizeService productSizeService;

    @Override
    public void init() throws ServletException {
        this.productService = new ProductService();
        this.productSizeService = new ProductSizeService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("search".equals(action)) {
            handleSearchProduct(req, resp);
        } else if ("add".equals(action)) {
            renderAddProductPage(req, resp);
        } else if ("edit".equals(action)) {
            renderEditProductPage(req, resp);
        } else if ("delete".equals(action)) {
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "GET method not supported for DELETE action.");
        } else {
            renderListProductPage(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("add".equals(action)) {
            handleAddProduct(req, resp);
        } else if ("edit".equals(action)) {
            handleEditProduct(req, resp);
        } else if ("delete".equals(action)) {
            handleDeleteProduct(req, resp);
        }
    }

    private void renderListProductPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int page = 1;
            int recordsPerPage = 5;

            if (req.getParameter("page") != null) {
                page = Integer.parseInt(req.getParameter("page"));
            }

            String category = req.getParameter("category");
            List<Product> products = productService.getProductsByPage((page - 1) * recordsPerPage, recordsPerPage, category);
            int noOfRecords = productService.getNoOfRecords();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);

            req.setAttribute("products", products);
            req.setAttribute("noOfPages", noOfPages);
            req.setAttribute("currentPage", page);
            req.setAttribute("category", category);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/products.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Error while rendering list of products.", e);
        }
    }

    private void handleSearchProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String query = req.getParameter("query");
            List<Product> products = productService.searchProductsByName(query);
            req.setAttribute("products", products);
            req.setAttribute("searchQuery", query); // Optional: Pass the search query to the view

            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/products.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Error while searching products.", e);
        }
    }

    private void renderAddProductPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/views/addProduct.jsp");
        dispatcher.forward(req, resp);
    }

    private void renderEditProductPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int productId = Integer.parseInt(req.getParameter("id"));
            Product product = productService.getProductById(productId);
            req.setAttribute("product", product);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/editProduct.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            throw new ServletException("Error while rendering edit product page.", e);
        }
    }

    private void handleAddProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            String image = req.getParameter("image");
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));

            Product product = new Product(0, name, price, image, categoryId);
            productService.addProduct(product);

            resp.sendRedirect(req.getContextPath() + "/products");
        } catch (Exception e) {
            throw new ServletException("Error while adding product.", e);
        }
    }

    private void handleEditProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            double price = Double.parseDouble(req.getParameter("price"));
            String image = req.getParameter("image");
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));

            Product product = new Product(id, name, price, image, categoryId);
            productService.updateProduct(product);

            resp.sendRedirect(req.getContextPath() + "/products");
        } catch (Exception e) {
            throw new ServletException("Error while updating product.", e);
        }
    }

    private void handleDeleteProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            productSizeService.deleteByProductId(id);
            productService.deleteProduct(id);
            resp.sendRedirect(req.getContextPath() + "/products");
        } catch (Exception e) {
            throw new ServletException("Error while deleting product.", e);
        }
    }
}
