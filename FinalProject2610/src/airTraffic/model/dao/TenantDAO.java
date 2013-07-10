package airTraffic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import airTraffic.model.bean.Tenant;

public class TenantDAO extends AbstractDAO{
	
	public List<Tenant> ViewTenantActive(int sID){
		
		List<Tenant> ar = new ArrayList<Tenant>();	
		
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
						
						Tenant tenant = new Tenant();
						
						tenant.settId(resultSet.getInt(2));
						tenant.setFirstName(resultSet.getString(3));
						tenant.setLastName(resultSet.getString(4));
						tenant.setAddress(resultSet.getString(5));
						tenant.setEmail(resultSet.getString(6));
						ar.add(tenant);
						
					} 
					resultSet.close();	
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ar;
		}

	
public List<Tenant> ViewTenantAll(int sID){
		
		List<Tenant> ar = new ArrayList<Tenant>();	
		
			String query = new StringBuilder()
				.append("select rent.s_id, tenant.t_id, tenant.first_name, tenant.last_name, tenant.address, tenant.email, tenant.is_active"+
						" from rent, tenant " +
                        "where rent.s_id=? and rent.t_id = tenant.t_id")
				.toString();

			try (Connection connection = getConnection();
				 PreparedStatement preparedStatement = connection.prepareStatement(query);) {
				
				preparedStatement.setInt(1, sID);
					
				try (ResultSet resultSet = preparedStatement.executeQuery();) {
					while (resultSet.next()) {
						
						Tenant tenant = new Tenant();
						
						tenant.settId(resultSet.getInt(2));
						tenant.setFirstName(resultSet.getString(3));
						tenant.setLastName(resultSet.getString(4));
						tenant.setAddress(resultSet.getString(5));
						tenant.setEmail(resultSet.getString(6));
						tenant.setActive(resultSet.getBoolean(7));
						ar.add(tenant);
						
					} 
					resultSet.close();	
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return ar;
		}
}
