package edu.njit.cs634.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.njit.cs634.knn.CentroidClustering;
import edu.njit.cs634.knn.Cluster;
import edu.njit.cs634.knn.CompleteLinkClustering;
import edu.njit.cs634.knn.GroupAverageClustering;
import edu.njit.cs634.knn.PointsVector;
import edu.njit.cs634.knn.SingleLinkClustering;

public class SCTests {

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
	public void groupAvgTest() 
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
		
		GroupAverageClustering slc = new GroupAverageClustering(3, vectors);
		ArrayList<Cluster> clusters = slc.assignClusters();
		assertTrue("Check number of clusters - 3", clusters.size() == 3);
		System.out.println("Silhouette Coefficient: " + slc.evaluateClusters());
	}
	
	@Test
	public void createTwoClusters()
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
		
		GroupAverageClustering slc = new GroupAverageClustering(2, vectors);
		ArrayList<Cluster> clusters = slc.assignClusters();
		assertTrue("Check number of clusters - 2", clusters.size() == 2);
		System.out.println("Silhouette Coefficient: " + slc.evaluateClusters());
	}
	

	@Test
	public void testMultipleClustering()
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
		
		GroupAverageClustering gac = new GroupAverageClustering(3, vectors);
		gac.assignClusters();
		System.out.println("GAC Silhouette Coefficient: " + gac.evaluateClusters());
		
		pv1 = new PointsVector(81, 76, 2);
		pv2 = new PointsVector(27, 92, 10);
		pv3 = new PointsVector(40, 35, 63);
		pv4 = new PointsVector(17, 56, 67);
		pv5 = new PointsVector(62, 78, 77);
		pv6 = new PointsVector(24, 12, 43);
		vectors[0] = pv1;
		vectors[1] = pv2;
		vectors[2] = pv3;
		vectors[3] = pv4;
		vectors[4] = pv5;
		vectors[5] = pv6;
		
		CentroidClustering cc = new CentroidClustering(3, vectors);
		cc.assignClusters();
		System.out.println("CC Silhouette Coefficient: " + cc.evaluateClusters());
		
		pv1 = new PointsVector(81, 76, 2);
		pv2 = new PointsVector(27, 92, 10);
		pv3 = new PointsVector(40, 35, 63);
		pv4 = new PointsVector(17, 56, 67);
		pv5 = new PointsVector(62, 78, 77);
		pv6 = new PointsVector(24, 12, 43);
		vectors[0] = pv1;
		vectors[1] = pv2;
		vectors[2] = pv3;
		vectors[3] = pv4;
		vectors[4] = pv5;
		vectors[5] = pv6;
		
		SingleLinkClustering slc = new SingleLinkClustering(3, vectors);
		slc.assignClusters();
		System.out.println("SLC Silhouette Coefficient: " + slc.evaluateClusters());
		
		pv1 = new PointsVector(81, 76, 2);
		pv2 = new PointsVector(27, 92, 10);
		pv3 = new PointsVector(40, 35, 63);
		pv4 = new PointsVector(17, 56, 67);
		pv5 = new PointsVector(62, 78, 77);
		pv6 = new PointsVector(24, 12, 43);
		vectors[0] = pv1;
		vectors[1] = pv2;
		vectors[2] = pv3;
		vectors[3] = pv4;
		vectors[4] = pv5;
		vectors[5] = pv6;
		
		CompleteLinkClustering clc = new CompleteLinkClustering(3, vectors);
		clc.assignClusters();
		System.out.println("CLC Silhouette Coefficient: " + clc.evaluateClusters());
	}
	
}
