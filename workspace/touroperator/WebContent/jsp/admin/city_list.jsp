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
	<title><fmt:message key="city.lables.cityList" bundle="${ rb }"/></title>
</head>
<body>
	<div class="container">
		<c:import url="${configBundle.getResourceBundle().handleGetObject('path.sample.header')}"/>
		<c:forEach items="${cityList}" var="city">
			<c:out value="${city.id}  "/>
			<c:out value="${city.name}"/>
			<c:out value="${city.description}"/>
			<c:out value="${city.country.id }"/>
			<c:out value="${city.country.name }"/>
			<br>
		</c:forEach>
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="add_city">
			<br>
			<input type="text" name="name" placeholder="<fmt:message key="city.lables.name" bundle="${rb}"/>" value="${name}">
			<br>
			<fmt:message key="city.lables.description" bundle="${rb}" />
			<br>
			<textarea name="description">${description}</textarea>
			<br>
			<fmt:message key="city.lables.country" bundle="${rb}" />
			<br>
			<select name="country">
				<c:forEach items = "${ countryList }" var="countryItem">
					<option value="${ countryItem.id }" label="${ countryItem.name }" 
					<c:if test="${country != null && countryItem.id == country}">selected="selected"</c:if>/>
				</c:forEach>
			</select>
			<input type="submit" value="<fmt:message key="city.lables.add" bundle="${rb}"/>">
		</form>
		<div id="page-buffer"></div>	
	</div>	
	<c:import url="${configBundle.getResourceBundle().handleGetObject('path.sample.footer')}"/>
</body>
</html>