<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
<title><spring:message code="login.title"></spring:message></title>

<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href="https://fonts.googleapis.com/css?family=Neucha&display=swap" rel="stylesheet" type='text/css'>
<link rel="stylesheet" href="../css/login.css">

</head>
<body>

	<div class="locales">
		<fieldset>
			<legend>
				<select id="locales" class="locales_sel">
					<option value="en"><spring:message code="login.english"></spring:message></option>
					<option value="ua"><spring:message code="login.ukrainian"></spring:message></option>
				</select>
			</legend>
		</fieldset>
	</div>

	<div class="text-center" style="padding:100px 0;width: 20%;margin: 0 auto;">
	<div class="logo"><spring:message code="login.login.title"></spring:message></div>
	<!-- Main Form -->
	<div class="login-form-1">
		<form method="POST" action="${contentPath}/login" id="login-form" class="text-left form-signin">
			<div class="login-form-main-message"></div>
			<div class="main-login-form">
				<div class="login-group ${error != null ? 'has-error' : ''}">
				<span>${message}</span>
					<div class="form-group">
						<input type="text" class="form-control" id="lg_username" name="email" placeholder="<spring:message code="login.email"></spring:message>">
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="lg_password" name="password" placeholder="<spring:message code="login.password"></spring:message>">
					</div>
		                 <span>${error}</span>
                         <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				</div>
				<button type="submit" class="btn btn-primary btn-lg btn-block pad-auth-btn"><spring:message code="login.signin"></spring:message></button>
			</div>
			<div class="etc-login-form">
				<p><spring:message code="login.newuser"></spring:message><a href="${contextPath}/registration"><spring:message code="login.create"></spring:message></a></p>
			</div>
		</form>
	</div>
	<!-- end:Main Form -->
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../js/locale.js"></script>

</body>
</html>