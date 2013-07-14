package de.tum.labcourse.finalproject;

public class AirplaneBean {
	private int a_id;
	private String type;
	private int first_class_seats;
	private int business_class_seats;
	private int economy_class_seats;

	
	public int getA_id(){
		return a_id;
	}
		public void setA_id(int a_id){
			this.a_id = a_id;
		}
		
		public String getType(){
			return type;
		}
			public void setType(String type){
				this.type = type;
			}
			public int getFirst_class_seats(){
				return first_class_seats;
			}
				public void setFirst_class_seats(int first_class_seats){
					this.first_class_seats = first_class_seats;
				}
				public int getBusiness_class_seats(){
					return business_class_seats;
				}
					public void setBusiness_class_seats(int business_class_seats){
						this.business_class_seats = business_class_seats;
					}
					public int getEconomy_class_seats(){
						return economy_class_seats;
					}
						public void setEconomy_class_seats(int economy_class_seats){
							this.economy_class_seats = economy_class_seats;
						}
	}




