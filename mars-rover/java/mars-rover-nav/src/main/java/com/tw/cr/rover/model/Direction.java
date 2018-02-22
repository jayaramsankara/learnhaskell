/**
 * 
 */
package com.tw.cr.rover.model;

/**
 * This class holds the enumeration values for the Directions - North, South, East and West.
 * @author jayaram_s
 *
 */
public enum Direction 
{
	North(270,'N'),
	South(90,'S'),
	East(0,'E'),
	West(180,'W');
	

	private int myIntVal = 0;
	
	private char myCharVal;

	private Direction (int ival, char cval)
	{
		myIntVal = ival;
		myCharVal = cval;
	}
	
	/**
	 * Returns the direction after turning 90 degree left from the current direction
	 * @return
	 */
	public Direction left()
	{
		return getDirection(myIntVal-90);
	}
	
	/**
	 * Returns the direction after turning 90 degree right from the current direction. .
	 * @return
	 */
	public Direction right()
	{
		return getDirection(myIntVal+90);
	}
	
	private Direction getDirection(int val)
	{
		Direction objRet = null;
		int newval = val;
		if(newval<0)
		{
			newval = 360 - Math.abs(newval);
		}
		else if(newval==360)
		{
			newval =0;
		}
		switch(newval)
		{
			case 0:
				objRet = Direction.East;
				break;
			case 90:
				objRet = Direction.South;
				break;
			case 180:
				objRet = Direction.West;
				break;
			case 270:
				objRet = Direction.North;
				break;
							
		}
		return objRet;
	}
	
	/**
	 * Gets the direction corresponding to the character N,S,E or W
	 * @param val The character value
	 * @return Direction.North for N, Direction.South for S, Direction.West for W, Direction.East for E, null for any other value
	 * @see Direction#North 
	 * @see Direction#South
	 * @see Direction#East
	 * @see Direction#West
	 */
	public static Direction getDirection (char val)
	{
		Direction objRet = null;
		switch(val)
		{
			case 'N':
			case 'n':
				objRet = Direction.North;
				break;
			case 'S':
			case 's':
				objRet = Direction.South;
				break;
			case 'W':
			case 'w':
				objRet = Direction.West;
				break;
			case 'E':
			case 'e':
				objRet = Direction.East;
				break;
			
		}
		return objRet;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 * Gets the string equivalent N for north, S for south, E for east and W for west
	 */
	@Override
	public String toString() 
	{
		StringBuffer objBuf = new StringBuffer();
		objBuf.append(myCharVal);
		return objBuf.toString();
	}
	
}
