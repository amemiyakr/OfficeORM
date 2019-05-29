<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="<spring:url value="/css/bootstrap.min.css" />" rel="stylesheet" />
<link href="<spring:url value="/css/style.css" />" rel="stylesheet" />
<title>ユーザー一覧</title>
</head>
<body>
	<div class="container">
		<c:import url="header/header.jsp" />
		<h1>ユーザー一覧</h1>
		<div class="row">
			<div class="col-md-12">
				<ul class="pagination nav navbar-nav navbar-right">

					<c:choose><%-- <<ボタン --%>
						<c:when test="${1 == page}">
							<li class=disabled><a href="">&laquo;</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="?p=<c:out value="${page-1}"/>">&laquo;</a></li>
						</c:otherwise>
					</c:choose>

					<c:forEach var="Pages" begin="1" end="${numPages}">

					<c:choose><%-- ページボタン --%>
						<c:when test="${Pages == page}">
							<li class="active"><a href="?p=${Pages}">${Pages}</a></li>
						</c:when>
						<c:otherwise>
							<li ><a href="?p=${Pages}">${Pages}</a></li>
						</c:otherwise>
					</c:choose>

					</c:forEach>

					<c:choose><%-- >>ボタン --%>
						<c:when test="${numPages == page}">
							<li class=disabled><a href="">&raquo;</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="?p=<c:out value="${page+1}"/>">&raquo;</a></li>
						</c:otherwise>
					</c:choose>


				</ul>
				<table class="table table-bordered ">
					<tr>
						<th>ID</th>
						<th>氏名</th>
						<th>所属グル－プ</th>
						<th>詳細</th>
					</tr>
					<c:forEach items="${needList}" var="user">
						<tr>
							<td><c:out value="${user.userId}" /></td>
							<td><c:out value="${user.userName}" /></td>
							<td><c:out value="${user.group.groupName}" /></td>
							<td><a href="#/<c:out value="${user.userId}" />" class="btn btn-default" >詳細</a></td>
						</tr>
					</c:forEach>
				</table>
				<p>
					<a href="#" class="btn btn-primary">ユーザーの登録</a>
				</p>
			</div>
		</div>
	</div>
	<script src="<spring:url value="/js/jquery-2.2.4.min.js" />"></script>
	<script src="<spring:url value="/js/bootstrap.min.js" />"></script>
</body>
</html>