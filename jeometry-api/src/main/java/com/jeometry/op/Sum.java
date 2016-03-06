package com.jeometry.op;

import com.jeometry.model.Vector;
import com.jeometry.model.scalar.Add;
import com.jeometry.model.scalar.Scalar;

public class Sum implements Vector {

	private Vector v;
	private Vector u;

	public Sum(Vector v, Vector u) {
		this.v = v;
		this.u = u;
	}

	@Override
	public Scalar x() {
		return new Add(this.v.x(), this.u.x());
	}

	@Override
	public Scalar y() {
		return new Add(this.v.y(), this.u.y());
	}

}
