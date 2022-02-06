package ride;

import java.util.*;

import car.Car;
import people.Customer;
import people.Driver;
/**
 * Class for storing the information of a single Ride
 * 
 * @author juan
 *
 */
public class PageBookOfRides {
	private Driver driver;
	private Car car;
	private Customer customer;
	private double[][] trajectory = new double[2][2];
	private double distance;
	private Calendar[] duration = new Calendar[2];
	private double evaluationMark;
	private double price;
	private int lenght;
	
	/**
	 * Constructor for a new object 
	 * @param driver Driver that drives during the ride
	 * @param car Car that the driver has driven
	 * @param customer Customer that has booked the ride
	 * @param trajectory Trajectory of the ride
	 * @param distance Distance of the ride
	 * @param duration Duration of the ride
	 * @param price Price of the ride
	 * @param lenght Duration in seconds of the ride
	 * @param evaluationMark evaluation of the Ride
	 */
	public PageBookOfRides(Driver driver, Car car, Customer customer, double[][] trajectory, double distance,
			Calendar[] duration, double price, int lenght,double evaluationMark){
		this.driver = driver;
		this.car = car;
		this.customer = customer;
		this.trajectory = trajectory;
		this.distance = distance;
		this.duration = duration;
		this.price=price;
		this.lenght=lenght;
		this.evaluationMark = evaluationMark;
		}
	
	/**
	 * 
	 * @return Driver that has completed the ride
	 */
	public Driver getDriver() {return this.driver;}
	/**
	 * 
	 * @return Car used for the ride
	 */
	public Car getCar() {return this.car;}
	/**
	 * 
	 * @return Customer that has completed the ride
	 */
	public Customer getCustomer() {return this.customer;}
	/**
	 * 
	 * @return Trajectory of the ride
	 */
	public double[][] getTrajectory(){return this.trajectory;}
	/**
	 * 
	 * @return Distance of the ride
	 */
	public double getDistance() {return this.distance;}
	/**
	 * 
	 * @return Duration of the ride
	 */
	public Calendar[] getDuration() {return this.duration;}
	/**
	 * 
	 * @return Evaluation Mark of the rider for the ride
	 */
	public double getEvaluationMark() {return this.evaluationMark;}
	/**
	 * 
	 * @return Price payed for the ride
	 */
	public double getPrice() {return this.price;}
	/**
	 * 
	 * @return duration in seconds of the ride
	 */
	public int getLenght() {return this.lenght;}
	
}
