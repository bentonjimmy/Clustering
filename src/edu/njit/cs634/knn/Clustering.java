package edu.njit.cs634.knn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Clustering {
	
	public Clustering()
	{
		coefficients = new HashMap<String, Double>();
		clusters = new HashMap<String, ArrayList<Cluster>>();
	}
	
	public String run()
	{	
		PointsVector[] points1 = new PointsVector[numOfPoints];
		
		/*
		 * Fill points1 with random points
		 */
		for(int i=0; i<points1.length; i++)
		{
			PointsVector pv = new PointsVector();
			pv.fillPoints();
			points1[i] = pv;
		}
		
		//Check for and remove any outliers in the dataset
		DistanceOutliers detector = new DistanceOutliers(p, d, points1);
		outliers = detector.getOutliers();
		nonOutliers = detector.getNonOutliers();
		
		runClusteringTechniques();
		
		return "running complete";
	}
	
	/**
	 * This will run the four different clustering algorithms
	 */
	protected void runClusteringTechniques()
	{
		double silCoef = 0;
		ArrayList<Cluster> resultClusters;
		
		//Runs single link clustering
		SingleLinkClustering slc = new SingleLinkClustering(numOfClusters, createCopyOfNonOutliers());
		resultClusters = slc.assignClusters();
		clusters.put(slc.getName(), resultClusters);
		silCoef = slc.evaluateClusters();
		coefficients.put(slc.getName(), silCoef);
		
		//Runs complete link clustering
		CompleteLinkClustering clc = new CompleteLinkClustering(numOfClusters, createCopyOfNonOutliers());
		resultClusters = clc.assignClusters();
		clusters.put(clc.getName(), resultClusters);
		silCoef = clc.evaluateClusters();
		coefficients.put(clc.getName(), silCoef);
		
		//Runs group averaging clustering
		GroupAverageClustering gac = new GroupAverageClustering(numOfClusters, createCopyOfNonOutliers());
		resultClusters = gac.assignClusters();
		clusters.put(gac.getName(), resultClusters);
		silCoef = gac.evaluateClusters();
		coefficients.put(gac.getName(), silCoef);
		
		//Runs centroid based clustering
		CentroidClustering cc = new CentroidClustering(numOfClusters, createCopyOfNonOutliers());
		resultClusters = cc.assignClusters();
		clusters.put(cc.getName(), resultClusters);
		silCoef = cc.evaluateClusters();
		coefficients.put(cc.getName(), silCoef);
	}
	
	/**
	 * This method creates a copy of the non-outlier dataset.  This can be used to
	 * not tamper with the original nonOutliers array
	 * @return an array of PointsVectors that is a copy of nonOutliers
	 */
	protected PointsVector[] createCopyOfNonOutliers()
	{
		PointsVector[] copy = new PointsVector[nonOutliers.length];
		//Copy the new dataset to the 4 points arrays
		for(int i=0; i<nonOutliers.length; i++)
		{
			PointsVector pv = (PointsVector) nonOutliers[i];
			copy[i] = pv.createCopyOfVector();
		}
		
		return copy;
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

	public int getNumOfClusters() {
		return numOfClusters;
	}

	public void setNumOfClusters(int numOfClusters) {
		this.numOfClusters = numOfClusters;
	}

	public Vector[] getNonOutliers() {
		return nonOutliers;
	}

	public void setNonOutliers(Vector[] nonOutliers) {
		this.nonOutliers = nonOutliers;
	}

	public Vector[] getOutliers() {
		return outliers;
	}

	public void setOutliers(Vector[] outliers) {
		this.outliers = outliers;
	}

	public HashMap<String, Double> getCoefficients() {
		return coefficients;
	}

	public void setCoefficients(HashMap<String, Double> coefficients) {
		this.coefficients = coefficients;
	}

	private double p = -1;
	private double d = -1;
	private int numOfPoints = -1;
	private int numOfClusters = -1;
	private Vector[] outliers, nonOutliers;
	private HashMap<String, Double> coefficients;
	private HashMap<String, ArrayList<Cluster>> clusters;
}
