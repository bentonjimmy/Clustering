package edu.njit.cs634.knn;

public class NumberGenerator {
	
	public NumberGenerator(int multiplier)
	{
		this.multiplier = multiplier;
	}
	
	public int getNumber()
	{
		return (int) (Math.random() * multiplier);
	}

	private int multiplier;
}
