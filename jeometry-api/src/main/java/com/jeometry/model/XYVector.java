package com.jeometry.model;

import com.jeometry.model.scalar.ScalarSupplier;
import com.jeometry.model.scalar.Scalar;

public class XYVector implements Vector {

	private Scalar x;
	
	private Scalar y;

	public XYVector(Scalar x, Scalar y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public XYVector(ScalarSupplier r, Scalar y) {
		super();
		this.x = r.random();
		this.y = y;
	}
	
	public XYVector(Scalar x, ScalarSupplier r) {
		super();
		this.x = x;
		this.y = r.random();
	}
	
	public XYVector(ScalarSupplier r) {
		super();
		this.x = r.random();
		this.y = r.random();
	}

	public Scalar x() {
		return x;
	}

	public Scalar y() {
		return y;
	}
	
	public void setX(Scalar x) {
		this.x = x;
	}
	
	public void setY(Scalar y) {
		this.y = y;
	}
	
}
