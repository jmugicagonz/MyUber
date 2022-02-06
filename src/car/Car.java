package car;
/**
 * Abstract class to create cars, extended by Berline, Van and Standard
 * 
 * @author Juan
 *
 */
public abstract class Car {
    protected int places;    //number of places
    protected String type;    //Standard, Berline or Van
    protected String id;    //Id different for each car
    private double coorX;    //X coordinate of the car
    private double coorY;    //Y coordinate of the car
    private boolean active;	 //to know if the car is active or it can be used by a new driver
    
    /**
     * Constructor that creates a new car 
     * 
     * @param coorX coordinate X of the car
     * @param coorY coordinate Y of the car
     */
    //Constructor with the car position
    public Car(double coorX, double coorY) {
        this.coorX = coorX;
        this.coorY = coorY;
    }
    
    /**
     * 
     * @return coordinate X
     */
    public double getCoorX() {return this.coorX;}
    
    /**
     * 
     * @return coordinate Y
     */
    public double getCoorY() {return this.coorY;}
    
    /**
     * To change the coordinate X
     * 
     * @param coorX new coordinate X of the car
     */
    public void setCoorX(double coorX) {this.coorX = coorX;}
    
    /**
     * To change the coordinate Y
     * 
     * @param coorY new coordinate Y of the car
     */
    public void setCoorY(double coorY) {this.coorY = coorY;}
    
    /**
     * Gets the number of places of the car
     * @return number of places
     */
    public int getNumPlaces() {return this.places;}
    
    /**
     * 
     * @return the type of car
     */
    public String getType() {return this.type;}
    
    /**
     * 
     * @return the car ID
     */
    public String getId() {return this.id;}
    
    /**
     * In order to check if the car is in use or not
     * 
     * @return true if active, false if not active
     */
    public boolean isActive() {return this.active;}    //returns true if the car is active, another driver cannot use it

    /**
     * Change the car state
     * 
     * @param active (true if a rider enters / false if a rider leaves the car)
     */
    public void setActive(boolean active) {this.active = active;}    //change a carState to true or false


	@Override
	public String toString() {
		return "Car [places=" + places + ", id=" + id + ", coorX=" + coorX + ", coorY=" + coorY + "]";
	}	

}
