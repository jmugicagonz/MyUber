package ride;
import java.util.Calendar;

import car.Car;
import conditions.KindOfRide;
import conditions.TrafficCondition;
import people.Customer;
import people.Driver;
import time.Time;
/**
 * Class that represents every ride done
 * 
 * @author juan
 *
 */
public class Ride implements Runnable {
	private Driver driver;
	private Car car;
	private Customer customer;
	private double[][] trajectory = new double[2][2];		//[0][0]=X1,[1][0]=Y1 //[0][1]=X2,[1][1]=Y2
	private double distance;	
	private Calendar[] duration = new Calendar[2];			//dutation in Calendar format
	private Status status; 									//Variable of type enum which define the status
	private TrafficCondition trafficCondition;
	private double evaluationMark;							//evaluation of the ride
	private double price;	
	private int lenght;										//time of the ride in seconds
	private KindOfRide kindOfRide;							//UberX,UberPool,UberBlack or UberVan
	private int numOfPeople;								//numOfPeople, important only for UberPool
	private Time time;
	/**
	 * Empty constructor for testing
	 */
	public Ride() {}
	
	/**
	 * Constructor for the Ride class
	 * @param driver Driver that performs the ride
	 * @param car Car that will be driven
	 * @param customer Customer that booked the ride
	 * @param trajectory Trajectory of the ride (initial => X[0][0], Y[1][0] and final point => X2[0][1], Y2[1][1])
	 * @param status Status of the ride
	 * @param kindOfRide Kind of Ride (UberX, UberPool, UberBlack, UberVan)
	 * @param trafficCondition Traffic condition when the ride is executed
	 */
	public Ride(Driver driver, Car car, Customer customer, double[][] trajectory,  Status status,
			KindOfRide kindOfRide, TrafficCondition trafficCondition) {
		this.driver=driver;
		this.car=car;
		this.customer=customer;
		this.trajectory=trajectory;
		this.distance=calculateDistance(trajectory);
		this.status=status;
		this.trafficCondition=trafficCondition;
		this.kindOfRide=kindOfRide;
		price = kindOfRide.calculateCost(trafficCondition, this.distance);
		Calendar cal= Calendar.getInstance();
		time = new Time(cal,trafficCondition,this.getDistance());
		this.lenght = this.time.getLenght();
	}
	/**
	 * Another constructor with number of people for the UberPool
	 * @param driver Driver that performs the ride
	 * @param car Car that will be driven
	 * @param customer Customer that booked the ride
	 * @param trajectory Trajectory of the ride (initial => X[0][0], Y[1][0] and final point => X2[0][1], Y2[1][1])
	 * @param status Status of the ride
	 * @param kindOfRide Kind of Ride (UberX, UberPool, UberBlack, UberVan)
	 * @param trafficCondition Traffic condition when the ride is executed
	 * @param numOfPeople number of people in that ride
	 */
	public Ride(Driver driver, Car car, Customer customer, double[][] trajectory, Status status,
			KindOfRide kindOfRide, TrafficCondition trafficCondition, int numOfPeople) {
		this.driver=driver;
		this.car=car;
		this.customer=customer;
		this.trajectory=trajectory;
		this.distance=calculateDistance(trajectory);
		this.status=status;
		this.trafficCondition=trafficCondition;
		this.kindOfRide=kindOfRide;
		price = kindOfRide.calculateCost(trafficCondition, this.distance);
		this.numOfPeople = numOfPeople;
		Calendar cal= Calendar.getInstance();
		time= new Time(cal,trafficCondition,this.getDistance());
		this.lenght = this.time.getLenght();
	}
	
	//calculate the distance between the initial and the final point getting the trajectory
	/**
	 * Calculate the distance between the initial and the final point getting the trajectory
	 * @param trajectory Trajectory of the ride (initial => X[0][0], Y[1][0] and final point => X2[0][1], Y2[1][1])
	 * @return The distance between initial and final point
	 */
	public double calculateDistance(double[][] trajectory) {
		double distance = Math.sqrt((Math.pow(trajectory[1][1]-trajectory[1][0], 2))+(Math.pow(trajectory[0][1]-trajectory[0][0], 2)));
		return distance;
	}
	/**
	 * 
	 * @return Driver of the ride
	 */
	public Driver getDriver() {return driver;}
	/**
	 * 
	 * @return Car for the ride
	 */
	public Car getCar() {return car;}
	/**
	 * 
	 * @return Customer that booked the ride
	 */
	public Customer getCustomer() {return customer;}
	/**
	 * 
	 * @return duration of the ride
	 */
	public Calendar[] getDuration() {return duration;}
	/**
	 * 
	 * @return Status of the ride
	 */
	public Status getStatus() {return status;}
	/**
	 * 
	 * @return Traffic condition when the ride was executed
	 */
	public TrafficCondition getTraffic_condition() {return trafficCondition;}
	/**
	 * 
	 * @return Evaluation mark of the ride
	 */
	public double getEvaluationMark() {return evaluationMark;}
	/**
	 * 
	 * @return Trajectory of the ride
	 */
	public double[][] getTrajectory() {return trajectory;}	
	/**
	 * 
	 * @return Distance of the ride
	 */
	public double getDistance() {return distance;}
	/**
	 * 
	 * @return Distance of the ride
	 */
	public double getPrice() {return price;}
	/**
	 * 
	 * @return Duration in seconds of the ride
	 */
	public int getLenght() {return this.lenght;}
	/**
	 * 
	 * @return Kind of ride (UberX, UberPool, UberBlack, UberVan)
	 */
	public KindOfRide getKindOfRide() {return this.kindOfRide;}
	/**
	 * 
	 * @return Places available for the ride
	 */
	public int getPlacesPoolAvailable() {return (4-this.numOfPeople);}		//places available
	/**
	 * 
	 * @return Time when the ride was started
	 */
	public Time getTime() {return this.time;}

	/**
	 * 
	 * @param trafficCondition Actual traffic condition for the ride
	 */
	public void setTraffic_condition(TrafficCondition trafficCondition) {this.trafficCondition = trafficCondition;}
	/**
	 * 
	 * @param evaluationMark Evaluation Mark for the driver for the ride
	 */
	public void setEvaluationMark(double evaluationMark) {this.evaluationMark = evaluationMark;}
	/**
	 * 
	 * @param trajectory New trajectory for the ride
	 */
	public void setTrajectory(double[][] trajectory) {this.trajectory = trajectory;}
	/**
	 * 
	 * @param cal Time when the ride starts
	 */
	public void setTime(Calendar cal) {this.time=new Time(cal,this.trafficCondition,this.distance);}

	/**
	 * 
	 * @param status New status of the ride
	 */
	public void setStatusRide(Status status) {this.status = status;}		//change the status of a ride

	/**
	 * Associate the start and end time to duration and get the length if the ride in seconds
	 * @param start Time when the ride has started
	 */
	public void setStartTime(Calendar start) {
		Time time = new Time(start,trafficCondition,distance);
		duration[0]=start;
		duration[1]=time.getEnd();
		this.lenght = time.getLenght();
	}

	//the ride starts
	@Override
	public void run() {
		try {
			//Just to PickUp the Client
			if(this.status==Status.CONFIRMED) {
				Calendar cal1 = Calendar.getInstance();
				Time timeToPickUp = new Time(cal1,this.trafficCondition,this.distance);
				int durationToPickUp = timeToPickUp.getLenght();
				Thread.sleep(durationToPickUp);
			}else{
				Thread.sleep(this.lenght);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
