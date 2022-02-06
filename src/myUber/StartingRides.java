package myUber;

import ride.Ride;

/**
 * Class implementing runnable that allows us to start a ride when we run it
 * 
 * @author juan
 *
 */
public class StartingRides implements Runnable {

	private Ride ride;
	private Core core;
	
	/**
	 * Constructor
	 * 
	 * @param ride Ride that we want to complete
	 * @param core Core where all the rides happens
	 */
	public StartingRides(Ride ride,Core core) {
		this.ride=ride;
		this.core=core;
	}

	/**
	 * The ride start by starting the thread
	 */
	@Override
	public void run() {
		try {
			core.pickUpCustomer(ride);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
