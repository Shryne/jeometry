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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Represents a matrix as the transpose of another matrix.
 * @param <T> scalar types
 * @since 0.1
 */
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TransposeMatrix<T> extends FixedMatrix<T> {

    /**
     * Constructor.
     * @param mat Matrix to transpose
     */
    public TransposeMatrix(final Matrix<T> mat) {
        super(mat.columns(), mat.lines(), TransposeMatrix.transpose(mat));
    }

    /**
     * Transposes a matrix.
     * @param mat Matrix to transpose
     * @param <T> scalar types
     * @return Scalar coordinates of the transposed matrix
     */
    private static <T> List<Scalar<T>> transpose(final Matrix<T> mat) {
        final int lines = mat.lines();
        final int cols = mat.columns();
        final List<Scalar<T>> result = new ArrayList<>(lines * cols);
        for (int line = 0; line < lines; ++line) {
            result.addAll(Arrays.asList(mat.line(line + 1)));
        }
        return result;
    }

}
