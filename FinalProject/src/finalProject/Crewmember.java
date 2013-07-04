package finalProject;

import java.sql.Date;

public class Crewmember extends Personnel {
	
	public enum crewPositionEnum {
		Pilot, CoPilot, HeadFlightAttendant, FlightAttendant
	}
	
	private String admission;
	private Integer number_of_flights;
	private String[] spoken_languages;
	private crewPositionEnum crewPosition;

	public Crewmember (String admission, Integer number_of_flights, String[] spoken_languages,
			crewPositionEnum crewPosition,Integer p_id, String first_name, String last_name,
			Date date_of_birth, Double salary ) {
		super(p_id, first_name, last_name,
				date_of_birth, salary);
		this.admission = admission;
		this.number_of_flights = number_of_flights;
		this.spoken_languages = spoken_languages;
		this.crewPosition = crewPosition;

	}

	public String getAdmission() {
		return admission;
	}

	public void setAdmission(String admission) {
		this.admission = admission;
	}

	public Integer getNumberOfFlights() {
		return number_of_flights;
	}

	public void setNumberOfFlights(Integer number_of_flights) {
		this.number_of_flights = number_of_flights;
	}

	public String[] getSpokenLanguages() {
		return spoken_languages;
	}

	public void setSpokenLanguages(String[] spoken_languages) {
		this.spoken_languages = spoken_languages;
	}

	public crewPositionEnum getCrewPosition() {
		return crewPosition;
	}

	public void setCrewPosition(crewPositionEnum crewPosition) {
		this.crewPosition = crewPosition;
	}

}

	