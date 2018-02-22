package com.tw.cr.rover.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tw.cr.rover.MarsRover;
import com.tw.cr.rover.RoverException;
import com.tw.cr.rover.model.Cordinates;
import com.tw.cr.rover.model.Direction;
import com.tw.cr.rover.model.LocationGrid;
import com.tw.cr.rover.model.RoverPos;

/**
 * Tests the methods of the class MarsRover
 * @author jayaram_s
 *
 */
public class TestMarsRover 
{

	private MarsRover rover = null;
	
	@Before public void initData()
	{
		rover= new MarsRover();
		LocationGrid grid = new LocationGrid(10,10);
		RoverPos position = new RoverPos(new Cordinates(5,5),Direction.North);
		rover.setLocationGridAndPos(grid,position);
	}
	
	@After public void clear()
	{
		rover=null;
	}
	
	@Test
	public void testSetLocationGridAndPos()
	{
		boolean bException = false;
		LocationGrid grid = new LocationGrid(10,10);
		RoverPos position = new RoverPos(new Cordinates(15,5),Direction.North);
		
		try
		{
			rover.setLocationGridAndPos(grid,position);
		}
		catch(IllegalArgumentException iae)
		{
			bException = true;
		}
		assertTrue(bException);
		
		bException = false;
		position.setCordVal(new Cordinates(10,15));
		try
		{
			rover.setLocationGridAndPos(grid,position);
		}
		catch(IllegalArgumentException iae)
		{
			bException = true;
		}
		assertTrue(bException);
		
		bException = false;
		position.setCordVal(new Cordinates(10,10));
		try
		{
			rover.setLocationGridAndPos(grid,position);
		}
		catch(IllegalArgumentException iae)
		{
			bException = true;
		}
		assertFalse(bException);
		
		bException = false;
		position.setCordVal(new Cordinates(0,0));
		try
		{
			rover.setLocationGridAndPos(grid,position);
		}
		catch(IllegalArgumentException iae)
		{
			bException = true;
		}
		assertFalse(bException);
		
		
	}

	@Test
	public void testMove() 
	{
		LocationGrid grid = new LocationGrid(10,10);
		RoverPos position = new RoverPos(new Cordinates(5,5),Direction.North);
		
		rover.setLocationGridAndPos(grid,position);
		boolean bException =false;
		try 
		{
			rover.move();
		}
		catch (RoverException e) 
		{
			bException = true;
		}
		assertFalse(bException);
		position.setCordVal(new Cordinates(10,10));
		bException =false;
		try 
		{
			rover.move();
		}
		catch (RoverException e) 
		{
			bException = true;
		}
		assertTrue(bException);
		
		
	}

}
