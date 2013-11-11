package edu.njit.cs634.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.njit.cs634.knn.PointsVector;

public class PointsVectorTests {

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
	public void fillPointsTest()
	{
		PointsVector pv = new PointsVector();
		PointsVector pv2;
		assertTrue("Test initial x", pv.getX() == 0);
		assertTrue("Test initial y", pv.getY() == 0);
		assertTrue("Test initial z", pv.getZ() == 0);
		pv.fillPoints();
		System.out.println(pv.toString());
		assertTrue("Very small chance this value is still 0 - x", pv.getX() != 0);
		assertTrue("Very small chance this value is still 0 - y", pv.getY() != 0);
		assertTrue("Very small chance this value is still 0 - z", pv.getZ() != 0);
		
		pv2 = new PointsVector();
		assertTrue("Test initial x", pv2.getX() == 0);
		assertTrue("Test initial y", pv2.getY() == 0);
		assertTrue("Test initial z", pv2.getZ() == 0);
		pv2.fillPoints();
		System.out.println(pv2.toString());
		assertTrue("Very small chance this value is still 0 - x", pv2.getX() != 0);
		assertTrue("Very small chance this value is still 0 - y", pv2.getY() != 0);
		assertTrue("Very small chance this value is still 0 - z", pv2.getZ() != 0);
	}

}
