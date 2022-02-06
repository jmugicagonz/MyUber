package conditions;
/**
 * Uber X class for those kinds of rides, implementing KindOfRide
 * 
 * @author Juan
 *
 */
public class UberX implements KindOfRide{
	public double trafficRate;		//coef. depending on the traffic
	private double basicRate;		//coef. depending on the distance
	/**
	 * Empty constructor
	 */
	public UberX() {
		
	}
	
	public double calculateCost(TrafficCondition traffic, double distance) {
		switch(traffic) {
		case light:
			trafficRate=1;
			break;
		case medium:
			trafficRate=1.1;
			break;
		case heavy:
			trafficRate=1.5;
			break;
		}
		if(distance<=5) {
			basicRate=3.3;
		}
		else if ((distance>5)&&(distance<=10)) {
			basicRate=4.2;
		}
		else if ((distance>10)&&(distance<=20)) {
			basicRate=1.91;
		}
		else if (distance>20){
			basicRate=1.5;
		}
		return basicRate*distance*trafficRate;
	
	}
}
