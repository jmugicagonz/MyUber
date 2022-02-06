package sorter;
import java.util.ArrayList;

import people.Driver;

public class SorterLeastOccupied implements Sorter{
	/**
	 * Empty constructor
	 */
	public SorterLeastOccupied() {
		
	}
	
	/**
	 * This method will calculate the driver who stays less time on a ride
	 * @param elements ArrayList with all the drivers
	 * @return Driver with less time on a ride
	 */
	public static Driver leastOccupiedDriver(ArrayList<Driver> elements) { 
		Driver leastOccupied = null;
		//We initialize the value into a big value (impossible) as we will look for the driver with the minimal value
		double leastOccupationRate = 1.00001; 
		if(elements.isEmpty()) //Just if we pass an empty list of elements to avoid errors
			return null;
		else{
			for(Driver d:elements){ //With this loop we will get the driver who is the least occupied and his occupation rate
				if(d.getOccupationRate()<leastOccupationRate){
					leastOccupied = d;
					leastOccupationRate =d.getOccupationRate();
				}	
			}
		}
		return leastOccupied; 
	}
	
	@Override
	//This method will sort all the drivers from the least occupied to the most occupied
	public ArrayList<Driver> sortD(ArrayList<Driver> elements) {
		//We create a list to avoid touching our original list of drivers
		ArrayList<Driver> driversCopy = (ArrayList<Driver>) elements.clone();  
		ArrayList<Driver> sortedDrivers = new ArrayList<Driver>(); //We create the list where we will put the elements sorted
		
		
		if(elements.size()<=1) {  //Just if we pass an empty list of elements to avoid errors
			return elements;
		}
		Driver leastOccupied;
		while(driversCopy.size()>0){ //Here we use the method to get the least occupied driver so as to range our drivers
			leastOccupied = SorterLeastOccupied.leastOccupiedDriver(driversCopy);
			sortedDrivers.add(leastOccupied);
			driversCopy.remove(leastOccupied);
		}
			
		return sortedDrivers;
		
	}
}
