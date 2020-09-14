<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Category Page</title>
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.11.2/css/all.css" />
<!-- Google Fonts Roboto -->
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" />
<!-- MDB -->
<link rel="stylesheet"
	href="<c:url value='/template/admin/css/mdb.min.css'/>" />
</head>
<body>
	<header>
		<!-- Navbar -->
		<nav class="navbar navbar-expand-lg navbar-light bg-white fixed-top">
			<div class="container">
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarExample01" aria-controls="navbarExample01"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarExample01">
					<ul class="navbar-nav mr-auto mb-2 mb-lg-0">
						<li class="nav-item active"><a class="nav-link"
							aria-current="page" href="/test/admin/home">Home</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/test/admin-post/home">Posts</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/test/admin-category/home">Categories</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Users</a></li>
					</ul>
				</div>
				<ul class="navbar-nav">
					<!-- Avatar -->
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
						aria-expanded="false"> <img
							src="https://mdbootstrap.com/img/Photos/Avatars/img (31).jpg"
							class="rounded-circle" height="22" alt="" loading="lazy" /> ${USER.userName }
					</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<li><a class="dropdown-item" href="#">My profile</a></li>
							<li><a class="dropdown-item" href="#">Settings</a></li>
							<li><a class="dropdown-item" href="/test/logout">Logout</a></li>
						</ul></li>
				</ul>
			</div>
		</nav>
		<!-- Navbar -->

		<!-- Jumbotron -->
		<div class="p-5 text-center bg-light" style="margin-top: 58px;">
			<h1 class="mb-3">Form Add Category</h1>
		</div>
		<!-- Jumbotron -->
	</header>

	<!-- Main content -->
    <main class="my-4">
        <div class="container">
            <form action="/test/admin-category/add" method="post">
                <div class="form-group mb-2">
                    <label class="form-check-label">Name</label>
                    <input type="text" class="form-control" name="categoryName" placeholder="Category Name" required="required">
                </div>
                <div class="form-group mb-2">
                    <label class="form-check-label">Code</label>
                    <input type="text" class="form-control" name="categoryCode" placeholder="Category Code" required="required">
                </div>
                <div>
                    <input type="submit" class="btn btn-primary" value="Add Category">
                </div>
            </form>
        </div>
    </main>
    <!-- Main content -->
    
    <div style="padding: 150px;"></div>

	<!-- Footer -->
	<footer class="bg-light text-center text-lg-left">
		<!-- Copyright -->
		<div class="text-center p-3"
			style="background-color: rgba(0, 0, 0, 0.2);">
			© 2020 Copyright: <a class="text-dark"
				href="https://mdbootstrap.com/">CleanCode.com</a>
		</div>
		<!-- Copyright -->
	</footer>
	<!-- Footer -->

	<!-- MDB -->
	<script type="text/javascript"
		src="<c:url value='/template/admin/js/mdb.min.js'/>"></script>
	<!-- Custom scripts -->
</body>
</html>