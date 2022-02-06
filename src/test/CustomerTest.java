package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import people.Customer;

class CustomerTest {

	@Test
	void testGetNumberOfRides() {
		Customer customer = new Customer("Aitor","Menta",0.5,9.7,"859347905");
		customer.increaseNumberOfRides();
		customer.increaseNumberOfRides();
		customer.increaseNumberOfRides();
		int testresult = customer.getNumberOfRides();
		int result = 3;
		assertTrue(testresult==result);
		
	}

	@Test
	void testGetAmountOfCharges() {
		Customer customer = new Customer("Aitor","Tilla",0.5,9.7,"859347905");
		customer.increaseAmountOfCharges(12.3);
		customer.increaseAmountOfCharges(2.95);
		customer.increaseAmountOfCharges(34.60);		
		double testresult = customer.getAmountOfCharges();
		double result = 12.3+2.95+34.6;
		assertTrue(testresult==result);
	}

	@Test
	void testGetTotalTime() {
		Customer customer = new Customer("Aitor","Nado",0.5,9.7,"859347905");
		customer.increaseTotalTime(1200);
		customer.increaseTotalTime(9876);
		customer.increaseTotalTime(10000);
		int testresult = customer.getTotalTime();
		int result = 1200+9876+10000;
		assertTrue(testresult==result);
	}
	
	@Test
	void testEvaluateRide() {
		Customer customer = new Customer("Doctor","Jones",0.5,9.7,"859347905");
		customer.giveMark(4);
		assertTrue(4==customer.evaluateRide());
	}
}