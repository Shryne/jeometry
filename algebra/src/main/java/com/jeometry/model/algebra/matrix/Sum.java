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
package com.jeometry.model.algebra.matrix;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.jeometry.aspects.DimensionsEqual;
import com.jeometry.model.algebra.scalar.Scalar;
import com.jeometry.model.algebra.vector.FixedVector;
import com.jeometry.model.algebra.vector.Vect;
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
public final class Sum implements Matrix {

    /**
     * Sum operands.
     */
    private final Multiset<Matrix> operands;

    /**
     * Constructor.
     * @param operands Sum operands
     */
    @DimensionsEqual
    public Sum(final Matrix... operands) {
        this.operands = HashMultiset.create(Arrays.asList(operands));
    }

    @Override
    public Scalar[] coords() {
        final Vect[] results = new Vect[this.operands.size()];
        int idx = 0;
        for (final Matrix oper : this.operands) {
            results[idx] = Sum.vector(oper.coords());
            ++idx;
        }
        return new com.jeometry.model.algebra.vector.Sum(results).coords();
    }

    @Override
    public Scalar[] column(final int index) {
        final Vect[] cols = new Vect[this.operands.size()];
        int idx = 0;
        for (final Matrix oper : this.operands) {
            cols[idx] = Sum.vector(oper.column(index));
            ++idx;
        }
        return new com.jeometry.model.algebra.vector.Sum(cols).coords();
    }

    @Override
    public Scalar[] line(final int index) {
        final Vect[] lines = new Vect[this.operands.size()];
        int idx = 0;
        for (final Matrix oper : this.operands) {
            lines[idx] = Sum.vector(oper.line(index));
            ++idx;
        }
        return new com.jeometry.model.algebra.vector.Sum(lines).coords();
    }

    @Override
    public Vect apply(final Vect input) {
        final Vect[] results = new Vect[this.operands.size()];
        int idx = 0;
        for (final Matrix oper : this.operands) {
            results[idx] = oper.apply(input);
            ++idx;
        }
        return new com.jeometry.model.algebra.vector.Sum(results);
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
    private static FixedVector vector(final Scalar... coords) {
        return new FixedVector(coords);
    }
}
