<%@ page import="java.util.List" %>
<%@ page import="com.example.casestudy.entity.Product" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Home</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <style>
    body {
      margin: 0;
      padding: 0;
      font-family: Arial, sans-serif;
    }
    header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 10px 20px;
      background-color: #fff;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      flex-direction: column;
    }
    header .logo {
      display: flex;
      justify-content: space-between;
      width: 100%;
    }
    header .logo img {
      height: 50px;
    }
    .title {
      width: 100%;
      background-color: black;
      display: flex;
      justify-content: center;
      align-content: center;
      align-items: center;
      height: 50px;
    }
    .title h1 {
      color: #FFFFFF;
      font-size: 12px;
      font-family: Quicksand, sans-serif;
      text-transform: uppercase;
    }
    .carousel-inner img {
      height: 800px;
      object-fit: cover;
    }
    .carousel-control-prev, .carousel-control-next {
      position: absolute;
      top: 50%;
      transform: translateY(-50%);
      width: 40px;
      height: 40px;
      background-color: rgba(0, 0, 0, 0.5);
      border: none;
      display: flex;
      justify-content: center;
      align-items: center;
      z-index: 10;
    }

    .carousel-control-prev {
      left: 10px;
    }

    .carousel-control-next {
      right: 10px;
    }

    .carousel-control-prev-icon, .carousel-control-next-icon {
      width: 20px;
      height: 20px;
      background-color: transparent;
      background-size: 100%;  }

    .carousel-control-prev-icon {
      background-image: url('https://img.icons8.com/ios-filled/50/ffffff/chevron-left.png'); /* Icon mũi tên trái */
    }

    .carousel-control-next-icon {
      background-image: url('https://img.icons8.com/ios-filled/50/ffffff/chevron-right.png'); /* Icon mũi tên phải */
    }
  </style>
</head>
<body>
<header>
  <div class="title">
    <h1>New Collection Coming Soon</h1>
  </div>
  <img src="https://theme.hstatic.net/200000162957/1001007349/14/logo.png?v=155" alt="Logo">
</header>
<div id="carouselExample" class="carousel slide" data-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="https://theme.hstatic.net/200000162957/1001007349/14/slideshow_1.jpg?v=155" class="d-block w-100" alt="Slide 1">
    </div>
    <div class="carousel-item">
      <img src="https://theme.hstatic.net/200000162957/1001007349/14/slideshow_2.jpg?v=155" class="d-block w-100" alt="Slide 2">
    </div>
    <div class="carousel-item">
      <img src="https://theme.hstatic.net/200000162957/1001007349/14/slideshow_3.jpg?v=155" class="d-block w-100" alt="Slide 3">
    </div>
  </div>
  <button class="carousel-control-prev" type="button" data-target="#carouselExample" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Prev</span>
  </button>
  <button class="carousel-control-next" type="button" data-target="#carouselExample" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="visually-hidden">Next</span>
  </button>
</div>

<div class="container">
  <div class="mb-4">
    <a href="products" class="btn btn-primary">Setting Products</a>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
