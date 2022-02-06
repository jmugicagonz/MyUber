package car;
/**
 * Creates a Berline car
 * 
 * @author Juan
 *
 */
public class Berline extends Car {
	
	private static int NUM = 1;		//counts the number of Standard cars already created

	/**
	 * Just the regular constructor to create a Berline car
	 * 
	 * @param coorX the coordinate x of the car 
	 * @param coorY the coordinate y of the car
	 */
	public Berline(double coorX, double coorY) {
		super(coorX, coorY);
		this.places = 4;
		this.type = "Berline";
		this.id = this.type+NUM;		//we want a format BerlineX being X= NUM
		NUM++;						//we increase NUM to avoid having the same id twice	
	}

}
