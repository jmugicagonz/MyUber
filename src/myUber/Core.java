package myUber;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import car.Car;
import car.FactoryOfCars;
import conditions.KindOfRide;
import conditions.TrafficCondition;
import conditions.UberBlack;
import conditions.UberPool;
import conditions.UberVan;
import conditions.UberX;
import people.Customer;
import people.Driver;
import people.StateDriver;
import ride.PageBookOfRides;
import ride.Ride;
import ride.Status;
/**
 * Class general that represents the system
 * 
 * @author juan
 *
 */
public class Core{
	
	static ArrayList<Driver> drivers = new ArrayList<Driver>();						//ArrayList with all the drivers
	static ArrayList<Customer> customers = new ArrayList<Customer>();					//ArrayList with all the customers
	static ArrayList<Car> cars = new ArrayList<Car>();									//ArrayList with all the cars
	static ArrayList<PageBookOfRides> bookOfRides = new ArrayList<PageBookOfRides>();	//ArrayList with the informationa about all the rides

    private int numberOfRides;			//total number of rides finished in the app
    private double amountOfCharges;		//total money spent in the app
	
    /**
     * Empty constructor
     */
    public Core() {}
    
    /**
     * To calculate the distance between two points (X1,Y1) and (X2,Y2)
     * 
     * @param coorX1 coordinate X of Point 1
     * @param coorY1 coordinate Y of Point 1
     * @param coorX2 coordinate X of Point 2
     * @param coorY2 coordinate Y of Point 2
     * @return the distance between Point 1 and Point 2
     */
	public static double getDistance(double coorX1, double coorY1, double coorX2, double coorY2) {
		return Math.sqrt(Math.pow(Math.abs(coorX1-coorX2), 2)+Math.pow(Math.abs(coorY1-coorY2), 2));
	}
	

	/**
	 * Returns a vector with the prices of the different kind of rides for the ride
	 * 
	 * @param traffic Traffic condition at the moment
	 * @param coorX coordinate X where the customer wants to go
	 * @param coorY coordinate Y where the customer wants to go
	 * @param cust customer who asks for the ride
	 * @return vector with the prices for the ride (prices[0]=>UberPool, prices[1]=>UberX, prices[2]=>UberBlack, prices[3]=>UberVan)
	 */
	public static double[] searchRide(TrafficCondition traffic, double coorX, double coorY,Customer cust){
		double[] prices = new double[4];
		double distance = getDistance(cust.getCoorX(),cust.getCoorY(),coorX,coorY);
		prices[0]=(new UberPool()).calculateCost(traffic,distance);
		prices[1]=(new UberX()).calculateCost(traffic,distance);
		prices[2]=(new UberBlack()).calculateCost(traffic,distance);
		prices[3]=(new UberVan()).calculateCost(traffic,distance);
		return prices;
	}
	

	/**
	 * Search a driver meeting the requirements to complete the ride
	 * 
	 * @param type Type of ride (UberPool, UberX, UberBlack, UberVan)
	 * @param cust customer who asks for the ride
	 * @return ArrayList with the drivers available ordered by distance 
	 */
	public static ArrayList<Driver> searchDriver(String type,Customer cust) {
		double coorX = cust.getCoorX();		//to search a driver close to the customer
		double coorY = cust.getCoorY();		//""
		
		ArrayList<Driver> driversList = new ArrayList<Driver>();	//Will store drivers meeting the requirements
		ArrayList<Double> distanceList = new ArrayList<Double>();	//Array to store the distance of the driver to the customer
		double maxDistance = 20;		//maximal distance to consider that a driver will be interested in doing that travel
		boolean count=true;
		
		for(Driver driver:drivers) {
			boolean added = false;
			//the driver must be onDuty and drive an appropriate car for the kindOfRide
			if((driver.getState().equals(StateDriver.onDuty)&&driver.getCar().getType().equals(type))) {
				double distance = getDistance(driver.getCar().getCoorX(),driver.getCar().getCoorY(),coorX,coorY);
				//if it's close enough
				if(distance<maxDistance) {
					//if is the first one we don't check
					if(count) {
						driversList.add(driver);
						distanceList.add(distance);
						count=false;
					}else {
						for(int i=0;i<distanceList.size();i++) {
							//if the distance is closer we add the driver in that position
							if(distance<distanceList.get(i)) {
								driversList.add(i,driver);		
								distanceList.add(i,distance);
								added = true;
								break;
							}
						}	
						if(!added) {
							driversList.add(driver);
							distanceList.add(distance);
						}	
					}
				}	
			}
		}
		return driversList;
	}
	
