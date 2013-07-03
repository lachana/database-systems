package finalProject;

import java.sql.Date;

public abstract class Personnel {

	
	private Integer p_id;
	private String first_name;
	private String last_name;
	private Date date_of_birth;
	private Double salary;

	public Personnel(Integer p_id, String first_name, String last_name,
			Date date_of_birth, Double salary ) {
		super();
		this.p_id = p_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.date_of_birth = date_of_birth;
		this.salary = salary;
	}

	public Integer getPersID() {
		return p_id;
	}

	public void setPersID(Integer p_id) {
		this.p_id = p_id;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public Date getDateOfBirth() {
		return date_of_birth;
	}

	public void setDateOfBirth(Date date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	
	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}
}


