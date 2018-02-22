/**
 * 
 */
package com.tw.cr.rover.model.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.tw.cr.rover.model.Direction;
import com.tw.cr.rover.model.RoverPos;

/**
 * Tests the methods in the class Direction
 * @author jayaram_s
 *
 */
public class TestDirection 
{

			
	/**
	 *Test method for {@link com.tw.cr.rover.model.Direction}
	 */
	@Test public void testCircularChange()
	{
		Direction origVal = Direction.East;
				
		assertTrue(origVal.left().toString().equals(Direction.North.toString()));
		assertTrue(origVal.right().toString().equals(Direction.South.toString()));
		
		origVal = Direction.South;
		assertTrue(origVal.left().toString().equals(Direction.East.toString()));
		assertTrue(origVal.right().toString().equals(Direction.West.toString()));
		
		origVal = Direction.West;
		assertTrue(origVal.left().toString().equals(Direction.South.toString()));
		assertTrue(origVal.right().toString().equals(Direction.North.toString()));
		
		origVal = Direction.North;
		assertTrue(origVal.left().toString().equals(Direction.West.toString()));
		assertTrue(origVal.right().toString().equals(Direction.East.toString()));
		
		
	}
	
}
