package airTraffic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.LinkedList;


import airTraffic.model.bean.FlightSegmentBean;

public class FlightSegmentDAO extends AbstractDAO {

	public LinkedList<FlightSegmentBean> getFlightSegmentsByDateAndDepAndDest(Date date, String departure, String destination)
			throws SQLException{
		LinkedList<FlightSegmentBean> flights = new LinkedList<FlightSegmentBean>();
		String query = "SELECT * FROM flight_segment WHERE date = ? AND destination_airport = ? AND departure_airport = ?";
		
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
					query = "SELECT marketing_carrier_code FROM marketing_carrier WHERE flight_number = ? AND date = ?";
					PreparedStatement pStmt2 = connection.prepareStatement(query);
					pStmt2.setString(1, flight.getFlight_number());
					pStmt2.setDate(2, date);
					
					try(ResultSet rs2 = pStmt.executeQuery();){
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
		String query = "SELECT * FROM flight_segment f, belongs_to b WHERE f.date = b.date AND f.flight_number = b.flight_number AND b.j_id = ?";
		
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
}
