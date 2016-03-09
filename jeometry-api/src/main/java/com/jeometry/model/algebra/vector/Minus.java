/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2016, Hamdi Douss
 *
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom
 * the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
 * OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.jeometry.model.algebra.vector;

import com.jeometry.model.algebra.scalar.Diff;
import com.jeometry.model.algebra.scalar.Scalar;

/**
 * A vector represented as the difference between two vectors.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class Minus implements Vect {
    /**
     * First operand.
     */
    private final Vect first;

    /**
     * Second operand.
     */
    private final Vect second;

    /**
     * Constructor.
     * @param first First operand (minuend)
     * @param second Second operand (subtrahend)
     */
    public Minus(final Vect first, final Vect second) {
        this.first = first;
        this.second = second;
    }

	@Override
	public Scalar[] coors() {
		final Scalar[] fcoors = this.first.coors();
    	final Scalar[] scoors = this.second.coors();
		Scalar[] result = new Scalar[fcoors.length];
		for (int i = 0; i < fcoors.length; ++i) {
			result[i] = new Diff(fcoors[i], scoors[i]);
		}
        return result;
	}

}
