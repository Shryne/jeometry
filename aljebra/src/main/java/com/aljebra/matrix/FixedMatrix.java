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

import com.aljebra.metric.scalar.Product;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.FixedVector;
import com.aljebra.vector.Vect;
import com.google.common.base.Preconditions;
import java.util.Arrays;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Represents a matrix defined by fixed coordinates.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@EqualsAndHashCode
@ToString
public class FixedMatrix<T> implements Matrix<T> {

    /**
     * Coordinates.
     */
    private Scalar<T>[] coors;

    /**
     * Source vector space dimension.
     */
    private final int source;

    /**
     * Target vector space dimension.
     */
    private final int target;

    /**
     * Constructor.
     * @param lines Matrix lines count
     * @param columns Matrix columns count
     * @param coor Matrix coordinates to be given in a column by column order
     */
    @SuppressWarnings("unchecked")
    public FixedMatrix(final int lines, final int columns,
        final Scalar<T>... coor) {
        this.source = columns;
        this.target = lines;
        this.coors = this.valid(coor);
    }

    /**
     * Modifies a coordinate of the matrix.
     * @param lin Line index of the coordinate to modify (1-based index)
     * @param col Column index of the coordinate to modify (1-based index)
     * @param cor New coordinate
     */
    public final void setCoor(final int lin, final int col, final Scalar<T> cor) {
        this.coors[this.index(lin, col)] = cor;
    }

    @Override
    public final Scalar<T>[] coords() {
        return Arrays.copyOf(this.coors, this.coors.length);
    }

    @Override
    public final Scalar<T>[] column(final int index) {
        final int first = this.index(1, index);
        return Arrays.copyOfRange(this.coors, first, first + this.target);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Scalar<T>[] line(final int index) {
        final int first = this.index(index, 1);
        final Scalar<T>[] result = new Scalar[this.source];
        for (int idx = 0; idx < this.source; ++idx) {
            result[idx] = this.coors[first + idx * this.target];
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Vect<T> apply(final Vect<T> input) {
        if (input.coords().length != this.source) {
            throw new IllegalArgumentException(
                String.format(
                    "Incompatible vector dimension %s with matrix dimension %s",
                    input.coords().length, this.source
                )
            );
        }
        final Scalar<T>[] result = new Scalar[this.target];
        for (int idx = 0; idx < this.target; ++idx) {
            result[idx] = this.product(input, idx + 1);
        }
        return new FixedVector<T>(result);
    }

    @Override
    public final int columns() {
        return this.source;
    }

    @Override
    public final int lines() {
        return this.target;
    }

    /**
     * Returns the single array index based on line and column indices.
     * @param lin Line index (1-based)
     * @param col Column index (1-based)
     * @return The single array index
     */
    private int index(final int lin, final int col) {
        return this.target * (col - 1) + lin - 1;
    }

    /**
     * Calculates the product of the given vector by a matrix line.
     * @param input Given vector
     * @param idx Matrix column index
     * @return Product
     */
    private Scalar<T> product(final Vect<T> input, final int idx) {
        return new Product<T>(input, new FixedVector<T>(this.line(idx)));
    }

    /**
     * Checks if this scalar array could be took as matrix coordinate, regarding
     * the matrix lines and columns count. Throws
     * {@link IllegalArgumentException} if the scalar array is not valid.
     * @param coor Scalar array to check
     * @return The scalar array if it is valid
     */
    @SuppressWarnings("unchecked")
    private Scalar<T>[] valid(final Scalar<T>... coor) {
        final int expected = this.source * this.target;
        Preconditions.checkArgument(
            expected == coor.length,
            "Expected %d scalars for a matrix with %d lines and %d columns",
            expected, this.source, this.target
        );
        return coor;
    }

}
