/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2020, Hamdi Douss
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
package com.aljebra.vector;

import com.aljebra.scalar.Multiplication;
import com.aljebra.scalar.Scalar;
import java.util.Arrays;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A vector represented as the multiplication of a vector by a scalar.
 * @param <T> scalar types
 * @since 0.1
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
public final class Times<T> implements Vect<T> {

    /**
     * Vector.
     */
    private final transient Vect<T> vector;

    /**
     * Scalar.
     */
    private final transient Scalar<T> scalar;

    /**
     * Constructor.
     * @param vector Vector to multiply
     * @param scalar Scalar by which to multiply
     */
    public Times(final Vect<T> vector, final Scalar<T> scalar) {
        this.vector = vector;
        this.scalar = scalar;
    }

    @Override
    public Scalar<T>[] coords() {
        final Scalar<T>[] coors = this.vector.coords();
        final Scalar<T>[] result = Arrays.copyOf(coors, coors.length);
        for (int idx = 0; idx < coors.length; ++idx) {
            result[idx] = this.mult(coors[idx]);
        }
        return result;
    }

    /**
     * Returns a multiplication of the given scalar by this {@link Times}
     * instance scalar.
     * @param scl Scalar
     * @return Multiplication
     */
    private Multiplication<T> mult(final Scalar<T> scl) {
        return new Multiplication<T>(scl, this.scalar);
    }
}
