<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${ locale }"/>
<fmt:setBundle basename="resources.messages" var="rb"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<header class="clear">
		<div class="container clear">
			<div class="rows clear">
				<div id="logo" class="cols col-3 clear">
					<img src="${ pageContext.servletContext.contextPath}/img/logo.png">
				</div>
				<nav class="cols col-6 clear">
					<label for="show_menu" class="show_menu"><fmt:message key="header.lables.menu.show" bundle="${rb}"/></label>
					<input type="checkbox" id="show_menu" role="button">
					<ul id="header_menu" class="clear">
						<li><a href="#" class="clear"><fmt:message key="header.lables.menu.main" bundle="${rb}"/></a></li>
						<li><a href="#" class="clear"><fmt:message key="header.lables.menu.catalog" bundle="${rb}"/></a></li>
						<li><a href="#" class="clear"><fmt:message key="header.lables.menu.contacts" bundle="${rb}"/></a></li>
						<li><a href="#" class="clear"><fmt:message key="header.lables.menu.about" bundle="${rb}"/></a></li>
					</ul>
				</nav>
				<div class="cols col-3 clear">
					<div id="user_name_or_login">
						<c:choose>
							<c:when test="${ client != null }">
								<fmt:message key="header.lables.clientName" bundle="${rb}" />
								<a href="Controller?command=profile_page" class="header_link"><c:out value="${ client.nickName }"/></a>
								<a href="Controller?command=logout"><fmt:message key="header.lables.logout" bundle="${ rb }"/></a>
							</c:when>
							<c:otherwise>
								<a href="Controller?command=login_page"><fmt:message key="header.lables.enter" bundle="${rb}" /></a>
							</c:otherwise>
						</c:choose>
					</div>
					<div id="locale_switch">
						<ctg:locale-switch/>
					</div>
				</div>
			</div>
		</div>
	</header>
</body>
</html>