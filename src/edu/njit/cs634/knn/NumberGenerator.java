package edu.njit.cs634.knn;

public class NumberGenerator {
	
	public NumberGenerator(int range, int multiplier)
	{
		this.range = range;
		this.multiplier = multiplier;
	}
	
	public int getNumber()
	{
		return (int) ((Math.random() * multiplier) % range);
	}

	private int range;
	private int multiplier;
}
