<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title><spring:message code="home.title"></spring:message></title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/home.css">

<!-- Font Awesome JS -->
<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"></script>
<script defer
	src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"></script>

<!-- jQuery CDN - Slim version (=without AJAX) -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<!-- Popper.JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<!-- Bootstrap JS -->
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>

</head>
<div class="wrapper">
	<!-- Sidebar  -->
	<nav id="sidebar">
		<div class="sidebar-header">
			<h3>
				<spring:message code="home.name"></spring:message>
			</h3>
		</div>

		<ul class="list-unstyled components">
			<p>${pageContext.request.userPrincipal.name}</p>
			<li class="active"><a href="/home"><spring:message
						code="home.homesidebar"></spring:message></a></li>
			<security:authorize access="hasRole('ROLE_ADMIN')">
				<li><a href="/create-faculty"><spring:message
							code="home.createfaculty"></spring:message></a></li>
				<li><a href="/registeredEntrants"><spring:message
							code="home.registeredentrants"></spring:message></a></li>
			</security:authorize>
		</ul>
	</nav>

	<!-- Header Content  -->
	<div id="content">

		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container-fluid">

				<button type="button" id="sidebarCollapse" class="btn btn-info">
					<i class="fas fa-align-left"></i><span> <spring:message
							code="home.togglesidebar"></spring:message></span>
				</button>
				<button class="btn btn-dark d-inline-block d-lg-none ml-auto"
					type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<i class="fas fa-align-justify"></i>
				</button>

				<div class="collapse navbar-collapse w-100 order-3 dual-collapse2">
					<ul class="nav navbar-nav ml-auto">
						<li class="nav-item" style="margin-right: 20px;">
							<div class="locales">
								<fieldset>
									<legend>
										<select id="locales" class="locales_sel">
											<option value="en"><spring:message
													code="home.english"></spring:message></option>
											<option value="ua"><spring:message
													code="home.ukrainian"></spring:message></option>
										</select>
									</legend>
								</fieldset>
							</div>
						</li>
						<li class="nav-item"><c:if
								test="${pageContext.request.userPrincipal.name != null}">
								<form id="logoutForm" method="POST"
									action="${contextPath}/logout">
									<input type="hidden" name="${_csrf.parameterName}"
										value="${_csrf.token}" />
									<button type="button" class="btn btn-info"
										onclick="document.forms['logoutForm'].submit()">
										<i class="fas fa-sign-out-alt"></i><span><spring:message
												code="home.logout"></spring:message></span>
									</button>
								</form>
							</c:if></li>

					</ul>
				</div>
			</div>
		</nav>

		<!-- Page Content  -->

		<div class="container">
			<div class="row">
				<!-- Page Content -->
				<c:if test="${not empty faculties}">
					<c:forEach items="${faculties}" var="currentFaculty">
						<div class="col-6 col-md-4">
							<div class="card">

								<!-- Card image -->
								<div class="view view-cascade overlay">
									<img class="card-img-top" src="../img/faculty-logo.jpg"
										alt="Card image cap">
								</div>

								<!-- Card content -->
								<div class="card-body">

									<!-- Title -->
									<h4 class="card-title">${currentFaculty.name}</h4>
									<!-- Text -->
									<p class="card-text">
										<spring:message code="home.quantitystudent"></spring:message>
										${currentFaculty.quantityOfStudents}
									</p>
									<p class="card-text">
										<spring:message code="home.listsubject"></spring:message>
										<br>
									</p>
									<c:forEach items="${currentFaculty.subjects}"
										var="currentSubject">
										<p class="card-text">${currentSubject}</p>
									</c:forEach>
									<!-- Button -->
									<security:authorize access="hasRole('ROLE_USER')">
										<a
											href="entrantRegistration?currentFacultyId=${currentFaculty.id}&currentUserEmail=${pageContext.request.userPrincipal.name}"
											class="btn btn-primary btn-lg btn-block"><spring:message
												code="home.choosefaculty"></spring:message></a>
										<a href="rating?currentFacultyId=${currentFaculty.id}"
											class="btn btn-primary btn-lg btn-block"><spring:message
												code="home.showrating"></spring:message></a>
									</security:authorize>
									<security:authorize access="hasRole('ROLE_ADMIN')">
										<a href="#" class="btn btn-primary btn-lg btn-block"><spring:message
												code="home.deletefaculty"></spring:message></a>
									</security:authorize>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:if>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="../js/home.js"></script>
<script type="text/javascript" src="../js/locale.js"></script>
</body>

</html>