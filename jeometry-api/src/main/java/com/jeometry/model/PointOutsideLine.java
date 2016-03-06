package com.jeometry.model;

import com.jeometry.model.scalar.Add;
import com.jeometry.model.scalar.Diff;
import com.jeometry.model.scalar.Division;
import com.jeometry.model.scalar.Multiplication;
import com.jeometry.model.scalar.ScalarSupplier;
import com.jeometry.model.scalar.Scalar;

public class PointOutsideLine implements Vector {

	private Line line ;
	private Scalar x;
	private Scalar y;
	
	public PointOutsideLine(Line line, ScalarSupplier r) {
		super();
		this.line = line;
		this.x = this.getXOutsideLine(r);
		this.y = this.getYOutsideLine(r);
	}

	private Scalar getXOutsideLine(ScalarSupplier r) {
		Vector dir = this.line.direction();
		Scalar x = r.random();
		if (r.equals(dir.x(), r.addIdentity())) {
			x = r.other(r.addIdentity());
		}
		return x;
	}

	private Scalar getYOutsideLine(ScalarSupplier r) {
		Scalar y = r.random();
		final Vector dir = this.line.direction();
		if (r.equals(dir.x(), r.addIdentity())) {
			return y;
		}
		final Vector point = this.line.point();
		final Scalar slope = new Division(dir.y(), dir.x());
		final Scalar b = new Diff(point.y(), new Multiplication(slope, point.x()));
		y = r.other(new Add(b, new Multiplication(slope, this.x)));
		return y;
	}

	@Override
	public Scalar x() {
		return this.x;
	}

	@Override
	public Scalar y() {
		return this.y;
	}

}
