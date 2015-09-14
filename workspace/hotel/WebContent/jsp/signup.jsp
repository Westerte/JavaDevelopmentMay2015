<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${ locale }"/>
<fmt:setBundle basename="resources.messages" var="rb"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="css/main.css"/>">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="signup.lables.signup" bundle="${ rb }"/></title>
</head>
<body>	
	<div id="page-wrapper">
	<c:import url="/jspf/header.jsp" />
		<div id="login_form">
			<div id="login_form_content">
				<div id="status">
						<c:choose>
							<c:when test="${ status != null }"> ${ status }</c:when>
							<c:otherwise><fmt:message key="signup.lables.enterData" bundle="${ rb }"/></c:otherwise>
						</c:choose>
				</div>
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="signup">
					<input type="text" name="login" value="${ login }" placeholder="<fmt:message key="lables.login" bundle="${ rb }"/>" class="fields input" id="login_field">
					<input type="text" name="email" value="${ email }" placeholder="<fmt:message key="lables.email" bundle="${ rb }"/>" class="fields input" id="email_field">
					<input type="password" name="password" placeholder="<fmt:message key="lables.password" bundle="${ rb }"/>" class="fields input" id="password_field">
					<input type="password" name="repeated_password" placeholder="<fmt:message key="lables.repeatPassword" bundle="${ rb }"/>" class="fields input" id="password_field">
					<input type="submit" value="<fmt:message key="signup.buttons.submit" bundle="${ rb }"/>" id="submit_button" class="input">
				</form>
				<a href="Controller?command=login_page" id="signup"><fmt:message key="signup.lables.backToMain" bundle="${ rb }"/></a>
			</div>
		
		</div>
	<div id="page-buffer"></div>
	</div>
	<c:import url="/jspf/footer.jsp" />	
</body>
</html>