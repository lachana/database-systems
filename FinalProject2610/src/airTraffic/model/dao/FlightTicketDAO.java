package airTraffic.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import airTraffic.model.bean.FlightSegmentBean;
import airTraffic.model.bean.JourneyBean;


public class FlightTicketDAO extends AbstractDAO {

	public int getNumberOfTicketsSoldForFlightAndClass(Date date, String flightNumber, String flightClass) throws SQLException{
		int result = 0;
		String query = "SELECT count(*) FROM flight_ticket f, belongs_to b WHERE " +
				"b.f_id = f.f_id AND b.date = ? AND b.flight_number = ? AND f.class = ?";
		
		try(Connection connection = getConnection();
				PreparedStatement pStmt = connection.prepareStatement(query);){
			pStmt.setDate(1, date);
			pStmt.setString(2, flightNumber);
			pStmt.setString(3, flightClass);
			
			try(ResultSet rs = pStmt.executeQuery();){
				
				if(rs.next()){	
					result = rs.getInt(1);
				}
			} 
		}
		
		return result;	
	}
	
	public double insertFlightTicket(FlightSegmentBean flight, String passportId, String flightClass, JourneyBean journey) throws SQLException{
		String query1 = "SELECT f.price FROM flight_ticket f, belongs_to b WHERE f.f_id = b.f_id AND b.flight_number = ? AND b.date = ? AND f.class = ?";
		String query2 = "INSERT INTO flight_ticket(class, price) VALUES(?, ?)";
		String query3 = "UPDATE journey SET total_price = total_price + ? WHERE j_id = ?";
		String query4 = "SELECT max(f_id) FROM flight_ticket";
		String query5 = "INSERT INTO belongs_to VALUES(?, ?, ?, ?)";
		String query6 = "INSERT INTO consists_of VALUES(?, ?, ?)";
		
		double price = 0.0;
		
		try(Connection connection = getConnection();
				PreparedStatement pStmt1 = connection.prepareStatement(query1);
				PreparedStatement pStmt2 = connection.prepareStatement(query2);
				PreparedStatement pStmt3 = connection.prepareStatement(query3);
				PreparedStatement pStmt4 = connection.prepareStatement(query4);
				PreparedStatement pStmt5 = connection.prepareStatement(query5);
				PreparedStatement pStmt6 = connection.prepareStatement(query6);){
			connection.setAutoCommit(false);
			pStmt1.setString(1, flight.getFlight_number());
			pStmt1.setDate(2, flight.getDate());
			pStmt1.setString(3, flightClass);
			
						
			try(ResultSet rs = pStmt1.executeQuery();){
				
				if(rs.next()){	
					price = rs.getDouble(1);
				}
			}
			
			//insert flightTicket
			pStmt2.setString(1, flightClass);
			pStmt2.setDouble(2, price);
			pStmt2.execute();
		
			//update totalPrice
			pStmt3.setDouble(1, price);
			pStmt3.setInt(2, journey.getjId());
			pStmt3.execute();
			
			//get fId
			int fId = 0;
			try(ResultSet rs = pStmt4.executeQuery();){				
				if(rs.next()){	
					fId = rs.getInt(1);
				}
			}
			
			//insert into belongs_to
			pStmt5.setInt(1, fId);
			pStmt5.setString(2, passportId);
			pStmt5.setDate(3, flight.getDate());
			pStmt5.setString(4, flight.getFlight_number());
			pStmt5.execute();
			
			//insert into consists_of
			pStmt6.setString(1, flight.getFlight_number());
			pStmt6.setDate(2, flight.getDate());
			pStmt6.setInt(3, journey.getjId());
			pStmt6.execute();
			
			connection.commit();
		}
		return price;
	}
}
