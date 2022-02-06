package car;
/**
 * Creates a standard car
 * 
 * @author Juan
 *
 */
public class Standard extends Car {
    
    private static int NUM = 1;        //counts the number of Standard cars already created

    /**
     * Just the regular constructor to create a Standard car
	 * 
	 * @param coorX the coordinate x of the car 
	 * @param coorY the coordinate y of the car
     */
    public Standard(double coorX, double coorY) {
        super(coorX, coorY);
        this.places = 4;
        this.type = "Standard";
        this.id = this.type+NUM;        //in order to get a format Standard1,Standard2...
        NUM++;                        //then we increase NUM so two cars doesn't have the same id
    }

}
