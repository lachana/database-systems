package airTraffic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.LinkedList;


import airTraffic.model.bean.FlightSegmentBean;

public class FlightSegmentDAO extends AbstractDAO {

	public LinkedList<FlightSegmentBean> getFlightSegmentsByDateAndDepAndDest(Date date, String departure, String destination)
			throws SQLException{
		LinkedList<FlightSegmentBean> flights = new LinkedList<FlightSegmentBean>();
		String query = "SELECT * FROM flight_segment WHERE date = ? AND arrival_airport = ? AND departure_airport = ?";
		
		try(Connection connection = getConnection();
				PreparedStatement pStmt = connection.prepareStatement(query);){
			pStmt.setDate(1, date);
			pStmt.setString(2, destination);
			pStmt.setString(3, departure);
			
			try(ResultSet rs = pStmt.executeQuery();){
				
				while(rs.next()){	
					FlightSegmentBean flight = new FlightSegmentBean();
					flight.setFlight_number(rs.getString(1));
					flight.setDate(rs.getDate(2));
					flight.setGate_nr(rs.getString(3));
					flight.setBoarding_time(rs.getTime(4));
					flight.setDeparture_airport(rs.getString(5));
					flight.setArrival_airport(rs.getString(6));
					flight.setDeparture_time(rs.getTime(7));
					flight.setArriving_time(rs.getTime(8));
					flight.setOperated_by(rs.getString(9));
					flight.setFlightBy(rs.getInt(10));
					
					LinkedList<String> marketingCarriers = new LinkedList<String>();
					String query2 = "SELECT marketing_carrier_code FROM marketing_carrier WHERE flight_number = ? AND date = ?";
					PreparedStatement pStmt2 = connection.prepareStatement(query2);
					pStmt2.setString(1, flight.getFlight_number());
					pStmt2.setDate(2, date);
					
					try(ResultSet rs2 = pStmt2.executeQuery();){
						while(rs2.next()){
							marketingCarriers.add(rs2.getString(1));
						}
					}
					flight.setMaerketing_carrier_flight_numbers(marketingCarriers);
					flights.add(flight);
				}
			} 
		}
		
		return flights;
	}
	
	public LinkedList<FlightSegmentBean> getFlightSegmentsByJId(int jId) throws SQLException{
		LinkedList<FlightSegmentBean> flights = new LinkedList<FlightSegmentBean>();
		String query = "SELECT * FROM flight_segment f, consists_of c WHERE f.date = c.date AND f.flight_number = c.flight_number AND c.j_id = ?";
		
		try(Connection connection = getConnection();
				PreparedStatement pStmt = connection.prepareStatement(query);){
			pStmt.setInt(1, jId);
			
			try(ResultSet rs = pStmt.executeQuery();){
				
				while(rs.next()){	
					FlightSegmentBean flight = new FlightSegmentBean();
					flight.setFlight_number(rs.getString(1));
					flight.setDate(rs.getDate(2));
					flight.setGate_nr(rs.getString(3));
					flight.setBoarding_time(rs.getTime(4));
					flight.setDeparture_airport(rs.getString(5));
					flight.setArrival_airport(rs.getString(6));
					flight.setDeparture_time(rs.getTime(7));
					flight.setArriving_time(rs.getTime(8));
					flight.setOperated_by(rs.getString(9));
					flight.setFlightBy(rs.getInt(10));
					
					flights.add(flight);
				}
			} 
		}
		
		return flights;
	}
	
	public ArrayList<FlightSegmentBean> getAllFlights() throws SQLException{
		ArrayList<FlightSegmentBean> flights = new ArrayList<FlightSegmentBean>();
		String query = "SELECT * FROM flight_segment ORDER BY date";
		
		try(Connection connection = getConnection();
				PreparedStatement pStmt = connection.prepareStatement(query);){
						
			try(ResultSet rs = pStmt.executeQuery();){
				
				while(rs.next()){	
					FlightSegmentBean flight = new FlightSegmentBean();
					flight.setFlight_number(rs.getString(1));
					flight.setDate(rs.getDate(2));
					flight.setGate_nr(rs.getString(3));
					flight.setBoarding_time(rs.getTime(4));
					flight.setDeparture_airport(rs.getString(5));
					flight.setArrival_airport(rs.getString(6));
					flight.setDeparture_time(rs.getTime(7));
					flight.setArriving_time(rs.getTime(8));
					flight.setOperated_by(rs.getString(9));
					flight.setFlightBy(rs.getInt(10));
					
					LinkedList<String> marketingCarriers = new LinkedList<String>();
					String query2 = "SELECT marketing_carrier_code FROM marketing_carrier WHERE flight_number = ? AND date = ?";
					PreparedStatement pStmt2 = connection.prepareStatement(query2);
					pStmt2.setString(1, flight.getFlight_number());
					pStmt2.setDate(2, flight.getDate());
					
					try(ResultSet rs2 = pStmt2.executeQuery();){
						while(rs2.next()){
							marketingCarriers.add(rs2.getString(1));
						}
					}
					flight.setMaerketing_carrier_flight_numbers(marketingCarriers);
					flights.add(flight);
				}
			} 
		}
		
		return flights;
	}
	
public void addNewFlight(FlightSegmentBean newFlight) throws SQLException {
		
		String query = new StringBuilder()
				.append("INSERT INTO flight_segment (flight_number, date, gate_nr, boarding_time,"
						+ " arriving_time, departure_time, arrival_airport, departure_airport,"
						+ " operated_by, flight_with) ")
				.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)").toString();

		Connection connection = getConnection();
		PreparedStatement pStmt = connection.prepareStatement(query);

		pStmt.setString(1, newFlight.getFlight_number());
		pStmt.setDate(2, newFlight.getDate());
		pStmt.setString(3, newFlight.getGate_nr());
		pStmt.setTime(4, newFlight.getBoarding_time());
		pStmt.setTime(5, newFlight.getArriving_time());
		pStmt.setTime(6, newFlight.getDeparture_time());
		pStmt.setString(7, newFlight.getArrival_airport());
		pStmt.setString(8, newFlight.getDeparture_airport());
		pStmt.setString(9, (newFlight.getOperated_by()));
		pStmt.setInt(10, newFlight.getFlightBy());
		pStmt.executeUpdate();
	}

	public void addMarketingCarrier(String flightNumber, Date date, String marketingCarrier, String marketingNumber) throws SQLException{
		String query = "INSERT INTO marketing_carrier VALUES(?, ?, ?, ?)";
		
		try(Connection connection = getConnection();
				PreparedStatement pStmt = connection.prepareStatement(query);){
			pStmt.setString(1, marketingCarrier);
			pStmt.setString(2, flightNumber);
			pStmt.setString(3, marketingNumber);
			pStmt.setDate(4, date);
			
			pStmt.execute();
				
			}
	}
}
