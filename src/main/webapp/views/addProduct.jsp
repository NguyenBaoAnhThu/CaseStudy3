<!DOCTYPE html>
<html>
<head>
  <title>Add Product</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-4">
  <h1 class="mb-4">Add New Product</h1>
  <form action="products" method="post">
    <input type="hidden" name="action" value="add"/>

    <div class="form-group">
      <label for="name">Name:</label>
      <input type="text" id="name" name="name" class="form-control" required/>
    </div>

    <div class="form-group">
      <label for="price">Price:</label>
      <input type="number" id="price" name="price" class="form-control" step="0.01" required/>
    </div>

    <div class="form-group">
      <label for="image">Image URL:</label>
      <input type="text" id="image" name="image" class="form-control" required/>
    </div>
    <div class="form-group">
      <label for="categoryId">Category:</label>
      <select id="categoryId" name="categoryId" class="form-control" required>
        <option value="1">T-SHIRTS</option>
        <option value="2">PANTS</option>
        <option value="3">JACKET</option>
        <option value="4">HOODIES</option>
      </select>
    </div>
    <button type="submit" class="btn btn-primary">Add Product</button>
  </form>

  <a href="products" class="btn btn-secondary mt-3">Back to Products</a>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
