/**
 * 
 */
package com.tw.cr.rover;

import com.tw.cr.rover.model.LocationGrid;
import com.tw.cr.rover.model.RoverPos;

/**
 * This class models the Rover w.r.t the application used to novigate it
 * over the rectangular plateau.
 * @author jayaram_s
 *
 */
public class MarsRover 
{
	
	private RoverPos position= null;
	private LocationGrid plateauGrid = null;
	
	public MarsRover()
	{
		this(new LocationGrid(0,0),new RoverPos());
	}
	
	public MarsRover( LocationGrid rectGrid,RoverPos pos)
	{
		setLocationGridAndPos(rectGrid, pos);
	}
	
	/**
	 * @return the objPosition
	 */
	public RoverPos getPosition() 
	{
		return position;
	}
	
	
	/**
	 * @return the objGrid
	 */
	public LocationGrid getPlateauGrid() 
	{
		return plateauGrid;
	}
	
	/**
	 * @param objGrid the objGrid to set
	 * @param rposition the rover position in the grid. Both the location grid and position should be reset together.
	 */
	public void setLocationGridAndPos(LocationGrid objGrid, RoverPos rposition) throws IllegalArgumentException
	{
		if(!objGrid.getEndCordinates().contains(rposition.getCordVal()))
		{
			throw new IllegalArgumentException("Rover position is not with in the location grid limits.");
		}
		this.plateauGrid = objGrid;
		this.position = rposition;
		
	}
	
	/**
	 * Moves the rover w.r.t to the plateau grid on which the rover is placed.
	 * @throws RoverException If rover cannot be moved.
	 */
	public void move() throws RoverException
	{
		if(plateauGrid.canMove(position))
		{
			position.move();
		}
		else
		{
			throw new RoverException(RoverErrors.ROVER_CANNOT_MOVE,"Rover at the edge of the plateau. Cannot move in the direction "+position.getDirVal());
		}
		
	}
	
	/**
	 * Turns the rover 90 degrees left.
	 */
	public void left()
	{
		position.left();
	}
	
	/**
	 * Turns the rover 90 degrees right
	 */
	public void right()
	{
		position.right();
	}
	
	
	
}
