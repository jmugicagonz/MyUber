
package test;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import conditions.KindOfRide;
import conditions.TrafficCondition;
import conditions.UberBlack;
import conditions.UberPool;
import conditions.UberVan;
import conditions.UberX;

class KindOfRideTest {

	@Test
	void testCalculateCost() {
		KindOfRide ub = new UberBlack();
		KindOfRide up = new UberPool();
		KindOfRide uv = new UberVan();
		KindOfRide ux = new UberX();
		
		double testresultb = ub.calculateCost(TrafficCondition.heavy, 7.5);
		double testresultp = up.calculateCost(TrafficCondition.medium, 14.375);
		double testresultv = uv.calculateCost(TrafficCondition.light, 2.125);
		double testresultx = ux.calculateCost(TrafficCondition.heavy, 40);
		
		double resultb = 7.5*1.6*5.5;
		double resultp = 14.375*1.1*1.3;
		double resultv = 2.125*1*6.2;
		double resultx = 40*1.5*1.5;
		
		assertTrue(resultb==testresultb);
		assertTrue(resultp==testresultp);
		assertTrue(resultv==testresultv);
		assertTrue(resultx==testresultx);
		}
}
