/**
 * 
 */
package com.tw.cr.rover.model.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tw.cr.rover.model.Cordinates;
import com.tw.cr.rover.model.Direction;
import com.tw.cr.rover.model.RoverPos;

/**
 * Tests the methods of the class RoverPos
 * @author jayaram_s
 *
 */
public class TestRoverPos 
{

	/**
	 * Test method for {@link com.tw.cr.rover.model.RoverPos#setCordVal(com.tw.cr.rover.model.Cordinates)}.
	 */
	@Test
	public void testSetCordVal() 
	{
		RoverPos objPos = null;
		boolean bException = false;
		try
		{
			objPos = new RoverPos(new Cordinates(-5,5),Direction.North);
		}
		catch (IllegalArgumentException iae)
		{
			bException = true;
		}
		assertTrue(bException);
		bException= false;
		
		try
		{
			objPos = new RoverPos(new Cordinates(5,5),Direction.North);
		}
		catch (IllegalArgumentException iae)
		{
			bException = true;
		}
		
		assertTrue(!bException);
		bException= false;
		
		try
		{
			objPos.setCordVal(new Cordinates(5,-5));
		}
		catch (IllegalArgumentException iae)
		{
			bException = true;
		}
		assertTrue(bException);
	}

	/**
	 * Test method for {@link com.tw.cr.rover.model.RoverPos#toString()}.
	 */
	@Test
	public void testToString() 
	{
		RoverPos objPos = new RoverPos(new Cordinates(5,5),Direction.North);
		String strVal = objPos.toString();
		assertTrue(strVal.equals("5 5 N"));
		
	}

}
