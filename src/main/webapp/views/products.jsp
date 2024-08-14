<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Products</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        img {
            width: 100px;
            height: auto;
        }
        .actions {
            display: flex;
            gap: 10px;
        }
        .pagination {
            justify-content: center;
        }
        .search-container {
            display: flex;
            gap: 10px;
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <a href="products" class="header-link">
        <h1>Products</h1>
    </a>

    <!-- Add Category and Add Product Buttons -->
    <div class="mb-3">
        <a href="products?action=add" class="btn btn-primary mb-3">Add New Product</a>
        <a href="categories?action=add" class="btn btn-secondary mb-3">Add New Category</a>
    </div>

    <!-- Search Form -->
    <div class="search-container">
        <form action="products" method="get" class="form-inline">
            <input type="hidden" name="action" value="search">
            <input type="text" name="query" class="form-control" placeholder="Search for products...">
            <button type="submit" class="btn btn-info">Search</button>
        </form>
    </div>

    <!-- Category Filters -->
    <div class="mb-3">
        <a href="products?category=T-SHIRTS" class="btn btn-secondary">T-SHIRTS</a>
        <a href="products?category=PANTS" class="btn btn-secondary">PANTS</a>
        <a href="products?category=JACKET" class="btn btn-secondary">JACKET</a>
        <a href="products?category=HOODIES" class="btn btn-secondary">HOODIES</a>
    </div>

    <!-- Product Table -->
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Image</th>
            <th>Name</th>
            <th>ID</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <td><img src="<c:out value="${product.image}"/>"/></td>
                <td><c:out value="${product.name}"/></td>
                <td><c:out value="${product.id}"/></td>
                <td class="price"><c:out value="${product.price}"/></td>
                <td class="actions">
                    <a href="products?action=edit&id=<c:out value="${product.id}"/>" class="btn btn-warning btn-sm">Edit</a>
                    <form action="products" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete"/>
                        <input type="hidden" name="id" value="<c:out value="${product.id}"/>"/>
                        <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <!-- Pagination -->
    <nav>
        <ul class="pagination">
            <c:if test="${currentPage > 1}">
                <li class="page-item">
                    <a class="page-link" href="products?page=${currentPage - 1}&category=<c:out value="${category}"/>">Previous</a>
                </li>
            </c:if>
            <c:forEach var="pageNum" begin="1" end="${noOfPages}">
                <li class="page-item ${pageNum == currentPage ? 'active' : ''}">
                    <a class="page-link" href="products?page=${pageNum}&category=<c:out value="${category}"/>">${pageNum}</a>
                </li>
            </c:forEach>
            <c:if test="${currentPage < noOfPages}">
                <li class="page-item">
                    <a class="page-link" href="products?page=${currentPage + 1}&category=<c:out value="${category}"/>">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    function formatPrice(price) {
        return price.toLocaleString('vi-VN', { style: 'currency', currency: 'VND' });
    }
    document.addEventListener('DOMContentLoaded', function() {
        const priceElements = document.querySelectorAll('.price');
        priceElements.forEach(function(element) {
            const price = parseFloat(element.textContent);
            element.textContent = formatPrice(price);
        });
    });
</script>
</body>
</html>
