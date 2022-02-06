package test;

import java.util.ArrayList;

import car.Car;
import car.FactoryOfCars;
import people.Customer;
import people.Driver;
import people.StateDriver;

public class TestCreateCars {

	public static void main(String[] args) {
		
		ArrayList<Car> cars = new ArrayList<Car>();
		
		for(int i=0;i<5;i++) {
			cars.add(FactoryOfCars.createCar("Van",12.2,5.6));
			cars.add(FactoryOfCars.createCar("StaNDard",12.2,5.6));
			cars.add(FactoryOfCars.createCar("BerliNE",12.2,5.6));
		}
		
		
		
		System.out.println(cars);
		
		Driver driver1 = new Driver("Fernando", "Alonso", cars.get(0));
		driver1.setState(StateDriver.onDuty);
		
		System.out.println(driver1);
		
		Customer customer1 = new Customer("David","Moreno",12.4,5.0,"1232478X");
		System.out.println(customer1);
	}
	
}
