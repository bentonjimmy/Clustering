package edu.njit.cs634.knn;

import java.util.ArrayList;

public interface ClusteringTechnique {

	public ArrayList<Cluster> assignClusters();
	public void receivePointsTable(PointsTable pt);
	public void calculateSimilarity();
	//public Vector[] removeOutliers();
	public String getName();
	public void setName(String name);
	
}
