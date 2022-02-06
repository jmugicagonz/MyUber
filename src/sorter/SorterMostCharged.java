package sorter;

import java.util.ArrayList;

import people.Customer;

public class SorterMostCharged implements Sorter{
	/**
	 * Empty constructor
	 */
	public SorterMostCharged() {
		
	}
		
	/**
	 * This method will calculate the customer who has payed more
	 * @param elements ArrayList with all the customers
	 * @return Customer that has payed the most
	 */
	public static Customer mostChargedCustomer(ArrayList<Customer> elements) {
		Customer mostCharged = null;
		double maxCharges = -1; //We initialize the value to -1 as we will look for the customer with the highest value
		if(elements.isEmpty()) //Just if we pass an empty list of elements to avoid errors
			return null;
		else{
			for(Customer c:elements){ //With this loop we will get the customer who has payed the most
				if(c.getAmountOfCharges()>maxCharges){
					mostCharged = c;
					maxCharges =c.getAmountOfCharges();
				}	
			}
		}
		return mostCharged; 
	}
	
	@Override
	//This method will sort all the customers from the least to the most charged
	public ArrayList<Customer> sortC(ArrayList<Customer> elements) {
		//We create a list to avoid touching our original list of customers
		ArrayList<Customer> customersCopy = (ArrayList<Customer>) elements.clone(); 
		//We create the list where we will put the elements sorted
		ArrayList<Customer> sortedCustomers = new ArrayList<Customer>();
		
		if(elements.size()<=1)		//Just if we pass an empty list of elements to avoid errors
			return elements;
		else{
			Customer mostCharged;
			while(customersCopy.size()>0){
				//Here we use the method to get the most charged customer so as to range our customers
				mostCharged = SorterMostCharged.mostChargedCustomer(customersCopy);
				sortedCustomers.add(mostCharged);
				customersCopy.remove(mostCharged);
			}
			customersCopy.clear();
			return sortedCustomers;
		}
	}		
}
