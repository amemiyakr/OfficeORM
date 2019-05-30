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
						<td><fmt:formatDate value="${event.startdate}" pattern="yyyy年MM月dd日(E) HH時mm分" /></td>
					</tr>
					<tr>
						<th>終了日時</th>
						<td><fmt:formatDate value="${event.enddate}" pattern="yyyy年MM月dd日(E) HH時mm分" /></td>
					</tr>
					<tr>
						<th>場所</th>
						<td><c:out value="${event.place}" /></td>
					</tr>
					<tr>
						<th>対象グループ</th>
						<td><c:out value="${event.group.groupName}" /></td>
					</tr>
					<tr>
						<th>詳細</th>
						<td><c:out value="${event.details}" /></td>
					</tr>
					<tr>
						<th>登録者</th>
						<td><c:out value="${event.user.userName}" /></td>
					</tr>
					<tr>
						<th>参加者</th>
						<td>
							<c:forEach items="${joinList}" var="join" varStatus="joinStatus">
								<c:out value="${join.user.userName}" /><c:if test="${joinStatus.last == false}">, </c:if>
							</c:forEach>
						</td>
					</tr>
				</table>
				<a href="<spring:url value="/eventList" />" class="btn btn-primary">一覧に戻る</a>
				<c:choose>
					<%-- このイベントに参加していない場合、「参加する」ボタンを表示 --%>
					<c:when test="${yourJoin == null}">
						<a href="<spring:url value="/joinEvent/${event.eventId}" />" class="btn btn-info">参加する</a>
					</c:when>
					<%-- このイベントに参加している場合、「参加を取り消す」ボタンを表示 --%>
					<c:otherwise>
						<a href="<spring:url value="/cancelEvent/${event.eventId}" />" class="btn btn-warning">参加を取り消す</a>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<%-- 自分が登録したイベントの場合、編集・削除ボタンを表示 --%>
					<c:when test="${loginUser.userId == event.user.userId}">
						<a href="<spring:url value="/editEvent/${event.eventId}" />" class="btn btn-default">編集</a>
						<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#del">削除</button>
					</c:when>
					<%-- 管理ユーザーは、どのイベントにも編集・削除ボタンを表示 --%>
					<c:when test="${loginUser.type.typeId == adminTypeId}">
						<a href="<spring:url value="/editEvent/${event.eventId}" />" class="btn btn-default">編集</a>
						<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#del">削除</button>
					</c:when>
					<%-- 一般ユーザーは、編集・削除ボタンを表示しない --%>
				</c:choose>

			</div>
		</div>
	</div>
	<!-- del -->
<div class="modal fade" id="del" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
    <div class="modal-body">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        本当に削除してよろしいですか？
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <a href="<spring:url value="/delEvent/${event.eventId}" />" class="btn btn-primary">OK</a>
      </div>
    </div>
  </div>
</div>
	<script src="<spring:url value="/js/jquery-2.2.4.min.js" />"></script>
	<script src="<spring:url value="/js/bootstrap.min.js" />"></script>
</body>
</html>