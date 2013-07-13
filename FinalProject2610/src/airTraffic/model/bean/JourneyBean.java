package airTraffic.model.bean;

public class JourneyBean {

	private int jId;
	private double totalPrice;
	private String travelAgency;
	private String paymentType;
	private boolean travelCancelInsurance;
	private String booked_by;
	
	public String getBooked_by() {
		return booked_by;
	}
	public void setBooked_by(String booked_by) {
		this.booked_by = booked_by;
	}
	public int getjId() {
		return jId;
	}
	public void setjId(int jId) {
		this.jId = jId;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getTravelAgency() {
		return travelAgency;
	}
	public void setTravelAgency(String travelAgency) {
		this.travelAgency = travelAgency;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public boolean isTravelCancelInsurance() {
		return travelCancelInsurance;
	}
	public void setTravelCancelInsurance(boolean travelCancelInsurance) {
		this.travelCancelInsurance = travelCancelInsurance;
	}
	
}
