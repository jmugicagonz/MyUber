package people;

import java.util.ArrayList;
import java.util.Calendar;

import car.Car;
import conditions.UberX;
import ride.PageBookOfRides;
import ride.Ride;
import time.Time;
/**
 * Class representing a Driver
 * 
 * @author juan
 *
 */
public class Driver {
	private String name;
	private String surname;
	private int id;
	private StateDriver state;
	private int numScores = 0;			//total number of times that a driver has been evaluated
	private double totalScore = 0;		//total score gotten by the drivers
	private Car car = null;					//car of the driver
    private int numberOfRides=0;
    private double amountOfCharges=0;		//money earned by his drives
    private int totalTimeOnRide=0;		//total time on a ride
    private int totalTimeActiveWaiting=0;
    private int totalTimeOnPause=0;
    private double occupationRate; 		//totalTimeOnRide/totalTimeActiveWaiting
    
    private int totalTimeInterval=0;				//in a interval of time for the balance function
    private int numberOfRidesInterval=0;			//total rides in a interval
    private double amountOfChargesInterval=0;		//total amount of money spent in a interval
    
    private ArrayList<Ride> rides = new ArrayList<Ride>();		//rides that the driver has in progress or in the queue
    															//useful for uberPool
	private Calendar startTime =Calendar.getInstance();
	private Calendar endTime = (Calendar) startTime.clone();
	
	private static int NUM = 1;		//variable storing number and increasing each time
	
	/**
	 * Constructor that creates a new driver
	 * @param name Name of the driver
	 * @param surname Surname of the driver
	 * @param car Car that the driver will use
	 */
	public Driver(String name, String surname, Car car) {
		this.name = name;
		this.surname = surname;
		this.state = StateDriver.offline;
		this.car = car;
		this.id = NUM;
		NUM++;
	}
	/**
	 * 
	 * @return number of rides completed by the driver
	 */
	public int getNumberOfRides() {return numberOfRides;}
	/**
	 * 
	 * @return amount of money earned by the driver
	 */
	public double getAmountOfCharges() {return amountOfCharges;}
	/**
	 * 
	 * @return Total time that the Driver has been on a ride
	 */
	public int getTotalTimeOnRide() {return totalTimeOnRide;}
	/**
	 * 
	 * @return Total time that the Driver has been active but waiting for a ride
	 */
	public int getTotalTimeActiveWaiting() {return this.totalTimeActiveWaiting;}
	/**
	 * 
	 * @return Name of the driver
	 */
	public String getName() {return this.name;}
	/**
	 * 
	 * @return Surname of the Driver
	 */
	public String getSurname() {return this.surname;}
	/**
	 * 
	 * @return Driver's ID
	 */
	public int getId() {return this.id;}
	/**
	 * 
	 * @return State of the driver
	 */
	public StateDriver getState() {return this.state;}
	/**
	 * Calculates the average score of the driver and returns its value
	 * @return Average score of the driver
	 */
	public double getScore() {
		if(this.numScores==0) {return 0;}
		return (this.totalScore/this.numScores);		//score out of 5
		}
	/**
	 * 
	 * @return The driver's Car
	 */
	public Car getCar() {return this.car;}
	/**
	 * 
	 * @return The rides that the driver has to do
	 */
	public ArrayList<Ride> getRides() {return this.rides;}
	/**
	 * For getting the number of rides in an interval previously selected
	 * @return number of rides in an interval previously selected 
	 */
	public int getNumberOfRidesInterval() {return this.numberOfRidesInterval;}
	/**
	 * For getting the amount of charges in an interval previously selected
	 * @return the amount of charges in an interval previously selected
	 */
	public double getAmountOfChargesInterval() {return this.amountOfChargesInterval;}
	/**
	 * For getting the total time of an interval previously selected
	 * @return total time of an interval previously selected
	 */
	public int getTotalTimeInterval() {return this.totalTimeInterval;}
	
