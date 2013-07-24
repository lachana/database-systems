package airTraffic.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import airTraffic.model.bean.FlightSegmentBean;

public class FlightSegmentDAOAirline extends AbstractDAO {

	public List<FlightSegmentBean> getAvailableFlights() throws SQLException {

		List<FlightSegmentBean> availableFlights = new ArrayList<FlightSegmentBean>();

		String query = new StringBuilder().append("SELECT * ")
				.append("FROM flight_segment ").append("ORDER BY flight_number")
				.toString();

		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement(query);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {

			FlightSegmentBean flight = new FlightSegmentBean();
			flight.setFlight_number(resultSet.getString(1));
			flight.setDate(resultSet.getDate(2));
			flight.setGate_nr(resultSet.getString(3));
			flight.setBoarding_time(resultSet.getTime(4));
			flight.setArriving_time(resultSet.getTime(6));
			flight.setDeparture_time(resultSet.getTime(7));
			flight.setArrival_airport(resultSet.getString(8));
			flight.setDeparture_airport(resultSet.getString(9));
			flight.setOperated_by(resultSet.getString(10));
			flight.setFlightBy(resultSet.getInt(11));
			availableFlights.add(flight);
		}

		resultSet.close();
		return availableFlights;
	}

	public void addNewFlight(FlightSegmentBean newFlight) throws SQLException {
		
		String query = new StringBuilder()
				.append("INSERT INTO flight_segment (flight_number, date, gate_nr, boarding_time,"
						+ " marketing_carrier_flight_numbers, arriving_time, departure_time, arrival_airport, departure_airport,"
						+ " operated_by, flightby) ")
				.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)").toString();

		Connection connection = getConnection();
		PreparedStatement pStmt = connection.prepareStatement(query);

		pStmt.setString(1, newFlight.getFlight_number());
		pStmt.setDate(2, new java.sql.Date(newFlight.getDate().getTime()));
		pStmt.setString(3, newFlight.getGate_nr());
		pStmt.setTime(4, new Time(newFlight.getBoarding_time().getTime()));
		
		pStmt.setTime(6, newFlight.getArriving_time());
		pStmt.setTime(7, newFlight.getDeparture_time());
		pStmt.setString(8, newFlight.getArrival_airport());
		pStmt.setString(9, newFlight.getDeparture_airport());
		pStmt.setString(10, (newFlight.getOperated_by()));
		pStmt.setInt(11, newFlight.getFlightBy());
		pStmt.executeUpdate();
	}

	/**
	 * Join array of strings to the string.
	 * 
	 * @param r
	 *            Array of strings
	 * @param d
	 *            delimiter
	 * @return String
	 */
	public static String join(String r[], String d) {
		if (r.length == 0)
			return "";
		StringBuilder sb = new StringBuilder();
		int i;
		for (i = 0; i < r.length - 1; i++)
			sb.append(r[i] + d);
		return sb.toString() + r[i];
	}
	/**
	 * Split string by delimiter with regard to the empty value and single element.
	 * 
	 * @param s String to split
	 * @param d Delimiter
	 * @return Array of strings
	 */
	public static String[] split(String s, String d) {
		if(s == null)
			return new String[0];
		else if(s.lastIndexOf(",") < 0)
			return new String[] {s};
		else
			return s.split(", ");
	}
}
