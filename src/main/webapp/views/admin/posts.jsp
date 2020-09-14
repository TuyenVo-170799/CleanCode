<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Manage Post Page</title>
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css" />
    <!-- Google Fonts Roboto -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" />
    <!-- MDB -->
    <link rel="stylesheet" href="<c:url value='/template/admin/css/mdb.min.css'/>" />
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
			<h1 class="mb-3">Post Management</h1>
		</div>
		<!-- Jumbotron -->
	</header>

	<!-- Main content -->
	<main class="my-4">
		<div class="container">
			<c:if test="${not empty message }">
				<div class="alert alert-${alert }">${message } </div>
			</c:if>
			<div style="text-align: center; padding-bottom: 20px;">
				<a href="/test/admin-post/add" class="btn btn-primary btn-lg active"
					role="button" aria-pressed="true">Add Post</a>
			</div>
			<table class="table caption-top">
				<caption>List of Post</caption>
				<thead class="table-light">
					<tr>
						<th scope="col">ID</th>
						<th scope="col">TITLE</th>
						<th scope="col">CREATEDDATE</th>
						<th scope="col">CREATEDBY</th>
						<th scope="col">ACTION</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="post" items="${posts }">
						<tr>
							<th scope="row">${post.id }</th>
							<td>${post.title }</td>
							<td>${post.createdDate }</td>
							<td>${post.createdBy }</td>
							<td><a class="btn btn-success" href="/test/admin-post/edit?id=<c:out value='${post.id }'/>" role="button">Edit</a>
								&nbsp; <a class="btn btn-danger" href="/test/admin-post/delete?id=<c:out value='${post.id }'/>" role="button" onclick="return confirmDelete()">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<!-- Table -->
		</div>
	</main>
	<!-- Main content -->

	<div style="padding: 100px;"></div>

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
	<script>
		function confirmDelete() {
			var x = confirm("Do you really delete this post?");
			if (x) 
				return true;
			else 
				return false;
		}
	</script>
</body>
</html>