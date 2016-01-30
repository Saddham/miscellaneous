package com.timepass;

public class Happy
{
	public native void compareLeadingZeroes(int x, int y);
	static
	{
		System.loadLibrary ("happy");   /* Note lowercase of classname! */
	}
	
	public static void main (String[] args)
	   {
	   System.out.println("hi");
	   }
}
