package time;
import java.util.Calendar;

import conditions.AverageSpeed;
import conditions.TrafficCondition;
/**
 * Class created for allowing us to manage the Time of a ride
 * 
 * @author juan
 *
 */
public class Time {
	private Calendar start;
	private Calendar end;
	private int lenght;									//time in seconds for the difference between 2 dates
	private Calendar[] duration = new Calendar[2]; 		//new array to save the start date and the end date
	/**
	 * Constructor for Time
	 * @param start Time when the ride starts
	 * @param traffic Traffic condition when the ride is being executed
	 * @param distance Distance of the ride
	 */
	public Time(Calendar start, TrafficCondition traffic, double distance) {
		this.start=start;
		double averageSpeed= (new AverageSpeed(traffic)).getAverageSpeed(); //We calcule the average speed for our condition of traffic
		double lenghtHours = distance/averageSpeed; //We calculate the lenght of the journey in hours
		double secsDou = lenghtHours*3600; //We convert the lenght into seconds
		int secs = (int) secsDou; //We fix the precision of our value in seconds
		this.lenght = secs;
		int mins = secs/60;
		secs = secs%60;
		int hours = mins/60;
		mins = mins%60;
		//Here we will fix the end date to the sum of the start date plus the duration of the journey in the correct form
		end=(Calendar) start.clone();
		end.add(Calendar.HOUR_OF_DAY,hours);
		end.add(Calendar.MINUTE,mins);
		end.add(Calendar.SECOND,secs);
		duration[0]=start;
		duration[1]=end;
		
	}
	/**
	 * Calculates the difference of time between two dates
	 * @param start Starting Date
	 * @param end Ending Date
	 * @return Difference of time in seconds
	 */
	public static int getDifference(Calendar start, Calendar end) {
		int startd = start.get(Calendar.DAY_OF_YEAR);
		int starth = start.get(Calendar.HOUR_OF_DAY);
		int startm = start.get(Calendar.MINUTE);
		int starts = start.get(Calendar.SECOND);
		int endd = end.get(Calendar.DAY_OF_YEAR);
		int endh = end.get(Calendar.HOUR_OF_DAY);
		int endm = end.get(Calendar.MINUTE);
		int ends = end.get(Calendar.SECOND);
		
		//get all the difference in seconds
		int diffD = endd-startd;
		int diffH = endh-starth;
		int diffM = endm-startm;
		int diffS = ends-starts;
		int result = diffS + diffM*60 + diffH*60*60 + diffD*60*60*24;
		
		return result;
	}
	
	/**
	 * 
	 * @return Initial and ending date of the ride
	 */
	public Calendar[] getDuration() {return duration;}
	/**
	 * 
	 * @return Duration in seconds
	 */
	public int getLenght() {return lenght;}
	/**
	 * 
	 * @return Starting Date
	 */
	public Calendar getStart() {return start;}
	/**
	 * 
	 * @return Ending Date
	 */
	public Calendar getEnd() {return end;}
	
}
