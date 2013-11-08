package edu.njit.cs634.knn;

public class PointsVector extends AbstractVector {
	
	public PointsVector()
	{
		x = y = z = 0;
		gen = new NumberGenerator(1000);
	}
	
	public void fillPoints()
	{
		x = gen.getNumber();
		y = gen.getNumber();
		z = gen.getNumber();
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}
	
	private static NumberGenerator gen;
	private int x, y, z;
	
}
