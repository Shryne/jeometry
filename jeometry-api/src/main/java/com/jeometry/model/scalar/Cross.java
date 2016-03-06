package com.jeometry.model.scalar;

import com.jeometry.model.Vector;

public class Cross {

	private Vector v;
	private Vector u;

	public Cross(Vector v, Vector u) {
		this.v = v;
		this.u = u;
	}
	
	public Vector u() {
		return u;
	}
	
	public Vector v() {
		return v;
	}
	
	public Scalar value() {
		return new Diff(new Multiplication(this.u.x(), this.v.y()), new Multiplication(this.v.x(), this.u.y()));
	}

}
