package com.jeometry.model;

public interface Line {

	/**
	 * Gives the direction of the Line
	 * @return Direction.
	 */
	public Vector direction();
	
	/**
	 * Gives a Point belonging to the Line
	 * @return a Point by which the line passes
	 */
	public Vector point();

}
