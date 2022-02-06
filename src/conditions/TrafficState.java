package conditions;
import java.util.Calendar;

/**
 * Contains the traffic condition depending on the time of the day
 * 
 * @author Juan
 *
 */
public class TrafficState {
	private TrafficCondition traffic;
	private int hour;
	private int random;
	
	/**
	 * 
	 * @param start Time when we want to know the state of the traffic
	 */
	public TrafficState(Calendar start){
		hour = start.get(Calendar.HOUR_OF_DAY);
		random = (int)Math.floor(Math.random()*101);
		if((hour>22)||(hour<=7)){
			if(random<=1) {traffic = TrafficCondition.heavy;}
			else if((random>1)&&(random<=5)) {traffic = TrafficCondition.medium;}
			else if(random>5) {traffic = TrafficCondition.light;}
		}
		if((hour>7)&&(hour<=11)){
			if(random<=5) {traffic = TrafficCondition.light;}
			else if((random>5)&&(random<=25)) {traffic = TrafficCondition.medium;}
			else if(random>25) {traffic = TrafficCondition.heavy;}
		}
		if((hour>11)&&(hour<=17)){
			if(random<=15) {traffic = TrafficCondition.light;}
			else if((random>15)&&(random<=30)) {traffic = TrafficCondition.heavy;}
			else if(random>30) {traffic = TrafficCondition.medium;}
		}
		if((hour>17)&&(hour<=22)){
			if(random<=1) {traffic = TrafficCondition.light;}
			else if((random>1)&&(random<=5)) {traffic = TrafficCondition.medium;}
			else if(random>5) {traffic = TrafficCondition.heavy;}
		}
	}
	
	public TrafficState(int dayHour) {
		random = (int)Math.floor(Math.random()*101);
		hour = dayHour;
		if((hour>22)||(hour<=7)){
			if(random<=1) {traffic = TrafficCondition.heavy;}
			else if((random>1)&&(random<=5)) {traffic = TrafficCondition.medium;}
			else if(random>5) {traffic = TrafficCondition.light;}
		}
		if((hour>7)&&(hour<=11)){
			if(random<=5) {traffic = TrafficCondition.light;}
			else if((random>5)&&(random<=25)) {traffic = TrafficCondition.medium;}
			else if(random>25) {traffic = TrafficCondition.heavy;}
		}
		if((hour>11)&&(hour<=17)){
			if(random<=15) {traffic = TrafficCondition.light;}
			else if((random>15)&&(random<=30)) {traffic = TrafficCondition.heavy;}
			else if(random>30) {traffic = TrafficCondition.medium;}
		}
		if((hour>17)&&(hour<=22)){
			if(random<=1) {traffic = TrafficCondition.light;}
			else if((random>1)&&(random<=5)) {traffic = TrafficCondition.medium;}
			else if(random>5) {traffic = TrafficCondition.heavy;}
		}
	}
	
	
	/**
	 * Get the traffic condition
	 * @return the traffic condition
	 */
	public TrafficCondition getTraffic() {return traffic;}

	
}
