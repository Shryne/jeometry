package com.jeometry.model.scalar;

public interface Scalar {
	
	public static class Default<T> implements Scalar{
		T n;

		public Default(final T num) {
			this.n = num;
		}

		public T value() {
			return this.n;
		}
		
	}
}
