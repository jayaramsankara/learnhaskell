package com.tw.cr.rover;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.tw.cr.rover.model.Cordinates;
import com.tw.cr.rover.model.Direction;
import com.tw.cr.rover.model.LocationGrid;
import com.tw.cr.rover.model.RoverPos;

/**
 * This is the main class which accepts the command lines, initializes rover and executes instructions.
 * @author jayaram_s
 */
public class RoverPlacer 
{
	
	private LocationGrid locGrid = null;
	
	public RoverPlacer(LocationGrid objGrid)
	{
		locGrid = objGrid;
	}
	
	
    public ArrayList<MarsRover> processCommands(ArrayList<String> commands ,ArrayList<String> results)throws RoverException
    {
    	ArrayList<MarsRover> rovers = null;
    	int size = commands.size();
    	if(size%2 != 0)
    	{
    		throw new RoverException(RoverErrors.INCORRECT_COMMANDSET,"Incorrect command format. There should be two lines of commands per rover.");
    	}
    	rovers = new ArrayList<MarsRover>(size/2);
    	
    	for(int cnt=0;cnt<size;cnt++)
    	{
    		String posString = commands.get(cnt++);
    		String instrString = commands.get(cnt);
    		MarsRover objRover = null;
    		String result = "Success";
    		boolean bSuccess = true;
    		try 
    		{
				objRover =  initRover(posString);
				
			}
    		catch (RoverException e) 
    		{
    			bSuccess = false;
    			result ="Error: Invalid Position for Rover #"+(cnt+1)/2+". Ignoring the instructions.";
			}
    		catch (IllegalArgumentException e) 
    		{
    			bSuccess = false;
    			result = "Error: Invalid Position for Rover #"+(cnt+1)/2+". Ignoring the instructions.";
			}
    		if(bSuccess)
    		{
    			try 
    			{
					processInstruction(objRover, instrString);
				} 
    			catch (RoverException e)
				{
    				bSuccess = false;
    				result = "Error: Invalid instruction for Rover #"+(cnt+1)/2+". Ignoring the instructions."+e.getRoverError();
				}
    			
    		}
    		
    		if(bSuccess)
    		{
    			rovers.add(objRover);
    		}
    		results.add(result);
    	}
	
    	return rovers;
    	
    }
    
    public void printRoverPositions(ArrayList<MarsRover> rovers, ArrayList<String> results)
    {
    	//Print the outputs
    	System.out.println("The rover positions after executing the commands:");
    	int cnt =0;
    	int rcnt =0;
    	for(String result: results)
    	{
    		if(result.equalsIgnoreCase("Success"))
    		{
    			System.out.println("Rover #"+(cnt+1)+" : "+rovers.get(rcnt++).getPosition().toString());
    		}
    		else
    		{
    			System.out.println("Rover #"+(cnt+1)+" : "+result);
    		}
    		cnt++;
    	}
    }
    
   
    private MarsRover initRover(String posString) throws RoverException
	{
		MarsRover objRover = null;
		if((posString==null) || (posString.length()<5))
		{
			throw new IllegalArgumentException("Invalid value for the rovers initial position");
		}
		String[] vals = posString.split("\\s");
				
		if(vals.length != 3)
		{
			throw new IllegalArgumentException("Invalid format for the rovers initial position string");
		}
		
		String strXVal = vals[0];
		String strYVal = vals[1];
		String strDirVal = vals[2];
		
		if(strDirVal.length()!=1)
		{
			throw new IllegalArgumentException("Invalid format for the rovers initial position string");
		}
		
		int xVal = 0; 
		int yVal = 0;
		
		try
		{
			xVal =  Integer.parseInt(strXVal);
			yVal =  Integer.parseInt(strYVal);
		}
		catch(NumberFormatException nfe)
		{
			throw new IllegalArgumentException("Invalid format for the rovers initial position string");
		}
		
		Cordinates objCord = new Cordinates(xVal,yVal);
		Direction dirVal = Direction.getDirection(strDirVal.charAt(0));
		if(dirVal == null)
		{
			throw new IllegalArgumentException("Invalid format for the rovers initial position string. Invalid direction. Valid values are N,S,E,W");
		}
		
		if(locGrid.getEndCordinates().contains(objCord))
		{
				objRover = new MarsRover(locGrid,new RoverPos(objCord,dirVal));
				
		}
		else
		{
			throw new RoverException(RoverErrors.ROVER_INVALID_POS, "Invalid position for Rover. Position outside of the plateau.");
		}
		
		return objRover;
	}
	
	
	private static LocationGrid getPlateau(BufferedReader objReader) throws IOException
	{
		System.out.println( "\nEnter the size of the plateau in the format <width height> : " );
    	LocationGrid grid = null;
		String size = objReader.readLine();
		String[] cords = size.split("\\s");
		if(cords.length!=2)
    	{
    		System.out.println("Error Invalid format for the size.");
    		grid = getPlateau(objReader);
    	}
		else
		{
			int xVal = 0; 
			int yVal = 0;
			
			try
			{
				xVal =  Integer.parseInt(cords[0]);
				yVal =  Integer.parseInt(cords[1]);
			}
			catch(NumberFormatException nfe)
			{
				System.out.println("Invalid plateau size : "+size);
				grid = getPlateau(objReader);
			}
			grid = new LocationGrid(xVal,yVal);
		}
		return grid;
	}
	
   
	private void processInstruction(MarsRover objRover, String instrString) throws RoverException 
	{
		int len = instrString.length();
		for(int cnt=0;cnt<len;cnt++)
		{
			char value = instrString.charAt(cnt);
			switch(value)
			{
			case 'L':
				objRover.left();
				break;
			case 'R':
				objRover.right();
				break;
			case 'M':
				objRover.move();
				break;
			default:
				throw new RoverException(RoverErrors.INCORRECT_COMMANDSET,"Invalid character in instruction String.");
					
			}
		}
	
		
	}
	    
	
	
    
    
    public static void main( String[] args ) throws IOException
    {
    	ArrayList<MarsRover> rovers = null;
    	ArrayList<String> commands = new ArrayList<String>();
    	BufferedReader objReader = new BufferedReader(new InputStreamReader(System.in));
    	
    	//Create the location grid 
    	LocationGrid grid = getPlateau(objReader);
    	RoverPlacer objPlacer = new RoverPlacer(grid);
    	
    	System.out.println( "\nEnter the rest of the commands , for each rover in separate lines.\n");
    	System.out.println( "There should be two command lines for each Rover.\n\n" +
    			" Line 1: Initial Position of the rover inside the rectangular plateau in the format <X Y [N|S|E|W]>\n" +
    			"Line 2: Instructions to the rover. Use the commands M,L or R in any order.\n" +
    			"Enter the string 'END' as the last line to start processing.\n\n");
    	boolean bread = true;
		while(bread)
		{
			String line =objReader.readLine();
			if((line !=null) && (!line.equals("END")))
			{
				commands.add(line);
			}
			else
			{
				bread = false;
			}
		}
 
		//Process the commands
		ArrayList<String> results = new ArrayList<String>();
    	try 
    	{
			rovers = objPlacer.processCommands(commands,results);
		} 
    	catch (RoverException e) 
    	{
			System.out.println( e.getRoverError());
		}
    	
    	//Print the outputs
    	if(rovers != null)
    	{
    		objPlacer.printRoverPositions(rovers,results);
    	}
        
        
        
    }
    

}
