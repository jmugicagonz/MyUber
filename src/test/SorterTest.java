package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import car.Car;
import car.FactoryOfCars;
import people.Customer;
import people.Driver;
import sorter.SorterLeastOccupied;
import sorter.SorterMostAppreciated;
import sorter.SorterMostCharged;
import sorter.SorterMostFrequent;

class SorterTest {

	@Test
	void testSortC() {
		Customer cust1 = new Customer("Disfatt", "Bidge", 1, 6, "7776281");
		Customer cust2 = new Customer("Diddy", "Doodat", 6, 5.4, "8876281");
		Customer cust3 = new Customer("Jabreakit", "Jubawdit", 1.2, 3.7, "9976281");
		ArrayList<Customer> customers = new ArrayList<Customer>();
		customers.add(cust1);
		customers.add(cust2);
		customers.add(cust3);
		
		cust1.increaseNumberOfRides();
		cust1.increaseNumberOfRides();
		cust2.increaseNumberOfRides();
		cust2.increaseNumberOfRides();
		cust2.increaseNumberOfRides();
		cust3.increaseNumberOfRides();
		
		assertTrue(cust2==SorterMostFrequent.mostFrequentCustomer(customers));
		
		SorterMostFrequent sortFreq = new SorterMostFrequent();
		ArrayList<Customer> freqResult = sortFreq.sortC(customers);
		
		assertTrue(cust2==freqResult.get(0)&&cust1==freqResult.get(1)&&cust3==freqResult.get(2));
		
		cust1.increaseAmountOfCharges(200);
		cust2.increaseAmountOfCharges(100);
		cust3.increaseAmountOfCharges(300);
		
		assertTrue(cust3==SorterMostCharged.mostChargedCustomer(customers));
		
		SorterMostCharged sortCharged = new SorterMostCharged();
		ArrayList<Customer> chargedResult = sortCharged.sortC(customers);
		
		assertTrue(cust3==chargedResult.get(0)&&cust1==chargedResult.get(1)&&cust2==chargedResult.get(2));
	}

	@Test
	void testSortD() {
		Car car = FactoryOfCars.createCar("Van", 12.2, 8);
		Driver driver1 = new Driver("Carlos","Sainz",car);
		Driver driver2 = new Driver("Aitor","Menta",car);
		Driver driver3 = new Driver("Ben","Dover",car);
		ArrayList<Driver> drivers = new ArrayList<Driver>();
		drivers.add(driver1);
		drivers.add(driver2);
		drivers.add(driver3);
		
		driver1.addScore(4);
		driver1.addScore(3);
		driver2.addScore(2);
		driver2.addScore(4);
		driver3.addScore(1);
		
		assertTrue(driver1==SorterMostAppreciated.mostAppreciatedDriver(drivers));
		
		SorterMostAppreciated sortApprec = new SorterMostAppreciated();
		ArrayList<Driver> apprecResult = sortApprec.sortD(drivers);
		
		assertTrue(driver1==apprecResult.get(0)&&driver2==apprecResult.get(1)&&driver3==apprecResult.get(2));
		
		driver1.setOccupationRate(0.3);
		driver2.setOccupationRate(0.2);
		driver3.setOccupationRate(0.5);
		
		assertTrue(driver2==SorterLeastOccupied.leastOccupiedDriver(drivers));
		
		SorterLeastOccupied sortOcc = new SorterLeastOccupied();
		ArrayList<Driver> occResult = sortOcc.sortD(drivers);
				
		assertTrue(driver2==occResult.get(0)&&driver1==occResult.get(1)&&driver3==occResult.get(2));
	}

}
