/**
 * 
 */
package com.tw.cr.rover.model;

/**
 * This class encapsulates the X and Y positions -cordinate values.
 * @author jayaram_s
 *
 */
public class Cordinates
{
	private int xVal =0;
	private int yVal =0;
	
	public Cordinates()
	{
		// no -op
	}
	
	public Cordinates(int x, int y)
	{
		
		xVal =x;
		yVal =y;
				
	}
	
	public int getXVal() 
	{
		return xVal;
	}

	public void setXVal(int val) 
	{
		xVal = val;
	}

	public int getYVal() 
	{
		return yVal;
	}

	public void setYVal(int val) 
	{
		yVal = val;
	}

	/**
	 * Incremenets (moves) the cordinate values as per the direction passed.
	 * @param dirVal The direction in which the cordinates should be moved.
	 * @see Direction
	 */
	public void moveXorY(Direction dirVal)
	{
		
		if(dirVal.compareTo(Direction.North)==0)
		{
			yVal++;
		}
		else if(dirVal.compareTo(Direction.South)==0)
		{
			yVal--;
		}
		else if(dirVal.compareTo(Direction.East)==0)
		{
			xVal++;
		}
		else if(dirVal.compareTo(Direction.West)==0)
		{
			xVal--;
		}
		
	}

	public boolean contains(Cordinates objCord) 
	{
		return ((objCord.getXVal()<=xVal) && (objCord.getYVal()<=yVal));
	}
	
	
}
