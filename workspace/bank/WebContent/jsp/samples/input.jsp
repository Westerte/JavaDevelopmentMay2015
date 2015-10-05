<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${ locale }"/>
<fmt:setBundle basename="resources.messages" var="rb"/>
<fmt:setBundle basename="resources.config" var="configBundle"/>
<fmt:setBundle basename="resources.regexpatterns" var="patternBundle"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	Фамилия
	<input type="text" name="secondName" value="${secondName}" placeholder="Иванов" pattern="${patternBundle.getResourceBundle().handleGetObject('pattern.onlylitter')}" required>
	<span class="icon-validation"></span> 
	<br><br>
	Имя
	<input type="text" name="firstName" value="${firstName}" placeholder="Иван" pattern="${patternBundle.getResourceBundle().handleGetObject('pattern.onlylitter')}" required>
	<span class="icon-validation"></span>
	<br><br>
	Отчество
	<input type="text" name="patronimic" value="${patronimic}" placeholder="Иванович" pattern="${patternBundle.getResourceBundle().handleGetObject('pattern.onlylitter')}" required>
	<span class="icon-validation"></span>
	<br><br>
	День рождения
	<input type="text" name="birthday" value='${birthday}' placeholder="01.01.2015" pattern="${patternBundle.getResourceBundle().handleGetObject('pattern.date')}" required>
	<span class="icon-validation"></span>
	<br><br>
	Пол
	<input type="radio" name="male" <c:if test="${ male == null || male == 'male'}">checked="checked"</c:if> value="male" id="male"><label for="male">муж.</label>
	<input type="radio" name="male" <c:if test="${male == 'female'}">checked="checked"</c:if>value="female" id="female"><label for="female">жен.</label>
	<br><br>				
	Серия паспорта
	<input type="text" name="passportSeria" value="${passportSeria}" placeholder="BM" required>
	<span class="icon-validation"></span>
	<br><br>
	Номер паспорта
	<input type="text" name="passportNumber" value="${passportNumber}" placeholder="xxxxxxx" pattern="${patternBundle.getResourceBundle().handleGetObject('pattern.passportNumber')}" required>
	<span class="icon-validation"></span>
	<br><br>
	Паспорт выдан
	<input type="text" name="passportPlace" value="${passportPlace}" placeholder="адрес" required>
	<span class="icon-validation"></span>
	<br><br>
	Дата получения пасспорта
	<input type="text" name="passportDate" value="${passportDate}" placeholder="01.01.2015" pattern="${patternBundle.getResourceBundle().handleGetObject('pattern.date')}" required>
	<span class="icon-validation"></span>
	<br><br>
	Идентификационный номер
	<input type="text" name="cid" value="${cid}" placeholder="xxxx xxxx xxxx xxxx" pattern="${patternBundle.getResourceBundle().handleGetObject('pattern.cid')}" required>
	<span class="icon-validation"></span>
	<br><br>
	Место рождения
	<input type="text" name="birthplace" value="${birthplace}" placeholder="адрес" required>
	<span class="icon-validation"></span>
	<br><br>
	Город фактического проживания
	<select name="currentCity">
	<c:forEach items = "${ cityList }" var="city">
		<option value="${ city.id }" <c:if test="${city.id == currentCity}">selected="selected"</c:if> label="${ city.name }"/>
	</c:forEach>
	</select>
	<br><br>
	Адрес фактического проживания
	<input type="text" name="currentAddress" value="${currentAddress}" placeholder="адрес" required>
	<span class="icon-validation"></span>
	<br><br>
	Телефон дом
	<input type="text" name="phoneHome" value="${phoneHome}" placeholder="8xxx..." pattern="${patternBundle.getResourceBundle().handleGetObject('pattern.home')}" required>
	<span class="icon-validation"></span>
	<br><br>
	Телефон моб
	<input type="text" name="phoneMobile" value="${phoneMobile}" placeholder="+375-xx-xxx-xx-xx" pattern="${patternBundle.getResourceBundle().handleGetObject('pattern.mobile')}" required>
	<span class="icon-validation"></span>
	<br><br>
	Email
	<input type="text" name="email" value="${email}" placeholder="mymail@example.com" pattern="${patternBundle.getResourceBundle().handleGetObject('pattern.email')}" required>
	<span class="icon-validation"></span>
	<br><br>
	Место работы
	<input type="text" name="workPlace" value="${workPlace}" placeholder="Example Inc." required>
	<span class="icon-validation"></span>
	<br><br>
	Должность
	<input type="text" name="position" value="${position}" placeholder="Должность" required>
	<span class="icon-validation"></span>
	<br><br>
	Адрес регистрации
	<input type="text" name="registrationAddress" value="${registrationAddress}" placeholder="адрес" required>
	<span class="icon-validation"></span>
	<br><br>
	Семейное положение
	<select name="martial">
	<c:forEach items = "${ martialList }" var="martialItem">
		<option value="${ martialItem.id }" <c:if test="${martialItem.id == martial}">selected="selected"</c:if> label="${ martialItem.name }" />
	</c:forEach>
	</select>
	<br><br>
	Национальность
	<select name="nationality">
	<c:forEach items = "${ nationalityList }" var="nationalityItem">
		<option value="${ nationalityItem.id }" <c:if test="${nationalityItem.id == nationality}">selected="selected"</c:if> label="${ nationalityItem.name }"/>
	</c:forEach>
	</select>
	<br><br>
	Инвалидность
	<select name="disability">
	<c:forEach items = "${ disabilityList }" var="disabilityItem">
		<option value="${ disabilityItem.id }" <c:if test="${disabilityItem.id == disability}">selected="selected"</c:if> label="${ disabilityItem.name }"/>
	</c:forEach>
	</select>
	<br><br>
	Пенсионер
	<input type="radio" name="pensioner"  <c:if test="${ pensioner == null || pensioner == true}">checked="checked"</c:if>  value="true" id="yes"><label for="yes">да</label>
	<input type="radio" name="pensioner" <c:if test="${ pensioner == false }">checked="checked"</c:if> value="false" id="no"><label for="no">нет</label>
	<br><br>
	Доход
	<input type="text" name="income" value="${income}"  placeholder="20000" pattern="${patternBundle.getResourceBundle().handleGetObject('pattern.decimal')}">
	<span class="icon-validation">руб.</span>
	<br><br>	
	<input type="submit" value="Отправить">				
</body>
</html>