package edu.njit.cs634.knn;

import java.util.ArrayList;

public class ClusteringKit {
	
	public ClusteringKit() {}
	
	public int addClusteringTechnique(ClusteringTechnique ct)
	{
		if(ct != null)
		{
			techniques.add(ct);
			return (techniques.size() - 1);
		}
		return -1;
	}
	
	public int getKitCount()
	{
		return techniques.size();
	}
	
	public ClusteringTechnique getTechnique(int i)
	{
		if(i >= 0 && i < techniques.size())
		{
			return techniques.get(i);
		}
		return null;
	}

	public ClusteringTechnique findTechnique(String name)
	{
		if(name != null)
		{
			for(int i=0; i<techniques.size(); i++)
			{
				ClusteringTechnique ct = techniques.get(i);
				if(name.equals(ct.getName()))
				{
					return ct;
				}
			}
		}
		return null;
	}
	
	public void setSelectedTechnique(int i)
	{
		ClusteringTechnique ct = techniques.get(i);
		if(ct != null)
		{
			selectedTechnique = ct;
		}
	}
	
	public ClusteringTechnique setSelectedTechnique(String name)
	{
		ClusteringTechnique ct = findTechnique(name);
		if(ct != null)
		{
			selectedTechnique = ct;
		}
		return ct;
	}
	
	public void setSelectedTechnique(ClusteringTechnique ct)
	{
		selectedTechnique = ct;
	}
	
	public ClusteringTechnique getClusteringTechnique()
	{
		return selectedTechnique;
	}
	
	protected ArrayList<ClusteringTechnique> techniques = new ArrayList<ClusteringTechnique>();
	protected ClusteringTechnique selectedTechnique = null;
}
