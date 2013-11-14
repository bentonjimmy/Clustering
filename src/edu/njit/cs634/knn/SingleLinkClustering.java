package edu.njit.cs634.knn;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

public class SingleLinkClustering extends AbstractClusteringTechnique {

	public SingleLinkClustering()
	{
		this(1, null);
	}
	
	public SingleLinkClustering(int clusterCount, Vector[] vectors)
	{
		super.initializeClustering(clusterCount, vectors);
		name  = "SingleLink";
	}
	
	@Override
	public ArrayList<Cluster> assignClusters() {
		
		ArrayList<WeightedEdge> distanceList = new ArrayList<WeightedEdge>();
		if(distances != null)
		{
			double[][] table = ((ConcretePointsTable) distances).getDistanceTable();
			Vector[] vectors = ((ConcretePointsTable) distances).getVectors();
			for(int i=0; i<((ConcretePointsTable) distances).tableSize; i++)
			{
				for(int j=0; table[i][j] > 0; j++)
				{
					distanceList.add(new WeightedEdge(table[i][j], vectors[i], vectors[j]));
				}
			}
			
			//Sort the distances in ascending order
			Collections.sort(distanceList);
			
			while(numOfClusters > endingClusterCount)
			{
				//Get the first/smallest distance
				WeightedEdge we = distanceList.remove(0);
				//Get one Vector associated with the distance
				PointsVector v1 = (PointsVector) we.getV1();
				//Get that Vector's cluster
				AHCluster cluster1 = (AHCluster) v1.getCluster();
				//Get the second Vector associated with the distance
				PointsVector v2 = (PointsVector) we.getV2();
				//Get v2's cluster
				AHCluster cluster2 = (AHCluster) v2.getCluster();
				//Remove v2's cluster from the ArrayList of clusters
				clusters.remove(cluster2);
				//Add v2 to v1's cluster
				boolean result = cluster1.add(v2);
				//The vector was successfully added
				if(result == true)
				{
					//decrement the number of clusters
					numOfClusters--;
				}
			}
		}
		//need to remove elements from clusters also
		return clusters;
	}
	
	

	@Override
	public void calculateSimilarity() {
		// TODO Auto-generated method stub

	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

}
