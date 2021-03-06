<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="<spring:url value="/css/bootstrap.min.css" />"
	rel="stylesheet" />
<link href="<spring:url value="/css/style.css" />" rel="stylesheet" />
<title>本日のイベント</title>
</head>
<body>
	<div class="container">
		<c:import url="header/header.jsp" />
		<h1>本日のイベント</h1>
		<div class="row">
			<div class="col-md-12">
				<ul class="pagination nav navbar-nav navbar-right">

					<c:choose>
						<%-- <<ボタン --%>
						<c:when test="${1 == page}">
							<li class=disabled><a href="">&laquo;</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="?p=<c:out value="${page-1}"/>">&laquo;</a></li>
						</c:otherwise>
					</c:choose>

					<c:forEach var="Pages" begin="1" end="${numPages}">

						<c:choose>
							<%-- ページボタン --%>
							<c:when test="${Pages == page}">
								<li class="active"><a href="?p=${Pages}">${Pages}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="?p=${Pages}">${Pages}</a></li>
							</c:otherwise>
						</c:choose>

					</c:forEach>

					<c:choose>
						<%-- >>ボタン --%>
						<c:when test="${numPages == page}">
							<li class=disabled><a href="">&raquo;</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="?p=<c:out value="${page+1}"/>">&raquo;</a></li>
						</c:otherwise>
					</c:choose>


				</ul>
				<table class="table table-bordered">
					<tr>
						<th>タイトル</th>
						<th>開始日時</th>
						<th>場所</th>
						<th>対象グループ</th>
						<th>詳細</th>
					</tr>
					<c:forEach items="${todayList }" var="event">
						<tr>
							<td>
							<c:out value="${event.title }" />
								<c:forEach items="${joinList}" var="join">
									<c:if test="${join.event.eventId == event.eventId && join.user.userId == userId }">
										<input type="submit" class="btn btn-danger" value="参加" disabled>
									</c:if>
								</c:forEach>
							</td>
							<td><fmt:formatDate value="${event.startdate }" pattern="yyyy年MM月dd日(E) HH時mm分" /></td>
							<td><c:out value="${event.place }" /></td>
							<td><c:out value="${event.group.groupName }" /></td>
							<td><a class="btn btn-default" href="detailsEvent/<c:out value="${event.eventId}" />">詳細</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<script src="<spring:url value="/js/jquery-2.2.4.min.js" />"></script>
	<script src="<spring:url value="/js/bootstrap.min.js" />"></script>
</body>
</html>