<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<jsp:include page="includes/bootstrapMeta.jsp" />
		<title>Flights</title>
		<jsp:include page="includes/bootstrapCss.jsp" />
	</head>
<body>
	<div class="container" role="main">
 
		<div class="page-header">
        	<h1>Flight Management</h1>
      	</div>
 
		<!--  Error message ----------------------------------------------------------- -->
		<c:if test="${not empty errorMessage}">
			<div class="alert alert-danger" role="alert">${errorMessage}</div>
		</c:if>
		<!--  Error message ----------------------------------------------------------- -->
 
		<!--  Warning message ----------------------------------------------------------- -->
		<c:if test="${not empty warningMessage}">
			<div class="alert alert-warning" role="warning">${warningMessage}</div>
		</c:if>
		<!--  Warning message ----------------------------------------------------------- -->
 
		<!--  successful message ----------------------------------------------------------- -->
		<c:if test="${not empty message}">
			<div class="alert alert-success" role="warning">${message}</div>
		</c:if>
 
 
		<!--  Search bar ----------------------------------------------------------- -->
		<jsp:include page="includes/searchNav.jsp" />
		<!--  Search bar ----------------------------------------------------------- -->
 
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<p>
					<a href="newFlight.jsp" class="btn btn-success">Add new Flight</a>
					<a href="fillFlightList" class="btn btn-success">Fill List</a>
				</p>
			</div>
		</div>
 
 
		<div class="row">
			<div class="col-md-12 col-md-offset-0">
 
				<table data-toggle="table" class="table table-striped">
					<thead>
						<tr>
							<th data-sortable="true">ID</th>
							<th data-sortable="true">Aircraft</th>
							<th data-sortable="true">Origin</th>
							<th data-sortable="true">Destination</th>
							<th data-sortable="true">Departure</th>
							<th data-sortable="true">Arrival</th>
							<th data-sortable="true">Passengers</th>
							<th data-sortable="true">Airline</th>
							<th data-sortable="true">Cancelled</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
					<!--  list all flights ----------------------------------------------------------- -->
					<c:forEach items="${flights}" var="flight">
						<tr>
							<td>${flight.flightId}</td>
							<td>${flight.aircraft}</td>
							<td>${flight.origin}</td>
							<td>${flight.destination}</td>
							<td><fmt:formatDate value="${flight.departure}" pattern="dd.MM.yyyy hh:mm"/></td>
							<td><fmt:formatDate value="${flight.arrival}" pattern="dd.MM.yyyy hh:mm"/></td>
							<td>${flight.numberOfPassengers}</td>
							<td>${flight.airline}</td>
							<td>${flight.isCancelled}</td>

							<td>
								<a href="editFlight?flightId=${flight.flightId}" class="btn btn-xs btn-success">Edit</a> 
								<a href="deleteFlight?flightId=${flight.flightId}" class="btn btn-xs btn-danger">Delete</a>
							</td>
						</tr>
					</c:forEach>
					<!--  list all flights ----------------------------------------------------------- -->
					</tbody>
				</table>
			</div>
		</div>
 
 
	</div>	<!--  End of container -->
 
	<!-- JS for Bootstrap -->
	<%@include file="includes/bootstrapJs.jsp"%>
	<!-- JS for Bootstrap -->
 
</body>
</html>