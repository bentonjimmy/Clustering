package edu.njit.cs634.knn;

public interface ClusteringTechnique {

	public void assignClusters();
	public void receivePointsTable(PointsTable pt);
	public void calculateSimilarity();
	//public Vector[] removeOutliers();
	public String getName();
	public void setName(String name);
	
}
