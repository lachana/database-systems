package airTraffic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import airTraffic.model.bean.AirplaneBean;

public class AirplaneDAO extends AbstractDAO {

	public AirplaneBean getAirplaneById(int aId) throws SQLException{
		String query = "SELECT * FROM airplane WHERE a_id = ?"; 
		AirplaneBean airplane = new AirplaneBean();
		
		try(Connection connection = getConnection();
				PreparedStatement pStmt = connection.prepareStatement(query);){
			pStmt.setInt(1, aId);
			
			try(ResultSet rs = pStmt.executeQuery();){
				
				if(rs.next()){	
				airplane.setaId(aId);
				airplane.setType(rs.getString(2));
				airplane.setFirstClassSeats(rs.getInt(3));
				airplane.setBusinessClassSeats(rs.getInt(4));
				airplane.setEconomyClassSeats(rs.getInt(5));
				}
			} 
		}
		
		return airplane;
	}
}
