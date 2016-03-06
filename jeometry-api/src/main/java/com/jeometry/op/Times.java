package com.jeometry.op;

import com.jeometry.model.Vector;
import com.jeometry.model.scalar.Multiplication;
import com.jeometry.model.scalar.Scalar;

public class Times implements Vector {

	private final transient Vector v;
	private final transient Scalar s;

	public Times(Vector v, Scalar s) {
		super();
		this.v = v;
		this.s = s;
	}

	@Override
	public Scalar x() {
		return new Multiplication(this.v.x(), s);
	}

	@Override
	public Scalar y() {
		return new Multiplication(this.v.y(), s);
	}

}
