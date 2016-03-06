package com.jeometry.model.scalar;

import com.jeometry.model.Vector;

public class Dot {

	private Vector v;
	private Vector u;

	public Dot(Vector v, Vector u) {
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
		return new Add(new Multiplication(this.u.x(), this.v.x()), new Multiplication(this.u.y(), this.v.y()));
	}

}
