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

import com.aljebra.scalar.Multiplication;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.FixedVector;
import com.aljebra.vector.Vect;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link Times}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class TimesTest {

    /**
     * {@link Times} calculates coordinates as the multiplication of scalars.
     */
    @Test
    public void calculatesTimesCoordinates() {
        final int lines = 3;
        final int cols = 4;
        final Scalar[] coorsa = TimesTest.scalars(lines * cols);
        final Scalar factor = Mockito.mock(Scalar.class);
        final Matrix times = new Times(
            new FixedMatrix(lines, cols, coorsa), factor
        );
        final Scalar[] expected = new com.aljebra.vector.Times(
            new FixedVector(coorsa), factor
        ).coords();
        MatcherAssert.assertThat(times.lines(), Matchers.equalTo(lines));
        MatcherAssert.assertThat(times.columns(), Matchers.equalTo(cols));
        MatcherAssert.assertThat(times.coords(), Matchers.equalTo(expected));
    }

    /**
     * {@link Times} can return lines and columns.
     */
    @Test
    public void returnsLinesAndColumns() {
        final Scalar scalara = Mockito.mock(Scalar.class);
        final Scalar scalarb = Mockito.mock(Scalar.class);
        final Scalar scalarc = Mockito.mock(Scalar.class);
        final Scalar scalard = Mockito.mock(Scalar.class);
        final Scalar scalare = Mockito.mock(Scalar.class);
        final Matrix matrix = new Times(
            new FixedMatrix(
                2, 2, scalara, scalarb, scalarc, scalard
            ),
            scalare
        );
        MatcherAssert.assertThat(
            matrix.line(1),
            Matchers.equalTo(
                new Scalar[]{
                    new Multiplication(scalara, scalare),
                    new Multiplication(scalarc, scalare),
                }
            )
        );
        MatcherAssert.assertThat(
            matrix.line(2),
            Matchers.equalTo(
                new Scalar[]{
                    new Multiplication(scalarb, scalare),
                    new Multiplication(scalard, scalare),
                }
            )
        );
        MatcherAssert.assertThat(
            matrix.column(1),
            Matchers.equalTo(
                new Scalar[]{
                    new Multiplication(scalara, scalare),
                    new Multiplication(scalarb, scalare),
                }
            )
        );
        MatcherAssert.assertThat(
            matrix.column(2),
            Matchers.equalTo(
                new Scalar[]{
                    new Multiplication(scalarc, scalare),
                    new Multiplication(scalard, scalare),
                }
            )
        );
    }

    /**
     * {@link Times} transforms a vector as the multiplication
     * of the transformations by the scalar (linear).
     */
    @Test
    public void appliesTimesTransformation() {
        final int lines = 3;
        final int cols = 4;
        final FixedMatrix first = new FixedMatrix(
            lines, cols, TimesTest.scalars(lines * cols)
        );
        final Vect input = Mockito.mock(Vect.class);
        Mockito.when(input.coords()).thenReturn(TimesTest.scalars(cols));
        final Scalar factor = Mockito.mock(Scalar.class);
        MatcherAssert.assertThat(
            new Times(first, factor).apply(input),
            Matchers.equalTo(
                new com.aljebra.vector.Times(first.apply(input), factor)
            )
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
