package airTraffic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import airTraffic.model.bean.JourneyBean;

public class JourneyDAO extends AbstractDAO {

	public LinkedList<JourneyBean> getJourneysByPassenger(String passportId) throws SQLException{
		LinkedList<JourneyBean> journeys = new LinkedList<JourneyBean>();
		String query = "SELECT * FROM journey WHERE booked_by = ?";
		
		try(Connection connection = getConnection();
				PreparedStatement pStmt = connection.prepareStatement(query);){
			pStmt.setString(1, passportId);
			
			try(ResultSet rs = pStmt.executeQuery();){
				
				while(rs.next()){	
					JourneyBean journey = new JourneyBean();
					journey.setjId(rs.getInt(1));
					journey.setTotalPrice(rs.getDouble(2));
					journey.setTravelAgency(rs.getString(3));
					journey.setPaymentType(rs.getString(4));
					journey.setTravelCancelInsurance(rs.getBoolean(5));
					journey.setBooked_by(passportId);
					
					journeys.add(journey);
				}
			} 
		}
		
		return journeys;
	}
	
	public void insertJourney(JourneyBean journey) throws SQLException{
		String query = "INSERT INTO journey(travel_agency, payment_type, travel_cancel_insurance, booked_by) VALUES(?, ?, ?, ?)";
		
		try(Connection connection = getConnection();
				PreparedStatement pStmt = connection.prepareStatement(query);){
			pStmt.setString(1, journey.getTravelAgency());
			pStmt.setString(2, journey.getPaymentType());
			pStmt.setBoolean(3, journey.isTravelCancelInsurance());
			pStmt.setString(4, journey.getBooked_by());
			
			pStmt.execute();
				
			
		}
	}
	
	public int getLastInsertId() throws SQLException{
		int result = 0;
		String query = "SELECT max(j_id) FROM journey";
		
		try(Connection connection = getConnection();
				PreparedStatement pStmt = connection.prepareStatement(query);){
			
			try(ResultSet rs = pStmt.executeQuery();){
				
				if(rs.next()){	
					result = rs.getInt(1);
				}
			} 
		}
		
		return result;
	}
}
