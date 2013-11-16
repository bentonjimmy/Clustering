package edu.njit.cs634.knn;

import java.util.HashMap;

public interface Cluster {

	public boolean add(Vector v);
	public boolean contains(Vector v);
	public Vector[] getVectors();
	public HashMap<Integer, Vector> getClusteredVectors();
	public void setClusteredVectors(HashMap<Integer, Vector> clusteredVectors);
	public int size();
	public int getID();
}