	/**
	 * Calculates the occupation rate of the driver and returns its value
	 * @return Occupation rate of the driver
	 */
	public double getOccupationRate() {
		if((this.totalTimeActiveWaiting+this.totalTimeOnRide)==0) return 0;
		this.occupationRate = ((double)this.totalTimeOnRide)/(this.totalTimeActiveWaiting+this.totalTimeOnRide);
		return occupationRate;
	}
	/**
	 * Calculates the activity rate of the driver and returns its value
	 * @return Activity rate of the driver
	 */
	public double getActivityRate() {
		if(this.totalTimeActiveWaiting+this.totalTimeOnPause+this.totalTimeOnRide==0) return 0;
		return (((double)this.totalTimeOnRide+this.totalTimeActiveWaiting)/(this.totalTimeActiveWaiting+this.totalTimeOnRide+this.totalTimeOnPause));
	}

	/**
	 * 
	 * @param name New name of the Driver
	 */
	public void setName(String name) {this.name = name;}
	/**
	 * 
	 * @param surname New name of the Driver
	 */
	public void setSurname(String surname) {this.surname = surname;}
	/**
	 * Adds a new ride to the list
	 * @param ride Ride that the Driver will have to do
	 */
	public void setRide(Ride ride) {this.rides.add(ride);}				//when a ride will start or in the queue
	/**
	 * When a ride is finished he eliminates from the rides that he has to do
	 * @param ride Ride that we want to eliminate (finished)
	 */
	public void eliminateRide(Ride ride) {this.rides.remove(ride);}		//when a ride is finished or canceled
	
	/**
	 * Just created for test purposes
	 * @param occupationRate New occupation rate for the driver
	 */
	public void setOccupationRate(double occupationRate) {
		this.totalTimeOnRide = (int) (1000*occupationRate);
		this.totalTimeActiveWaiting = (1000-this.totalTimeOnRide);
	}
	
	/**
	 * The driver gets a new score for a Ride
	 * @param rate Score that the driver has received on his last ride
	 */
	public void addScore(double rate) {
		this.totalScore+=rate;
		this.numScores++;
	}
	/**
	 * Increase the number of rides by 1 when the ride is finished
	 */
	public void increaseRide() {
		this.numberOfRides++;
	}
	/**
	 * Increase the amount of charges 
	 * @param ammountCharged amount that we want to increase
	 */
	public void increaseAmmountCharged(double ammountCharged) {
		this.amountOfCharges += ammountCharged;
	}
	
