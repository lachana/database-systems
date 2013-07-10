package airTraffic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import airTraffic.model.bean.Tenant;

public class TenantDAO extends AbstractDAO{
	
	public Tenant ViewTenantActive(int sID){
		
		Tenant tenant = new Tenant();
		
			String query = new StringBuilder()
				.append("select rent.s_id, tenant.t_id, tenant.first_name, tenant.last_name, tenant.address, tenant.email"+
						" from rent, tenant " +
                        "where rent.s_id=? and rent.t_id = tenant.t_id and tenant.is_active='true'")
				.toString();

			try (Connection connection = getConnection();
				 PreparedStatement preparedStatement = connection.prepareStatement(query);) {
				
				preparedStatement.setInt(1, sID);
					
				try (ResultSet resultSet = preparedStatement.executeQuery();) {
					while (resultSet.next()) {
						
						tenant.settId(resultSet.getInt(2));
						tenant.setFirstName(resultSet.getString(3));
						tenant.setLastName(resultSet.getString(4));
						tenant.setAddress(resultSet.getString(5));
						tenant.setEmail(resultSet.getString(6));
											
					} 
					resultSet.close();	
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tenant;
		}

}
