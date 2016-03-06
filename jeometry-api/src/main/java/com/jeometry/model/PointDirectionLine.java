package com.jeometry.model;

public class PointDirectionLine implements Line {
	
	private Vector direction;
	
	private Vector point;
	
	public PointDirectionLine(Vector direction, Vector point) {
		super();
		this.direction = direction;
		this.point = point;
	}

	public Vector direction() {
		return direction;
	}
	
	public void setDirection(Vector direction) {
		this.direction = direction;
	}
	
	public Vector point() {
		return point;
	}
	
	public void setPoint(Vector point) {
		this.point = point;
	}
}
