package conditions;

/**
 * Uber Black class for those kinds of rides, implementing KindOfRide
 * 
 * @author Juan
 *
 */
public class UberBlack implements KindOfRide{
	private double trafficRate;			//coef. depending on the traffic
	private double basicRate;			//coef. depending on the distance
	
	/**
	 * Empty constructor
	 */
	public UberBlack() {
		
	}
	
	@Override
	public double calculateCost(TrafficCondition traffic, double distance) {
		switch(traffic) {
		case light:
			trafficRate=1;
			break;
		case medium:
			trafficRate=1.3;
			break;
		case heavy:
			trafficRate=1.6;
			break;
		}
		if(distance<=5) {
			this.basicRate=6.2;
		}
		else if ((distance>5)&&(distance<=10)) {
			this.basicRate=5.5;
		}
		else if ((distance>10)&&(distance<=20)) {
			this.basicRate=3.25;
		}
		else if (distance>20){
			this.basicRate=2.6;
		}
		
		return basicRate*distance*trafficRate;		//returns the cost

	
	}
}

