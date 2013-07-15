package airTraffic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import airTraffic.model.bean.TenantBean;

public class TenantDAO extends AbstractDAO{
	
	public List<TenantBean> ViewTenantActive(int sID){
		
		List<TenantBean> ar = new ArrayList<TenantBean>();	
		
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
						
						TenantBean tenant = new TenantBean();
						
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

	
public List<TenantBean> ViewTenantAll(int sID){
		
		List<TenantBean> ar = new ArrayList<TenantBean>();	
		
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
						
						TenantBean tenant = new TenantBean();
						
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
