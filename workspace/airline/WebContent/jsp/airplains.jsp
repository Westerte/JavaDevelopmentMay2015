<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>     
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table border="2px">
		<h3>Airliners</h3>
		<hr>
		<tr>
			<td>Number plate</td>
			<td>Manufacturer</td>
			<td>Model</td>
			<td>Max range</td>
			<td>Capacity</td>
			<td>Bearing capacity</td>
			<td>Fuel consumption</td>
			<td>Seats count</td>
			<td>ClassCount</td>
			<td>LuggageCapacity</td>
		</tr>
		<c:forEach var="airplane" items="${airplanes}">  
			<c:if test="${ airplane.getClass().getName().equals('edu.nesterenko.airline.entity.Airliner') }" > 
				<tr>
					<td><c:out value="${ airplane.getNumberPlate() }"/></td>
					<td><c:out value="${ airplane.getManufacturer() }"/></td>
					<td><c:out value="${ airplane.getModel() }"/></td>
					<td><c:out value="${ airplane.getMaxRange() }"/></td>
					<td><c:out value="${ airplane.getCapacity() }"/></td>
					<td><c:out value="${ airplane.getBearingCapacity() }"/></td>
					<td><c:out value="${ airplane.getFuelConsumption() }"/></td>
					<td><c:out value="${ airplane.getSeatsCount() }"/></td>
					<td><c:out value="${ airplane.getClassCount()}"/></td>
					<td><c:out value="${ airplane.getLuggageCapacity()}"/></td>
				</tr>
			</c:if>
		</c:forEach>		
	</table>
	<br>
	<table border="2px">
		<h3>Freighters</h3>
		<hr>
		<tr>
			<td>Number plate</td>
			<td>Manufacturer</td>
			<td>Model</td>
			<td>Max range</td>
			<td>Capacity</td>
			<td>Bearing capacity</td>
			<td>Fuel consumption</td>
			<td>Cargo hold count</td>
		</tr>
		<c:forEach var="airplane" items="${airplanes}" varStatus="status">
			<c:if test="${ airplane.getClass().getName().equals('edu.nesterenko.airline.entity.Freighter') }" >
				<tr>
					<td><c:out value="${ airplane.getNumberPlate() }"/></td>
					<td><c:out value="${ airplane.getManufacturer() }"/></td>
					<td><c:out value="${ airplane.getModel() }"/></td>
					<td><c:out value="${ airplane.getMaxRange() }"/></td>
					<td><c:out value="${ airplane.getCapacity() }"/></td>
					<td><c:out value="${ airplane.getBearingCapacity() }"/></td>
					<td><c:out value="${ airplane.getFuelConsumption() }"/></td>
					<td><c:out value="${ airplane.getCargoHoldCount() }"/></td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<br>
	<div>
		<form action="Controller" method="POST">
			<input type="hidden" name="command" value="find_all">
			<input type="submit" value="Show all"> 
		</form>
		<br>
		<form action="Controller" method="POST">
			<input type="hidden" name="command" value="sort_airplains_by_max_range">
			<input type="submit" value="Sort by max range"> 
		</form>
		<br>
		Find by fuel consumption:
		<form action="Controller" method="POST">
			<input type="hidden" name="command" value="find_by_fuel_consumption">
			min:
			<input type="text" name="min">
			max:
			<input type="text" name="max">
			<input type="submit" value="OK"> 
		</form>
	</div>
	<br>
	<form action="Controller" method="POST">
		<input type="hidden" name="command" value="calculate_general_capacity">
		
		<input type="submit" value="calculate general capacity"> 
		<c:if test="${!empty generalCapacity}">	
			<br>		
			General Capacity: <c:out value="${ generalCapacity }"/>
		</c:if>
	</form>
</body>
</html>