	/**
	 * Search a driver meeting the requirements to complete the ride
	 * 
	 * @param type Type of ride (UberPool, UberX, UberBlack, UberVan)
	 * @param cust customer who asks for the ride
	 * @return ArrayList with the drivers available ordered by distance 
	 */
	
	/**
	 * Search a driver meeting the requirements to complete the ride
	 * 
	 * @param type Type of ride (UberPool, UberX, UberBlack, UberVan)
	 * @param cust customer who asks for the ride
	 * @param destCoorX coordinate X where the customer wants to go
	 * @param destCoorY coordinate Y where the customer wants to go
	 * @param numPeople number of people who will be on the ride (for UberPool is max 2 people)
	 * @return ArrayList with the drivers available ordered by convenience
	 */
	public static ArrayList<Driver> searchPoolDriver(String type,Customer cust,double destCoorX,double destCoorY, int numPeople){
		double coorX = cust.getCoorX();		//to search a driver close to the customer
		double coorY = cust.getCoorY();		//""
		
		ArrayList<Driver> driversList = new ArrayList<Driver>();		//Will store drivers meeting the requirements
		ArrayList<Double> distanceList = new ArrayList<Double>();		//Array to store the distance of the driver to the customer
		double maxDetour = 4;				//maxDetour possible for doing that UberPool
		double maxDistance = 10;			//if the car is free just do the normal way and find close drivers
		boolean count=true;
		boolean alright=false;				//variable equal to true if the driver is available to complete the ride
		for(Driver driver:drivers) {
			boolean added = false;
			alright=false;
			//this is if the driver is onDuty (with no one in the car yet)
			if(driver.getCar().getType().equals(type)&&driver.getNextRide()==null) {
				alright=true;
			}else if(driver.getCar().getType().equals(type)&&driver.getNextRide().getKindOfRide() instanceof UberPool
					&&driver.getNextRide().getPlacesPoolAvailable()>=numPeople) {
				alright=true;
			}
			if(alright) {
				if(driver.getState().equals(StateDriver.onDuty)){
					//we add 5 to the distance in favor of the drivers that already have someone in the car 
					double distance = getDistance(driver.getCar().getCoorX(),driver.getCar().getCoorY(),coorX,coorY)+5;
					if(distance<maxDistance) {
						//if is the first one we don't check
						if(count) {
							driversList.add(driver);
							distanceList.add(distance);
							count=false;
						}else {
							for(int i=0;i<distanceList.size();i++) {
								//if the distance is closer we add the driver in that position
								if(distance<distanceList.get(i)) {
									driversList.add(i,driver);		
									distanceList.add(i,distance);
									added = true;
									break;
								}
								
							}
							//if the driver has not been added we add him at the end of the list
							if(!added) {
								driversList.add(driver);
								distanceList.add(distance);
							}	
						}
					}	
				}
				//if the driver is already on a ride (there are people in the car)
				else if(driver.getState().equals(StateDriver.onARide)) {
					//changer trajectory to see the three points if a third one ask for a ride
					double[][] trajectory = driver.getNextRide().getTrajectory();
					double cust1coorX = trajectory[0][1];
					double cust1coorY = trajectory[1][1];
					//if trajectory is compatible with ride take him, if not not
					//we see if the difference between the direct ride and the ride with detour is too big
					double directDist = getDistance(cust.getCoorX(),cust.getCoorY(),destCoorX,destCoorY);
					double distPool = getDistance(coorX,coorY,cust1coorX,cust1coorY)+getDistance(coorX,coorY,destCoorX,destCoorY);
					if((distPool-directDist)<maxDetour) {
						if(count) {
							driversList.add(driver);
							distanceList.add(distPool-directDist);
							count=false;
						}else {
							for(int i=0;i<distanceList.size();i++) {
								if((distPool-directDist)<distanceList.get(i)) {
									driversList.add(i,driver);		
									distanceList.add(i,distPool-directDist);
									added = true;
									break;
								}
							}
							//if the driver has not been added we add him at the end of the list
							if(!added) {
								driversList.add(driver);
								distanceList.add(distPool-directDist);
							}	
						}
					}
				}	
			}
		}
		return driversList;
	}
	
