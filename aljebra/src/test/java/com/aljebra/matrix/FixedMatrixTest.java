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
import com.aljebra.scalar.Scalar.Default;
import com.aljebra.vector.FixedVector;
import com.aljebra.vector.Vect;
import com.aljebra.vector.metric.Product;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link FixedMatrix}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FixedMatrixTest {

    /**
     * {@link FixedMatrix} can return coordinates.
     */
    @Test
    public void returnsCoords() {
        final int lines = 3;
        final int cols = 4;
        final Scalar[] scalars = FixedMatrixTest.scalars(lines * cols);
        final FixedMatrix matrix = new FixedMatrix(lines, cols, scalars);
        MatcherAssert.assertThat(
            matrix.coords(), FixedMatrixTest.matchers(scalars)
        );
    }

    /**
     * {@link FixedMatrix} can return lines and columns.
     */
    @Test
    public void returnsLinesAndColumns() {
        final Default<String> scalara = new Scalar.Default<String>("a");
        final Default<String> scalarb = new Scalar.Default<String>("b");
        final Default<String> scalarc = new Scalar.Default<String>("c");
        final Default<String> scalard = new Scalar.Default<String>("d");
        final FixedMatrix matrix = new FixedMatrix(
            2, 2, scalara, scalarb, scalarc, scalard
        );
        MatcherAssert.assertThat(
            matrix.line(1), FixedMatrixTest.matchers(scalara, scalarc)
        );
        MatcherAssert.assertThat(
            matrix.line(2), FixedMatrixTest.matchers(scalarb, scalard)
        );
        MatcherAssert.assertThat(
            matrix.column(1), FixedMatrixTest.matchers(scalara, scalarb)
        );
        MatcherAssert.assertThat(
            matrix.column(2), FixedMatrixTest.matchers(scalarc, scalard)
        );
    }

    /**
     * {@link FixedMatrix} can apply transformation.
     */
    @Test
    public void appliesTransformation() {
        final int lines = 3;
        final int cols = 4;
        final FixedMatrix matrix = new FixedMatrix(
            lines, cols, FixedMatrixTest.scalars(lines * cols)
        );
        final Scalar[] expected = new Scalar[lines];
        final Vect input = new FixedVector(FixedMatrixTest.scalars(cols));
        for (int idx = 0; idx < lines; ++idx) {
            expected[idx] = FixedMatrixTest.pdt(input, matrix.line(idx + 1));
        }
        MatcherAssert.assertThat(
            matrix.apply(input).coords(), FixedMatrixTest.matchers(expected)
        );
    }

    /**
     * Gives a scalar representing the product value of the given vector
     * by a coordinate array.
     * @param input Input vector
     * @param scalars Scalar array by which to operate the dot product
     * @return A {@link Scalar}, value of the product
     */
    private static Scalar pdt(final Vect input, final Scalar... scalars) {
        return new Product(input, new FixedVector(scalars));
    }

    /**
     * Mocks an array of {@link Scalar} with a given length.
     * @param length Array length
     * @return An array of scalars
     */
    private static Scalar[] scalars(final int length) {
        final Scalar[] result = new Scalar[length];
        for (int idx = 0; idx < result.length; ++idx) {
            result[idx] = Mockito.mock(Scalar.class);
        }
        return result;
    }

    /**
     * Build an equality matcher for elements of a scalar array.
     * @param scalars Scalar array
     * @return A hamcrest equality matcher
     */
    @SuppressWarnings("unchecked")
    private static Matcher<Scalar[]> matchers(final Scalar... scalars) {
        final Matcher<Scalar>[] matchers = new Matcher[scalars.length];
        for (int idx = 0; idx < scalars.length; ++idx) {
            matchers[idx] = Matchers.equalTo(scalars[idx]);
        }
        return Matchers.array(matchers);
    }
}
