package edu.njit.cs634.knn;

public class PointsVector extends AbstractVector {
	
	public PointsVector()
	{
		x = y = z = 0;
		gen = NumberGenerator.getInstance();
		vectorID = ++numOfVectors;
	}
	
	public PointsVector(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		vectorID = ++numOfVectors;
	}
	
	public PointsVector createCopyOfVector()
	{
		PointsVector copy = new PointsVector();
		copy.setCluster(this.getCluster());
		copy.setX(this.getX());
		copy.setY(this.getY());
		copy.setZ(this.getZ());
		copy.vectorID = this.vectorID;
		
		return copy;
	}
	
	public void fillPoints()
	{
		x = gen.getNumber();
		y = gen.getNumber();
		z = gen.getNumber();
	}
	
	public String toString()
	{
		return "("+getX()+", "+getY()+", "+getZ()+")";
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
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setZ(int z) {
		this.z = z;
	}

	private static NumberGenerator gen;
	private int x, y, z;
	private static int numOfVectors = 0;
}
