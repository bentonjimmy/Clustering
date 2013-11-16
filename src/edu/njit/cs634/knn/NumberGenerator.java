package edu.njit.cs634.knn;

/**
 * This class is used simply to create random numbers between
 * 0 and the multiplier.  Initially the multiplier is set to 1000.
 * @author jmb66
 *
 */
public class NumberGenerator {
	
	private static NumberGenerator theInstance = new NumberGenerator();
	
	private NumberGenerator()
	{
	}
	
	public static NumberGenerator getInstance()
	{
		return theInstance;
	}
	
	public int getNumber()
	{
		return (int) (Math.random() * multiplier);
	}
	
	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}
	
	public Object clone() throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
	}

	private int multiplier = 1000;
	
}
