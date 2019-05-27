<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="<spring:url value="/css/bootstrap.min.css" />" rel="stylesheet" />
<link href="<spring:url value="/css/style.css" />" rel="stylesheet" />
<title>ログイン</title>
</head>
<body>
	<div class="container">
		<c:import url="header/header.jsp" />
		<form:errors path="user" />
		<form:form modelAttribute="user">
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<form:input path="loginId" cssClass="form-control" placeholder="ログインID" />
						<form:errors path="loginId" />
					</div>
					<div class="form-group">
						<form:input path="pass" type="password" cssClass="form-control" placeholder="パスワード" />
						<form:errors path="pass" />
					</div>

					<p><input type="submit" value="ログイン" class="btn btn-primary btn-block"></p>
				</div>
			</div>
		</form:form>
	</div>
	<script src="<spring:url value="/js/jquery-2.2.4.min.js" />"></script>
	<script src="<spring:url value="/js/bootstrap.min.js" />"></script>
</body>
</html>