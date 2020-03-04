/**
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
package com.aljebra.matrix;

import com.aljebra.aspects.DimensionsEqual;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.FixedVector;
import com.aljebra.vector.Vect;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.Arrays;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A Matrix represented as the sum of a set of matrices.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@EqualsAndHashCode
@ToString(includeFieldNames = false)
public final class Sum<T> implements Matrix<T> {

    /**
     * Sum operands.
     */
    private final Multiset<Matrix<T>> operands;

    /**
     * Constructor.
     * @param operands Sum operands
     */
    @SuppressWarnings("unchecked")
    @DimensionsEqual
    public Sum(final Matrix<T>... operands) {
        this.operands = HashMultiset.create(Arrays.asList(operands));
    }

    @SuppressWarnings("unchecked")
    @Override
    public Scalar<T>[] coords() {
        final Vect<T>[] results = new Vect[this.operands.size()];
        int idx = 0;
        for (final Matrix<T> oper : this.operands) {
            results[idx] = Sum.vector(oper.coords());
            ++idx;
        }
        return new com.aljebra.vector.Sum<T>(results).coords();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Scalar<T>[] column(final int index) {
        final Vect<T>[] cols = new Vect[this.operands.size()];
        int idx = 0;
        for (final Matrix<T> oper : this.operands) {
            cols[idx] = Sum.vector(oper.column(index));
            ++idx;
        }
        return new com.aljebra.vector.Sum<T>(cols).coords();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Scalar<T>[] line(final int index) {
        final Vect<T>[] lines = new Vect[this.operands.size()];
        int idx = 0;
        for (final Matrix<T> oper : this.operands) {
            lines[idx] = Sum.vector(oper.line(index));
            ++idx;
        }
        return new com.aljebra.vector.Sum<T>(lines).coords();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Vect<T> apply(final Vect<T> input) {
        final Vect<T>[] results = new Vect[this.operands.size()];
        int idx = 0;
        for (final Matrix<T> oper : this.operands) {
            results[idx] = oper.apply(input);
            ++idx;
        }
        return new com.aljebra.vector.Sum<T>(results);
    }

    @Override
    public int columns() {
        return this.operands.iterator().next().columns();
    }

    @Override
    public int lines() {
        return this.operands.iterator().next().lines();
    }

    /**
     * Builds a vector given its coordinates.
     * @param coords Vector coordinates
     * @return A {@link FixedVector} instance
     */
    @SuppressWarnings("unchecked")
    private static <T> FixedVector<T> vector(final Scalar<T>... coords) {
        return new FixedVector<T>(coords);
    }
}
