package edu.njit.cs634.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CentroidTests.class, ClusterTests.class,
		CompleteLinkTest.class, GroupAverageTests.class,
		PointsTableTests.class, PointsVectorTests.class, SingleLinkTest.class })
public class AllTests {

}
