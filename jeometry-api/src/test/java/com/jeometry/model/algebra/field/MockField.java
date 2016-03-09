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
package com.jeometry.model.algebra.field;

import com.jeometry.model.algebra.scalar.Scalar;
import java.util.Random;

/**
 * Mock field for {@link Double}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class MockField implements Field<Double> {

    /**
     * Bound integer to get a random number.
     */
    private static final Integer FOUR = 4;

    /**
     * Tolerance to assume to doubles are equal.
     */
    private static final Double TOLERANCE = 1.e-4;

    @Override
    public Scalar random() {
        return new Scalar.Default<Double>(
            Double.valueOf(new Random().nextInt(MockField.FOUR))
        );
    }

    @Override
    public Scalar other(final Scalar scalar) {
        return new Scalar.Default<Double>(
            ((Scalar.Default<Double>) scalar).value() + 1
        );
    }

    @Override
    public Scalar addIdentity() {
        return new Scalar.Default<Double>((double) 0);
    }

    @Override
    public boolean equals(final Scalar scalar, final Scalar other) {
        final Double first = ((Scalar.Default<Double>) scalar).value();
        final Double second = ((Scalar.Default<Double>) scalar).value();
        return Math.abs(first - second) < MockField.TOLERANCE;
    }

    @Override
    public Double actual(final Scalar scalar) {
        return ((Scalar.Default<Double>) scalar).value();
    }

}
