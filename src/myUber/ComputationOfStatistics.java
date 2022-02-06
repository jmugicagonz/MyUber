package myUber;
import java.util.*;

import people.Customer;
import people.Driver;
import ride.PageBookOfRides;
import sorter.SorterLeastOccupied;
import sorter.SorterMostAppreciated;
import sorter.SorterMostCharged;
import sorter.SorterMostFrequent;
/**
 * Class with the book of rides with functions to calculate statistics about customers and drivers
 * 
 * @author Juan
 *
 */
public class ComputationOfStatistics {
	//To create a copy of the actual bookOfRides
	private ArrayList<PageBookOfRides> bookOfRides = new ArrayList<PageBookOfRides>();
	/**
	 * Constructor
	 * 
	 * @param bookOfRides information about the finished drives 
	 */
	public ComputationOfStatistics(ArrayList<PageBookOfRides> bookOfRides){
		this.bookOfRides = (ArrayList<PageBookOfRides>) bookOfRides.clone();
	}
	
	/**
	 * Gets the customer's statistics (most frequent and most charged customer)
	 */
	public void customerStatistics() {
		ArrayList<Customer> bookOfCustomers = new ArrayList<Customer>();		//where we will keep the information about customers
		for(PageBookOfRides page:bookOfRides) {
			if(bookOfCustomers.contains(page.getCustomer())) {
				//do nothing if the customer is already in the ArrayList
			}else bookOfCustomers.add(page.getCustomer());
		}
		Customer mostCharged = SorterMostCharged.mostChargedCustomer(bookOfCustomers);
		Customer mostFrequent = SorterMostFrequent.mostFrequentCustomer(bookOfCustomers);
		System.out.println("The most frequent customer with: "+mostFrequent.getNumberOfRides()+" rides is: "+mostFrequent+"\n"+
		"The most charged customer with: "+String.format( "%.2f",mostCharged.getAmountOfCharges())+"€ is: "+mostCharged);
	}
	
	/**
	 * Gets the driver's statistics (least occupied and most appreciated)
	 */
	public void driverStatistics() {
		ArrayList<Driver> bookOfDrivers = new ArrayList<Driver>();	//where drivers will be saved
		for(PageBookOfRides page:bookOfRides) {
			if(bookOfDrivers.contains(page.getDriver())) {
				//do nothing if the driver is already in the ArrayList
			}else bookOfDrivers.add(page.getDriver());
		}
		Driver leastOccupied = SorterLeastOccupied.leastOccupiedDriver(bookOfDrivers);
		Driver mostAppreciated = SorterMostAppreciated.mostAppreciatedDriver(bookOfDrivers);
		System.out.println("The least occupied driver with an occupation rate: "+String.format( "%.2f",leastOccupied.getOccupationRate())+
				" is: "+leastOccupied+"\n"+"The most appreciated driver with a score of: "+
				String.format( "%.2f",mostAppreciated.getScore())+" is: "+mostAppreciated);
	}
	
	/**
	 * Print most frequent and most charged customer
	 * Print least occupied driver and the one most Appreciated
	 */
	public void computeStatistics() {
		System.out.println("The actual statistics are: ");
		customerStatistics();
		driverStatistics();
	}
}

