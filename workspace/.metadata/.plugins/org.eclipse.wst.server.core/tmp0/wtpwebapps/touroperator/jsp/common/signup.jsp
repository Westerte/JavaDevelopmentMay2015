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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="signup.lables.signup" bundle="${ rb }"/></title>
</head>
<body>	
	<div class="container">
		<c:import url="${configBundle.getResourceBundle().handleGetObject('path.sample.header')}"/>
			<div id="login_form" class="cols col-4 clear">
				<div id="login_form_content" class="cols col-12 clear">
					<div class="status ${status?'red':''}" class="cols col-12 clear">
							<c:choose>
								<c:when test="${ status }"><fmt:message key="lables.badData" bundle="${ rb }"/></c:when>
								<c:otherwise><fmt:message key="signup.lables.enterData" bundle="${ rb }"/></c:otherwise>
							</c:choose>
					</div>
					<form action="Controller" method="post">
						<input type="hidden" name="command" value="signup">
						<input type="text" name="login" value="${ login }" placeholder="<fmt:message key="lables.login" bundle="${ rb }"/>" class="fields input cols col-12 clear" id="login_field">
						<input type="text" name="email" value="${ email }" placeholder="<fmt:message key="lables.email" bundle="${ rb }"/>" class="fields input cols col-12 clear" id="email_field">
						<input type="password" name="password" placeholder="<fmt:message key="lables.password" bundle="${ rb }"/>" class="fields input cols col-12 clear" id="password_field">
						<input type="password" name="repeated_password" placeholder="<fmt:message key="lables.repeatPassword" bundle="${ rb }"/>" class="fields input cols col-12 clear" id="password_field">
						<input type="submit" value="<fmt:message key="signup.buttons.submit" bundle="${ rb }"/>" id="submit_button" class="input cols col-12 clear">
					</form>
					<div class="input cols col-12">
						<a href="Controller?command=login_page" id="signup"><fmt:message key="signup.lables.backToMain" bundle="${ rb }"/></a>
					</div>
				</div>
			
			</div>
		<div id="page-buffer"></div>
	</div>
	<c:import url="${configBundle.getResourceBundle().handleGetObject('path.sample.footer')}"/>	
</body>
</html>