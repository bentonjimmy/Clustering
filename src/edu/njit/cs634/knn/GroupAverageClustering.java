package edu.njit.cs634.knn;

import java.util.ArrayList;

/**
 * This class implements the clustering based off of the Group Averaging technique
 * of defining the distance between two clusters as being the average distance between
 * all points in the clusters.
 * @author Jim Benton
 *
 */
public class GroupAverageClustering extends AbstractClusteringTechnique {

	public GroupAverageClustering()
	{
		this(1, null);
	}
	
	public GroupAverageClustering(int clusterCount, Vector[] vectors)
	{
		super.initializeClustering(clusterCount, vectors);
		name  = "GroupAverage";
	}
	
	@Override
	public ArrayList<Cluster> assignClusters() 
	{
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
					a = lanceWilliams(a, sizeOfC1, b, sizeOfC2);
					updateCell(i, getColumn(), a, weightsTable);
					i++;
				}
				
				updateTable(getRow().intValue());
			}
		}
		
		return clusters;
	}
	
	/**
	 * This method is used to calculate the Lance-Williams method values used to 
	 * help calculate the distance between two clusters.
	 * @param a
	 * @param sizeOfA
	 * @param b
	 * @param sizeOfB
	 * @param distAB
	 * @return
	 */
	protected double lanceWilliams(double a, double sizeOfA, double b, double sizeOfB)
	{
		double alphaA = (sizeOfA / (sizeOfA + sizeOfB));
		double alphaB = (sizeOfB / (sizeOfA + sizeOfB));
		return (alphaA * a) + (alphaB * b);
	}
	
}
