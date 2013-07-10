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
					
					journeys.add(journey);
				}
			} 
		}
		
		return journeys;
	}
}
