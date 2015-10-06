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
			<c:out value="${tour.resortHotel.resort.name }"/>
			<c:out value="${tour.resortHotel.resort.city.name }"/>
			<c:out value="${tour.resortHotel.resort.city.country.name }"/>
			<br>
		</c:forEach>
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="add_tour">
			<br>
			<input type="text" name="name" placeholder="<fmt:message key="tour.lables.name" bundle="${rb}"/>" value="${name}">
			<br>
			<fmt:message key="tour.lables.description" bundle="${rb}"/>
			<br>
			<textarea name="description"><c:out value="${description}"/></textarea>
			<br>
			<input type="text" name="cost" placeholder="<fmt:message key="tour.lables.cost" bundle="${rb}"/>" value="${cost}">
			<br>
			<input type="text" name="beginDate" placeholder="<fmt:message key="tour.lables.beginDate" bundle="${rb}"/>" value="${beginDate}">
			<br>
			<input type="text" name="endDate" placeholder="<fmt:message key="tour.lables.endDate" bundle="${rb}"/>" value="${endDate}">
			<br>
			<input type="text" name="food" placeholder="<fmt:message key="tour.lables.food" bundle="${rb}"/>" value="${food}">
			<br>
			<input type="text" name="path" placeholder="<fmt:message key="tour.lables.path" bundle="${rb}"/>" value="${path}">
			<br>
			<input type="text" name="pathTime" placeholder="<fmt:message key="tour.lables.pathTime" bundle="${rb}"/>" value="${pathTime}">
			<br>
			<select name="restType">
				<c:forEach items="${restTypeList}" var="restTypeItem">
					<option value="${restTypeItem.id}" label="${restTypeItem.name}" 
					<c:if test="${restType != null && restTypeItem.id == restType}">selected="selected"</c:if> >
				</c:forEach>
			</select>
			<br>
			<select name="resortHotel">
				<c:forEach items="${resortHotelList}" var="resortHotelItem">
					<option value="${resortHotelItem.id}" label="${resortHotelItem.name}, 
					${resortHotelItem.resort.name}, ${resortHotelItem.resort.city.name}, 
					${resortHotelItem.resort.city.country.name}" <c:if test="${resortHotel != null && 
					resortHotelItem.id == resortHotel}">selected="selected"</c:if>>
				</c:forEach>
			</select>
			<br>
			<input type="submit" value="<fmt:message key="tour.lables.add" bundle="${rb}"/>">
		</form>
		<div id="page-buffer"></div>	
	</div>	
	<c:import url="${configBundle.getResourceBundle().handleGetObject('path.sample.footer')}"/>
</body>
</html>