<%@ page pageEncoding="UTF-8"%>
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
			<p class="text-primary">
				<a class="navbar-brand" href="#">
					<strong>Event Manager</strong>
				</a>
			</p>
		</div>


		<div class="collapse navbar-collapse" id="mynavbar">
			<ul class="nav navbar-nav">
				<li><a href="todayEvent">本日のイベント</a></li>
				<li><a href="#">イベント管理</a></li>
				<li><a href="#">ユーザー管理</a></li>
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