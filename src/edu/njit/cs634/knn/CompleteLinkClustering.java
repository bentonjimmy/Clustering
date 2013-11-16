package edu.njit.cs634.knn;

import java.util.ArrayList;

public class CompleteLinkClustering extends AbstractClusteringTechnique {

	public CompleteLinkClustering()
	{
		this(1, null);
	}
	
	public CompleteLinkClustering(int clusterCount, Vector[] vectors)
	{
		super.initializeClustering(clusterCount, vectors);
		name  = "CompleteLink";
	}
	
	@Override
	public ArrayList<Cluster> assignClusters() {
		
		int sizeOfC1;
		int sizeOfC2;
		ArrayList<ArrayList<WeightedEdge>> weightsTable = ((ConcretePointsTable) distances).getWeightsTable();
		
		while(numOfClusters > endingClusterCount)
		{
			//Find the shortest distance between two clusters
			WeightedEdge toCluster = findShortestDistance(weightsTable);
			
			//Get one Vector associated with the distance
			PointsVector v1 = (PointsVector) toCluster.getV1();
			//Get that Vector's cluster
			AHCluster cluster1 = (AHCluster) v1.getCluster();
			//Get the size of cluster1
			sizeOfC1 = cluster1.size();
			//Get the second Vector associated with the distance
			PointsVector v2 = (PointsVector) toCluster.getV2();
			//Get v2's cluster
			AHCluster cluster2 = (AHCluster) v2.getCluster();
			//Get the size of cluster2
			sizeOfC2 = cluster2.size();
			//Remove v2's cluster from the ArrayList of clusters
			clusters.remove(cluster2);
			//Add v2 to v1's cluster
			boolean result = cluster1.add(v2);
			//The vector was successfully added
			if(result == true)
			{
				//decrement the number of clusters
				numOfClusters--;
				
				int i=0;
				/*
				 * The number of comparisons needed is equal to the length of a row
				 * or column of the table
				 */
				int tableSize = ((ConcretePointsTable) distances).getTableSize();
				while(i < tableSize)
				{
					double a = traverseTable(i, getColumn(), weightsTable);
					double b = traverseTable(i, getRow(), weightsTable);
					a = getMax(a, b);
					updateCell(i, getColumn(), a, weightsTable);
					i++;
				}
				
				updateTable(getRow().intValue());
			}
		}
		
		return clusters;
	}
	
	protected double getMax(double a, double b)
	{
		if(a >= b)
		{
			return a;
		}
		else
		{
			return b;
		}
	}

}
