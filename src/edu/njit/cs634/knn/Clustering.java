package edu.njit.cs634.knn;

public class Clustering {
	
	public Clustering()
	{}
	
	public String run()
	{
		
		
		return "running complete...";
	}

	public double getP() {
		return p;
	}
	public void setP(double p) {
		this.p = p;
	}
	public double getD() {
		return d;
	}
	public void setD(double d) {
		this.d = d;
	}
	public int getNumOfPoints() {
		return numOfPoints;
	}
	public void setNumOfPoints(int numOfPoints) {
		this.numOfPoints = numOfPoints;
	}

	private double p = -1;
	private double d = -1;
	private int numOfPoints = -1;
}
