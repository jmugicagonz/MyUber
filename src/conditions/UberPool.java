package conditions;
/**
 * Uber Pool class for those kinds of rides, implementing KindOfRide
 * 
 * @author Juan
 *
 */
public class UberPool implements KindOfRide{
	private double trafficRate;			//coef. depending on the traffic
	private double basicRate;			//coef. depending on the distance
	
	/**
	 * Empty constructor
	 */
	public UberPool() {
		
	}
	
	@Override
	public double calculateCost(TrafficCondition traffic, double distance) {
		switch(traffic) {
			case light:
				trafficRate=1;
				break;
			case medium:
				trafficRate=1.1;
				break;
			case heavy:
				trafficRate=1.2;
				break;
		}
		if(distance<=5) {
			this.basicRate=2.4;
		}
		else if ((distance>5)&&(distance<=10)) {
			this.basicRate=3;
		}
		else if ((distance>10)&&(distance<=20)) {
			this.basicRate=1.3;
		}
		else if (distance>20){
			this.basicRate=1.1;
		}
		return basicRate*distance*trafficRate;
	
	}
}
