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
<title>イベント一覧</title>
</head>
<body>
	<div class="container">
		<c:import url="header/header.jsp" />
		<h1>イベント一覧</h1>
		<div class="row">
			<div class="col-md-12">
				<ul class="pagination nav navbar-nav navbar-right">
					<li><a href="#">&laquo;</a></li>
					<li><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">&raquo;</a></li>
				</ul>
				<table class="table table-bordered ">
					<tr>
						<th>タイトル</th>
						<th>開始日時</th>
						<th>場所</th>
						<th>対象グループ</th>
						<th>詳細</th>
					</tr>
					<c:forEach items="${eventList}" var="event">
						<tr>
							<td>
								<c:out value="${event.title}" />
								<c:forEach items="${joinList}" var="join">
									<c:if test="${join.event.eventId} == ${event.eventId} && ${join.user.userId} == ${sessionScope.LoginId}">
										<input type="submit" class="btn btn-danger" value="参加" disabled>
									</c:if>
								</c:forEach>
							</td>
							<td><fmt:formatDate value="${event.startdate }" pattern="yyyy年MM月dd日(E) HH時mm分" /></td>
							<td><c:out value="${event.place}" /></td>
							<td><c:out value="${event.group.groupName}" /></td>
							<td><a href="#" class="btn btn-default">詳細</a></td>
						</tr>
					</c:forEach>
				</table>
				<p>
					<a href="#" class="btn btn-primary">イベントの登録</a>
				</p>
			</div>
		</div>
	</div>
	<script src="<spring:url value="/js/jquery-2.2.4.min.js" />"></script>
	<script src="<spring:url value="/js/bootstrap.min.js" />"></script>
</body>
</html>