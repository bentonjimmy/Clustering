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
	
	/**
	 * This will evaluate the quality of the clusters that are created by a clustering
	 * technique.
	 * @return a double representing the quality of the clusters
	 */
	public double evaluateClusters()
	{
		evaluator.setClusters(clusters);
		
		return evaluator.calculateCoefficient();
	}
	
	protected void initializeClustering(int clusterCount, Vector[] vectors)
	{
		evaluator = new SilhouetteCoefficient();
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
		
		//pass the distances between the points to the evaluator
		evaluator.createCopyOfWeightsTable(((ConcretePointsTable)distances).getWeightsTable());
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
	
	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}

	protected SilhouetteCoefficient evaluator;
	protected String name; 
	protected ArrayList<Cluster> clusters; //Holds the clusters
	protected int numOfClusters;
	protected int endingClusterCount; //Holds the number of clusters the algorithm will stop at
	private Integer row = new Integer(0);
	private Integer column = new Integer(0);
}
