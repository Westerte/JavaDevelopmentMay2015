<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${ locale }"/>
<fmt:setBundle basename="resources.messages" var="rb"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="css/main.css"/>">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="main.lables.main" bundle="${ rb }"/></title>
</head>
<body>
	<div id="page-wrapper">
	<c:import url="/jspf/header.jsp" />
	
	<div id="page-buffer"></div>
	</div>
	<c:import url="/jspf/footer.jsp" />	
</body>
</html>