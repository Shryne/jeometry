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
package com.aljebra.matrix;

import com.aljebra.scalar.Scalar;
import com.aljebra.vector.FixedVector;
import com.aljebra.vector.Vect;
import java.util.Arrays;
import lombok.EqualsAndHashCode;

/**
 * Represents a one-row-matrix defined by a vector.
 * @param <T> scalar types
 * @since 0.1
 */
@EqualsAndHashCode()
public final class VectorMatrix<T> implements Matrix<T> {

    /**
     * Actual representation of this vector matrix.
     */
    private final Matrix<T> actual;

    /**
     * Constructor.
     * @param coor Scalars to build the matrix from
     */
    public VectorMatrix(final Iterable<? extends Scalar<T>> coor) {
        this(new FixedVector<T>(coor));
    }

    /**
     * Constructor.
     * @param vector Vector to build the matrix from
     */
    public VectorMatrix(final Vect<T> vector) {
        this.actual = new FixedMatrix<>(1, vector.coords().length, Arrays.asList(vector.coords()));
    }

    @Override
    public Scalar<T>[] coords() {
        return this.actual.coords();
    }

    @Override
    public Scalar<T>[] column(final int index) {
        return this.actual.column(index);
    }

    @Override
    public Scalar<T>[] line(final int index) {
        return this.actual.line(index);
    }

    @Override
    public Vect<T> apply(final Vect<T> input) {
        return this.actual.apply(input);
    }

    @Override
    public int columns() {
        return this.actual.columns();
    }

    @Override
    public int lines() {
        return this.actual.lines();
    }
}
