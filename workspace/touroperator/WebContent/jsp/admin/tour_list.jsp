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
	<title><fmt:message key="tour.lables.tourList" bundle="${ rb }"/></title>
</head>
<body>
	<div class="container">
		<c:import url="${configBundle.getResourceBundle().handleGetObject('path.sample.header')}"/>
		<c:forEach items="${tourList}" var="tour">
			<c:out value="${tour.id}  "/>
			<c:out value="${tour.name}"/>
			<c:out value="${tour.description}"/>
			<c:out value="${tour.cost}"/>
			<c:out value="${tour.beginDate}"/>
			<c:out value="${tour.endDate}"/>
			<c:out value="${tour.days}"/>
			<c:out value="${tour.food}"/>
			<c:out value="${tour.path}"/>
			<c:out value="${tour.pathTime}"/>
			<c:out value="${tour.restType.name}"/>
			<c:out value="${tour.resortHotel.name}"/>
			<br>
		</c:forEach>
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="add_rest_type">
			<br>
			<input type="text" name="name" placeholder="<fmt:message key="tour.lables.name" bundle="${rb}"/>">
			<br>
			<fmt:message key="tour.lables.description" bundle="${rb}"/>
			<br>
			<textarea name="description" rows="10" cols="10"></textarea>
			<br>
			<input type="submit" value="<fmt:message key="tour.lables.add" bundle="${rb}"/>">
		</form>
		<div id="page-buffer"></div>	
	</div>	
	<c:import url="${configBundle.getResourceBundle().handleGetObject('path.sample.footer')}"/>
</body>
</html>