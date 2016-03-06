package com.jeometry.model;

public class ParallelPassingByLine implements Line {

	private Vector point;
	
	private Line parallel;
	
	public ParallelPassingByLine(Vector point, Line parallel) {
		super();
		this.point = point;
		this.parallel = parallel;
	}

	public Vector direction() {
		return parallel.direction();
	}

	public Vector point() {
		return point;
	}

}
