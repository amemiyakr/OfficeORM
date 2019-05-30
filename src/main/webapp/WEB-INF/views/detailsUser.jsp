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
<title>ユーザ管理</title>
</head>
<body>
	<div class="container">
		<c:import url="header/header.jsp" />
		<h1>ユーザ詳細</h1>
		<div class="row">
			<div class="col-md-12">
				<table class="table">
					<tr>
						<th>ID</th>
						<td><c:out value="${user.userId}" /></td>
					</tr>
					<tr>
						<th>氏名</th>
						<td><c:out value="${user.userName}" /></td>
					</tr>
					<tr>
						<th>ログインID</th>
						<td><c:out value="${user.loginId}" /></td>
					</tr>
					<tr>
						<th>対象グループ</th>
						<td><c:out value="${user.group.groupName}" /></td>
					</tr>
				</table>
				<a href="<spring:url value="/userList" />" class="btn btn-primary">一覧に戻る</a>
				<a href="<spring:url value="/editUser/${user.userId}" />" class="btn btn-default">編集</a>
				<c:if test="${user.userId != userId}">
					<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#del">削除</button>
				</c:if>
			</div>
		</div>
	</div>
	<!-- del -->
	<div class="modal fade" id="del" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-body">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					本当に削除してよろしいですか？
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<a href="<spring:url value="/delUser/${user.userId}" />" class="btn btn-primary">OK</a>
				</div>
			</div>
		</div>
	</div>
	<script src="<spring:url value="/js/jquery-2.2.4.min.js" />"></script>
	<script src="<spring:url value="/js/bootstrap.min.js" />"></script>
</body>
</html>