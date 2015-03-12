package pl.maciejborkowski.jsonparser2;

import java.util.List;

public class Vehicle {
	public String make;
	public String model;
	public int productionYear;
        public String[] accessories; 
	public int mileage;
        private int realMileage;
	
	public Vehicle(String make, String model, int productionYear) {
		super();
		this.make = make;
		this.model = model;
		this.productionYear = productionYear;
	}
	
	public void backMileage(int mileage) {
		this.mileage -= mileage; 
	}

        public int getRealMileage() {
            return realMileage;
        }

        public void setRealMileage(int realMileage) {
            this.realMileage = realMileage;
        }     
}
