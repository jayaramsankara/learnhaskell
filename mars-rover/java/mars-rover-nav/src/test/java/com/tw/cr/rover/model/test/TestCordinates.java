package com.tw.cr.rover.model.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.tw.cr.rover.model.Cordinates;
import com.tw.cr.rover.model.Direction;


/**
 * Tests the methods in the class Cordinates
 * @author jayaram_s
 *
 */
public class TestCordinates 
{
	
	/**
	 * Test method for {@link com.tw.cr.rover.model.Cordinates#contains(Cordinates)}
	 */
	@Test public void testContains()
	{
		Cordinates largeCord = new Cordinates(50,50);
		Cordinates smallCord = new Cordinates(10,10);
		assertTrue(!smallCord.contains(largeCord));
		assertTrue(largeCord.contains(smallCord));
		assertTrue(largeCord.contains(largeCord));
		
	}
	
	/**
	 * Test method for {@link com.tw.cr.rover.model.Cordinates#moveXorY(Direction)}
	 */
	@Test public void testMoveXorY()
	{
		Cordinates refCord = new Cordinates(5,10);
		
		refCord.moveXorY(Direction.North);
		assertTrue(refCord.getYVal()==11);
		refCord.moveXorY(Direction.East);
		assertTrue(refCord.getXVal()==6);
		refCord.moveXorY(Direction.South);
		assertTrue(refCord.getYVal()==10);
		refCord.moveXorY(Direction.West);
		assertTrue(refCord.getXVal()==5);
		
	}

	
}
