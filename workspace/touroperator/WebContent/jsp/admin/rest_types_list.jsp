<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<title><fmt:message key="restType.lables.restTypeList" bundle="${ rb }"/></title>
</head>
<body>
	<div class="container">
		<c:import url="${configBundle.getResourceBundle().handleGetObject('path.sample.header')}"/>
		<c:forEach items="${restTypeList}" var="restType">
			<c:out value="${restType.id}  "/>
			<c:out value="${restType.name}"/>
			<c:out value="${restType.description}"/>
			<br>
		</c:forEach>
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="add_rest_type">
			<br>
			<input type="text" name="name" placeholder="<fmt:message key="restType.lables.name" bundle="${rb}"/>" value="${name}">
			<br>
			<fmt:message key="restType.lables.description" bundle="${rb}"/>
			<br>
			<textarea name="description">${description}</textarea>
			<br>
			<input type="submit" value="<fmt:message key="restType.lables.add" bundle="${rb}"/>">
		</form>
		<div id="page-buffer"></div>	
	</div>	
	<c:import url="${configBundle.getResourceBundle().handleGetObject('path.sample.footer')}"/>
</body>
</html>