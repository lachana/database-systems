package de.tum.labcourse.finalproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDAO {

	protected Connection getConnection() throws SQLException{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection("jdbc:postgresql://127.0.0.1/postgres", "speechkey", "6o.bT[Y3^9EVv8j4am@FDs<ew");
	} 
}
