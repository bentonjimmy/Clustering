package edu.njit.cs634.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.njit.cs634.knn.ConcretePointsTable;
import edu.njit.cs634.knn.PointsVector;
import edu.njit.cs634.knn.Vector;

public class PointsTableTests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void simpleDistanceTest()
	{
		Vector[] points = new Vector[2];
		PointsVector pv1 = new PointsVector();
		PointsVector pv2 = new PointsVector();
		pv1.setX(1);
		pv1.setY(1);
		pv1.setZ(1);
		pv2.setX(3);
		pv2.setY(3);
		pv2.setZ(2);
		points[0] = pv1;
		points[1] = pv2;
		ConcretePointsTable pointstable = new ConcretePointsTable(points);
		/*
		Vector[] varray = new Vector[2];
		varray[0] = pv1;
		varray[1] = pv2;
		pointstable.setVectors(varray);
		pointstable.measureDistance();
		*/
		double[][] table = pointstable.getDistanceTable();
		for(int i=0; i<2; i++)
		{
			for(int j=0; j<2; j++)
			{
				if(i==j)
				{
					assertTrue("Distance will be 0", table[i][j] == 0);
				}
				else
				{
					assertTrue("Distance will be 3", table[i][j] == 3);
				}
				System.out.print(table[i][j]+" ");
			}
			System.out.print("\n");
		}
		
	}

}
