package de.tum.labcourse.finalproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FlightSegmentDAO extends AbstractDAO {

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
			flight.setBoarding_time(resultSet.getDate(4));
			flight.setMaerketing_carrier_flight_numbers(FlightSegmentDAO.split(resultSet.getString(5), ", "));
			flight.setArriving_time(resultSet.getTimestamp(6));
			flight.setDeparture_time(resultSet.getTimestamp(7));
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
		List<FlightSegmentBean> updatedFlights = new ArrayList<FlightSegmentBean>();

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
		pStmt.setTimestamp(4, new Timestamp(newFlight.getBoarding_time().getTime()));
		pStmt.setString(
				5,
				FlightSegmentDAO.join(
						newFlight.getMaerketing_carrier_flight_numbers(), ", "));
		pStmt.setTimestamp(6, newFlight.getArriving_time());
		pStmt.setTimestamp(7, newFlight.getDeparture_time());
		pStmt.setString(8, newFlight.getArrival_airport());
		pStmt.setString(9, newFlight.getDeparture_airport());
		pStmt.setString(10, (newFlight.getOperated_by()));
		pStmt.setInt(11, newFlight.getFlightBy());
		String qy = pStmt.toString();
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
