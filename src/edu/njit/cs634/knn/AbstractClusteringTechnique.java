package edu.njit.cs634.knn;

public abstract class AbstractClusteringTechnique implements
		ClusteringTechnique {
	
	PointsTable distances;

	public PointsTable getDistances() {
		return distances;
	}

	public void setDistances(PointsTable distances) {
		this.distances = distances;
	}
	
	@Override
	public void receivePointsTable(PointsTable pt) {
		this.distances = pt;

	}

}
