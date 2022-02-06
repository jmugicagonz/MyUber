package sorter;
import java.util.*;

import people.Customer;
import people.Driver;

public interface Sorter {
	/**
	 * 
	 * @param elements ArrayList containing all the customers
	 * @return ArrayList with the customers sorted
	 */
	public default ArrayList<Customer> sortC(ArrayList<Customer> elements){
		return null;
	}
	/**
	 * 
	 * @param elements ArrayList containing all the drivers
	 * @return ArrayList with the drivers sorted
	 */
	public default ArrayList<Driver> sortD(ArrayList<Driver> elements){
		return null;
	}
}
