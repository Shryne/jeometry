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

import com.aljebra.scalar.Multiplication;
import com.aljebra.scalar.Scalar;
import com.aljebra.scalar.mock.Scalars;
import com.aljebra.vector.FixedVector;
import com.aljebra.vector.Vect;
import java.util.Arrays;
import java.util.Iterator;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link Times}.
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
        final Iterable<Scalar<Object>> coorsa = new Scalars<>(lines * cols);
        final Scalar<Object> factor = new Scalars<>(1).iterator().next();
        final Matrix<Object> times = new Times<>(
            new FixedMatrix<Object>(lines, cols, coorsa), factor
        );
        final Scalar<Object>[] expected = new com.aljebra.vector.Times<Object>(
            new FixedVector<Object>(coorsa), factor
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
        final int lines = 2;
        final int cols = 2;
        final Iterator<Scalar<Object>> scalars = new Scalars<>(lines * cols + 1).iterator();
        final Scalar<Object> scalara = scalars.next();
        final Scalar<Object> scalarb = scalars.next();
        final Scalar<Object> scalarc = scalars.next();
        final Scalar<Object> scalard = scalars.next();
        final Scalar<Object> scalare = scalars.next();
        final Matrix<Object> matrix = new Times<>(
            new FixedMatrix<Object>(
                lines, cols, Arrays.asList(scalara, scalarb, scalarc, scalard)
            ),
            scalare
        );
        MatcherAssert.assertThat(
            matrix.line(1),
            Matchers.equalTo(
                new Scalar[] {
                    new Multiplication<Object>(Arrays.asList(scalara, scalare)),
                    new Multiplication<Object>(Arrays.asList(scalarc, scalare)),
                }
            )
        );
        MatcherAssert.assertThat(
            matrix.line(2),
            Matchers.equalTo(
                new Scalar[] {
                    new Multiplication<Object>(Arrays.asList(scalarb, scalare)),
                    new Multiplication<Object>(Arrays.asList(scalard, scalare)),
                }
            )
        );
        MatcherAssert.assertThat(
            matrix.column(1),
            Matchers.equalTo(
                new Scalar[] {
                    new Multiplication<Object>(Arrays.asList(scalara, scalare)),
                    new Multiplication<Object>(Arrays.asList(scalarb, scalare)),
                }
            )
        );
        MatcherAssert.assertThat(
            matrix.column(2),
            Matchers.equalTo(
                new Scalar[] {
                    new Multiplication<Object>(Arrays.asList(scalarc, scalare)),
                    new Multiplication<Object>(Arrays.asList(scalard, scalare)),
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
        final FixedMatrix<Object> first = new FixedMatrix<>(
            lines, cols, new Scalars<>(lines * cols)
        );
        final Vect<Object> input = new FixedVector<>(new Scalars<>(cols));
        final Scalar<Object> factor = new Scalars<>(1).iterator().next();
        MatcherAssert.assertThat(
            new Times<Object>(first, factor).apply(input),
            Matchers.equalTo(
                new com.aljebra.vector.Times<Object>(first.apply(input), factor)
            )
        );
    }

}
