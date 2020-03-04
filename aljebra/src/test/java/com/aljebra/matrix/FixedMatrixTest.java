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
import com.aljebra.vector.FixedVector;
import com.aljebra.vector.Vect;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 * Tests for {@link FixedMatrix}.
 * @since 0.1
 */
public final class FixedMatrixTest {

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * {@link FixedMatrix} can return coordinates.
     */
    @Test
    public void returnsCoords() {
        final int lines = 3;
        final int cols = 4;
        final Scalar<Double>[] scalars = FixedMatrixTest.scalars(lines * cols);
        final FixedMatrix<Double> matrix = new FixedMatrix<Double>(lines, cols, scalars);
        MatcherAssert.assertThat(
            matrix.coords(), Matchers.equalTo(scalars)
        );
    }

    /**
     * {@link FixedMatrix} can return lines and columns.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void returnsLinesAndColumns() {
        final Default<String> scalara = new Scalar.Default<>("a");
        final Default<String> scalarb = new Scalar.Default<>("b");
        final Default<String> scalarc = new Scalar.Default<>("c");
        final Default<String> scalard = new Scalar.Default<>("d");
        final FixedMatrix<String> matrix = new FixedMatrix<String>(
            2, 2, scalara, scalarb, scalarc, scalard
        );
        MatcherAssert.assertThat(
            matrix.line(1), Matchers.equalTo(new Scalar[] {scalara, scalarc})
        );
        MatcherAssert.assertThat(
            matrix.line(2), Matchers.equalTo(new Scalar[] {scalarb, scalard})
        );
        MatcherAssert.assertThat(
            matrix.column(1), Matchers.equalTo(new Scalar[] {scalara, scalarb})
        );
        MatcherAssert.assertThat(
            matrix.column(2), Matchers.equalTo(new Scalar[] {scalarc, scalard})
        );
    }

    /**
     * {@link FixedMatrix} can apply transformation.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void appliesTransformation() {
        final int lines = 3;
        final int cols = 4;
        final FixedMatrix<Double> matrix = new FixedMatrix<Double>(
            lines, cols, FixedMatrixTest.scalars(lines * cols)
        );
        final Scalar<Double>[] expected = new Scalar[lines];
        final Vect<Double> input = new FixedVector<Double>(FixedMatrixTest.scalars(cols));
        for (int idx = 0; idx < lines; ++idx) {
            expected[idx] = FixedMatrixTest.pdt(input, matrix.line(idx + 1));
        }
        MatcherAssert.assertThat(
            matrix.apply(input).coords(), Matchers.equalTo(expected)
        );
    }

    /**
     * {@link FixedMatrix} throws exception when transforming
     * a wrong vector size.
     */
    @Test
    public void errorsWhenBadVectorSize() {
        this.thrown.expect(IllegalArgumentException.class);
        final int lines = 3;
        final int cols = 4;
        final FixedMatrix<Double> matrix = new FixedMatrix<Double>(
            lines, cols, FixedMatrixTest.scalars(lines * cols)
        );
        final Vect<Double> input = new FixedVector<Double>(FixedMatrixTest.scalars(cols + 1));
        matrix.apply(input);
    }

    /**
     * Gives a scalar representing the product value of the given vector
     * by a coordinate array.
     * @param input Input vector
     * @param scalars Scalar array by which to operate the dot product
     * @return A {@link Scalar}, value of the product
     */
    @SuppressWarnings("unchecked")
    private static Scalar<Double> pdt(final Vect<Double> input, final Scalar<Double>... scalars) {
        return new Product<Double>(input, new FixedVector<Double>(scalars));
    }

    /**
     * Mocks an array of {@link Scalar} with a given length.
     * @param length Array length
     * @return An array of scalars
     */
    @SuppressWarnings("unchecked")
    private static Scalar<Double>[] scalars(final int length) {
        final Scalar<Double>[] result = new Scalar[length];
        for (int idx = 0; idx < result.length; ++idx) {
            result[idx] = Mockito.mock(Scalar.class);
        }
        return result;
    }
}
