package com.jeometry.model.scalar;

public class Diff implements Scalar{
	private final Scalar a;
	private final Scalar b;
	
	public Diff(Scalar a, Scalar b) {
		this.a = a;
		this.b = b;
	}
	
	public Scalar a() {
		return a;
	}
	
	public Scalar b() {
		return b;
	}

}
