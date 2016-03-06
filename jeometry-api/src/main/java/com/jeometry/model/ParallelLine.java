package com.jeometry.model;

import com.jeometry.model.scalar.ScalarSupplier;

public class ParallelLine implements Line {
	
	private final Line parallel;
	private final Vector point;
	
	public ParallelLine(Line parallel, ScalarSupplier r) {
		super();
		this.parallel = parallel;
		this.point = new PointOutsideLine(this.parallel, r);
	}

	public Vector direction() {
		return parallel.direction();
	}

	public Vector point() {
		return this.point;
	}

}
