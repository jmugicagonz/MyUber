package conditions;

/**
 * Interface implemented by UberBlack, UberPool, UberVan and UberX
 * 
 * @author Juan
 *
 */
public interface KindOfRide {
	
	/**
	 * Calculate the cost of the ride
	 * 
	 * @param traffic traffic condition
	 * @param distance distance of the ride
	 * @return cost in € of the ride
	 */
	public double calculateCost(TrafficCondition traffic, double distance);

}
