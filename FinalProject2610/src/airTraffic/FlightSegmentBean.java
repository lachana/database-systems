package airTraffic;

import java.util.Date;
import java.util.LinkedList;

public class FlightSegmentBean {

	private Date date ;
	private String flight_number;
	private LinkedList<String> maerketing_carrier_flight_numbers;
	private String gate_nr;
	private Date boarding_time;
	private Date arriving_time;
	private Date departin_time;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getFlight_number() {
		return flight_number;
	}
	public void setFlight_number(String flight_number) {
		this.flight_number = flight_number;
	}
	public LinkedList<String> getMaerketing_carrier_flight_numbers() {
		return maerketing_carrier_flight_numbers;
	}
	public void setMaerketing_carrier_flight_numbers(
			LinkedList<String> maerketing_carrier_flight_numbers) {
		this.maerketing_carrier_flight_numbers = maerketing_carrier_flight_numbers;
	}
	public String getGate_nr() {
		return gate_nr;
	}
	public void setGate_nr(String gate_nr) {
		this.gate_nr = gate_nr;
	}
	public Date getBoarding_time() {
		return boarding_time;
	}
	public void setBoarding_time(Date boarding_time) {
		this.boarding_time = boarding_time;
	}
	public Date getArriving_time() {
		return arriving_time;
	}
	public void setArriving_time(Date arriving_time) {
		this.arriving_time = arriving_time;
	}
	public Date getDepartin_time() {
		return departin_time;
	}
	public void setDepartin_time(Date departin_time) {
		this.departin_time = departin_time;
	}
	
	
}
