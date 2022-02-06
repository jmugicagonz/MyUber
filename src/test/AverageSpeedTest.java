package test;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import conditions.AverageSpeed;
import conditions.TrafficCondition;

class AverageSpeedTest {

	@Test
	void testGetAverageSpeed() {
		AverageSpeed asl = new AverageSpeed(TrafficCondition.light);
		AverageSpeed asm = new AverageSpeed(TrafficCondition.medium);
		AverageSpeed ash = new AverageSpeed(TrafficCondition.heavy);
		
		
		double testresultl = asl.getAverageSpeed();
		double testresultm = asm.getAverageSpeed();
		double testresulth = ash.getAverageSpeed();
		
		double resultl = 15;
		double resultm = 7.3;
		double resulth = 3;

		assertTrue(resultl==testresultl);
		assertTrue(resultm==testresultm);
		assertTrue(resulth==testresulth);





	}

}
