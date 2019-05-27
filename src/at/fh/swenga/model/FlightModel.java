package at.fh.swenga.model;

import java.util.Date;

public class FlightModel {
	
	private int flightId;
	private String aircraft;
	private String origin;
	private String destination;
	private Date departure;
	private Date arrival;
	private int numberOfPassengers;
	private String airline;
	private boolean isCancelled;
	
	
	// constructors
	public FlightModel() {
		// TODO Auto-generated constructor stub
	}

	public FlightModel(int flightId, String aircraft, String origin, String destination, Date departure, Date arrival,
			int numberOfPassengers, String airline, boolean isCancelled) {
		super();
		this.flightId = flightId;
		this.aircraft = aircraft;
		this.origin = origin;
		this.destination = destination;
		this.departure = departure;
		this.arrival = arrival;
		this.numberOfPassengers = numberOfPassengers;
		this.airline = airline;
		this.isCancelled = isCancelled;
	}

	
	// getter & setter
	public int getFlightId() {
		return flightId;
	}

	public void setFlightId(int flightId) {
		this.flightId = flightId;
	}

	public String getAircraft() {
		return aircraft;
	}

	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
		this.departure = departure;
	}

	public Date getArrival() {
		return arrival;
	}

	public void setArrival(Date arrival) {
		this.arrival = arrival;
	}

	public int getNumberOfPassengers() {
		return numberOfPassengers;
	}

	public void setNumberOfPassengers(int numberOfPassengers) {
		this.numberOfPassengers = numberOfPassengers;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public boolean getIsCancelled() {
		return isCancelled;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	// equals & hashcode
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + flightId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlightModel other = (FlightModel) obj;
		if (flightId != other.flightId)
			return false;
		return true;
	}

	// toString
	@Override
	public String toString() {
		return "FlightModel [flightId=" + flightId + ", aircraft=" + aircraft + ", origin=" + origin + ", destination="
				+ destination + ", departure=" + departure + ", arrival=" + arrival + ", numberOfPassengers="
				+ numberOfPassengers + ", airline=" + airline + ", isCancelled=" + isCancelled + "]";
	}
	
}
