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
	<title><fmt:message key="resortHotel.lables.resortHotelList" bundle="${ rb }"/></title>
</head>
<body>
	<div class="container">
		<c:import url="${configBundle.getResourceBundle().handleGetObject('path.sample.header')}"/>
		<c:forEach items="${resortHotelList}" var="resortHotel">
			<c:out value="${resortHotel.id}  "/>
			<c:out value="${resortHotel.name}"/>
			<c:out value="${resortHotel.description}"/>
			<c:out value="${resortHotel.stars}"/>
			<c:out value="${resortHotel.resort.name }"/>
			<c:out value="${resortHotel.resort.city.name }"/>
			<c:out value="${resortHotel.resort.city.country.name }"/>
			<br>
		</c:forEach>
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="add_resort_hotel">
			<br>
			<input type="text" name="name"
				placeholder="<fmt:message key="resortHotel.lables.name" bundle="${rb}"/>" value="${name}">
			<br>
			<fmt:message key="resortHotel.lables.description" bundle="${rb}"/>
			<br>
			<textarea name="description">${description}</textarea>
			<br>
			<fmt:message key="resortHotel.lables.resort" bundle="${rb}" />
			<br>
			<select name="resort">
				<c:forEach items = "${ resortList }" var="resortItem">
					<option value="${ resortItem.id }" label="${ resortItem.name }, ${resortItem.city.name},
					${resort.city.country.name}" <c:if test="${resort != null && resortItem.id = resort }"></c:if>
					/>
				</c:forEach>
			</select>
			<br>
			<fmt:message key="resortHotel.lables.stars" bundle="${ rb }"></fmt:message>
			<br>
			<select name="stars">
				<option value="1" label="1" <c:if test="${stars != null && stars == 1}">selected="selected"</c:if>>
				<option value="2" label="2" <c:if test="${stars != null && stars == 2}">selected="selected"</c:if>>
				<option value="3" label="3" <c:if test="${stars != null && stars == 3}">selected="selected"</c:if>>
				<option value="4" label="4" <c:if test="${stars != null && stars == 4}">selected="selected"</c:if>>
				<option value="5" label="5" <c:if test="${stars != null && stars == 5}">selected="selected"</c:if>>
			</select>
			<input type="submit" value="<fmt:message key="resortHotel.lables.add" bundle="${rb}"/>">
		</form>
		<div id="page-buffer"></div>	
	</div>	
	<c:import url="${configBundle.getResourceBundle().handleGetObject('path.sample.footer')}"/>
</body>
</html>