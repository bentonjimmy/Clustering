package edu.njit.cs634.knn;

/**
 * 
 * @author jmb66
 *
 */
public class ConcretePointsTable extends AbstractPointsTable {

	public ConcretePointsTable(Vector[] vectors)
	{
		this.vectors = vectors;
		this.tableSize =vectors.length;
		distanceTable = new double [this.tableSize][this.tableSize];
		measureDistance();
	}
	
	/**
	 * This method will iterate through the vectors array and measure the distance
	 * between each vector.
	 */
	@Override
	public void measureDistance()
	{
		for(int i=0; i<this.tableSize; i++)
		{
			for(int j=0; j<this.tableSize; j++)
			{
				//This will call MeasureDistance below
				distanceTable[i][j] = measureDistance(vectors[i], vectors[j]);
			}
		}
	}
	
	/**
	 * Measures the euclidean distance between two vectors and return the distance.
	 * @param v1 - a Vector
	 * @param v2 - a Vector
	 * @return a double representing the distance between the two vectors
	 */
	protected double measureDistance(Vector v1, Vector v2) 
	{
		PointsVector pv1 = (PointsVector)v1;
		PointsVector pv2 = (PointsVector)v2;
		
		//Measure the Euclidean distance
		return Math.sqrt(Math.pow((pv1.getX() - pv2.getX()), 2) +
				Math.pow((pv1.getY() - pv2.getY()), 2) + 
				Math.pow((pv1.getZ() - pv2.getZ()), 2));
		
	}

}
