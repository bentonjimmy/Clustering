package edu.njit.cs634.knn;

import java.util.ArrayList;

public class CentroidClustering extends AbstractClusteringTechnique {

	public CentroidClustering()
	{
		this(1, null);
	}
	
	public CentroidClustering(int clusterCount, Vector[] vectors)
	{
		super.initializeClustering(clusterCount, vectors);
		name  = "Centroid";
	}
	
	@Override
	public ArrayList<Cluster> assignClusters() 
	{
		int sizeOfC1;
		int sizeOfC2;
		double weight;
		ArrayList<ArrayList<WeightedEdge>> weightsTable = ((ConcretePointsTable) distances).getWeightsTable();
		
		while(numOfClusters > endingClusterCount)
		{
			//Find the shortest distance between two clusters
			WeightedEdge toCluster = findShortestDistance(weightsTable);
			//Get the distance between the two Vectors
			weight = toCluster.getWeight();
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
					a = lanceWilliams(a, sizeOfC1, b, sizeOfC2, weight);
					updateCell(i, getColumn(), a, weightsTable);
					i++;
				}
				
				updateTable(getRow().intValue());
			}
			
		}
		
		return clusters;
	}
	
	
	
	protected double lanceWilliams(double a, double sizeOfA, double b, double sizeOfB, double distAB)
	{
		double alphaA = (sizeOfA / (sizeOfA + sizeOfB));
		double alphaB = (sizeOfB / (sizeOfA + sizeOfB));
		double beta = (-1* sizeOfA * sizeOfB)/(Math.pow((sizeOfA + sizeOfB), 2));
		return (alphaA * a) + (alphaB * b) + (beta * distAB);
	}

}
