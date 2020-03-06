<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

<title><spring:message code="facultycreation.title"></spring:message></title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/home.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

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
			<li><a href="/home"><spring:message code="home.homesidebar"></spring:message></a></li>
			<li class="active"><a href="/create-faculty"><spring:message
						code="home.createfaculty"></spring:message></a></li>
			<li><a href="/registeredEntrants"><spring:message
						code="home.registeredentrants"></spring:message></a></li>
		</ul>
	</nav>

	<!-- Header Content  -->
	<div id="content">

		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container-fluid">

				<button type="button" id="sidebarCollapse" class="btn btn-info">
					<i class="fas fa-align-left"></i> <span><spring:message
							code="home.togglesidebar"></spring:message></span>
				</button>
				<button class="btn btn-dark d-inline-block d-lg-none ml-auto"
					type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<i class="fas fa-align-justify"></i>
				</button>

				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav ml-auto">
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
		<form:form method="POST" action="${contextPath}/addFaculty"
			modelAttribute="faculty">
			<table>
				<tr>
					<td><form:label path="name">
							<spring:message code="facultycreation.newfaculty"></spring:message>
						</form:label></td>
					<td><form:input type="text" path="name" /></td>
				</tr>

				<tr>
					<td><form:label path="quantityOfStudents">
							<spring:message code="facultycreation.quantitystudent"></spring:message>
						</form:label></td>
					<td><form:input path="quantityOfStudents" /></td>
				</tr>

				<tr>
					<td><spring:message code="facultycreation.choosesubject"></spring:message>
						<br> <c:if test="${not empty subjects}">
							<c:forEach items="${subjects}" var="currentSubject">
								<form:checkbox path="subjects" value="${currentSubject}" />${currentSubject}<br>
							</c:forEach>
						</c:if></td>
				</tr>

				<tr>
					<td><input type="submit"
						value="<spring:message code="facultycreation.submit"></spring:message>" /></td>
				</tr>
			</table>

			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form:form>


	</div>
</div>
<script type="text/javascript" src="../js/home.js"></script>
</body>

</html>