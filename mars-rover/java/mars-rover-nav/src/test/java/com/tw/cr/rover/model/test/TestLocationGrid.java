package com.tw.cr.rover.model.test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tw.cr.rover.model.Cordinates;
import com.tw.cr.rover.model.Direction;
import com.tw.cr.rover.model.LocationGrid;
import com.tw.cr.rover.model.RoverPos;


/**
 * Tests the methods of the class LocationGrid
 * @author jayaram_s
 *
 */
public class TestLocationGrid 
{
	private LocationGrid grid = null;
	
	
	@Before public void setUp()
	{
		grid = new LocationGrid(10,10);
	}

	
	@After	public void tearDown()  {
		grid=null;
		
	}
	
	/**
	 * Test method for {@link com.tw.cr.rover.model.LocationGrid#canMove(RoverPos)}
	 */
	@Test	public void testCanMoveUp()
	{
			
		RoverPos objPos = new RoverPos();
		
		Cordinates objCord = new Cordinates(5,9);
		Direction dirVal = Direction.North;
		
		objPos.setCordVal(objCord);
		objPos.setDirVal(dirVal);
		
		assertTrue(grid.canMove(objPos));
		objCord.moveXorY(Direction.North);
		assertTrue(!grid.canMove(objPos));
		objCord.moveXorY(Direction.North);
		assertTrue(!grid.canMove(objPos));
		objCord.moveXorY(Direction.South);
		assertTrue(!grid.canMove(objPos));
		objCord.moveXorY(Direction.South);
		assertTrue(grid.canMove(objPos));
		objCord.moveXorY(Direction.South);
		assertTrue(grid.canMove(objPos));
		
		
	}
	
	/**
	 * Test method for {@link com.tw.cr.rover.model.LocationGrid#canMove(RoverPos)}
	 */
	@Test public void testCanMoveDown()
	{
				
		RoverPos objPos = new RoverPos();
		
		Cordinates objCord = new Cordinates(9,1);
		Direction dirVal = Direction.South;
		
		objPos.setCordVal(objCord);
		objPos.setDirVal(dirVal);
		
		assertTrue(grid.canMove(objPos));
		objCord.moveXorY(Direction.South);
		assertTrue(!grid.canMove(objPos));
		objCord.moveXorY(Direction.South);
		assertTrue(!grid.canMove(objPos));
		objCord.moveXorY(Direction.North);
		assertTrue(!grid.canMove(objPos));
		objCord.moveXorY(Direction.North);
		assertTrue(grid.canMove(objPos));
		objCord.moveXorY(Direction.North);
		assertTrue(grid.canMove(objPos));
		
	}
	
	/**
	 * Test method for {@link com.tw.cr.rover.model.LocationGrid#canMove(RoverPos)}
	 */
	@Test public void testCanMoveLeft()
	{
			
		RoverPos objPos = new RoverPos();
		
		Cordinates objCord = new Cordinates(1,9);
		Direction dirVal = Direction.West;
		
		objPos.setCordVal(objCord);
		objPos.setDirVal(dirVal);
		
		assertTrue(grid.canMove(objPos));
		objCord.moveXorY(Direction.West);
		assertTrue(!grid.canMove(objPos));
		objCord.moveXorY(Direction.West);
		assertTrue(!grid.canMove(objPos));
		objCord.moveXorY(Direction.East);
		assertTrue(!grid.canMove(objPos));
		objCord.moveXorY(Direction.East);
		assertTrue(grid.canMove(objPos));
		objCord.moveXorY(Direction.East);
		assertTrue(grid.canMove(objPos));
		
		
	}
	
	/**
	 * Test method for {@link com.tw.cr.rover.model.LocationGrid#canMove(RoverPos)}
	 */
	@Test public void testCanMoveRight()
	{
				
		RoverPos objPos = new RoverPos();
		
		Cordinates objCord = new Cordinates(9,9);
		Direction dirVal = Direction.East;
		
		objPos.setCordVal(objCord);
		objPos.setDirVal(dirVal);
		
		assertTrue(grid.canMove(objPos));
		objCord.moveXorY(Direction.East);
		assertTrue(!grid.canMove(objPos));
		objCord.moveXorY(Direction.East);
		assertTrue(!grid.canMove(objPos));
		objCord.moveXorY(Direction.West);
		assertTrue(!grid.canMove(objPos));
		objCord.moveXorY(Direction.West);
		assertTrue(grid.canMove(objPos));
		objCord.moveXorY(Direction.West);
		assertTrue(grid.canMove(objPos));
		
		
	}

	

}
