package edu.njit.cs634.knn;

public interface Cluster {

	public boolean add(Vector v);
	public boolean contains(Vector v);
}
