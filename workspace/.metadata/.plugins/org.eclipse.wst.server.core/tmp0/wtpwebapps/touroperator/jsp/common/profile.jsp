<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${ locale }"/>
<fmt:setBundle basename="resources.messages" var="rb"/>
<fmt:setBundle basename="resources.config" var="configBundle"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" rel="stylesheet" href="<c:url value="css/main.css"/>">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta http-equiv="X-UA-Compatible" content="ID=edge">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title><fmt:message key="profile.lables.profile" bundle="${rb}"/></title>
</head>
<body>
	<div class="container">
		<c:import url="${configBundle.getResourceBundle().handleGetObject('path.sample.header')}"/>
			<fmt:message key="profile.lables.login" bundle="${rb}"/>
			<c:out value="${client.nickName}" />
			<br>
			<fmt:message key="profile.lables.email" bundle="${rb}"/>
			<c:out value="${client.email}" />
			<br>
			<fmt:message key="profile.lables.status" bundle="${rb}"/>
			<c:out value="${client.clientType}"/>
			<br>
			<c:if test="${client.clientType =='ADMIN'}">
				<a href="Controller?command=admin_panel_page"><fmt:message key="profile.lables.adminPanel" bundle="${rb}"/></a>
			</c:if>
		<div id="page-buffer"></div>	
	</div>	
	<c:import url="${configBundle.getResourceBundle().handleGetObject('path.sample.footer')}"/>
</body>
</html>