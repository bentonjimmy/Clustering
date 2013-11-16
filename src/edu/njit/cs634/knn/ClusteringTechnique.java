package edu.njit.cs634.knn;

import java.util.ArrayList;

/**
 * The interface used to define method used by the four
 * different clustering techniques.
 * @author jmb66
 *
 */
public interface ClusteringTechnique {

	public ArrayList<Cluster> assignClusters();
	public void receivePointsTable(PointsTable pt);
	public String getName();
	public void setName(String name);
	
}
