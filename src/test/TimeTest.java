package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import org.junit.jupiter.api.Test;

import conditions.TrafficCondition;
import time.Time;

class TimeTest {
	
	@Test
	void testTime() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(2018, 3, 24, 13, 45);
		Calendar cal2 = (Calendar) cal1.clone();
		
		Time time1 = new Time(cal1,TrafficCondition.heavy,14.6);
		Calendar[] duration = time1.getDuration();
		
		int lenght = time1.getLenght();
		cal2.add(Calendar.SECOND, lenght);
		
		assertTrue(cal1.getTime().equals(duration[0].getTime())&&cal2.getTimeInMillis()==duration[1].getTimeInMillis());
	}

	@Test
	void testGetDuration() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(2018, 3, 24, 13, 45);
		Calendar cal2 = (Calendar) cal1.clone();
		
		Time time1 = new Time(cal1,TrafficCondition.medium,14.6);
		Calendar[] duration = time1.getDuration();
		
		cal2.add(Calendar.HOUR, 2);
		
		assertTrue(cal1.getTime().equals(duration[0].getTime())&&cal2.getTimeInMillis()==duration[1].getTimeInMillis());
	}

	@Test
	void testGetLenght() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(2018, 3, 24, 13, 45);
		
		Time time1 = new Time(cal1,TrafficCondition.light,5);
		Time time2 = new Time(cal1,TrafficCondition.heavy,5);
		int result = 5*3600/15;		
		//We check the duration of the way and that the same way is shorter with light traffic
		assertTrue(result==time1.getLenght()&&time1.getLenght()<time2.getLenght());
	}
	
	@Test
	void testGetDifference() {
		Calendar cal1 = Calendar.getInstance();
		cal1.set(2018, 3, 24, 13, 45);
		Calendar cal2 = (Calendar) cal1.clone();
		
		cal2.add(Calendar.HOUR, 3);
		
		assertTrue(3*3600==Time.getDifference(cal1, cal2));
	}

}
