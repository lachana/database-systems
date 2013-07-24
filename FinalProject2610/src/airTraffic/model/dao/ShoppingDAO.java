package airTraffic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import airTraffic.model.bean.ShoppingServiceBean;

public class ShoppingDAO extends AbstractDAO {

	public List<ShoppingServiceBean> ViewAreas(){

		List<ShoppingServiceBean> ar = new ArrayList<ShoppingServiceBean>();

		String query = new StringBuilder()
		.append("SELECT * from shopping_service order by s_id")
		//	.append("FROM shopping_service")
		.toString();

		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				while (resultSet.next()) {

					ShoppingServiceBean area = new ShoppingServiceBean();
					area.setsId(resultSet.getInt(1));
					area.setName(resultSet.getString(2));
					area.setLocation(resultSet.getString(3));
					area.setAssignment(resultSet.getString(4));
					area.setSize(resultSet.getInt(5));
					area.setAreaNumber(resultSet.getInt(6));
					area.setOfferedBy(resultSet.getString(7));
					ar.add(area);

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

	public void addArea(ShoppingServiceBean shopping) {


		String sql = "INSERT INTO shopping_service (sid, name, location, assignment, size, area_number, offered_by)" +
				" VALUES (?, ?, ?, ?, ?, ?, ? )";
		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);) {

			ps.setInt(1, shopping.getsId());
			ps.setString(2, shopping.getName());
			ps.setString(3, shopping.getLocation()); 
			ps.setString(4, shopping.getAssignment());
			ps.setInt(5, shopping.getSize()); 
			ps.setInt(6, shopping.getAreaNumber());
			ps.setString(7, shopping.getOfferedBy());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}