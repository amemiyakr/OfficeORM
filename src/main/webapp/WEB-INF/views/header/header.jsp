<%@ page pageEncoding="UTF-8"%>
<%@ page import="com.example.event.domain.Type"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<header>
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#mynavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>

				<a class="navbar-brand" href="#">
					<p class="text-primary"><strong>Event Manager</strong></p>
				</a>

		</div>


		<div class="collapse navbar-collapse" id="mynavbar">
			<ul class="nav navbar-nav">
				<li><a href="<spring:url value="/todayEvent" />">本日のイベント</a></li>
				<li><a href="<spring:url value="/eventList" />">イベント管理</a></li>
				<c:if test= "${ typeId == Type.ADMIN}">
					<li><a href="<spring:url value="/userList" />">ユーザー管理</a></li>
				</c:if>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<span class="glyphicon glyphicon-user"></span>
						<c:out value="${userName}" />
						<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="<spring:url value="/logout" />">ログアウト</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</nav>
</header>