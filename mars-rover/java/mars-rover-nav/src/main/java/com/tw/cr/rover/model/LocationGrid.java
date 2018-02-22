package com.tw.cr.rover.model;

/**
 * This class corresponds to the rectangular location at which rovers are placed.
 * @author jayaram_s
 *
 */
public class LocationGrid 
{
	private  Cordinates startCordinates = null;
	private  Cordinates endCordinates = null;
	
	
	
	public LocationGrid(int xSize, int ySize)
	{
		startCordinates = new Cordinates();
		endCordinates = new Cordinates(xSize,ySize);
		
	}

	/**
	 * Checks whether any rover can be moved to the new position passed.
	 * @param objPosition The position to which any rover could be moved
	 * @return true if the new position is valid, false otherwise
	 */
	public boolean canMove(RoverPos objPosition) 
	{
		boolean bret = true;
		Direction dirVal = objPosition.getDirVal();
		Cordinates cordVal = objPosition.getCordVal();
		if(dirVal.compareTo(Direction.North)==0)
		{
			bret = !isTop(cordVal);
		}
		else if(dirVal.compareTo(Direction.South)==0)
		{
			bret = !isBottom(cordVal);
		}
		else if(dirVal.compareTo(Direction.East)==0)
		{
			bret = !isRight(cordVal);
		}
		else if(dirVal.compareTo(Direction.West)==0)
		{
			bret = !isLeft(cordVal);
		}
		return bret;
	}
	
	private boolean isTop(Cordinates objCord)
	{
		return (objCord.getYVal() >= endCordinates.getYVal());
	}
	
	private boolean isBottom(Cordinates objCord)
	{
		return (objCord.getYVal() <= startCordinates.getYVal());
	}
	
	private boolean isLeft(Cordinates objCord)
	{
		return (objCord.getXVal() <= startCordinates.getXVal());
	}
	
	private boolean isRight(Cordinates objCord)
	{
		return (objCord.getXVal() >= endCordinates.getXVal());
	}

	/**
	 * @return the startCordinates
	 */
	public Cordinates getStartCordinates() {
		return startCordinates;
	}

	/**
	 * @param startCordinates the startCordinates to set
	 */
	public void setStartCordinates(Cordinates startCordinates) {
		this.startCordinates = startCordinates;
	}

	/**
	 * @return the endCordinates
	 */
	public Cordinates getEndCordinates() {
		return endCordinates;
	}

	/**
	 * @param endCordinates the endCordinates to set
	 */
	public void setEndCordinates(Cordinates endCordinates) {
		this.endCordinates = endCordinates;
	}
	
	

}
