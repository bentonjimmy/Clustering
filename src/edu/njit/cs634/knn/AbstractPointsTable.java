package edu.njit.cs634.knn;

public abstract class AbstractPointsTable implements PointsTable {

	public double[][] getDistanceTable() {
		return distanceTable;
	}
	public void setDistanceTable(double[][] distanceTable) {
		this.distanceTable = distanceTable;
	}
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
	
	protected double[][] distanceTable;
	protected Vector[] vectors;
	protected int tableSize;
}
