package test;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

import car.Car;
import car.FactoryOfCars;
import conditions.TrafficCondition;
import conditions.UberX;
import people.Customer;
import people.Driver;
import ride.Ride;
import ride.Status;

class RideTest {

	@Test
	// We initialize a ride and we see if it's well initialized and the getters are properly implemented
	void testRide() {
		Car car = FactoryOfCars.createCar("StandArd",12.3,3.8);
		Driver driver = new Driver("Daniel","Ricciardo",car);
		Customer customer = new Customer("Jorge","Nitales",11.1,3,"37217367");
		double[][] trajectory = new double[2][2];
		trajectory[0][0]=2;
		trajectory[1][0]=2;
		trajectory[0][1]=4;
		trajectory[1][1]=4;
		UberX KindRide = new UberX();
		
		Ride ride = new Ride(driver,car,customer,trajectory,Status.CONFIRMED,KindRide,TrafficCondition.light);
		
		assertTrue(car==ride.getCar());
		assertTrue(driver==ride.getDriver());
		assertTrue(customer==ride.getCustomer());
		assertTrue(trajectory.equals(ride.getTrajectory()));
		assertTrue(Status.CONFIRMED.equals(ride.getStatus()));
		assertTrue(KindRide.equals(ride.getKindOfRide()));
		assertTrue(TrafficCondition.light.equals(ride.getTraffic_condition()));	
	}

	@Test
	void testCalculateDistance() {
		Ride ride = new Ride();
		double[][] trajectory = new double[2][2];
		trajectory[0][0]=2;
		trajectory[1][0]=2;
		trajectory[0][1]=4;
		trajectory[1][1]=4;
		double result = (double) Math.sqrt(8);
		double testresult = (double) ride.calculateDistance(trajectory);
		assertTrue(result==testresult);
	}

	@Test
	void testRun() {
		Car car = FactoryOfCars.createCar("StandArd",2,2);
		Driver driver = new Driver("Daniel","Ricciardo",car);
		Customer customer = new Customer("Jorge","Nitales",2,2,"37217367");
		double[][] trajectory = new double[2][2];
		trajectory[0][0]=2;
		trajectory[1][0]=2;
		trajectory[0][1]=4;
		trajectory[1][1]=4;
		UberX KindRide = new UberX();
		
		Ride ride = new Ride(driver,car,customer,trajectory,Status.CONFIRMED,KindRide,TrafficCondition.light);
				
		Thread t0 = new Thread(ride);
		t0.start();
		try {
            t0.join();					//wait until the way is ended to continue with the code
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		System.out.println("This is finished");
	}

}