	/**
	 * After getting the prices the customer chooses the ride that he prefers
	 *  
	 * @param type Type of ride (UberPool, UberX, UberBlack, UberVan)
	 * @param cust customer who asks for the ride
	 * @param coorX coordinate X where the customer wants to go
	 * @param coorY coordinate Y where the customer wants to go
	 * @param price price of the chosen ride
	 * @param traffic Traffic Condition at the moment of the ride
	 * @param numPeople number of people that will do the ride
	 * @return ride that the client will complete
	 */
	public static Ride selectRide(String type, Customer cust, double coorX, double coorY, double price, TrafficCondition traffic, int numPeople) {
				
		KindOfRide kindOfRide;
		
		ArrayList<Driver> driversAvailable = new ArrayList<Driver>();
		//differentiate kinds of ride and create a specific kindOfRide depending on the request 
		if(type.equalsIgnoreCase("UberPool")) {
			//don't allow booking an uberPool for more than 2 people
			if(numPeople>2) {
				System.out.println("It is not possible to book an UberPool for more than 2 people, try UberX");
				return null;
			}
			driversAvailable = searchPoolDriver("Standard",cust,coorX,coorY,numPeople);
			kindOfRide = new UberPool();
		}else if(type.equalsIgnoreCase("UberX")) {
			driversAvailable = searchDriver("Standard",cust);
			kindOfRide = new UberX();
		}else if(type.equalsIgnoreCase("uberBlack")) {
			driversAvailable = searchDriver("Berline",cust);
			kindOfRide = new UberBlack();
		}else {
			driversAvailable = searchDriver("Van",cust);
			kindOfRide = new UberVan();
		}
		Ride ride = null;
		if(driversAvailable.isEmpty()){
			System.out.println("We couldn't find a driver for your ride :( ");
			return ride;
		}
		//calculate the trajectory of a ride
		double trajectory[][] = new double [2][2];
		trajectory[0][0]=cust.getCoorX();
		trajectory[1][0]=cust.getCoorY();
		trajectory[0][1]=coorX;
		trajectory[1][1]=coorY;
		for(Driver driver:driversAvailable) {
			ride = new Ride(driver,driver.getCar(),cust,trajectory,Status.UNCONFIRMED,kindOfRide,traffic);
			//if the driver accept
			if(driver.askForRide(type,coorX,coorY,price,ride)) {
				driver.setRide(ride);
				ride.setStatusRide(Status.CONFIRMED);		//ride status is confirmed			
				driver.setState(StateDriver.onARide);		//the driver is on a ride	
				break;										//when driver accept exit the for
			}else {
				ride=null;
			}
		}
		return ride;
	}
	
