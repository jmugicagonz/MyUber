package test;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import car.Berline;
import car.FactoryOfCars;
import car.Standard;
import car.Van;

class FactoryOfCarsTest {

	@Test
	void testCreateCar() {
		Standard testresultS = (Standard) FactoryOfCars.createCar("Standard", 1, 2);
		Standard resultS = new Standard(1,2);
		Berline testresultB = (Berline) FactoryOfCars.createCar("bErLiNE", 2, 1);
		Berline resultB = new Berline(2,1);
		Van testresultV = (Van) FactoryOfCars.createCar("vaN", 3, 4);
		Van resultV = new Van(3,4);
		assertTrue((resultS.getCoorX()==testresultS.getCoorX())&&(2==testresultS.getCoorY()));
		assertTrue((resultB.getCoorX()==testresultB.getCoorX())&&(resultB.getCoorY()==testresultB.getCoorY()));
		assertTrue((resultV.getCoorX()==testresultV.getCoorX())&&(resultV.getCoorY()==testresultV.getCoorY()));

		
	}

}