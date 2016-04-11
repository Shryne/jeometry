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
import com.aljebra.vector.metric.Product;
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
public class FixedMatrix implements Matrix {

    /**
     * Coordinates.
     */
    private Scalar[] coors;

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
     * @param coor Matrix coordinates
     */
    public FixedMatrix(final int lines, final int columns,
        final Scalar... coor) {
        this.source = lines;
        this.target = columns;
        this.coors = this.valid(coor);
    }

    /**
     * Modifies a coordinate of the matrix.
     * @param lin Line index of the coordinate to modify (1-based index)
     * @param col Column index of the coordinate to modify (1-based index)
     * @param cor New coordinate
     */
    public final void setCoor(final int lin, final int col, final Scalar cor) {
        this.coors[this.index(lin, col)] = cor;
    }

    @Override
    public final Scalar[] coords() {
        return Arrays.copyOf(this.coors, this.coors.length);
    }

    @Override
    public final Scalar[] column(final int index) {
        final int first = this.index(1, index);
        return Arrays.copyOfRange(this.coors, first, first + this.source);
    }

    @Override
    public final Scalar[] line(final int index) {
        final int first = this.index(index, 1);
        final Scalar[] result = new Scalar[this.target];
        for (int idx = 0; idx < this.target; ++idx) {
            result[idx] = this.coors[first + idx * this.source];
        }
        return result;
    }

    @Override
    public final Vect apply(final Vect input) {
        final Scalar[] result = new Scalar[this.target];
        for (int idx = 0; idx < this.target; ++idx) {
            result[idx] = this.product(input, idx + 1);
        }
        return new FixedVector(result);
    }

    @Override
    public final int columns() {
        return this.target;
    }

    @Override
    public final int lines() {
        return this.source;
    }

    /**
     * Returns the single array index based on line and column indices.
     * @param lin Line index (1-based)
     * @param col Column index (1-based)
     * @return The single array index
     */
    private int index(final int lin, final int col) {
        return this.source * (col - 1) + lin - 1;
    }

    /**
     * Calculates the product of the given vector by a matrix column.
     * @param input Given vector
     * @param idx Matrix column index
     * @return Product
     */
    private Scalar product(final Vect input, final int idx) {
        return new Product(input, new FixedVector(this.column(idx)));
    }

    /**
     * Checks if this scalar array could be took as matrix coordinate, regarding
     * the matrix lines and columns count. Throws
     * {@link IllegalArgumentException} if the scalar array is not valid.
     * @param coor Scalar array to check
     * @return The scalar array if it is valid
     */
    private Scalar[] valid(final Scalar... coor) {
        final int expected = this.source * this.target;
        Preconditions.checkArgument(
            expected == coor.length,
            "Expected %d scalars for a matrix with %d lines and %d columns",
            expected, this.source, this.target
        );
        return coor;
    }

}
