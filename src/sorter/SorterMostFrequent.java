package sorter;
import java.util.ArrayList;

import people.Customer;

public class SorterMostFrequent implements Sorter{
	/**
	 * Empty constructor
	 */
	public SorterMostFrequent() {
		
	}
	
	/**
	 * This method will calculate the customer who has picked the highest number of rides
	 * @param elements ArrayList with all the customers
	 * @return Customer who has picked the highes number of rides
	 */
	public static Customer mostFrequentCustomer(ArrayList<Customer> elements) {
		Customer mostFrequent = null;
		int maxRides = -1;	//We initialize the value to -1 as we will look for the customer with the highest value
		if(elements.isEmpty())	//Just if we pass an empty list of elements to avoid errors
			return null;
		else{
			for(Customer c:elements){	//With this loop we will get the customer who has made the highest number of rides
				if(c.getNumberOfRides()>maxRides){
					mostFrequent = c;
					maxRides =c.getNumberOfRides();
				}	
			}
		}
		return mostFrequent; 
	}
	
	@Override
	//This method will sort all the customers in terms of number of rides from lowest to highest
	public ArrayList<Customer> sortC(ArrayList<Customer> elements) {
		//We create a list to avoid touching our original list of customers
		ArrayList<Customer> customersCopy = (ArrayList<Customer>) elements.clone(); 
		ArrayList<Customer> sortedCustomers = new ArrayList<Customer>();	//We create the list where we will put the elements sorted
		
		if(elements.size()<=1)	//Just if we pass an empty list of elements to avoid errors
			return elements;
		else{
			Customer mostFrequent;
			while(customersCopy.size()>0){
				//Here we use the method to get the most charged customer so as to range our customers
				mostFrequent = SorterMostFrequent.mostFrequentCustomer(customersCopy);
				sortedCustomers.add(mostFrequent);
				customersCopy.remove(mostFrequent);
			}
			customersCopy=null;
			return sortedCustomers;
		}
	}
		
}



