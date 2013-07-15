package airTraffic.model.bean;

public class AirlineBean {
	public String abbreviation;
	public String name;
	public String headquarter;
	
	public String getAbbreviation(){
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation){
		this.abbreviation = abbreviation;
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getHeadquarter(){
		return headquarter;
	}
	public void setHeadquarter(String headquarter){
		this.headquarter = headquarter;
	}

}
