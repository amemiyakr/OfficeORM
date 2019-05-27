<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link href="<spring:url value="/css/bootstrap.min.css" />"
	rel="stylesheet" />
<link href="<spring:url value="/css/style.css" />" rel="stylesheet" />
<title>イベント登録</title>
</head>
<body>
	<div class="container">
		<c:import url="header/header.jsp" />
		<h1>イベント登録</h1>
		<form:form modelAttribute="event">
			<div class="row">
				<div class="col-md-12">
					<div class="form-group">
						<p>
							<strong>タイトル(必須)</strong>
						</p>
						<form:input path="title" cssClass="form-control" placeholder="" />
						<form:errors path="title" cssClass="form-control alert-danger" />
					</div>
					<div class="form-group">
						<p>
							<strong>開始日時(必須)</strong>
						</p>
						<form:input path="startdate" cssClass="form-control"
							placeholder="0000-00-00 00:00:00" />
						<form:errors path="startdate" cssClass="form-control alert-danger" />
					</div>
					<div class="form-group">
						<p>
							<strong>終了日時(必須)</strong>
						</p>
						<form:input path="enddate" cssClass="form-control"
							placeholder="0000-00-00 00:00:00" />
					</div>
					<div class="form-group">
						<p>
							<strong>場所(必須)</strong>
						</p>
						<form:input path="place" cssClass="form-control" placeholder="" />
						<form:errors path="place" cssClass="form-control alert-danger" />
					</div>
					<div class="form-group">
						<p>
							<strong>対象グループ</strong>
						</p>
						<form:select path="group.groupId" items="${group }"
							itemLabel="groupName" itemValue="groupId" cssClass="form-control" />
					</div>
					<div class="form-group">
						<p>
							<strong>詳細</strong>
						</p>
						<form:textarea path="details" cssClass="form-control"
							placeholder="" />
					</div>
					<p>
						<form:hidden path="user.userId" />
						<a href="#" class="btn btn-default">キャンセル</a> <input type="submit"
							value="登録" class="btn btn-primary">
					</p>
				</div>
			</div>
		</form:form>
	</div>
	<script src="<spring:url value="/js/jquery-2.2.4.min.js" />"></script>
	<script src="<spring:url value="/js/bootstrap.min.js" />"></script>
</body>
</html>