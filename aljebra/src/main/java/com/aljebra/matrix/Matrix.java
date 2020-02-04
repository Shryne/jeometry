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

import com.aljebra.scalar.Scalar;
import com.aljebra.vector.Vect;

/**
 * Matrix interface. Represents an m*n dimension matrix that can be assimilated
 * to a linear transformation from n-dimension vector space to a m-dimension
 * vector space. The two vector spaces are supposed to be over the same field.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Matrix {

    /**
     * Matrix coordinates, ordered as a column by column scalars. If the matrix
     * is a 2x2 matrix with [a b] as a first line and [c d] as a second line,
     * the methods returns the scalar array in this order: a, c, b and d.
     * @return Matrix scalars.
     */
    Scalar[] coords();

    /**
     * Access the i-th column of the matrix.
     * @param index Column index
     * @return A scalar array representing the i-th column
     */
    Scalar[] column(final int index);

    /**
     * Access the j-th line of the matrix.
     * @param index Line index
     * @return A scalar array representing the j-th line
     */
    Scalar[] line(final int index);

    /**
     * Applies the linear transformation represented by this matrix on a vector.
     * @param input Input vector
     * @return Tranformation result
     */
    Vect apply(final Vect input);

    /**
     * Gives the matrix columns count which corresponds
     * to the source vector space dimension.
     * @return Columns number
     */
    int columns();

    /**
     * Gives the matrix lines count which corresponds
     * to the target vector space dimension.
     * @return Lines number
     */
    int lines();
}
