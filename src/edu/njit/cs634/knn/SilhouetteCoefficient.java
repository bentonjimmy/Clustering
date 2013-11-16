package edu.njit.cs634.knn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class SilhouetteCoefficient {
	
	public SilhouetteCoefficient(){}
	
	public SilhouetteCoefficient(ArrayList<Cluster> clusters)
	{
		this.clusters = clusters;
	}
	
	/**
	 * This method will calculate the silhouette coefficient based off of the distances
	 * in weightsTable and how the points are clustered.
	 * @return a double that represents the silhouette coefficient
	 */
	public double calculateCoefficient()
	{
		double[] coefficients = new double[weightsTable.size()];
		int j = 0;
		double distance = 0;
		double size;
		double vectorDistances;
		double a = 0;
		double b = 0;
		PointsVector v1 = null;
		PointsVector v2 = null;
		
		//Loops through "columns" of the weightsTable
		for(ArrayList<WeightedEdge> list : weightsTable)
		{
			HashMap <Integer, Double> clusterWeights = new HashMap <Integer, Double>();
			HashMap <Integer, Double> clusterSizes = new HashMap<Integer, Double>();
			vectorDistances = 0;
			size = 0;
			
			//Loop through "rows" of the weightsTable
			for(WeightedEdge we : list)
			{
				distance = we.getWeight();
				v1 = (PointsVector) we.getV1();
				v2 = (PointsVector) we.getV2();
				//We do not want to do anything with the distance between a vector and itself
				//In a table this would be when x=y, i.e. (1,1), (2,2), (3,3), etc.
				if(v1 != v2)
				{
					//Check if they are in the same cluster
					if(v1.getCluster().contains(v2))
					{
						vectorDistances += distance;
						size++;
					}
					else //Not in the same cluster
					{
						Double temp = clusterWeights.get(v2.getCluster().getID());
						//Check if v2's cluster is already in the table
						if(temp != null)
						{
							temp += distance;
							clusterWeights.put(v2.getCluster().getID(), temp);
							double tempsize = clusterSizes.get(v2.getCluster().getID());
							tempsize++;
							clusterSizes.put(v2.getCluster().getID(), tempsize);
						}
						else //v2's cluster needs to be added
						{
							clusterWeights.put(v2.getCluster().getID(), distance);
							clusterSizes.put(v2.getCluster().getID(), 1.0);
						}
					}
				}
			}
			
			if(size != 0) //Don't divide by 0
			{
				a = vectorDistances / size;
			}
			b = calculateB(clusterWeights, clusterSizes);
			
			//put the coefficient for this point in the array
			//All coefficients will be averaged below
			coefficients[j] = calculateS(a, b);
			j++;
		}
		
		return avgOfCoefficients(coefficients);
	}
	
	/**
	 * This will create the copy of the weightsTable that is needed in order to calculate the coefficient.
	 * It doesn't created entirely new copies of the vectors since the references to the vectors that will
	 * be clustered needs to be maintained.
	 * @param weightsTable - the table to copy
	 */
	public void createCopyOfWeightsTable(ArrayList<ArrayList<WeightedEdge>> weightsTable)
	{
		ArrayList<ArrayList<WeightedEdge>> copy = new ArrayList<ArrayList<WeightedEdge>>();
		for(ArrayList<WeightedEdge> list : weightsTable)
		{
			ArrayList<WeightedEdge> copyList = new ArrayList<WeightedEdge>();
	
			for(WeightedEdge we : list)
			{
				WeightedEdge copyWe = new WeightedEdge(we.getWeight(), we.getV1(), we.getV2());
				copyList.add(copyWe);
			}
			copy.add(copyList);
		}
		
		setWeightsTable(copy);
	}
	
	/**
	 * This will calculate the value of b, which is the distance from one point to points outside
	 * of its own cluster.
	 * @param clusterWeights - a Hashmap that will hold the sum of all the distances from one point 
	 * to all points in another cluster
	 * @param clusterSizes - a Hashmap that will hold the size of all the other clusters
	 * @return the smallest average distance from a single point to all points in other clusters
	 */
	protected double calculateB(HashMap <Integer, Double> clusterWeights, HashMap <Integer, Double> clusterSizes)
	{
		Set<Integer> keySet = clusterWeights.keySet();
		Iterator<Integer> iter = keySet.iterator();
		
		double b = 1000000;
		//Iterate through all sum of all the distances in a cluster
		while(iter.hasNext())
		{
			Integer i = iter.next();
			//Find the smallest avg
			double avg = clusterWeights.get(i) / clusterSizes.get(i);
			if(avg < b)
			{
				b = avg;
			}
		}
		
		return b;
	}
	
	/**
	 * This will calculate the average of all the coefficients calculated
	 * @param coefficients - the array that holds all of the coefficients
	 * @return - a double that presents the average silhouette coefficient for all points
	 */
	protected double avgOfCoefficients(double[] coefficients)
	{
		double sum=0.0;
		for(int i=0; i<coefficients.length; i++)
		{
			sum += coefficients[i];
		}
		
		return sum/((double) coefficients.length);
	}
	
	/**
	 * Calculates an individual silhouette coefficient
	 * @param a - the average distance from a point to all other points in its cluster
	 * @param b - the minimum average distance from a point to another cluster
	 * @return - a double that presents the silhouette coefficient for a point
	 */
	protected double calculateS(double a, double b)
	{
		double s = 0.0;
		if(a>b)
		{
			s = (b-a)/a;
		}
		else
		{
			s = (b-a)/b;
		}
		
		return s;
	}
	
	public ArrayList<Cluster> getClusters() {
		return clusters;
	}

	public void setClusters(ArrayList<Cluster> clusters) {
		this.clusters = clusters;
	}

	public ArrayList<ArrayList<WeightedEdge>> getWeightsTable() {
		return weightsTable;
	}

	public void setWeightsTable(ArrayList<ArrayList<WeightedEdge>> weightsTable) {
		this.weightsTable = weightsTable;
	}

	private ArrayList<Cluster> clusters;
	protected ArrayList<ArrayList<WeightedEdge>> weightsTable;
	
}
