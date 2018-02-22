package com.tw.cr.rover.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.tw.cr.rover.model.test.TestCordinates;
import com.tw.cr.rover.model.test.TestDirection;
import com.tw.cr.rover.model.test.TestLocationGrid;
import com.tw.cr.rover.model.test.TestRoverPos;

@RunWith(Suite.class)

@Suite.SuiteClasses ({ TestCordinates.class,TestDirection.class,
	TestLocationGrid.class,TestRoverPos.class,TestMarsRover.class,
	TestRoverPlacer.class	
}
)

/**
 * Test suite that runs all the test cases for mars-rover-nav
 * @author jayaram_s
 */
public class AllTests {

	
}
