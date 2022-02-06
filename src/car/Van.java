package car;
/**
 * Creates a Van
 * 
 * @author Juan
 *
 */
public class Van extends Car {

    private static int NUM = 1;        //counts the number of Standard cars already created

    /**
     * Just the regular constructor to create a Van
	 * 
	 * @param coorX the coordinate x of the car 
	 * @param coorY the coordinate y of the car
     */
    public Van(double coorX, double coorY) {
        super(coorX, coorY);
        this.places = 6;
        this.type = "Van";
        this.id = this.type+NUM;        //we want a format VanX being X= NUM
        NUM++;                        //we increase NUM to avoid having the same id twice    
    }

}
