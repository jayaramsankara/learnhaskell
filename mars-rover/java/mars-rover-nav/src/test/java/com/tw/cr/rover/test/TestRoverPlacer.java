package com.tw.cr.rover.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tw.cr.rover.MarsRover;
import com.tw.cr.rover.RoverException;
import com.tw.cr.rover.RoverPlacer;
import com.tw.cr.rover.model.LocationGrid;

/**
 * Tests the methods of the class RoverPlacer
 * @author jayaram_s
 *
 */
public class TestRoverPlacer 
{
	
	private ArrayList<String> commands = null;
	private ArrayList<String> invalidcommands = null;
	private ArrayList<String> results = null;
	private LocationGrid grid = null;
		
	@Before public void initData()
	{
		grid = new LocationGrid(5,5);
		invalidcommands = new ArrayList<String>(4);
		invalidcommands.add("1 2 N");
		invalidcommands.add("LMLMLMLMM");
		invalidcommands.add("X Y N");
		invalidcommands.add("WWW");
		
		commands = new ArrayList<String>(4);
		commands.add("1 2 N");
		commands.add("LMLMLMLMM");
		commands.add("3 3 E");
		commands.add("MMRMMRMRRM");
		
		results = new ArrayList<String>(2);
		results.add("1 3 N");
		results.add("5 1 E");
	}
	
	@After public void cleanupData()
	{
		invalidcommands.clear();
		invalidcommands  = null;
		
		commands.clear();
		commands = null;
		
		results.clear();
		results = null;
	}
	
	@Test
	public void testProcessCommands1() throws RoverException 
	{
		System.out.println("\nTest case #1:");
		RoverPlacer objPlacer = new RoverPlacer(grid);
		ArrayList<String> cmdResults = new ArrayList<String>();
		ArrayList<MarsRover> rovers = objPlacer.processCommands(commands,cmdResults);
		if(rovers == null)
		{
			fail("Failed to process commands.");
		}
		else
		{
			if(rovers.size() != 2)
			{
				fail("Commands not processed correctly. Number of rovers is not 2");
			}
			else
			{
				String position1 = rovers.get(0).getPosition().toString();
				String position2 = rovers.get(1).getPosition().toString();
				
				System.out.println(position1);
				System.out.println(position2);
				
				assertEquals( position1,results.get(0));
				assertEquals( position2,results.get(1));
			}
		}
	}
	
	@Test
	public void testProcessCommands2() throws RoverException 
	{
		System.out.println("\nTest case #2:");
		RoverPlacer objPlacer = new RoverPlacer(grid);
		ArrayList<String> cmdResults = new ArrayList<String>();
		ArrayList<MarsRover> rovers = objPlacer.processCommands(invalidcommands,cmdResults);
		if(rovers == null)
		{
			fail("Failed to process commands.");
		}
		else
		{
			if(rovers.size() != 1)
			{
				fail("Commands not processed correctly. Number of rovers is not 1");
			}
			else
			{
				String position1 = rovers.get(0).getPosition().toString();
				assertEquals( position1,results.get(0));
				System.out.println(position1);
				System.out.println(cmdResults.get(1));
				
			}
		}
	}


	

}
