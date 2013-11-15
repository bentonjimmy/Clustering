package edu.njit.cs634.knn;

import java.util.ArrayList;
import java.util.HashMap;

public class SilhouetteCoefficient {
	
	public SilhouetteCoefficient(ArrayList<Cluster> clusters)
	{
		this.clusters = clusters;
	}
	
	public double calculateCoefficient()
	{
		Vector [] vectors;
		for(Cluster c: clusters)
		{
			vectors = c.getVectors();
			for(int i=0; i<vectors.length; i++)
			{
				calculateA(vectors[i], vectors);
			}
		}
		
		return 0;
	}
	
	protected double calculateA(Vector v, Vector[] vectors)
	{
		double sum = 0.0;
		for(int i=0; i<vectors.length; i++)
		{
			if(vectors[i] != v)
			{
				sum += measureDistance(v, vectors[i]);
			}
		}
		
		return (sum / (vectors.length - 1));
	}

	/**
	 * Measures the euclidean distance between two vectors and return the distance.
	 * @param v1 - a Vector
	 * @param v2 - a Vector
	 * @return a double representing the distance between the two vectors
	 */
	protected double measureDistance(Vector v1, Vector v2) 
	{
		PointsVector pv1 = (PointsVector)v1;
		PointsVector pv2 = (PointsVector)v2;
		
		//Measure the Euclidean distance
		return Math.sqrt(Math.pow((pv1.getX() - pv2.getX()), 2) +
				Math.pow((pv1.getY() - pv2.getY()), 2) + 
				Math.pow((pv1.getZ() - pv2.getZ()), 2));
		
	}
	
	private ArrayList<Cluster> clusters;
	
}
