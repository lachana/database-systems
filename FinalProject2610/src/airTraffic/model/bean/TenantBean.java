package airTraffic.model.bean;

import java.util.Date;

public class TenantBean {
	
	private int tId;
	private boolean isActive;
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	private String bankAccount;
	private Date rentPaid;
	
	public int gettId() {
		return tId;
	}
	public void settId(int tId) {
		this.tId = tId;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public Date getRentPaid() {
		return rentPaid;
	}
	public void setRentPaid(Date rentPaid) {
		this.rentPaid = rentPaid;
	}
}
