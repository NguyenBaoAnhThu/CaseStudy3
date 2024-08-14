<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title><c:choose>
        <c:when test="${not empty category}">Edit Category</c:when>
        <c:otherwise>Add New Category</c:otherwise>
    </c:choose></title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <h2><c:choose>
        <c:when test="${not empty category}">Edit Category</c:when>
        <c:otherwise>Add New Category</c:otherwise>
    </c:choose></h2>

    <form action="categories" method="post">
        <c:choose>
            <c:when test="${not empty category}">
                <input type="hidden" name="action" value="edit">
                <input type="hidden" name="id" value="<c:out value="${category.id}"/>">
            </c:when>
            <c:otherwise>
                <input type="hidden" name="action" value="add">
            </c:otherwise>
        </c:choose>
        <div class="form-group">
            <label for="categoryName">Category Name:</label>
            <input type="text" class="form-control" id="categoryName" name="name"
                   value="<c:out value="${category.name}"/>"
                   required>
        </div>
        <button type="submit" class="btn btn-primary">
            <c:choose>
                <c:when test="${not empty category}">Update Category</c:when>
                <c:otherwise>Add Category</c:otherwise>
            </c:choose>
        </button>
        <a href="products" class="btn btn-secondary">Cancel</a>
    </form>

    <hr class="my-4">

    <h3>Existing Categories</h3>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="category" items="${categories}">
            <tr>
                <td><c:out value="${category.id}"/></td>
                <td><c:out value="${category.name}"/></td>
                <td>
                    <form action="categories" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete"/>
                        <input type="hidden" name="id" value="<c:out value="${category.id}"/>"/>
                        <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                    </form>
                    <a href="categories?action=edit&id=<c:out value="${category.id}"/>" class="btn btn-warning btn-sm">Edit</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
