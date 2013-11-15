package edu.njit.cs634.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.njit.cs634.knn.AHCluster;
import edu.njit.cs634.knn.PointsVector;
import edu.njit.cs634.knn.Vector;

public class ClusterTests {

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
	public void simpleTest() {
		AHCluster cluster = new AHCluster();
		PointsVector pv1 = new PointsVector(40, 53, 1);
		PointsVector pv2 = new PointsVector(22, 38, 1);
		PointsVector pv3 = new PointsVector(35, 32, 1);
		boolean result;
		
		assertTrue("Check initial size", cluster.size() == 0);
		result = cluster.add(pv1);
		assertTrue("Check that pv1 was successfully added to the cluster", result == true);
		assertTrue("Check initial after one add", cluster.size() == 1);
		
		result = cluster.add(pv2);
		assertTrue("Check that pv2 was successfully added to the cluster", result == true);
		assertTrue("Check initial after two add", cluster.size() == 2);
		
		result = cluster.add(pv3);
		assertTrue("Check that pv3 was successfully added to the cluster", result == true);
		assertTrue("Check initial after three add", cluster.size() == 3);
		
		result = cluster.add(pv2);
		assertTrue("pv2 is already in this cluster, expecting false", result == false);
		assertTrue("Check initial after duplicate add", cluster.size() == 3);
		
		Vector[] vectors = cluster.getVectors();
		assertTrue("Expecting to receive three Vectors", vectors.length == 3);
		for(int i=0; i< vectors.length; i++)
		{
			System.out.println(vectors[i].toString());
		}
		
	}
	
	@Test
	public void clusteringMultipleClusters()
	{
		AHCluster cluster1 = new AHCluster();
		AHCluster cluster2 = new AHCluster();
		PointsVector pv1 = new PointsVector(40, 53, 1);
		PointsVector pv2 = new PointsVector(22, 38, 1);
		PointsVector pv3 = new PointsVector(35, 32, 1);
		PointsVector pv4 = new PointsVector(26, 19, 1);
		PointsVector pv5 = new PointsVector(8, 41, 1);
		PointsVector pv6 = new PointsVector(45, 30, 1);
		PointsVector pv7 = new PointsVector(26, 19, 1);
		PointsVector pv8 = new PointsVector(8, 41, 1);
		PointsVector pv9 = new PointsVector(45, 30, 1);
		boolean result;
		
		cluster1.add(pv1);
		cluster1.add(pv2);
		cluster1.add(pv3);
		cluster2.add(pv4);
		cluster2.add(pv5);
		cluster2.add(pv6);
		
		assertTrue("Check initial size of cluster1", cluster1.size() == 3);
		assertTrue("Check initial size of cluster2", cluster2.size() == 3);
		cluster1.add(pv5);
		assertTrue("Check size of cluster1 after adding a point from cluster2", cluster1.size() == 6);
		Vector[] vectors = cluster1.getVectors();
		assertTrue("Expecting to receive six Vectors", vectors.length == 6);
		for(int i=0; i< vectors.length; i++)
		{
			System.out.println(vectors[i].toString());
		}
	}

}
