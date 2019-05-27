<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:include page="includes/bootstrapMeta.jsp" />
	<title>Flights</title>
	<jsp:include page="includes/bootstrapCss.jsp" />
	<link href="http://www.malot.fr/bootstrap-datetimepicker/bootstrap-datetimepicker/css/bootstrap-datetimepicker.css" rel="stylesheet">
</head>
<body>
	<div class="container" role="main">
 
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<form class="form-horizontal" method="post" action="saveNewFlight">
					<fieldset>
						<legend>New Flight</legend>
 
						<!-- flightId -->
						<div class="form-group">
							<label for="inputFlightId" class="col-md-3 control-label">Flight ID</label>
							<div class="col-md-9">
								<input class="form-control" id="inputFlightId" type="text" name="flightId" value="">
							</div>
						</div>
 
						<!-- aircraft -->
						<div class="form-group">
							<label for="inputAircraft" class="col-md-3 control-label">Aircraft</label>
							<div class="col-md-9">
								<input class="form-control" id="inputAircraft" type="text" name="aircraft" value="">
							</div>
						</div>
 
						<!-- origin -->
						<div class="form-group">
							<label for="inputOrigin" class="col-md-3 control-label">Origin</label>
							<div class="col-md-9">
								<input class="form-control" id="inputOrigin" type="text" name="origin" value="">
							</div>
						</div>
						
						<!-- destination -->
						<div class="form-group">
							<label for="inputDestination" class="col-md-3 control-label">Destination</label>
							<div class="col-md-9">
								<input class="form-control" id="inputDestination" type="text" name="destination" value="">
							</div>
						</div>
 
						<!-- departure -->
						<div class="form-group">
							<label for="inputDeparture" class="col-md-3 control-label">Departure</label>
							<div class="col-md-9">
								<input class="form_datetime" id="inputDeparture" placeholder="Date" type="text" readonly name="departure" value="">
							</div>
						</div>
						
						<!-- arrival -->
						<div class="form-group">
							<label for="inputArrival" class="col-md-3 control-label">Arrival</label>
							<div class="col-md-9">
								<input class="form_datetime" id="inputArrival" placeholder="Date" type="text" readonly name="arrival" value="">
							</div>
						</div>
						
						<!-- number of passengers -->
						<div class="form-group">
							<label for="inputNumberOfPassengers" class="col-md-3 control-label"># of Passengers</label>
							<div class="col-md-9">
								<input class="form-control" id="inputNumberOfPassengers" type="text" name="numberOfPassengers" value="">
							</div>
						</div>
						
						<!-- airline -->
						<div class="form-group">
							<label for="inputNumberOfPassengers" class="col-md-3 control-label">Airline</label>
							<div class="col-md-9">
								<input class="form-control" id="inputAirline" type="text" name="airline" value="">
							</div>
						</div>
						
						<!-- is cancelled -->
						<div class="form-group">
							<label for="inputCancelled" class="col-md-3 control-label">Cancelled</label>
							<div class="col-md-9">
								<input class="form-control" id="inputCancelled" type="checkbox" name="isCancelled" value="true">
							</div>
						</div>
 
						<!-- buttons -->
						<div class="form-group">
							<div class="col-md-10 col-md-offset-2">
								<button type="submit" class="btn btn-primary">Submit</button>
								<a href="listFlights" class="btn btn-default">Cancel</a>
							</div>
						</div>
 
					</fieldset>
				</form>
			</div>
		</div>
 
	</div><!-- End of container -->
 
	<!-- JS for Bootstrap -->
	<%@include file="includes/bootstrapJs.jsp"%>
	<!-- JS for Bootstrap -->
 
	<!-- JS for Datetime picker -->
	<script type="text/javascript" src="http://www.malot.fr/bootstrap-datetimepicker/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
 
	<script>
		$(function() {
			$(".form_datetime").datetimepicker({
				format : "dd.mm.yyyy hh:ii",
				pickerPosition : "bottom-left",
				autoclose : true,
				todayBtn : true,
				todayHighlight : true,
				keyboardNavigation : true,
				minView : 0,
				minuteStep : 1,
				weekStart : 1,
				startDate: new Date
			});
		});
	</script>
 
</body>
</html>