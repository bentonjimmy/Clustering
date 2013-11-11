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
		this.endingClusterCount = clusterCount;
		clusters = new ArrayList<Cluster>();
		for(int i=0; i<vectors.length; i++)
		{
			Cluster c = new AHCluster();
			c.add(vectors[i]);
			clusters.add(c);
		}
		numOfClusters = 0;
	}
	
	@Override
	public void assignClusters() {
		
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
			
			Collections.sort(distanceList);
			
			while(numOfClusters >= endingClusterCount)
			{
				WeightedEdge we = distanceList.remove(0);
				PointsVector v1 = (PointsVector) we.getV1();
				AHCluster cluster = (AHCluster) v1.getCluster();
				boolean result = cluster.add((PointsVector) we.getV2());
				//The vector was successfully added
				if(result == true)
				{
					//decrement the number of clusters
					numOfClusters--;
				}
			}
		}
	}
	
	

	@Override
	public void calculateSimilarity() {
		// TODO Auto-generated method stub

	}
	
	protected void findShortestDistance(Vector v1, Vector v2)
	{
		double shortest = -1;
		double[][] table = ((ConcretePointsTable) distances).getDistanceTable();
		
		for(int i=0; i<((ConcretePointsTable) this.distances).getTableSize(); i++)
		{
			double qs = quickSelect(table[i], 1);
			if(shortest == -1)
			{
				shortest = qs;
			}
			else if(qs < shortest)
			{
				shortest = qs;
			}
		}
	}
	
	protected double quickSelect(double[] convert, int k)
	{
		Double[] elements = new Double[convert.length];
		for(int i=0; i<convert.length; i++)
		{
			elements[i] = convert[i];
		}
		return quickSelect(elements, k);
	}

	protected double quickSelect(Double[] elements, int k)
	{
		if(elements.length == 1)
		{
			return elements[0];
		}
		
		int rand = (int)(Math.random() * elements.length);
		double x = elements[rand];
		ArrayList<Double> l = new ArrayList<Double>();
		ArrayList<Double> e = new ArrayList<Double>();
		ArrayList<Double> g = new ArrayList<Double>();
		for(int i=0; i<elements.length; i++)
		{
			if(elements[i] < x)
				l.add(elements[i]);
			else if(elements[i] == x)
				e.add(elements[i]);
			else
				g.add(elements[i]);
		}
		
		if(k <= l.size())
		{
			return quickSelect(l.toArray(new Double[0]), k);
		}
		else if(k <= (l.size() + e.size()))
		{
			return x;
		}
		else
		{
			return quickSelect(g.toArray(new Double[0]), k - l.size() - e.size());
		}
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	private String name = "SingleLink"; 
	private ArrayList<Cluster> clusters; //Holds the clusters
	private int numOfClusters;
	private int endingClusterCount; //Holds the number of clusters the algorithm will stop at
}