	/**
	 * Process after the ride is confirmed and the rider search the customer
	 * 
	 * @param ride Ride that the client will complete
	 * @throws InterruptedException Exception during the execution if there is some problem
	 */
	public void pickUpCustomer(Ride ride) throws InterruptedException {
		if(ride.getDriver().pickingUberPoolCust(ride)) {
			System.out.println("Driver "+ride.getDriver().getId()+" just picked up Customer "+ride.getCustomer().getId() );
			ride.setStatusRide(Status.ONGOING);		//change the status
		}else {
			System.out.println("Driver "+ride.getDriver().getId()+" is picking up Customer "+ride.getCustomer().getId() );
			Thread t0 = new Thread(ride);
			t0.start();						//start the way
			try {
	            t0.join();					//wait until the way is ended to continue with the code
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
			//if the status change to CANCELLED liberate the driver
			if(ride.getStatus().equals(Status.CANCELLED)) {
				double[][] finalCoor = ride.getTrajectory();
				ride.getCar().setCoorX(finalCoor[0][0]);
				ride.getCar().setCoorY(finalCoor[0][1]);
				ride.getDriver().setState(StateDriver.onDuty);
				System.out.println("Customer "+ride.getCustomer().getId()+" cancelled his ride");
			}else {
				System.out.println("Driver "+ride.getDriver().getId()+" just picked up Customer "+ride.getCustomer().getId() );
				ride.setStatusRide(Status.ONGOING);		//change the status
				startRide(ride);						//start the ride
			}
		}
	}
	
	//Customer is already in the car and we want the ride to start
	/**
	 * Customer is already in the car and we want the ride to start
	 * 
	 * @param ride Ride that the client will complete
	 * @throws InterruptedException Exception during the execution if there is some problem
	 */
	public void startRide(Ride ride) throws InterruptedException {
		System.out.println("Ride with driver "+ride.getDriver().getId()+" and customer "+ride.getCustomer().getId()+" just started");
		Calendar time = Calendar.getInstance();			//time that the ride starts
		ride.setStartTime(time);						//we include this time into the info of the ride
		Thread t1 = new Thread(ride);
		t1.start();										//start ride
		try {
            t1.join();									//wait till the way is finished to continue with the ride
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		ride.setStatusRide(Status.COMPLETED);			//ride status is completed
		System.out.println("Ride with driver "+ride.getDriver().getId()+" and customer "+ride.getCustomer().getId()+" just finished");

		
		Driver driver = ride.getDriver();				//create an object with the driver of the ride
		Car car = ride.getCar();						//same with the car
		Customer customer = ride.getCustomer();			//same with customer
		double price = ride.getPrice();					//we get the price of the way
		
		//The information of the ride that we want to store
		PageBookOfRides page = new PageBookOfRides(driver,car,customer,ride.getTrajectory(),ride.getDistance(),
				ride.getDuration(),price,ride.getLenght(),ride.getEvaluationMark());
		//Add this information to the bookOfRides (data of all the rides)
		bookOfRides.add(page);
		
		customer.increaseNumberOfRides();				//increase by 1 the number of rides of the customer
		customer.increaseAmountOfCharges(price);		//increase the money spent
		customer.increaseTotalTime(ride.getLenght());	//increase the total time on a ride
		double score = customer.evaluateRide();
		
		driver.increaseRide();
		driver.increaseAmmountCharged(price);
		if(score!=0) {
			driver.addScore(score);
		}
		driver.eliminateRide(ride);						//eliminate this ride to the list of the rides that the driver has to do
														//useful for the UberPool
	
		double[][] finalCoor = ride.getTrajectory();
		car.setCoorX(finalCoor[0][1]);					//change the coordinates of the car with the new position
		car.setCoorY(finalCoor[1][1]);
		customer.setCoorX(finalCoor[0][1]);
		customer.setCoorY(finalCoor[1][1]);
		
		this.numberOfRides++;				//increase the system total number of rides
		this.amountOfCharges+=price;		//increase the system total number of money spent
		
		//if it's an uberPool and there are some passengers still in the car do the way that is left
		if(driver.anyRidesLeft()) {
			Ride nextRide=driver.getNextRide();
			startRide(nextRide);
		}else {
			driver.setState(StateDriver.onDuty); //when is free change the status to onDuty
		}
	}
	
	/**
	 * Get the system balance with the total number of rides and the total amount of money spent in the App
	 */
	public void getBalance() {
		System.out.println("Total number of rides: "+this.numberOfRides);
		System.out.println("Total money spent in the App: "+this.amountOfCharges);
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		Core core = new Core();
		
		Car car1 = FactoryOfCars.createCar("berline", 2, 2);
		Car car2 = FactoryOfCars.createCar("berline", 3, 3);
		Car car3 = FactoryOfCars.createCar("van", 7, 5);
		Car car4 = FactoryOfCars.createCar("standard", 1, 1);
		Car car5 = FactoryOfCars.createCar("standard", 4, 8);
		cars.add(car1);
		cars.add(car2);
		cars.add(car3);
		cars.add(car4);
		cars.add(car3);
		for(int i=0;i<10;i++) {
			Driver driver = new Driver("Name","Surname",cars.get(i/2));
			drivers.add(driver);
		}
		for(int i=1;i<7;i++) {
			Customer cust = new Customer("Name","Surname",i*2,i*2,"423574234864");
			customers.add(cust);
		}
		for(Driver driv:drivers) {
			driv.setState(StateDriver.onDuty);
		}
		
		double[] price = searchRide(TrafficCondition.heavy, 4, 4, customers.get(0));
		customers.get(0).giveMark(2);
		Ride ride1 = selectRide("Uberblack",customers.get(0),4,4,price[2],TrafficCondition.heavy,2);
		StartingRides startingRide1 = new StartingRides(ride1,core);
		Thread t1 = new Thread(startingRide1);
		t1.start();

		double[] price1 = searchRide(TrafficCondition.heavy, 1, 1, customers.get(3));
		customers.get(3).giveMark(2.3);
		Ride ride2 = selectRide("Uberblack",customers.get(3),1,1,price1[3],TrafficCondition.heavy,2);
		StartingRides startingRide2 = new StartingRides(ride2,core);
		Thread t2 = new Thread(startingRide2);
		t2.start();
		
		double[] price2 = searchRide(TrafficCondition.medium,10,10,customers.get(1));
		customers.get(1).giveMark(1);
		Ride ride3 = selectRide("UberPool",customers.get(1),10,10,price2[0],TrafficCondition.medium,2);
		StartingRides startingRide3 = new StartingRides(ride3,core);
		Thread t3 = new Thread(startingRide3);
		t3.start();
		
		
		TimeUnit.SECONDS.sleep(4);
		double[] price3 = searchRide(TrafficCondition.medium,12,12,customers.get(4));
		customers.get(4).giveMark(5);
		Ride ride4 = selectRide("UberPool",customers.get(4),12,12,price3[0],TrafficCondition.medium,2);
		StartingRides startingRide4 = new StartingRides(ride4,core);
		Thread t4 = new Thread(startingRide4);
		t4.start();
		
		
		
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		}catch (Exception e) {
			// TODO: handle exception
		}
		for(Driver driv:drivers) {
			driv.setState(StateDriver.offline);
		}
		ComputationOfStatistics compResults = new ComputationOfStatistics(bookOfRides);
		compResults.computeStatistics();
	}
}
