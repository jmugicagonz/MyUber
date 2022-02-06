package myUber;

//THIS IS THE INTERFACE FOR PART 2

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import car.*;
import conditions.*;
import people.*;
import ride.*;
import sorter.*;
import java.io.*;
import exceptions.*;

//TODO list
//create init file and read it's information
//create a function in order to put drivers onduty
//include new functions to UML (Program)
//finish to write the report
//put all the test in a folder

public class Clui {
	public static int[] random() {
		int[] coordinates = new int[2];
		coordinates[0] = (int)Math.floor(Math.random()*11)-5;
		coordinates[1]= (int)Math.floor(Math.random()*11)-5;		
		return coordinates;
	}
	public static void showInfoCustomers(ArrayList<Customer> customers) { //Prints the info of each customer
		for(Customer customer:customers) {
			System.out.println(customer.toString());
			customer.getBalance();
			System.out.print("\n");
		}		
	}
	public static void showInfoDrivers(ArrayList<Driver> drivers) { //Prints the info of each driver
		for(Driver driver:drivers) {
			System.out.println(driver.toString());
			driver.getTotalBalance();
			System.out.print("\n");
		}
	}
	public static void showInfoCars(ArrayList<Car> cars) { //Prints the info of each car
		for(Car car:cars) {
			System.out.println(car.toString());
		}
		System.out.print("\n");
	}
	public static Customer searchCustomer(ArrayList<Customer> customers, String iD) { //To search a customer by its id in the list of customers
		try {
		for(Customer customer:customers) {
			if(customer.getId() == Integer.parseInt(iD)){
				return customer;
			}
		}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("Customer not found");
		return null;
	}
	public static Driver searchDriver(ArrayList<Driver> drivers, String name, String surname) { //To search a driver by its id in the list of drivers
		try {
		for(Driver driver:drivers) {
			if((driver.getName().equals(name))&&(driver.getSurname().equals(surname))){
				return driver;
			}
		}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("Driver not found");
		return null;
	}
	public static Car searchCar(ArrayList<Car> cars, String iD) { //To search a car by its id in the list of cars
		try {
		for(Car car:cars) {
			if((car.getId().equals(iD))){
				return car;
			}
		}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("Car not found");
		return null;
	}
	//The function computeLine will permit to correctly respond to each command introduced.
	public static void computeLine(String[] s1,ArrayList<Driver> drivers,ArrayList<Car> cars, ArrayList<Customer> customers, Scanner sc,Core core, int l) throws NumberArgumentsException, TypeArgumentsException, InterruptedException, ValueArgumentsException, IOException{
		int hour;
		Customer customerAsking;
		TrafficCondition traffic;
		double[] prices = new double[4];
		double destCoorX;
		double destCoorY;
		double driverMark;
		Ride ride;
		
		switch(s1[0]) {
			case "setup":
				if(s1.length==5) {
					try{
					int nStandardCars = Integer.parseInt(s1[1]);
					int nBerlinCars = Integer.parseInt(s1[2]);
					int nVanCars = Integer.parseInt(s1[3]);
					int nCustomers = Integer.parseInt(s1[4]);
					int totalCars = nStandardCars+nBerlinCars+nVanCars;
					for(int i=0; i<totalCars; i++) {
						int[] coordinates=random();
						if(i<nStandardCars) {
							cars.add(FactoryOfCars.createCar("standard", coordinates[0], coordinates[1]));
						}
						else if(i<(nStandardCars+nBerlinCars)){
							cars.add(FactoryOfCars.createCar("berline", coordinates[0], coordinates[1]));	
						}
						else{
							cars.add(FactoryOfCars.createCar("van", coordinates[0], coordinates[1]));	
						}
						drivers.add(new Driver("driver"+i+"name","driver"+i+"surname",(Car) cars.get(cars.size()-1)));
						drivers.get(drivers.size()-1).setState(StateDriver.offDuty);
						}
					for(int i=0; i<nCustomers; i++) {
						int[] coordinates=random();
						customers.add(new Customer("customer"+i+"name","customer"+i+"surname",coordinates[0],coordinates[1],null));
						}
					}
					
					catch(NumberFormatException e) {throw new TypeArgumentsException("setup <int> <int> <int> <int>");}
					
				}
				else {throw new NumberArgumentsException(4,s1.length-1);}
			break;
			
			case "addCustomer":
				if(s1.length==3) {
					int[] coorAddCustomer = random();
					customers.add(new Customer(s1[1],s1[2],coorAddCustomer[0],coorAddCustomer[1],null));
					showInfoCustomers(customers);
				}
				else {throw new NumberArgumentsException(2,s1.length-1);}
			break;
			
			case "addCarDriver":
				if(s1.length==4) {
					int[] coorAddCarDriver = random();
					cars.add(FactoryOfCars.createCar(s1[3], coorAddCarDriver[0], coorAddCarDriver[1]));
					drivers.add(new Driver(s1[1],s1[2],cars.get(cars.size()-1)));
					showInfoDrivers(drivers);
				}
				else {throw new NumberArgumentsException(3,s1.length-1);}
			break;
			
			case "addDriver":
				if(s1.length==4) {
					Car car = searchCar(cars,s1[3]);
					if(car!=null) {
					drivers.add(new Driver(s1[1],s1[2],searchCar(cars, s1[3])));
					showInfoDrivers(drivers);
					}
				}
				else {throw new NumberArgumentsException(3,s1.length-1);}
			break;
			
			case "setDriverStatus":
				if(s1.length==4) {
					Driver driverToSetStatus = searchDriver(drivers, s1[1],s1[2]);
					if(driverToSetStatus!=null) {
					if(s1[3].equalsIgnoreCase("offline")){
						driverToSetStatus.setState(StateDriver.offline);
					}
					else if(s1[3].equalsIgnoreCase("onDuty")){
						driverToSetStatus.setState(StateDriver.onDuty);
					}
					else if(s1[3].equalsIgnoreCase("onARide")){
						driverToSetStatus.setState(StateDriver.onARide);
					}
					else if (s1[3].equalsIgnoreCase("offDuty")){
						driverToSetStatus.setState(StateDriver.offDuty);
					}
					else{System.out.println("The state introduced is not valid. Try with: offline|onDuty|onARide|offDuty");}
					showInfoDrivers(drivers);
					}
				}
				else {throw new NumberArgumentsException(3,s1.length-1);}
			break;
			
			case "makeDriversActive":
				if(s1.length==1) {
					if(!drivers.isEmpty()) {
						for(Driver driver:drivers) {
							driver.setState(StateDriver.onDuty);}
					}
					else {System.out.println("The list of drivers is empty.");}
				}
				else {throw new NumberArgumentsException(0,s1.length-1);}
			break;
				
			case "makeDriversOffline":
				if(s1.length==1) {
					if(!drivers.isEmpty()) {
						for(Driver driver:drivers) {
							driver.setState(StateDriver.offline);
						}
					}
					else {System.out.println("The list of drivers is empty.");}

				}
				else {throw new NumberArgumentsException(0,s1.length-1);}

			break;
			
			case "moveCar":
				if(s1.length==4) {
					try {
						Car carToMove = searchCar(cars,s1[1]);
						if(carToMove!=null) {
							carToMove.setCoorX(Integer.parseInt(s1[2]));
							carToMove.setCoorY(Integer.parseInt(s1[3]));
							showInfoCars(cars);
						}
					}
					catch(NumberFormatException e) {throw new TypeArgumentsException("moveCar <String> <int> <int>");}

				}
				else {throw new NumberArgumentsException(3,s1.length-1);}

			break;
			
			case "moveCustomer":
				if(s1.length==4) {
					try {
						Customer customerToMove = searchCustomer(customers,s1[1]);
						if(customerToMove!=null) {
							customerToMove.setCoorX(Integer.parseInt(s1[2]));
							customerToMove.setCoorY(Integer.parseInt(s1[3]));
							showInfoCustomers(customers);
						}
					}
					catch(NumberFormatException e) {throw new TypeArgumentsException("moveCustomer <String> <int> <int>");}

				}
				else {throw new NumberArgumentsException(3,s1.length-1);}

			break;
			
			case "displayState":
				if(s1.length==1) {
				showInfoCars(cars);
				showInfoCustomers(customers);
				showInfoDrivers(drivers);
				}
				else {throw new NumberArgumentsException(0,s1.length-1);}
			break;
			
			case "printState":
				try {
				    BufferedWriter writer = new BufferedWriter(new FileWriter("testScenarioOutput.txt"));
				    for(Customer customer:customers) {
						writer.write(customer.toString());
						writer.newLine();
					}
				    writer.newLine();
				    for(Driver driver:drivers) {
						writer.write(driver.toString());
						writer.newLine();
					}
				    writer.newLine();
				    for(Car car:cars) {
						writer.write(car.toString());
						writer.newLine();
				    }
				    writer.newLine();
				    double quan = 0;
					for(Driver driver:drivers) {
						quan+=driver.getAmountOfCharges();
					}
					writer.write("The total amount of money cashed by all drivers in the myUber system is: "+String.format( "%.2f",quan));
				     
				    writer.close();
				}
				catch(RuntimeException e) {}
			break;
			
			case "ask4price":
				if(s1.length==5) {
					customerAsking = searchCustomer(customers,s1[1]);
					if(customerAsking!=null) {
						try {
							Calendar instant = Calendar.getInstance();
							hour = instant.get(Calendar.HOUR_OF_DAY);
							if((Integer.parseInt(s1[4])>=0)&&(Integer.parseInt(s1[4])<=23)) {
								hour = Integer.parseInt(s1[4]);
								traffic = (new TrafficState(hour).getTraffic());
							}
							else if(Integer.parseInt(s1[4])>23) {
								System.out.println("Error in time indrodued: please, introduce"
								+ "a value 0<=hour<=23 if your want to predict in that hour or a value hour<0 to use the current time.");
								break;
							}else {
								TrafficState tf = new TrafficState(instant);
								traffic = tf.getTraffic();
							}
							prices = Core.searchRide(traffic, Integer.parseInt(s1[2]), Integer.parseInt(s1[3]), customerAsking);
							System.out.println("Prices:\n"+"Price of an Uber Pool: " + String.format( "%.2f",prices[0]) +
									"\nPrice of an Uber X: " + String.format( "%.2f",prices[1]) + 
									"\nPrice of an Uber Black: " + String.format( "%.2f",prices[2]) + 
									"\nPrice of an Uber Van: " + String.format( "%.2f",prices[3]) + "\n");
						}
						catch(NumberFormatException e) {throw new TypeArgumentsException("ask4price <String> <int> <int> <0<=int<=23|int<0");}

					}
				}
				else {throw new NumberArgumentsException(4,s1.length-1);}
			break;
			
			case "simRide":
				if(s1.length==7) {
					try {
						customerAsking = searchCustomer(customers,s1[1]);
						if(customerAsking!=null) {
							destCoorX = Integer.parseInt(s1[2]);
							destCoorY = Integer.parseInt(s1[3]);
							hour = Integer.parseInt(s1[4]);
							String rideType = s1[5];
							driverMark = Double.parseDouble(s1[6]);
							if(driverMark<=5&&driverMark>=1) {
								traffic = (new TrafficState(hour).getTraffic());
								prices = Core.searchRide(traffic, destCoorX, destCoorY, customerAsking);
								double finalPrice = 0;
								switch(rideType){
									case "UberPool": 
										finalPrice = prices[0];
										break;
									case "UberX":
										finalPrice = prices[1];
										break;
									case "UberBlack": 
										finalPrice = prices[2];
										break;
									case "UberVan":
										finalPrice = prices[3];
										break;
									default :
										System.out.println("Type of ride unexistant. Use UberPool, UberX, UberBlack,UberVan");
										return;
								}
								ride = Core.selectRide(rideType, customerAsking, destCoorX, destCoorY, finalPrice, traffic, 2);
								if(ride!=null) {
									customerAsking.giveMark(driverMark);
									StartingRides startingRide = new StartingRides(ride,core);
									Thread t = new Thread(startingRide);
									t.start();
									t.join();
									showInfoDrivers(drivers);
									showInfoCustomers(customers);
								}
							}
							else {
								throw new ValueArgumentsException("Drivers mark should be between 1 and 5");
							}
							
						}
					}						
					catch(ValueArgumentsException e) {}
					catch(NumberFormatException e) {throw new TypeArgumentsException("simRide <int> <int> <int> <0<=int<=23|int<0> <String> <double>");}
				}else {throw new NumberArgumentsException(6,s1.length-1);}
			break;
			
			case "simRide_i":
				if(s1.length!=5) {
					throw new NumberArgumentsException(4,s1.length-1);
				}
				try {
					customerAsking = searchCustomer(customers,s1[1]);
					if(customerAsking==null) {break;}
					destCoorX = Integer.parseInt(s1[2]);
					destCoorY = Integer.parseInt(s1[3]);
					hour = Integer.parseInt(s1[4]);
				}
				catch(NumberFormatException e) {throw new TypeArgumentsException("simRide_i <int> <int> <int> <0<=int<=23|int<0");}
				traffic = (new TrafficState(hour).getTraffic());
				prices = Core.searchRide(traffic, destCoorX, destCoorY, customerAsking);
				System.out.println("Prices:\n"+"Price of an Uber Pool: " + String.format( "%.2f",prices[0]) + 
						"\nPrice of an Uber X: " + String.format( "%.2f",prices[1]) + 
						"\nPrice of an Uber Black: " + String.format( "%.2f",prices[2]) + 
						"\nPrice of an Uber Van: " + String.format( "%.2f",prices[3]));
				System.out.println("Press 1 for choosing UberPool\nPress 2 for choosing UberX\nPress 3 for choosing UberBlack"+
						"\nPress 4 for choosing UberVan\nPress 0 to cancel and exit");
				boolean enter = true;
				ride = null;
				while(enter) {
					int option = sc.nextInt();
					if (option==0) {l=0; System.out.println("Exiting program;"); return;}
					switch(option) {
					case 1:
						//UberPool
						ride = Core.selectRide("UberPool", customerAsking, destCoorX, destCoorY, prices[0], traffic, 2);
						enter = false;
						break;
					case 2:
						//UberX
						ride = Core.selectRide("UberX", customerAsking, destCoorX, destCoorY, prices[1], traffic, 2);
						enter = false;
						break;
					case 3:
						//UberBlack
						ride = Core.selectRide("UberX", customerAsking, destCoorX, destCoorY, prices[1], traffic, 2);
						enter = false;
						break;
					case 4:
						//UberVan
						ride = Core.selectRide("UberX", customerAsking, destCoorX, destCoorY, prices[1], traffic, 2);
						enter = false;
						break;
					default:
						System.out.println("That's not a valid number. Please insert a number between 0 and 4");
					}
				}
				if(ride==null) {break;}
				StartingRides startingRide1 = new StartingRides(ride,core);
				Thread t1 = new Thread(startingRide1);
				t1.start();
				t1.join();
				driverMark = 0;
				while(driverMark<1||driverMark>5) {
					System.out.println("What mark do you give to the ride (between 1 and 5) ?");
					driverMark = sc.nextDouble();
					try {
						if (driverMark<1||driverMark>5) {
							throw new ValueArgumentsException("Drivers mark should be between 1 and 5");
						}
					}catch(ValueArgumentsException e ) {}					
				}

				if(driverMark<=5&&driverMark>=1) {
					throw new ValueArgumentsException("");
				}
				ride.getDriver().addScore(driverMark);
				showInfoDrivers(drivers);
				showInfoCustomers(customers);				
			break;
			

			case "displayDrivers":
				if(s1.length==2) {
					if(!drivers.isEmpty()) {
						if((!s1[1].equalsIgnoreCase("mostappreciated"))&&(!s1[1].equalsIgnoreCase("mostoccupied"))) {
							System.out.println("Error: you can just sort drivers by their appretiation or occupation. The argument must be: mostappreciated|mostoccupied\n");
							break;
						}
						if(s1[1].equalsIgnoreCase("mostappreciated")) {
							Sorter sort = new SorterMostAppreciated();
								ArrayList<Driver> sortedDrivers = sort.sortD(drivers);
								System.out.println("The drivers sorted by an increasing appreciation rate are: \n");
								showInfoDrivers(sortedDrivers);
						}
						else {
							Sorter sort = new SorterLeastOccupied();
							ArrayList<Driver> sortedDrivers = sort.sortD(drivers);
							System.out.println("The drives sorted by an increassing occupation rate are: \n");
							showInfoDrivers(sortedDrivers);
						}
					}
					else {System.out.println("The list of drivers is empty!");}

				}
				else {throw new NumberArgumentsException(1,s1.length-1);}
			break;
			
			case "displayCustomers":
				if(s1.length==2) {
					if(!customers.isEmpty()) {
						if((!s1[1].equalsIgnoreCase("mostfrequent"))&&(!s1[1].equalsIgnoreCase("mostcharged"))) {
							System.out.println("Error: you can just sort customers by their charges or number of rides. The argument must be: mostfrequent|mostcharged\n");
							break;
						}
						if(s1[1].equalsIgnoreCase("mostfrequent")) {
							Sorter sort = new SorterMostFrequent();
							ArrayList<Customer> sortedCustomers = sort.sortC(customers);
							System.out.println("The customers sorted by an increasing number of rides are: \n");
							showInfoCustomers(sortedCustomers);
		
		
						}
						else {
							Sorter sort = new SorterMostCharged();
							ArrayList<Customer> sortedCustomers = sort.sortC(customers);
							System.out.println("The customers sorted by the increasing quantity of money payed are: \n");
							showInfoCustomers(sortedCustomers);
						}
					}
					else {System.out.println("The list of costumers is empty!");}
				}
				else {throw new NumberArgumentsException(1,s1.length-1);}

			break;						
	
			case "totalCashed":
				if(s1.length==1) {
					if(!drivers.isEmpty()) {
						double quantity = 0;
						for(Driver driver:drivers) {
							quantity+=driver.getAmountOfCharges();
						}
						System.out.println("The total amount of money cashed by all drivers in the myUber system is: "+String.format( "%.2f",quantity)+"\n");
					}
					else {System.out.println("The list of drivers is empty!");}
				}
				else {throw new NumberArgumentsException(0,s1.length-1);}
			break;
			
			case "runtest":
				if(s1.length==2) {
					FileReader filemu = null;
					BufferedReader readermu = null;
					try {
						int l1=1;
						filemu =  new FileReader(s1[1]);
						readermu = new BufferedReader(filemu);
						String linemu = "";
						while((linemu = readermu.readLine())!=null) {
							computeLine(linemu.split(" "),drivers,cars,customers,null,core,l1);
						}
					}
					catch(Exception e) {
						throw new RuntimeException(e);
					} 
					finally {
						if(readermu!=null) {
							try{readermu.close();}
							catch(IOException e) {}
						}
					}
				}
				else {throw new NumberArgumentsException(1,s1.length-1);}
			break;
				
			default: 
				System.out.println("The command introduced doesn't correspond with the avaliable commands. Try entering the instruction in lower case letters");
		
	}
}

	
	
	public static void main(String[] args) throws InterruptedException, ValueArgumentsException, IOException {
		//First we instantiate the necessary parameters to correctly run the program
		Core core = new Core();
		ArrayList<Driver> drivers = Core.drivers;
		ArrayList<Car> cars = Core.cars;
		ArrayList<Customer> customers = Core.customers;
		FileReader filemu = null;
		BufferedReader readermu = null;
		try {
			int l=1;
			filemu =  new FileReader("my_uber.ini");
			readermu = new BufferedReader(filemu);
			String linemu = "";
			while((linemu = readermu.readLine())!=null) {
				computeLine(linemu.split(" "),drivers,cars,customers,null,core,l);
			}
		}
		catch(Exception e) {
			throw new RuntimeException(e);
		} 
		finally {
			if(readermu!=null) {
				try{readermu.close();}
				catch(IOException e) {}
			}
		}
		int l=1;
		while(l!=0) { 		//With a while(l!=0) we can do an infinite loop, just until the user introduce a zero
			System.out.println("Enter a command(0 to exit): ");
			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
			String s1[] = s.split(" ");
			if (s.contentEquals("0")) { //To close the program if the user introduces a zero
				l=0;
				System.out.println("Exiting program");
				sc.close();
				}
			else if (s1[0].equals("init")||s1[0].equals("runtest")) { //If we can to initialize the program reading a file
				FileReader file = null;
				BufferedReader reader = null;
				if(s1[0].equals("init")) {
					cars.clear();
					drivers.clear();
					customers.clear();
				}
				try {
					file =  new FileReader(s1[1]);
					reader = new BufferedReader(file);
					String line = "";
					while((line = reader.readLine())!=null) {
						computeLine(line.split(" "),drivers,cars,customers,null,core,l);
					}
				}
				catch(Exception e) {
					throw new RuntimeException(e);
				} 
				finally {
					if(reader!=null) {
						try{reader.close();}
						catch(IOException e) {}
					}
				}
			}
			else { //If we just want to directly start introducing the commands in the console (command line)
				try {
					computeLine(s1,drivers,cars,customers,sc,core,l);
				} catch (NumberArgumentsException | TypeArgumentsException e) {
					
				}	
			}
			
			
			}
		}		
}

