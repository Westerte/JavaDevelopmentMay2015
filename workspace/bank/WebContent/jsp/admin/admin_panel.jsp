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
<title><fmt:message key="adminPanel.lables.adminPanel" bundle="${rb}"/></title>
</head>
<body>
	<div class="container">
		<c:import url="${configBundle.getResourceBundle().handleGetObject('path.sample.header')}"/>
			<a href="Controller?command=country_list_page"><fmt:message key="adminPanel.lables.countries" bundle="${rb}"/></a>
			<br>
			<a href="Controller?command=city_list_page"><fmt:message key="adminPanel.lables.cities" bundle="${rb}"/></a>
			<br>
			<a href="Controller?command=resort_list_page"><fmt:message key="adminPanel.lables.resorts" bundle="${rb}"/></a>
			<br>
			<a href="Controller?command=resort_hotel_list_page"><fmt:message key="adminPanel.lables.resortHotels" bundle="${rb}"/></a>
			<br>
			<a href="Controller?command=rest_type_list_page"><fmt:message key="adminPanel.lables.restTypes" bundle="${rb}"/></a>
		<div id="page-buffer"></div>	
	</div>	
	<c:import url="${configBundle.getResourceBundle().handleGetObject('path.sample.footer')}"/>
</body>
</html>