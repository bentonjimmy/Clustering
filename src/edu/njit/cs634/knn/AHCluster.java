package edu.njit.cs634.knn;

import java.util.HashMap;

/**
 * This class represents a concrete representation of a cluster.  Each cluster
 * is assigned an id to identify it.
 * @author Jim Benton
 *
 */
public class AHCluster extends AbstractCluster {
	
	public AHCluster()
	{
		clusteredVectors = new HashMap<Integer, Vector>();
		id = ++numOfClusters;
	}

	private static int numOfClusters = 0;
	
	

}
