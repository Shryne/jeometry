package com.jeometry.model;

import com.jeometry.model.scalar.ScalarSupplier;
import com.jeometry.model.scalar.Scalar;
import com.jeometry.op.Sum;
import com.jeometry.op.Times;

public class PointInLine implements Vector {

	private Line line;
	private Scalar factor;
	
	public PointInLine(Line line, ScalarSupplier r) {
		super();
		this.line = line;
		this.factor = r.random();
	}

	@Override
	public Scalar x() {
		return new Sum(
			new Times(this.line.direction(), this.factor),
			this.line.point()
		).x();
	}

	@Override
	public Scalar y() {
		return new Sum(
			new Times(this.line.direction(), this.factor),
			this.line.point()
		).y();
	}

}
