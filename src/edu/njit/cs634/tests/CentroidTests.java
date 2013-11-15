package edu.njit.cs634.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.njit.cs634.knn.AHCluster;
import edu.njit.cs634.knn.Cluster;
import edu.njit.cs634.knn.GroupAverageClustering;
import edu.njit.cs634.knn.PointsVector;

public class CentroidTests {

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
	public void simpleCompleteTest() {
		PointsVector pv1 = new PointsVector(40, 53, 1);
		PointsVector pv2 = new PointsVector(22, 38, 1);
		PointsVector pv3 = new PointsVector(36, 32, 1);
		PointsVector pv4 = new PointsVector(26, 19, 1);
		PointsVector pv5 = new PointsVector(8, 41, 1);
		PointsVector pv6 = new PointsVector(45, 30, 1);
		
		PointsVector[] vectors = new PointsVector[6];
		vectors[0] = pv1;
		vectors[1] = pv2;
		vectors[2] = pv3;
		vectors[3] = pv4;
		vectors[4] = pv5;
		vectors[5] = pv6;
		
		GroupAverageClustering slc = new GroupAverageClustering(6, vectors);
		ArrayList<Cluster> clusters = slc.assignClusters();
		assertTrue("Check number of clusters - 6", clusters.size() == 6);
		printClusters(clusters, "Test: simpleCompleteTest - Clusters:");
	}
	
	@Test
	public void createThreeClusters()
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
		
		//Check which clusters have which Vectors
		
		GroupAverageClustering slc = new GroupAverageClustering(3, vectors);
		ArrayList<Cluster> clusters = slc.assignClusters();
		assertTrue("Check number of clusters - 3", clusters.size() == 3);
		printClusters(clusters, "Test: createThreeClusters - Clusters:");
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
		
		//Check which clusters have which Vectors
		
		GroupAverageClustering slc = new GroupAverageClustering(2, vectors);
		ArrayList<Cluster> clusters = slc.assignClusters();
		assertTrue("Check number of clusters - 2", clusters.size() == 2);
		printClusters(clusters, "Test: createTwoClusters - Clusters:");
	}
	
	@Test
	public void createThreeClusters2()
	{
		PointsVector pv1 = new PointsVector(34, 48, 59);
		PointsVector pv2 = new PointsVector(70, 72, 95);
		PointsVector pv3 = new PointsVector(3, 27, 3);
		PointsVector pv4 = new PointsVector(66, 14, 40);
		PointsVector pv5 = new PointsVector(2, 22, 61);
		PointsVector pv6 = new PointsVector(78, 59, 11);
		
		PointsVector[] vectors = new PointsVector[6];
		vectors[0] = pv1;
		vectors[1] = pv2;
		vectors[2] = pv3;
		vectors[3] = pv4;
		vectors[4] = pv5;
		vectors[5] = pv6;
		
		//Check which clusters have which Vectors
		
		GroupAverageClustering slc = new GroupAverageClustering(3, vectors);
		ArrayList<Cluster> clusters = slc.assignClusters();
		assertTrue("Check number of clusters - 3", clusters.size() == 3);
		printClusters(clusters, "Test: createThreeClusters2 - Clusters:");
	}
	
	@Test
	public void createTwoClusters2()
	{
		PointsVector pv1 = new PointsVector(34, 48, 59);
		PointsVector pv2 = new PointsVector(70, 72, 95);
		PointsVector pv3 = new PointsVector(3, 27, 3);
		PointsVector pv4 = new PointsVector(66, 14, 40);
		PointsVector pv5 = new PointsVector(2, 22, 61);
		PointsVector pv6 = new PointsVector(78, 59, 11);
		
		PointsVector[] vectors = new PointsVector[6];
		vectors[0] = pv1;
		vectors[1] = pv2;
		vectors[2] = pv3;
		vectors[3] = pv4;
		vectors[4] = pv5;
		vectors[5] = pv6;
		
		//Check which clusters have which Vectors
		
		GroupAverageClustering slc = new GroupAverageClustering(2, vectors);
		ArrayList<Cluster> clusters = slc.assignClusters();
		assertTrue("Check number of clusters - 2", clusters.size() == 2);
		printClusters(clusters, "Test: createTwoClusters2 - Clusters:");
	}

	protected void printClusters(ArrayList<Cluster> clusters, String message)
	{
		System.out.println(message);
		for(Cluster c: clusters)
		{
			System.out.print(((AHCluster) c).toString());
			System.out.print("\n");
		}
	}

}
