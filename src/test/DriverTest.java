package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

import car.Car;
import car.FactoryOfCars;
import car.Standard;
import conditions.KindOfRide;
import conditions.TrafficCondition;
import conditions.UberBlack;
import conditions.UberPool;
import conditions.UberX;
import people.Customer;
import people.Driver;
import people.StateDriver;
import ride.PageBookOfRides;
import ride.Ride;
import ride.Status;

class DriverTest {

	@Test
	void testSetState() {
		Car car = FactoryOfCars.createCar("VAn", 3.6, 8.4);
		Driver driver = new Driver("Fernando","Alonso",car);
		assertTrue(driver.getState()==StateDriver.offline);
		driver.setState(StateDriver.onDuty);
		assertTrue(driver.getState()==StateDriver.onDuty);
	}

	@Test
	void testAskForRide() {
		Car car = FactoryOfCars.createCar("Berline", 3.6, 8.4);
		Driver driver = new Driver("Schumacher","Michael",car);
		Customer cust = new Customer("Juan","Mateo",3,8,"34828HG");
		double[][] trajectory = new double[2][2];
		trajectory[0][0] = 3;
		trajectory[0][1] = 8;
		trajectory[1][0] = 3.4;
		trajectory[1][1] = 8.2;
		UberBlack uberRide = new UberBlack();
		Ride ride = new Ride(driver,car,cust,trajectory,Status.UNCONFIRMED,uberRide,TrafficCondition.light);
		assertTrue(driver.askForRide("UberBlack", 3.4, 8.2, 20.4, ride));
		driver.setState(StateDriver.onARide);
		assertFalse(driver.askForRide("UberBlack", 3, 8.3, 20, ride));
		driver.setState(StateDriver.onDuty);
		Car car2 = FactoryOfCars.createCar("Standard", 3.6, 8.4);
		Driver driver2 = new Driver("name","surname",car2);
		UberX uberRide1 = new UberX();
		UberPool uberRide2 = new UberPool();
		driver2.setState(StateDriver.onDuty);
		Ride ride2 = new Ride(driver2,car2,cust,trajectory,Status.UNCONFIRMED,uberRide2,TrafficCondition.light);
		Ride ride1 = new Ride(driver2,car2,cust,trajectory,Status.UNCONFIRMED,uberRide1,TrafficCondition.light);
		driver2.setRide(ride1);
		//they ask for an uberPool but there is already an UberX in progress => reject
		assertFalse(driver2.askForRide("UberPool", 3, 8.3, 20, ride2));
		//they ask for an uberPool and there is already an UberPool in progress => accept
		driver2.eliminateRide(ride1);
		driver2.setRide(ride2);
		assertTrue(driver2.askForRide("UberPool", 3, 8.3, 20, ride2));

	}

	@Test
	void testGetBalance() {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		Calendar cal3 = Calendar.getInstance();
		Calendar cal4 = Calendar.getInstance();
		Calendar cal5 = Calendar.getInstance();
		Calendar cal6 = Calendar.getInstance();
		
		Calendar[] interval = new Calendar[2];
		
		cal1.set(2018, 3, 24, 13, 45);
		cal2.set(2018, 6, 24, 13, 45);
		cal3.set(2018, 3, 24, 13, 42);
		cal4.set(2018, 5, 24, 13, 45);
		cal5.set(2018, 6, 10, 14, 40);
		cal6.set(2018, 6, 24, 13, 44);

		interval[0]=cal1;
		interval[1]=cal2;
		
		Car car = new Standard(1,2);
		Driver driver = new Driver("Juan","Mateo",car);
		Customer customer = new Customer("Pepe","Bottle",1.1,2.2,"2849HGH");
		double[][] trajectory = new double[2][2];
		trajectory[0][0]=1.1;
		trajectory[1][0]=2.2;
		trajectory[0][1]=15.0;
		trajectory[1][1]=20.5;
		KindOfRide uberBlack = new UberBlack();
		
		Ride ride1 = new Ride(driver, car, customer, trajectory,  Status.ONGOING,uberBlack, TrafficCondition.heavy);
		Ride ride2 = new Ride(driver, car, customer, trajectory,  Status.ONGOING,uberBlack, TrafficCondition.heavy);
		Ride ride3 = new Ride(driver, car, customer, trajectory,  Status.ONGOING,uberBlack, TrafficCondition.heavy);
		Ride ride4 = new Ride(driver, car, customer, trajectory,  Status.ONGOING,uberBlack, TrafficCondition.heavy);
		
		ride1.setStartTime(cal3);
		ride2.setStartTime(cal4);
		ride3.setStartTime(cal5);
		ride4.setStartTime(cal6);
		
		PageBookOfRides page1 = new PageBookOfRides(driver,car,customer,trajectory,ride1.getDistance(),ride1.getDuration(),ride1.getPrice(),ride1.getLenght(),4.5);
		PageBookOfRides page2 = new PageBookOfRides(driver,car,customer,trajectory,ride2.getDistance(),ride2.getDuration(),ride2.getPrice(),ride2.getLenght(),4.5);
		PageBookOfRides page3 = new PageBookOfRides(driver,car,customer,trajectory,ride3.getDistance(),ride3.getDuration(),ride3.getPrice(),ride3.getLenght(),4.5);
		PageBookOfRides page4 = new PageBookOfRides(driver,car,customer,trajectory,ride4.getDistance(),ride4.getDuration(),ride4.getPrice(),ride4.getLenght(),4.5);
		
		ArrayList<PageBookOfRides> bookOfRides = new ArrayList<PageBookOfRides>();
		bookOfRides.add(page1);
		bookOfRides.add(page2);
		bookOfRides.add(page3);
		bookOfRides.add(page4);

		driver.getBalance(interval, bookOfRides);
		int testresult1 = driver.getNumberOfRidesInterval();
		int result1 = 2;
		assertTrue(testresult1==result1);

		
		
	}

}
