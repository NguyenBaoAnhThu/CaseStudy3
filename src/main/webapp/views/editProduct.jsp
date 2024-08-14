<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>Edit Product</title>
  <!-- Link to Bootstrap CSS -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <h1 class="mt-4">Edit Product</h1>
  <form action="products" method="post" class="mt-3">
    <input type="hidden" name="action" value="edit"/>
    <input type="hidden" name="id" value="<c:out value="${product.id}"/>"/>

    <div class="form-group">
      <label for="name">Name:</label>
      <input type="text" id="name" name="name" class="form-control" value="<c:out value="${product.name}"/>" required/>
    </div>

    <div class="form-group">
      <label for="price">Price:</label>
      <input type="number" id="price" name="price" class="form-control" step="0.01" value="<c:out value="${product.price}"/>" required/>
    </div>

    <div class="form-group">
      <label for="image">Image URL:</label>
      <input type="text" id="image" name="image" class="form-control" value="<c:out value="${product.image}"/>" required/>
    </div>

    <div class="form-group">
      <label for="categoryId">Category:</label>
      <select id="categoryId" name="categoryId" class="form-control" required>
        <option value="1" ${product.categoryId == 1 ? 'selected' : ''}>T-SHIRTS</option>
        <option value="2" ${product.categoryId == 2 ? 'selected' : ''}>PANTS</option>
        <option value="3" ${product.categoryId == 3 ? 'selected' : ''}>JACKET</option>
        <option value="4" ${product.categoryId == 4 ? 'selected' : ''}>HOODIES</option>
      </select>
    </div>
    <button type="submit" class="btn btn-primary">Update Product</button>
  </form>
  <a href="products" class="btn btn-secondary mt-3">Back to Products</a>
</div>

<!-- Include Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
