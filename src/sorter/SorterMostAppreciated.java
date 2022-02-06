package sorter;
import java.util.ArrayList;

import people.Driver;
public class SorterMostAppreciated implements Sorter{
	/**
	 * Empty constructor
	 */
	public SorterMostAppreciated() {
		
	}

	/**
	 * This method will calculate the driver who has the highest score
	 * @param elements ArrayList with all the drivers
	 * @return the driver with the highest score 
	 */
	public static Driver mostAppreciatedDriver(ArrayList<Driver> elements) {
		Driver mostAppreciated = null;
		double maxScore = -1; 	//We initialize the value to zero as we will look for the driver with the highest value
		if(elements.isEmpty())	//Just if we pass an empty list of elements to avoid errors
			return null;
		else{
			for(Driver d:elements){ //With this loop we will get the driver who has the best score as well as his score
				if(d.getScore()>maxScore){
					mostAppreciated = d;
					maxScore =d.getScore();
				}	
			}
		}
		return mostAppreciated; 
	}
	
	@Override
	//This method will sort all the drivers from the worst  to the best in terms of score
	public ArrayList<Driver> sortD(ArrayList<Driver> elements) {
		//We create a list to avoid touching our original list of drivers
		ArrayList<Driver> driversCopy = (ArrayList<Driver>) elements.clone(); 
		ArrayList<Driver> sortedDrivers = new ArrayList<Driver>();		//We create the list where we will put the elements sorted
		
		if(elements.size()<=1)	//Just if we pass an empty list of elements to avoid errors
			return elements;
		else{
			Driver mostAppreciated;
			while(driversCopy.size()>0){
				//We use the method to get the best evaluated driver so as to range our drivers
				mostAppreciated = SorterMostAppreciated.mostAppreciatedDriver(driversCopy); 
				sortedDrivers.add(mostAppreciated);
				driversCopy.remove(mostAppreciated);
			}
			
			return sortedDrivers;
		}
	}

	

}

