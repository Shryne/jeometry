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
package com.jeometry.model.algebra.matrix;

import com.jeometry.model.algebra.scalar.Add;
import com.jeometry.model.algebra.scalar.Multiplication;
import com.jeometry.model.algebra.scalar.Scalar;
import com.jeometry.model.algebra.scalar.Scalar.Default;
import com.jeometry.model.algebra.vector.FixedVector;
import com.jeometry.model.algebra.vector.Vect;
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
        @SuppressWarnings("unchecked")
        final Matcher<Scalar>[] matchers = new Matcher[scalars.length];
        for (int idx = 0; idx < scalars.length; ++idx) {
            matchers[idx] = Matchers.equalTo(scalars[idx]);
        }
        final FixedMatrix matrix = new FixedMatrix(lines, cols, scalars);
        MatcherAssert.assertThat(
            matrix.coords(), Matchers.array(matchers)
        );
    }

    /**
     * {@link FixedMatrix} can return lines and columns.
     */
    @SuppressWarnings("unchecked")
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
            matrix.line(1),
            Matchers.array(Matchers.equalTo(scalara), Matchers.equalTo(scalarc))
        );
        MatcherAssert.assertThat(
            matrix.line(2),
            Matchers.array(Matchers.equalTo(scalarb), Matchers.equalTo(scalard))
        );
        MatcherAssert.assertThat(
            matrix.column(1),
            Matchers.array(Matchers.equalTo(scalara), Matchers.equalTo(scalarb))
        );
        MatcherAssert.assertThat(
            matrix.column(2),
            Matchers.array(Matchers.equalTo(scalarc), Matchers.equalTo(scalard))
        );
    }

    /**
     * {@link FixedMatrix} can apply transformation.
     */
    @Test
    public void appliesTransformation() {
        final int lines = 3;
        final int cols = 4;
        final Scalar[] scalars = FixedMatrixTest.scalars(lines * cols);
        final FixedMatrix matrix = new FixedMatrix(lines, cols, scalars);
        @SuppressWarnings("unchecked")
        final Matcher<Scalar>[] matchers = new Matcher[cols];
        final Vect input = new FixedVector(FixedMatrixTest.scalars(lines));
        for (int idx = 0; idx < cols; ++idx) {
            final Scalar[] multis = new Scalar[lines];
            final Scalar[] column = matrix.column(idx + 1);
            for (int idl = 0; idl < lines; ++idl) {
                multis[idl] = new Multiplication(
                    input.coords()[idl], column[idl]
                );
            }
            matchers[idx] = Matchers.equalTo(new Add(multis));
        }
        MatcherAssert.assertThat(
            matrix.apply(input).coords(), Matchers.array(matchers)
        );
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
}
