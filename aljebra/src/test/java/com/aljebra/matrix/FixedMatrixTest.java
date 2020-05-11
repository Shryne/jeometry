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
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
        final Iterable<Scalar<Object>> scalars = new Scalars<>(lines * cols);
        final FixedMatrix<Object> matrix = new FixedMatrix<>(lines, cols, scalars);
        MatcherAssert.assertThat(
            Arrays.asList(matrix.coords()), Matchers.equalTo(Lists.newArrayList(scalars))
        );
    }

    /**
     * {@link FixedMatrix} can return lines and columns.
     */
    @Test
    public void returnsLinesAndColumns() {
        final Default<String> scalara = new Scalar.Default<>("a");
        final Default<String> scalarb = new Scalar.Default<>("b");
        final Default<String> scalarc = new Scalar.Default<>("c");
        final Default<String> scalard = new Scalar.Default<>("d");
        final FixedMatrix<String> matrix = new FixedMatrix<>(
            2, 2, Arrays.asList(scalara, scalarb, scalarc, scalard)
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
    @Test
    public void appliesTransformation() {
        final int lines = 3;
        final int cols = 4;
        final FixedMatrix<Object> matrix = new FixedMatrix<>(
            lines, cols, new Scalars<>(lines * cols)
        );
        final List<Scalar<Object>> expected = new ArrayList<>(lines);
        final Vect<Object> input = new FixedVector<>(new Scalars<>(cols));
        for (int idx = 0; idx < lines; ++idx) {
            expected.add(FixedMatrixTest.pdt(input, Arrays.asList(matrix.line(idx + 1))));
        }
        MatcherAssert.assertThat(
            Arrays.asList(matrix.apply(input).coords()), Matchers.equalTo(expected)
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
        final FixedMatrix<Object> matrix = new FixedMatrix<>(
            lines, cols, new Scalars<>(lines * cols)
        );
        final Vect<Object> input = new FixedVector<>(new Scalars<>(cols + 1));
        matrix.apply(input);
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
