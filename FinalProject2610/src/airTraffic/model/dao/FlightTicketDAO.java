package airTraffic.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


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
}
