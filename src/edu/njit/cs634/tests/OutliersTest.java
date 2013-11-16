package edu.njit.cs634.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.njit.cs634.knn.DistanceOutliers;
import edu.njit.cs634.knn.PointsVector;

public class OutliersTest {

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
	public void noOutliers()
	{
		PointsVector pv1 = new PointsVector(81, 76, 2);
		PointsVector pv2 = new PointsVector(27, 92, 10);
		PointsVector pv3 = new PointsVector(40, 35, 63);
		PointsVector pv4 = new PointsVector(17, 56, 67);
		PointsVector pv5 = new PointsVector(62, 78, 77);
		PointsVector pv6 = new PointsVector(24, 12, 43);
		
		PointsVector[] vectors = new PointsVector[6];
		vectors[0] = pv1;
		vectors[1] = pv2;
		vectors[2] = pv3;
		vectors[3] = pv4;
		vectors[4] = pv5;
		vectors[5] = pv6;
		
		DistanceOutliers detector = new DistanceOutliers(.80, 100, vectors);
		PointsVector[] outliers = detector.getOutliers();
		PointsVector[] nonOutliers = detector.getNonOutliers();
		
		assertTrue("There should be no outliers", outliers.length == 0);
		assertTrue("The dataset should still be 6", nonOutliers.length == 6);
		
	}
	
	@Test
	public void oneOutlier()
	{
		PointsVector pv1 = new PointsVector(81, 76, 2);
		PointsVector pv2 = new PointsVector(27, 92, 10);
		PointsVector pv3 = new PointsVector(40, 35, 63);
		PointsVector pv4 = new PointsVector(17, 56, 67);
		PointsVector pv5 = new PointsVector(62, 78, 77);
		PointsVector pv6 = new PointsVector(24, 12, 43);
		PointsVector pv7 = new PointsVector(1000, 1000, 1000);
		
		PointsVector[] vectors = new PointsVector[7];
		vectors[0] = pv1;
		vectors[1] = pv2;
		vectors[2] = pv3;
		vectors[3] = pv4;
		vectors[4] = pv5;
		vectors[5] = pv6;
		vectors[6] = pv7;
		
		DistanceOutliers detector = new DistanceOutliers(.80, 100, vectors);
		PointsVector[] outliers = detector.getOutliers();
		PointsVector[] nonOutliers = detector.getNonOutliers();
		
		assertTrue("There should be one outlier", outliers.length == 1);
		System.out.println("Outliers: ");
		for(int i=0; i<outliers.length; i++)
		{
			System.out.println(outliers[i]);
		}
		assertTrue("The dataset should be 6", nonOutliers.length == 6);
		System.out.println("Non-Outliers: ");
		for(int i=0; i<nonOutliers.length; i++)
		{
			System.out.println(nonOutliers[i]);
		}
		
	}
	
	@Test
	public void twoOutlier()
	{
		PointsVector pv1 = new PointsVector(1000, 0, 1000);
		PointsVector pv2 = new PointsVector(81, 76, 2);
		PointsVector pv3 = new PointsVector(27, 92, 10);
		PointsVector pv4 = new PointsVector(40, 35, 63);
		PointsVector pv5 = new PointsVector(17, 56, 67);
		PointsVector pv6 = new PointsVector(62, 78, 77);
		PointsVector pv7 = new PointsVector(24, 12, 43);
		PointsVector pv8 = new PointsVector(1000, 1000, 1000);
		
		PointsVector[] vectors = new PointsVector[8];
		vectors[0] = pv1;
		vectors[1] = pv2;
		vectors[2] = pv3;
		vectors[3] = pv4;
		vectors[4] = pv5;
		vectors[5] = pv6;
		vectors[6] = pv7;
		vectors[7] = pv8;
		
		DistanceOutliers detector = new DistanceOutliers(.80, 100, vectors);
		PointsVector[] outliers = detector.getOutliers();
		PointsVector[] nonOutliers = detector.getNonOutliers();
		
		assertTrue("There should be one outlier", outliers.length == 2);
		System.out.println("Outliers: ");
		for(int i=0; i<outliers.length; i++)
		{
			System.out.println(outliers[i]);
		}
		assertTrue("The dataset should be 6", nonOutliers.length == 6);
		System.out.println("Non-Outliers: ");
		for(int i=0; i<nonOutliers.length; i++)
		{
			System.out.println(nonOutliers[i]);
		}
		
	}
	
	@Test
	public void manyOutlier()
	{
		PointsVector pv1 = new PointsVector(1000, 0, 1000);
		PointsVector pv2 = new PointsVector(81, 76, 2);
		PointsVector pv3 = new PointsVector(27, 92, 10);
		PointsVector pv4 = new PointsVector(40, 35, 63);
		PointsVector pv5 = new PointsVector(17, 56, 67);
		PointsVector pv6 = new PointsVector(62, 78, 77);
		PointsVector pv7 = new PointsVector(24, 12, 43);
		PointsVector pv8 = new PointsVector(1000, 1000, 1000);
		
		PointsVector[] vectors = new PointsVector[8];
		vectors[0] = pv1;
		vectors[1] = pv2;
		vectors[2] = pv3;
		vectors[3] = pv4;
		vectors[4] = pv5;
		vectors[5] = pv6;
		vectors[6] = pv7;
		vectors[7] = pv8;
		
		DistanceOutliers detector = new DistanceOutliers(.50, 70, vectors);
		PointsVector[] outliers = detector.getOutliers();
		PointsVector[] nonOutliers = detector.getNonOutliers();
		
		//assertTrue("There should be one outlier", outliers.length == 2);
		System.out.println("Outliers: ");
		for(int i=0; i<outliers.length; i++)
		{
			System.out.println(outliers[i]);
		}
		//assertTrue("The dataset should be 6", nonOutliers.length == 6);
		System.out.println("Non-Outliers: ");
		for(int i=0; i<nonOutliers.length; i++)
		{
			System.out.println(nonOutliers[i]);
		}
		
	}

}
