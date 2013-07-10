package airTraffic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import airTraffic.model.bean.PassengerBean;

public class PassengerDAO extends AbstractDAO {

	public PassengerBean getPassengerByPassportId(String passportId) throws SQLException{
		PassengerBean passenger = new PassengerBean();
		String query = "SELECT * FROM passenger WHERE passport_id = ?";
		
		try(Connection connection = getConnection();
				PreparedStatement pStmt = connection.prepareStatement(query);){
			pStmt.setString(1, passportId);
			
			try(ResultSet rs = pStmt.executeQuery();){				
				if(rs.next()){	
					passenger.setPassportId(passportId);
					passenger.setFirstName(rs.getString(2));
					passenger.setLastName(rs.getString(3));
					passenger.setAddress(rs.getString(4));
					passenger.setDateOfBirth(rs.getDate(5));
				}
			} 
		}
		
		return passenger;
	}
}
