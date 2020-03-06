<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title><spring:message code="registration.title"></spring:message></title>

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet"
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href='http://fonts.googleapis.com/css?family=Varela+Round'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="../css/login.css">
</head>
<body>

	<div class="locales">
		<fieldset>
			<legend>
				<select id="locales" class="locales_sel">
					<option value="en"><spring:message code="registration.english"></spring:message></option>
					<option value="ua"><spring:message code="registration.ukrainian"></spring:message></option>
				</select>
			</legend>
		</fieldset>
	</div>

	<!-- REGISTRATION FORM -->
	<div class="text-center" style="padding: 50px 0">
		<div class="logo"><spring:message code="registration.titleregistration"></spring:message></div>
		<!-- Main Form -->
		<div class="login-form-1">
			<form:form method="POST" modelAttribute="userForm" id="register-form"
				class="text-left" enctype="multipart/form-data">
				<div class="login-form-main-message"></div>
				<div class="main-login-form">
					<div class="login-group">
						<spring:bind path="firstName">
							<div class="form-group ${status.error ? 'has-error' : ''}">
								<label for="reg_firstname" class="sr-only"><spring:message code="registration.firstname" var="registrationFirstName"></spring:message></label> 
								<form:input type="text" path="firstName" class="form-control" id="reg_firstname"
									name="firstName" placeholder="${registrationFirstName}"></form:input>
								<form:errors path="firstName"></form:errors>
							</div>
						</spring:bind>
						
						<spring:bind path="lastName">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label for="reg_lastname" class="sr-only"><spring:message code="registration.lastname" var="registrationLastName"></spring:message></label> 
							<form:input type="text" path="lastName" class="form-control" id="reg_lastname" name="lastName"
								placeholder="${registrationLastName}"></form:input>
							<form:errors path="lastName"></form:errors>
						</div>
						</spring:bind>
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label for="reg_password" class="sr-only"><spring:message code="registration.password" var="registrationPassword"></spring:message></label> 
							<form:input type="password" path="password" class="form-control" id="reg_password"
								name="reg_password" placeholder="${registrationPassword}"></form:input>
							<form:errors path="password"></form:errors>
						</div>
						<div class="form-group  ${status.error ? 'has-error' : ''}">
							<label for="reg_password_confirm" class="sr-only"><spring:message code="registration.confirmpassword" var="registrationConfirmPassword"></spring:message></label> 
							<form:input type="password" path="passwordConfirm" class="form-control"
								id="reg_password_confirm" name="reg_password_confirm"
								placeholder="${registrationConfirmPassword}"></form:input>
							<form:errors path="passwordConfirm"></form:errors>
						</div>

						<spring:bind path="email">
						<div class="form-group  ${status.error ? 'has-error' : ''}">
							<label for="reg_email" class="sr-only"><spring:message code="registration.email" var="registrationEmail"></spring:message></label> 
							<form:input type="text"  path="email" class="form-control" id="reg_email" name="reg_email"
								placeholder="${registrationEmail}"></form:input>
							<form:errors path="email"></form:errors>
						</div>
						</spring:bind>

					</div>
					<button type="submit" class="login-button">
						<i class="fa fa-chevron-right"></i>
					</button>
				</div>
				<div class="etc-login-form">
					<p><spring:message code="registration.accountexist"></spring:message>
					<a href="${contextPath}/login"><spring:message code="registration.changetologin"></spring:message></a></p>
				</div>
			</form:form>
		</div>
		<!-- end:Main Form -->
	</div>


	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="../js/locale.js"></script>

</body>
</html>