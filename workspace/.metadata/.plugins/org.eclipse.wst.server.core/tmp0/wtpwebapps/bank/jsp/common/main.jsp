<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${ locale }"/>
<fmt:setBundle basename="resources.messages" var="rb"/>
<fmt:setBundle basename="resources.config" var="configBundle"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="css/main.css"/>">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><fmt:message key="main.lables.main" bundle="${ rb }"/></title>
</head>
<body>
<div class="container">
		<c:import url="${configBundle.getResourceBundle().handleGetObject('path.sample.header')}"/>
	<table border="1" >
		<tr>
			<td>Фамилия</td>
			<td>Имя</td>
			<td>Отчество</td>
			<td>День рождения</td>
			<td>Пол</td>
			<td>Серия пасспорта</td>
			<td>Номер пасспорта</td>
			<td>Паспорт выдан</td>
			<td>Дата получения пасспорта</td>
			<td>Идентификационный номер</td>
			<td>Место рождения</td>
			<td>Город фактического проживания</td>
			<td>Адрес фактического проживания</td>
			<td>Телефон дом</td>
			<td>Телефон моб</td>
			<td>Email</td>
			<td>Место работы</td>
			<td>Должность</td>
			<td>Адрес регистрации</td>
			<td>Семейное положение</td>
			<td>Национальность</td>
			<td>Инвалидность</td>
			<td>Пенсионер</td>
			<td>Доход</td>
			<td></td>
			<td></td>
		</tr>
		<c:forEach items="${clientList}" var="client">
			<tr>
				<td><c:out value="${client.secondName}"/></td>
				<td><c:out value="${client.firstName}"/></td>
				<td><c:out value="${client.patronimic}"/></td>
				<td><fmt:formatDate value="${client.birthday}" pattern="${configBundle.getResourceBundle().handleGetObject('date.format')}"/></td>
				<td><c:out value="${client.male}"/></td>
				<td><c:out value="${client.passportSeria}"/></td>
				<td><c:out value="${client.passportNumber}"/></td>
				<td><c:out value="${client.passportPlace}"/></td>
				<td><fmt:formatDate value="${client.passportDate}"  pattern="${configBundle.getResourceBundle().handleGetObject('date.format')}"/></td>
				<td><c:out value="${client.cid}"/></td>
				<td><c:out value="${client.birthplace}"/></td>
				<td><c:out value="${client.currentCity.name}"/></td>
				<td><c:out value="${client.currentAddress}"/></td>
				<td><c:out value="${client.phoneHome}"/></td>
				<td><c:out value="${client.phoneMobile}"/></td>
				<td><c:out value="${client.email}"/></td>
				<td><c:out value="${client.workPlace}"/></td>
				<td><c:out value="${client.position}"/></td>
				<td><c:out value="${client.registrationAddress}"/></td>
				<td><c:out value="${client.martial.name}"/></td>
				<td><c:out value="${client.nationality.name}"/></td>
				<td><c:out value="${client.disability.name}"/></td>
				<td><c:out value="${client.pensioner}"/></td>
				<td><c:out value="${client.income}"/></td>
				<td><label for="edit_page_submit_${client.id}"  class="linkable">Изменить</label></td>
				<td><label for="delete_submit_${client.id}" class="linkable">Удалить</label></td>
			</tr>
			<form method="post" aciton="Controller">
				<input type="hidden" name="command" value="edit_page">
				<input type="hidden" name="id" value="<c:out value="${client.id}"/>">
				<input type="submit" id="edit_page_submit_${client.id}" class="hiden">
			</form>
			<form method="post" aciton="Controller" >
				<input type="hidden" name="command" value="delete">
				<input type="hidden" name="id" value="<c:out value="${client.id}"/>">
				<input type="submit" id="delete_submit_${client.id}" class="hiden">
			</form>
		</c:forEach>
	</table>
	<form action="Controller" method="post">
		<input type="hidden" name="command" value="view_all">
		<input type="submit" value="Показать все">
	</form>	
	<a href="${pageContext.servletContext.contextPath }/Controller?command=add_page">Добавить нового клиента</a>	
	<div id="page-buffer"></div>	
	</div>	
	<c:import url="${configBundle.getResourceBundle().handleGetObject('path.sample.footer')}"/>
</body>
</html>