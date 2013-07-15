package airTraffic.model.bean;

public class ShoppingServiceBean {

	private int sId;
	private String name;
	private String assignment;
	private int size;
	private int areaNumber;
	private String location;
	private String offeredBy;
	
	public int getsId() {
		return sId;
	}
	public void setsId(int sId) {
		this.sId = sId;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		
	}
	public String getAssignment() {
		return assignment;
	}
	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getAreaNumber() {
		return areaNumber;
	}
	public void setAreaNumber(int areaNumber) {
		this.areaNumber = areaNumber;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getOfferedBy() {
		return offeredBy;
	}
	public void setOfferedBy(String offeredBy) {
		this.offeredBy = offeredBy;
	}
}
