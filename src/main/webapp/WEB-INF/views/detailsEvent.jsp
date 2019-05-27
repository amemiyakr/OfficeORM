<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="<spring:url value="/css/bootstrap.min.css" />" rel="stylesheet" />
<link href="<spring:url value="/css/style.css" />" rel="stylesheet" />
<title>イベント管理</title>
</head>
<body>
	<div class="container">
		<c:import url="header/header.jsp" />
		<h1>イベント詳細</h1>
		<div class="row">
			<div class="col-md-12">
				<table class="table">
					<tr>
						<th>タイトル</th>
						<td><c:out value="${event.title}" /></td>
					</tr>
					<tr>
						<th>開始日時</th>
						<td><c:out value="${event.startdate}" /></td>
					</tr>
					<tr>
						<th>終了日時</th>
						<td><c:out value="${event.enddate}" /></td>
					</tr>
					<tr>
						<th>場所</th>
						<td><c:out value="${event.place}" /></td>
					</tr>
					<tr>
						<th>対象グループ</th>
						<td><c:out value="${event.group}" /></td>
					</tr>
					<tr>
						<th>詳細</th>
						<td><c:out value="${event.details}" /></td>
					</tr>
					<tr>
						<th>登録者</th>
						<td><c:out value="${event.user.userName}" /></td>
					</tr>
					<!-- TODO: 参加者の項目を追加する -->
				</table>
			</div>
		</div>
	</div>
	<script src="<spring:url value="/js/jquery-2.2.4.min.js" />"></script>
	<script src="<spring:url value="/js/bootstrap.min.js" />"></script>
</body>
</html>