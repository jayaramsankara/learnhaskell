/**
 * 
 */
package com.tw.cr.rover;

/**
 * @author jayaram_s
 *
 */
public class RoverException extends Exception 
{
	private int code = 0;
	private String roverError = null;
	
	public RoverException(int errorCode)
	{
		this(errorCode,"General Rover Error");
	}
	
	public RoverException(int errorCode, String desc)
	{
		code = errorCode;
		roverError = desc;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() 
	{
		StringBuffer msgBuf = new StringBuffer();
		msgBuf.append(super.getMessage());
		msgBuf.append(" : Error Code - ");
		msgBuf.append(code);
		if(roverError != null)
		{
			msgBuf.append(" , Error Description - ");
			msgBuf.append(roverError);
		}
		return msgBuf.toString();
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return the roverError
	 */
	public String getRoverError() {
		return roverError;
	}
	
	

}
