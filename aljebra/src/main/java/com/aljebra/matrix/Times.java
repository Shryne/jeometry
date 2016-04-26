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
package com.aljebra.matrix;

import com.aljebra.scalar.Scalar;
import com.aljebra.vector.FixedVector;
import com.aljebra.vector.Vect;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A Matrix represented as the multiplication of a matrix by a scalar.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@EqualsAndHashCode
@ToString(includeFieldNames = false)
public final class Times implements Matrix {

    /**
     * Matrix.
     */
    private final transient Matrix matrix;

    /**
     * Scalar.
     */
    private final transient Scalar scalar;

    /**
     * Constructor.
     * @param mat Matrix to multiply
     * @param scalar Scalar by which to multiply
     */
    public Times(final Matrix mat, final Scalar scalar) {
        this.matrix = mat;
        this.scalar = scalar;
    }

    @Override
    public Scalar[] coords() {
        return new com.aljebra.vector.Times(
            new FixedVector(this.matrix.coords()), this.scalar
        ).coords();
    }

    @Override
    public Scalar[] column(final int index) {
        return new com.aljebra.vector.Times(
            new FixedVector(this.matrix.column(index)), this.scalar
        ).coords();
    }

    @Override
    public Scalar[] line(final int index) {
        return new com.aljebra.vector.Times(
            new FixedVector(this.matrix.line(index)), this.scalar
        ).coords();
    }

    @Override
    public Vect apply(final Vect input) {
        return new com.aljebra.vector.Times(
            this.matrix.apply(input), this.scalar
        );
    }

    @Override
    public int columns() {
        return this.matrix.columns();
    }

    @Override
    public int lines() {
        return this.matrix.lines();
    }
}
