package edu.njit.cs634.knn;

public class ConcretePointsTable extends AbstractPointsTable {

	public ConcretePointsTable(int tableSize)
	{
		this.tableSize = tableSize;
		vectorTable = new PointsVector[this.tableSize][this.tableSize];
	}
	
	public void fillTable()
	{
		PointsVector pv;
		for(int i=0; i<this.tableSize; i++)
		{
			for(int j=0; j<this.tableSize; j++)
			{
				pv = new PointsVector();
				pv.fillPoints();
				vectorTable[i][j] = pv;
			}
		}
	}
	
	public void MeasureDistance()
	{
		
	}
	
	@Override
	public double MeasureDistance(Vector v1, Vector v2) 
	{
		PointsVector pv1 = (PointsVector)v1;
		PointsVector pv2 = (PointsVector)v2;
		
		//Measure the Euclidean distance
		return Math.sqrt(Math.pow((pv1.getX() - pv2.getX()), 2) +
				Math.pow((pv1.getY() - pv2.getY()), 2) + 
				Math.pow((pv1.getZ() - pv2.getZ()), 2));
		
	}

}
