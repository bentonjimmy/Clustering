package edu.njit.cs634.knn;

import java.util.HashMap;

public class AHCluster extends AbstractCluster {
	
	public AHCluster()
	{
		clusteredVectors = new HashMap<Integer, Vector>();
		id = ++numOfClusters;
	}

	private static int numOfClusters = 0;
	
	

}
