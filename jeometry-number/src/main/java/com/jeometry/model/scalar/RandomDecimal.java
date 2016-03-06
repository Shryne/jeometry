package com.jeometry.model.scalar;

public class RandomDecimal implements ScalarSupplier {

	private static final int MINBOUND = -1000;
	private static final int MAXBOUND = 1000;
	
	private final transient java.util.Random rand;

	public RandomDecimal() {
		this(new java.util.Random());
	}

	public RandomDecimal(java.util.Random rand) {
		super();
		this.rand = rand;
	}

	@Override
	public Scalar random() {
		return new Scalar.Default<Double>(
			this.rand.nextDouble()
			* (RandomDecimal.MAXBOUND - RandomDecimal.MINBOUND)
			+ RandomDecimal.MINBOUND
		);
	}

	@Override
	public Scalar other(Scalar x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Scalar addIdentity() {
		return new Scalar.Default<Double>(new Double(0));
	}

	@Override
	public boolean equals(Scalar x, Scalar addIdentity) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
