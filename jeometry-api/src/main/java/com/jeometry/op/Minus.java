package com.jeometry.op;

import com.jeometry.model.Vector;
import com.jeometry.model.scalar.Diff;
import com.jeometry.model.scalar.Scalar;

public class Minus implements Vector {

	private Vector b;
	private Vector a;

	public Minus(Vector a, Vector b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public Scalar x() {		
		return new Diff(this.a.x(), this.b.x());
	}

	@Override
	public Scalar y() {
		return new Diff(this.a.y(), this.b.y());
	}

}
