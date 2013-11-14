package edu.njit.cs634.knn;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class AbstractPointsTable implements PointsTable {

	public double[][] getDistanceTable() {
		for(int i=0; i<weightsTable.size(); i++)
		{
			ArrayList<WeightedEdge> temp = weightsTable.get(i);
			for(int j=0; j<temp.size(); j++)
			{
				WeightedEdge we = temp.get(j);
				distanceTable[i][j] = we.getWeight();
			}
		}
		return distanceTable;
	}
	/*
	public void setDistanceTable(double[][] distanceTable) {
		this.distanceTable = distanceTable;
	}
	*/
	public Vector[] getVectors() {
		return vectors;
	}
	public void setVectors(Vector[] vectors) {
		this.vectors = vectors;
	}
	public int getTableSize() {
		return tableSize;
	}
	public void setTableSize(int tableSize) {
		this.tableSize = tableSize;
	}
	
	public ArrayList<ArrayList<WeightedEdge>> getWeightsTable() {
		return weightsTable;
	}
	public void setWeightsTable(ArrayList<ArrayList<WeightedEdge>> weightsTable) {
		this.weightsTable = weightsTable;
	}

	protected ArrayList<ArrayList<WeightedEdge>> weightsTable;
	protected double[][] distanceTable;
	protected Vector[] vectors;
	protected int tableSize;
}
