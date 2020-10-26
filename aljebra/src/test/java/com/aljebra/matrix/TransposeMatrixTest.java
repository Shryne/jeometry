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

import com.aljebra.metric.scalar.Product;
import com.aljebra.scalar.Scalar;
import com.aljebra.scalar.Scalar.Default;
import com.aljebra.scalar.mock.Scalars;
import com.aljebra.vector.FixedVector;
import com.aljebra.vector.Vect;
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
            Arrays.asList(matrix.coords()),
            Matchers.equalTo(Arrays.asList(scala, scald, scalb, scale, scalc, scalf))
        );
    }

    /**
     * {@link TransposeMatrix} can return lines and columns count.
     */
    @Test
    public void returnsLinesAndColumnsCount() {
        final int lines = 3;
        final int cols = 4;
        final Matrix<Object> matrix = new TransposeMatrix<>(
            new FixedMatrix<Object>(
                lines, cols, new Scalars<>(lines * cols)
            )
        );
        MatcherAssert.assertThat(matrix.lines(), Matchers.equalTo(cols));
        MatcherAssert.assertThat(matrix.columns(), Matchers.equalTo(lines));
    }

    /**
     * {@link TransposeMatrix} can return lines and columns elements.
     */
    @Test
    public void returnsLinesAndColumns() {
        final Default<String> scalara = new Scalar.Default<>("a");
        final Default<String> scalarb = new Scalar.Default<>("b");
        final Default<String> scalarc = new Scalar.Default<>("c");
        final Default<String> scalard = new Scalar.Default<>("d");
        final Matrix<String> matrix = new TransposeMatrix<>(
            new FixedMatrix<>(
                2, 2, Arrays.asList(scalara, scalarb, scalarc, scalard)
            )
        );
        MatcherAssert.assertThat(
            matrix.column(1), Matchers.equalTo(new Scalar[] {scalara, scalarc})
        );
        MatcherAssert.assertThat(
            matrix.column(2), Matchers.equalTo(new Scalar[] {scalarb, scalard})
        );
        MatcherAssert.assertThat(
            matrix.line(1), Matchers.equalTo(new Scalar[] {scalara, scalarb})
        );
        MatcherAssert.assertThat(
            matrix.line(2), Matchers.equalTo(new Scalar[] {scalarc, scalard})
        );
    }

    /**
     * {@link FixedMatrix} can apply transformation.
     */
    @Test
    public void appliesTransformation() {
        final int lines = 3;
        final int cols = 4;
        final Matrix<Object> matrix = new TransposeMatrix<>(
            new FixedMatrix<>(
                lines, cols, new Scalars<>(lines * cols)
            )
        );
        final List<Scalar<Object>> expected = new ArrayList<>(cols);
        final Vect<Object> input = new FixedVector<>(new Scalars<>(lines));
        for (int idx = 0; idx < cols; ++idx) {
            expected.add(TransposeMatrixTest.pdt(input, Arrays.asList(matrix.line(idx + 1))));
        }
        MatcherAssert.assertThat(
            Arrays.asList(matrix.apply(input).coords()), Matchers.equalTo(expected)
        );
    }

    /**
     * {@link TransposeMatrix} respects equality regarding transposed matrix.
     */
    @Test
    public void respectsEqualAndHashcode() {
        final int lines = 3;
        final int cols = 4;
        final FixedMatrix<Object> input = new FixedMatrix<>(
            lines, cols, new Scalars<>(lines * cols)
        );
        MatcherAssert.assertThat(
            new TransposeMatrix<>(input),
            Matchers.equalTo(new TransposeMatrix<>(input))
        );
        MatcherAssert.assertThat(
            new TransposeMatrix<>(input).hashCode(),
            Matchers.equalTo(new TransposeMatrix<>(input).hashCode())
        );
    }

    /**
     * {@link TransposeMatrix} toString prints matrix content.
     */
    @Test
    public void printsCoords() {
        final int lines = 3;
        final int cols = 4;
        final Scalars<Object> scalars = new Scalars<>(lines * cols);
        final TransposeMatrix<Object> matrix = new TransposeMatrix<>(
            new FixedMatrix<Object>(lines, cols, scalars)
        );
        for (final Scalar<Object> scalar : scalars) {
            MatcherAssert.assertThat(
                matrix.toString(), Matchers.containsString(scalar.toString())
            );
        }
    }

    /**
     * Gives a scalar representing the product value of the given vector
     * by a coordinate array.
     * @param input Input vector
     * @param scalars Scalar list by which to operate the dot product
     * @return A {@link Scalar}, value of the product
     */
    private static Scalar<Object> pdt(final Vect<Object> input,
        final List<Scalar<Object>> scalars) {
        return new Product<Object>(input, new FixedVector<Object>(scalars));
    }
}
