package conditions;
/**
 * Class to get the AverageSpeed from a Traffic Condition
 * 
 * @author Juan
 *
 */
public class AverageSpeed {
	private double averageSpeed;

	/**
	 * Constructor that creates the average speed of a ride
	 * 
	 * @param traffic light, medium or heavy, influence the average speed
	 */
	public AverageSpeed(TrafficCondition traffic){
		switch(traffic) {
		case light:
			averageSpeed=15;
			break;
		case medium:
			averageSpeed=7.3;
			break;
		case heavy:
			averageSpeed=3;
			break;
		}
		
	}
	/**
	 *Get the Average Speed
	 * @return the average speed
	 */
	public double getAverageSpeed() {return averageSpeed;}
}

