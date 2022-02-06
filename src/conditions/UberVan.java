package conditions;
/**
 * Uber Van class for those kinds of rides, implementing KindOfRide
 * 
 * @author Juan
 *
 */
public class UberVan implements KindOfRide{
	private double trafficRate;		//coef. depending on the traffic
	private double basicRate;		//coef. depending on the distance
	
	/**
	 * Empty constructor
	 */
	public UberVan() {}
	
	@Override
	public double calculateCost(TrafficCondition traffic, double distance) {
		switch(traffic) {
		case light:
			this.trafficRate=1;
			break;
		case medium:
			this.trafficRate=1.5;
			break;
		case heavy:
			this.trafficRate=1.8;
			break;
		}
		if(distance<=5) {
			this.basicRate=6.2;
		}
		else if ((distance>5)&&(distance<=10)) {
			this.basicRate=7.7;
		}
		else if ((distance>10)&&(distance<=20)) {
			this.basicRate=3.25;
		}
		else if (distance>20){
			this.basicRate=2.6;
		}
		return basicRate*distance*trafficRate;
	
	}
}
