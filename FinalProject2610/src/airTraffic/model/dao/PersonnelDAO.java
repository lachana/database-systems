package airTraffic.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import airTraffic.model.bean.CrewmemberBean;

public class PersonnelDAO extends AbstractDAO {

	public LinkedList<CrewmemberBean> getCrewmembersByCrewPositionAndFreeDateAndAirline(String position, Date date, String airline) throws SQLException{
		LinkedList<CrewmemberBean> crew = new LinkedList<CrewmemberBean>();
		String query = "SELECT c.p_id, c.first_name, c.last_name FROM crewmember c WHERE c.position = ?  AND c.works_for = ? AND c.p_id NOT IN(SELECT s.p_id FROM serves s WHERE s.date = ?)";
		
		try(Connection connection = getConnection();
				PreparedStatement pStmt = connection.prepareStatement(query);){
			pStmt.setString(1, position);
			pStmt.setDate(3, date);
			pStmt.setString(2, airline);
			
			try(ResultSet rs = pStmt.executeQuery();){
				
				while(rs.next()){	
					CrewmemberBean crewMember = new CrewmemberBean();
					crewMember.setPersID(rs.getInt(1));
					crewMember.setFirstName(rs.getString(2));
					crewMember.setLastName(rs.getString(3));
					
					crew.add(crewMember);
				}
			} 
		}
		
		return crew;
	}
	
	public void addServe(String flightNumber, Date date, int pId) throws SQLException{
	String query = "INSERT INTO serves(flight_number, date, p_id) VALUES(?, ?, ?)";
		
		try(Connection connection = getConnection();
				PreparedStatement pStmt = connection.prepareStatement(query);){
			pStmt.setString(1, flightNumber);
			pStmt.setDate(2, date);
			pStmt.setInt(3, pId);
			
			pStmt.execute();
				
			
		}
	}
}
