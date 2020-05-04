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
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link TransposeMatrix}.
 * @since 0.1
 */
public final class TransposeMatrixTest {

    /**
     * {@link TransposeMatrix} calculates coordinates.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void calculatesTransposedCoordinates() {
        final int lines = 3;
        final int cols = 2;
        final Scalar<Object> scala = new Scalar.Default<>(new Object());
        final Scalar<Object> scalb = new Scalar.Default<>(new Object());
        final Scalar<Object> scalc = new Scalar.Default<>(new Object());
        final Scalar<Object> scald = new Scalar.Default<>(new Object());
        final Scalar<Object> scale = new Scalar.Default<>(new Object());
        final Scalar<Object> scalf = new Scalar.Default<>(new Object());
        final Matrix<Object> matrix = new TransposeMatrix<>(
            new FixedMatrix<Object>(
                lines, cols, Arrays.asList(scala, scalb, scalc, scald, scale, scalf)
            )
        );
        MatcherAssert.assertThat(
            matrix.coords(),
            Matchers.array(
                Matchers.equalTo(scala), Matchers.equalTo(scald),
                Matchers.equalTo(scalb), Matchers.equalTo(scale),
                Matchers.equalTo(scalc), Matchers.equalTo(scalf)
            )
        );
    }

    /**
     * {@link TransposeMatrix} can return lines and columns.
     */
    @Test
    public void returnsLinesAndColumns() {
        final int lines = 3;
        final int cols = 4;
        final Matrix<Object> matrix = new TransposeMatrix<>(
            new FixedMatrix<Object>(
                lines, cols, TransposeMatrixTest.scalars(lines * cols)
            )
        );
        MatcherAssert.assertThat(matrix.lines(), Matchers.equalTo(cols));
        MatcherAssert.assertThat(matrix.columns(), Matchers.equalTo(lines));
    }

    /**
     * Mocks a list of {@link Scalar} with a given size.
     * @param length List size
     * @return A list of scalars
     */
    private static List<Scalar<Object>> scalars(final int length) {
        final List<Scalar<Object>> result = new ArrayList<>(length);
        for (int idx = 0; idx < length; ++idx) {
            result.add(new Scalar.Default<>(new Object()));
        }
        return result;
    }

}
