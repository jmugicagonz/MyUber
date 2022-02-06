package people;

import java.util.ArrayList;

import ride.Ride;
import ride.Status;

/**
 * Class representing a User of the App, a customer
 * 
 * @author juan
 *
 */
public class Customer {

	private String name;
    private String surname;
    private int id;
    private double coorX;
    private double coorY;
    private String credCard;
    private ArrayList<String> messages = new ArrayList<String>();
    private int numberOfRides;
    private double amountOfCharges;
    private int totalTime;
    private double giveMark=0;
    
    private static int NUM = 1;    //variable storing number and increasing each time
    
    /**
     * Constructor that creates a customer
     * 
     * @param name Name of the customer
     * @param surname Surname of the customer
     * @param coorX coordinate X where the customer is located
     * @param coorY coordinate Y where the customer is located
     * @param credCard credit card of the customer
     */
    public Customer(String name, String surname, double coorX, double coorY, String credCard) {
    	this.name = name;
        this.surname = surname;
        this.coorX = coorX;
        this.coorY = coorY;
        this.credCard = credCard;
        this.id = NUM;
        NUM++;                        //increasing NUM to avoid having the same id twice
        numberOfRides = 0;				//all those are set to 0 with a new user
        amountOfCharges = 0;
        totalTime=0;
    }
    /**
     * 
     * @return Customer's Name
     */
    public String getName() {return this.name;}
    /**
     * 
     * @return Customer's Surname
     */
    public String getSurname() {return this.surname;}
    /**
     * 
     * @return Customer's ID
     */
    public int getId() {return this.id;}
    /**
     * 
     * @return Coordinate X where the customer is located
     */
    public double getCoorX() {return this.coorX;}
    /**
     * 
     * @return Coordinate Y where the customer is located
     */
    public double getCoorY() {return this.coorY;}
    /**
     * 
     * @return Credit Card number of the customer
     */
    public String getCreditCardNum() {return this.credCard;}
    /**
     * 
     * @return Messages that the customer has received
     */
    public ArrayList<String> getMessages() {return this.messages;}
    
    /**
     * Changes the name
     * @param name New Name of the customer
     */
    public void setName(String name) {this.name = name;}
    /**
     * Changes the surname
     * @param surname New Surname of the customer
     */
    public void setSurname(String surname) {this.surname = surname;}
    /**
     * Changes the coordinate X of the customer
     * @param coorX New coordinate X of the customer
     */
    public void setCoorX(double coorX) {this.coorX = coorX;}
    /**
     * Changes the coordinate Y of the customer
     * @param coorY New coordinate Y of the customer
     */
    public void setCoorY(double coorY) {this.coorY = coorY;}
    
    /**
     * 
     * @return Number of Rides completed by the customer
     */
	public int getNumberOfRides() {return numberOfRides;}
	/**
	 * 
	 * @return Amount of money payed by the customer in total
	 */
	public double getAmountOfCharges() {return amountOfCharges;}
	/**
	 * 
	 * @return Total Time that he customer has been on a ride
	 */
	public int getTotalTime() {return totalTime;}

	//Those will be called when a customer driver is finished
	/**
	 * Increases by one the number of rides of the customer
	 */
	public void increaseNumberOfRides() {this.numberOfRides++;}
	/**
	 * Increases the amount of money that the customer has payed
	 * @param amountOfCharges Amount of money to increase the total amount of money payed
	 */
	public void increaseAmountOfCharges(double amountOfCharges) {this.amountOfCharges += amountOfCharges;}
	/**
	 * Increases the amount of time of the customer on a ride
	 * @param totalTime Amount of time to increase the total time on a ride
	 */
	public void increaseTotalTime(int totalTime) {this.totalTime += totalTime;}
	
	/**
	 * In order to define the mark that the customer will give to the driver
	 * 
	 * @param mark Mark that he will give to the driver
	 */
	public void giveMark(double mark) {
		this.giveMark = mark;
	}
	
	@Override
	public String toString() {
		return "Customer [name=" + name + ", surname=" + surname + ", id=" + id + ", coorX=" + coorX + ", coorY="
				+ coorY + ", number of rides: " + numberOfRides + ", amount of charges: " + String.format( "%.2f",amountOfCharges) + " ]";
	}

	/**
	 * The client evaluate the ride with a mark that he has stored
	 * 
	 * @return evaluation for the driver for last ride
	 */
    public double evaluateRide() {
    	double value = 0;
    	
    	value = giveMark;
    	giveMark = 0;
    	
    	return value;
    }
    
    /**
     * Shows the customer balance (number of rides and total time spent)
     */
    public void getBalance() {
    	System.out.println("The balance of the custumer is:");
    	System.out.println("Number of rides: "+numberOfRides+"\n"+"Amount of charges: "+String.format( "%.2f",amountOfCharges)+"\n"+
    	"Total time spent: "+totalTime);
    }
    
    /**
     * When the customer wants to cancel a drive before the driver has arrived
     * @param ride Ride that we want to cancel
     */
    public void cancelRide(Ride ride) {
    	ride.setStatusRide(Status.CANCELLED);
    }
}


