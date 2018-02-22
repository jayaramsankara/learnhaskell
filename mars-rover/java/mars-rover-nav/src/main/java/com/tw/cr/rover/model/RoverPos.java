/**
 * 
 */
package com.tw.cr.rover.model;

/**
 * This class encapsulates the rover's position which includes its coordinates and the direction.
 * @author jayaram_s
 *
 */
public class RoverPos 
{
		
	private Direction dirVal = Direction.North;
	private Cordinates cordVal = null;

	public RoverPos()
	{
		cordVal = new Cordinates();
	}
	
	

	
	public RoverPos(Cordinates objCord, Direction dirValue) 
	{
		
		dirVal = dirValue;
		setCordVal(objCord);
	}


	/**
	 * Retrieves the direction value corresponding to this position.
	 * @see Direction#North
	 * @see Direction#South
	 * @see Direction#West
	 * @see Direction#East
	 */
	public Direction getDirVal() 
	{
		return dirVal;
	}

	/**
	 * Sets the direction value corresponding to this position.
	 * @param dirVal The direction value
	 * @see Direction#North
	 * @see Direction#South
	 * @see Direction#West
	 * @see Direction#East
	 */
	public void setDirVal(Direction dirVal) 
	{
		this.dirVal = dirVal;
	}
	
	/**
	 * 
	 * @return the coordinate value
	 */
	public Cordinates getCordVal() 
	{
		return cordVal;
	}

	/**
	 * Sets coordinate value of this position
	 * @param objCord
	 */
	public void setCordVal(Cordinates objCord) 
	{
		if((objCord==null) || (objCord.getXVal()<0) || (objCord.getYVal()<0))
		{
			throw new IllegalArgumentException("Invalid cordinates. Either the cordinates is null or the values are less than 0.");
		}
		this.cordVal = objCord;
	}




	/**
	 * Turns the direction of this position 90 degrees left
	 */
	public void left() 
	{
		setDirVal(dirVal.left());
		
	}
	
	/**
	 * Turns the direction of this position 90 degrees right
	 */
	public void right() 
	{
		setDirVal(dirVal.right());
		
	}
	
	/**
	 * Moves the cordinates of the position as per the associated direction value.
	 */
	public void move() 
	{
		cordVal.moveXorY(dirVal);
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		StringBuffer objBuf = new StringBuffer();
		
		objBuf.append(cordVal.getXVal());
		objBuf.append(" ");
		objBuf.append(cordVal.getYVal());
		objBuf.append(" ");
		objBuf.append(dirVal.toString());
		
		return objBuf.toString();
	}



	
	
}
