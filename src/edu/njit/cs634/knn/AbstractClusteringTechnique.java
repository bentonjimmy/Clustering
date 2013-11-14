package edu.njit.cs634.knn;

import java.util.ArrayList;

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
	
	protected void initializeClustering(int clusterCount, Vector[] vectors)
	{
		//Sets the value for where this will stop clustering vectors
		this.endingClusterCount = clusterCount;
		clusters = new ArrayList<Cluster>();
		if(vectors != null)
		{
			//Create a cluster for each Vector
			for(int i=0; i<vectors.length; i++)
			{
				Cluster c = new AHCluster();
				c.add(vectors[i]);
				//Holds all the clusters 
				clusters.add(c);
			}
			numOfClusters = vectors.length;
			distances = new ConcretePointsTable(vectors);
		}
	}

	protected String name; 
	protected ArrayList<Cluster> clusters; //Holds the clusters
	protected int numOfClusters;
	protected int endingClusterCount; //Holds the number of clusters the algorithm will stop at
}