	/**
	 * when driving a uberPool, to see if there are still some customers inside the car
	 * @return true if he still have to finish a ride // false if he has already finished and the car is empty of customers
	 */
	public boolean anyRidesLeft() {
		if(this.rides.isEmpty()) {
			return false;
		}
		return true;
	}
	/**
	 * To check if the ride is in the queue but not the ride that the driver is already doing
	 * @param ride Ride that we want to know about
	 * @return if the Ride is in the queue of the driver return true
	 */
	public boolean pickingUberPoolCust(Ride ride) {
		if(this.rides.contains(ride)) {
			if(this.getNextRide().equals(ride)) return false;
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @return Next Ride that the driver has to do
	 */
	public Ride getNextRide() {
		if(anyRidesLeft()) {
			return this.rides.get(0);
		}else {
			return null;
		}
	}

	/**
	 * To change the status of the driver for when he is on a Pause, on a ride, offline...
	 * @param state New State of the driver
	 */
	public void setState(StateDriver state) {
		if(this.getCar() == null) return;
		if(this.state.equals(state)) return;		//not allow changing to the same state that the driver was before
		if(state.equals(StateDriver.offline)) {		
			car.setActive(false);					//liberate the car
			this.endTime = Calendar.getInstance();
			if(this.state.equals(StateDriver.onDuty)) {
				this.totalTimeActiveWaiting += Time.getDifference(startTime, endTime);
			}else {
				this.totalTimeOnPause += Time.getDifference(startTime, endTime);
			}
			this.state = state;						//change to the new state
			return;
		}
		if(this.state.equals(StateDriver.offline)&&car.isActive()) {
			System.out.println("Car already in use. Driver "+ this.getId()+ " cannot be on duty.\n");
			return;
		}
		else {
			if(this.getState().equals(StateDriver.onARide)){
				this.endTime = Calendar.getInstance();
				this.totalTimeOnRide += Time.getDifference(startTime, endTime);
				this.startTime = Calendar.getInstance();
			}else if(this.getState().equals(StateDriver.onDuty)) {
				this.endTime = Calendar.getInstance();
				this.totalTimeActiveWaiting += Time.getDifference(startTime, endTime);
				this.startTime = Calendar.getInstance();
			}else if(this.getState().equals(StateDriver.offDuty)) {
				this.endTime = Calendar.getInstance();
				this.totalTimeOnPause += Time.getDifference(startTime, endTime);
				this.startTime = Calendar.getInstance();
			}else {
				this.startTime = Calendar.getInstance();
			}
			
			this.state = state;
			car.setActive(true);
			
		}
	}

	/**
	 * Client ask him for a ride and he has to decide if doing it or not
	 * Right now he accepts if he is available
	 * @param type Type of ride
	 * @param coorX coordinate X of the destination
	 * @param coorY coordinate Y of the destination
	 * @param price price that he will earn for the ride
	 * @param ride ride that he will have to complete
	 * @return true if he accepts // false if he rejects
	 */
	public boolean askForRide(String type,double coorX,double coorY,double price,Ride ride) {
		//we check if there is already someone in the car
		if(this.anyRidesLeft()) {
			//if we this ride is a UberX we don't allow more people (black and van are rejected after by the type of car)
			if(this.getNextRide().getKindOfRide() instanceof UberX) {
				return false;
			}
		}if(type.equalsIgnoreCase("UberPool")) {
			//do nothing, this is just to skip next condition 
		}
		//if they are not asking for an UberPool the driver should not accept if he is on a ride
		else if(this.state.equals(StateDriver.onARide)) {
			return false;
		}
		//the type of car for each way and the driver's car should match, if not reject
		if(ride.getCar().getType()!=this.getCar().getType()) {
			return false;
		}
		//all conditions are ok, return true
		return true;
		}
	
	@Override
	public String toString() {
		return "Driver [name=" + name + ", surname=" + surname + ", id=" + id + ", state=" + state + ", car=" + car
				+ ", number of rides: "+numberOfRides+"\n"+", amount of charges: "+String.format( "%.2f",amountOfCharges) + 
				", occupation rate: "+String.format( "%.2f",this.getOccupationRate())+", driver Mark: " + this.getScore() + "]";
	}
	
	/**
	 * 
	 * @param interval interval of time when we want to know driver's statistics
	 * @param bookOfRides Book Of Rides where we keep the information about all the finished rides
	 */
    public void getBalance(Calendar[] interval, ArrayList<PageBookOfRides> bookOfRides) {
    	this.numberOfRidesInterval=0;
    	this.amountOfChargesInterval=0;
    	this.totalTimeInterval=0;
    	for(PageBookOfRides page:bookOfRides) {
    		if((interval[0].before(page.getDuration()[0]))&&(interval[1].after(page.getDuration()[1]))) {
    			this.numberOfRidesInterval++;
    			this.amountOfChargesInterval+=page.getPrice();
    			this.totalTimeInterval+=page.getLenght();
    			
    		}
    	}
    	System.out.println("The balance of the driver for the interval of time selected is:");
    	System.out.println("Number of rides: "+numberOfRidesInterval+"\n"+"Amount of charges: "+String.format( "%.2f",amountOfChargesInterval)
    	+"\n"+"Total time spent: "+totalTimeInterval);
    }
    
    public void getTotalBalance() {
    	System.out.println("The total balance of the driver is:");
    	System.out.println("Number of rides: "+numberOfRides+"\n"+"Amount of charges: "+String.format( "%.2f",amountOfCharges)+"\n"+
    	"Occupation rate: "+this.getOccupationRate()+"\nDriver Mark: " + this.getScore());
    }

	
}

