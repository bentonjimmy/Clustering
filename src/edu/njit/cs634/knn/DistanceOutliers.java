package edu.njit.cs634.knn;

import java.util.ArrayList;

public class DistanceOutliers {
	
	/**
	 * This class is used to remove outliers from a set of points.  The outliers
	 * are removed if p percent of the points are over d distance away from a single
	 * point.
	 * @param p - the percentage of points that must be d distance away from a point
	 * @param d - the distance used to find outliers
	 * @param vectors - the series of points
	 */
	public DistanceOutliers(double p, double d, PointsVector[] vectors)
	{
		this.vectors = vectors;
		this.p = p;
		this.d = d;
		outliers = null;
		nonOutliers = null;
	}
	
	/**
	 * Returns the points that were considered to be outliers.
	 * @return an array containing the Vectors found to be outliers.
	 */
	public PointsVector[] getOutliers()
	{
		if(outliers == null)
		{
			findOutliers();
		}
		return outliers.toArray(new PointsVector[0]);
	}

	/**
	 * Returns the original data after the outliers have been removed.
	 * @return an array containing the Vectors after the outliers have been removed.
	 */
	public PointsVector[] getNonOutliers()
	{
		if(nonOutliers == null)
		{
			findOutliers();
		}
		return nonOutliers.toArray(new PointsVector[0]);
	}
	
	/**
	 * This method will find the outliers in the set of 
	 * points provided.
	 */
	protected void findOutliers()
	{
		outliers = new ArrayList<PointsVector>();
		nonOutliers = new ArrayList<PointsVector>();
		
		/*
		 *This will hold the counts as to how many times a vector is greater
		 *than d distance away from another vector. 
		 */
		double[] hits = new double[vectors.length];
		
		for(int i=0; i<vectors.length; i++)
		{
			for(int j=0; j<vectors.length; j++)
			{
				//Measure the distance between the two vectors
				double distance = measureDistance(vectors[i], vectors[j]);
				if(distance >= d)
				{
					double temp = hits[i];
					temp++;
					hits[i] = temp;
				}
			}
		}
		
		//Check which Vectors are over the threshold
		for(int i=0; i<hits.length; i++)
		{
			double count = hits[i];
			if((count)/((double)hits.length) >= p)
			{
				//The point is an outlier
				outliers.add(vectors[i]);
			}
			else
			{
				//The point is not an outlier
				nonOutliers.add(vectors[i]);
			}
		}
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
	
	private PointsVector[] vectors;
	private ArrayList<PointsVector> outliers;
	private ArrayList<PointsVector> nonOutliers;
	double p, d;
}
