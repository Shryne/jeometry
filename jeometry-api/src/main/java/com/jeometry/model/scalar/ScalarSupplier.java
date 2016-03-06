package com.jeometry.model.scalar;

/**
 * Interface to generate a random scalar.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @since 0.1
 */

public interface ScalarSupplier {

	/**
	 * Generates a random scalar.
	 * @return A random scalar.
	 */
	public Scalar random();

	public Scalar other(Scalar x);

	public Scalar addIdentity();

	public boolean equals(Scalar x, Scalar addIdentity);
}
