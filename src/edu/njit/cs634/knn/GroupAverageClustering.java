package edu.njit.cs634.knn;

import java.util.ArrayList;

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
			}
			
			int i=0;
			/*
			 * The number of comparisons needed is equal to the length of a row
			 * or column of the table
			 */
			int tableSize = ((ConcretePointsTable) distances).getTableSize();
			while(i < tableSize)
			{
				double a = traverseTable(i, column, weightsTable);
				double b = traverseTable(i, row, weightsTable);
				a = lanceWilliams(a, sizeOfC1, b, sizeOfC2);
				updateCell(i, column, a, weightsTable);
				i++;
			}
			
			updateTable(row.intValue());
		}
		
		return clusters;
	}
	
	/**
	 * This method updates the table containing the distances between each Vector/Cluster.
	 * After we updated the table with the new distances between the Vectors we no longer
	 * need one row and one column since it has been merged into another row and column.
	 * This method removes the unneeded row and column and then decrements the table size.
	 * @param toRemove - the value of the row and column to be removed from the table
	 */
	protected void updateTable(int toRemove)
	{
		ArrayList<ArrayList<WeightedEdge>> table = ((ConcretePointsTable) distances).getWeightsTable();
		ArrayList<WeightedEdge> list = null;
		for(int i=0; i<((ConcretePointsTable) distances).getTableSize(); i++)
		{
			//Get the column
			list = table.get(i);
			//Remove the unneeded cell
			list.remove(toRemove);
		}
		//Remove the unneeded column
		table.remove(toRemove);
		((ConcretePointsTable) distances).setWeightsTable(table);
		//Update the table size after the unneeded row and column have been removed
		int temp = ((ConcretePointsTable) distances).getTableSize();
		((ConcretePointsTable) distances).setTableSize(--temp);
	}
	
	/**
	 * This method is used along with a loop to properly traverse only half of the table
	 * that contains the vectors' distances from each other.  Since the table is a mirror
	 * image of itself, we only need to go through anything left of where the vector is 
	 * measuring it's distance from itself.
	 * @param traverse - this variable controls which value will be returned.  It controls
	 * the movement throughout the table.
	 * @param turn - this is the value of where the traversal stops from going left to right
	 * and beings going up to down.
	 * @param weightsTable - the ArrayList of Weighted Edge ArrayLists
	 * @return the distance value at a certain Weighted Edge
	 */
	protected double traverseTable(int traverse, int turn, ArrayList<ArrayList<WeightedEdge>> weightsTable)
	{
		ArrayList<WeightedEdge> list;
		WeightedEdge we;
		if(traverse<=turn)
		{
			list = weightsTable.get(traverse);
			we = list.get(turn);
			return we.getWeight();
		}
		else
		{
			list = weightsTable.get(turn);
			we = list.get(traverse);
			return we.getWeight();
		}
	}
	
	/**
	 * This method will update the column i and row j of weightsTable wit newValue.
	 * @param i - the "column" that holds that WeightEdge that will receive the new value
	 * @param j - the "row" of the WeightedEdge that will receive the new value
	 * @param newValue - the new weight
	 * @param weightsTable - the ArrayList of WeightedEdge ArrayLists that holds the distances
	 * between Vectors.
	 */
	protected void updateCell(int i, int j, double newValue, ArrayList<ArrayList<WeightedEdge>> weightsTable)
	{
		ArrayList<WeightedEdge> list;
		WeightedEdge we;
		if(i<=j)
		{
			list = weightsTable.get(i);
			we = list.get(j);
			we.setWeight(newValue);
		}
		else
		{
			list = weightsTable.get(j);
			we = list.get(i);
			we.setWeight(newValue);
		}
	}
	
	protected double lanceWilliams(double a, double sizeOfA, double b, double sizeOfB)
	{
		double alphaA = (sizeOfA / (sizeOfA + sizeOfB));
		double alphaB = (sizeOfB / (sizeOfA + sizeOfB));
		return (alphaA * a) + (alphaB * b);
	}
	
	/**
	 * This method will look through half of the table that has the distances between Vectors.
	 * Because the table is a mirror image of itself over x=y, only half the values need to be
	 * checked.  It will then return the Weighted Edge that has the smallest value in the table.
	 * @param row - this is used to capture the row where the smallest value is found
	 * @param column - this is used to capture the column where the smallest value is found
	 * @param weightsTable - the table containing the distances between Vectors
	 * @return a WeightedEdge that contains the smallest value and therefore the two Vectors 
	 * should be clustered.
	 */
	protected WeightedEdge findShortestDistance(ArrayList<ArrayList<WeightedEdge>> weightsTable)
	{
		double shortest = 0;
		WeightedEdge shortestDistance = null;
		WeightedEdge we = null;
		for(int i=0; i<weightsTable.size(); i++)
		{
			ArrayList<WeightedEdge> temp = weightsTable.get(i);
			for(int j=i+1; j<temp.size(); j++)
			{
				we = temp.get(j);
				if((shortest == 0 || we.getWeight() < shortest) && (we.getWeight() > 0))
				{
					shortest = we.getWeight();
					shortestDistance = we;
					column = i;
					row = j;
				}
			}
		}
		
		return shortestDistance;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	private Integer row = new Integer(0);
	private Integer column = new Integer(0);
}
