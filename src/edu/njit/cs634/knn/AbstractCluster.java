package edu.njit.cs634.knn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public abstract class AbstractCluster implements Cluster {

	/**
	 * This method adds the Vector v to this Cluster if it is not already a part of the
	 * cluster.  If the Vector was already part of another Cluster, all the Vectors in that
	 * Cluster are also added to this Cluster.  Every Vector added to the Cluster has it's 
	 * reference to the Cluster updated with this Cluster.
	 * @param v - the Vector that will be added to the Cluster
	 * @return a boolean that is true if the Vector was added to the Cluster or false
	 * if the Vector was already part of the cluster or if it was unable to add the Vector
	 */
	@Override
	public boolean add(Vector v)
	{
		if(v != null)
		{
			//Only add the new vector if it's not already part of the cluster
			if(clusteredVectors.get(v.getVectorID()) == null)
			{
				Vector vectorsToAdd[];
				Cluster c = ((PointsVector) v).getCluster();
				if(c != null)
				{
					//Get all the vectors in v's cluster
					vectorsToAdd = ((AbstractCluster)c).getVectors();
				}
				else
				{
					vectorsToAdd = new Vector[1];
					vectorsToAdd[0] = v;
				}
				for(int i=0; i<vectorsToAdd.length; i++)
				{
					PointsVector pv = (PointsVector)vectorsToAdd[i];
					//Update the Vectors references to the cluster they belong to
					pv.setCluster(this);
					//Add the Vector to this cluster
					clusteredVectors.put(pv.getVectorID(), pv);
					size++;
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method converts the vectors in this cluster to an array of Vectors.
	 * @return an array of Vectors
	 */
	public Vector[] getVectors()
	{
		ArrayList<Vector> vArray = new ArrayList<Vector>();
		Set<Integer> keys = clusteredVectors.keySet();
		for(Integer i: keys)
		{
			vArray.add(clusteredVectors.get(i));
		}
		return vArray.toArray(new Vector[0]);
	}
	
	@Override
	public boolean contains(Vector v) 
	{
		if(clusteredVectors.get(v.getVectorID()) != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("[");
		Set<Integer> keys = clusteredVectors.keySet();
		for(Integer i: keys)
		{
			PointsVector v = (PointsVector) clusteredVectors.get(i);
			buffer.append(v.toString());
		}
		buffer.append("]");
		return buffer.toString();
	}
	
	public int getID() {
		return id;
	}
	
	public int size() {
		return size;
	}

	public HashMap<Integer, Vector> getClusteredVectors() {
		return clusteredVectors;
	}

	public void setClusteredVectors(HashMap<Integer, Vector> clusteredVectors) {
		this.clusteredVectors = clusteredVectors;
	}


	protected int id;
	//protected int level;
	protected int size = 0;
	protected HashMap<Integer, Vector> clusteredVectors;
}
