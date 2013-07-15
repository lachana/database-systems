package airTraffic.model.bean;

public class RunwayBean {
	
	private int rId;
	private boolean isAvailable;
	private String number;
	private String runwayOf;
	
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getRunwayOf() {
		return runwayOf;
	}
	public void setRunwayOf(String runwayOf) {
		this.runwayOf = runwayOf;
	}
}
