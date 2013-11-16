package edu.njit.cs634.knn;

import java.util.Comparator;

public class WeightedEdge implements Comparable<WeightedEdge>{
	
	public WeightedEdge(double weight, Vector v1, Vector v2)
	{
		this.weight = weight;
		this.v1 = v1;
		this.v2 = v2;
	}
	
	@Override
	public int compareTo(WeightedEdge we2) 
	{
		if(this == we2)
		{
			return 0;
		}
		if(this.weight < we2.weight)
		{
			return -1;
		}
		else if(this.weight == we2.weight)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
	
	public WeightedEdge createCopyOfWeightedEdge()
	{
		WeightedEdge copy = null;
		PointsVector copyV1 = ((PointsVector) this.getV1()).createCopyOfVector();
		PointsVector copyV2 = ((PointsVector) this.getV2()).createCopyOfVector();
		copy = new WeightedEdge(this.getWeight(), copyV1, copyV2);
		
		return copy;
	}
	
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public Vector getV1() {
		return v1;
	}
	public void setV1(Vector v1) {
		this.v1 = v1;
	}
	public Vector getV2() {
		return v2;
	}
	public void setV2(Vector v2) {
		this.v2 = v2;
	}

	private double weight;
	private Vector v1;
	private Vector v2;

}
