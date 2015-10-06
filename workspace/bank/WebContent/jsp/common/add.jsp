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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Добавить</title>
</head>
<body>
<div class="container">
		<c:import url="${configBundle.getResourceBundle().handleGetObject('path.sample.header')}"/>
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="add">
			<c:import url="${configBundle.getResourceBundle().handleGetObject('path.sample.input')}"/>
			<!-- Фамилия
			<input type="text" name="secondName" value="Нестеренко" >
			<br><br>
			Имя
			<input type="text" name="firstName" value="Вадим">
			<br><br>
			Отчество
			<input type="text" name="patronimic" value="Николаевич">
			<br><br>
			День рождения
			<input type="text" name="birthday" value="01.04.1995">
			<br><br>
			Пол
			<input type="radio" name="male" checked="checked" value="male" id="male"><label for="male">муж.</label>
			<input type="radio" name="male" value="female" id="female"><label for="female">жен.</label>
			<br><br>				
			Серия пасспорта
			<input type="text" name="passportSeria" value="BM">
			<br><br>
			Номер пасспорта
			<input type="text" name="passportNumber" value="1888311">
			<br><br>
			Паспорт выдан
			<input type="text" name="passportPlace" value="Ушачское РОВД Витебской области">
			<br><br>
			Дата получения пасспорта
			<input type="text" name="passportDate" value="01.01.2000" >
			<br><br>
			Идентификационный номер
			<input type="text" name="cid" value="4444 4444 4444 4444" >
			<br><br>
			Место рождения
			<input type="text" name="birthplace" value="Ушачи" >
			<br><br>
			Город фактического проживания
			<select name="currentCity">
			<c:forEach items = "${ cityList }" var="city">
				<option value="${ city.id }" label="${ city.name }"/>
			</c:forEach>
			</select>
			<br><br>
			Адрес фактического проживания
			<input type="text" name="currentAddress" value="ул.Якуба Колоса" >
			<br><br>
			Телефон дом
			<input type="text" name="phoneHome" value="80215821204" >
			<br><br>
			Телефон моб
			<input type="text" name="phoneMobile" value="+375-29-256-07-32" >
			<br><br>
			Email
			<input type="text" name="email" value="westerte@mail.ru" >
			<br><br>
			Место работы
			<input type="text" name="workPlace" value="epam" >
			<br><br>
			Должность
			<input type="text" name="position" value="programmer">
			<br><br>
			Адрес регистрации
			<input type="text" name="registrationAddress" value="г.п Ушачи ул.Садовая 3,5" >
			<br><br>
			Семейное положение
			<select name="martial">
			<c:forEach items = "${ martialList }" var="martial">
				<option value="${ martial.id }" label="${ martial.name }" />
			</c:forEach>
			</select>
			<br><br>
			Национальность
			<select name="nationality">
			<c:forEach items = "${ nationalityList }" var="nationality">
				<option value="${ nationality.id }" label="${ nationality.name }"/>
			</c:forEach>
			</select>
			<br><br>
			Инвалидность
			<select name="disability">
			<c:forEach items = "${ disabilityList }" var="disability">
				<option value="${ disability.id }" label="${ disability.name }"/>
			</c:forEach>
			</select>
			<br><br>
			Пенсионер
			<input type="radio" name="pensioner"  checked="checked"  value="true" id="yes"><label for="yes">да</label>
			<input type="radio" name="pensioner" value="false" id="no"><label for="no">нет</label>
			<br><br>
			Доход
			<input type="text" name="income" value="20000" >
			<br><br>	
			<input type="submit" value="Отправить">	-->
		</form>
		<div id="page-buffer"></div>	
	</div>	
	<c:import url="${configBundle.getResourceBundle().handleGetObject('path.sample.footer')}"/>
</body>
</html>