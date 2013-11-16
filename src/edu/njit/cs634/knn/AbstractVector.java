package edu.njit.cs634.knn;

public abstract class AbstractVector implements Vector{

	public int getVectorID() {
		return vectorID;
	}
	
	public Cluster getCluster() {
		return cluster;
	}

	public void setCluster(Cluster cluster) {
		this.cluster = cluster;
	}

	int clusterNumber;
	Cluster cluster;
	int vectorID;
}
