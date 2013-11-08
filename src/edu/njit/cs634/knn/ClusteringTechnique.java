package edu.njit.cs634.knn;

public interface ClusteringTechnique {

	public void AssignClusters();
	public void ReceivePointsTable(PointsTable pt);
	public void CalculateSimilarity();
	public Vector[] removeOutliers();
	
}
