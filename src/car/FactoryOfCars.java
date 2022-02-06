package car;

/**
 * Factory creating all the cars
 * 
 * @author Juan
 *
 */
public class FactoryOfCars {
	//create cars, checks the type first
	/**
	 * Create a car function
	 * 
	 * @param carType type of the car: Standard, berline or van
	 * @param coorX coordinate X of the car
	 * @param coorY coordinate Y of the car
	 * @return
	 */
	public static Car createCar(String carType, double coorX, double coorY) {
		if(carType.equalsIgnoreCase("STANDARD")){
			return new Standard(coorX,coorY);
		}else if(carType.equalsIgnoreCase("BERLINE")) {
			return new Berline(coorX,coorY);
		}else if(carType.equalsIgnoreCase("VAN")) {
			return new Van(coorX,coorY);
		}
		return null;
	}

}
