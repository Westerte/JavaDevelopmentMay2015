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
	<header>
		<div id="locale_switch">
			<ctg:locale-switch/>
		</div>
		<div id="user_name_or_login">
			<c:choose>
				<c:when test="${ client != null }">
					<fmt:message key="header.lables.clientName" bundle="${rb}" />
					<a href="/" class="header_link"><c:out value="${ client.nickName }"/></a>
					<a href="Controller?command=logout"><fmt:message key="header.lables.logout" bundle="${ rb }"/></a>
				</c:when>
				<c:otherwise>
					<a href="Controller?command=login_page"><fmt:message key="header.lables.enter" bundle="${rb}" /></a>
				</c:otherwise>
			</c:choose>
		</div>
	</header>
</body>
</html>