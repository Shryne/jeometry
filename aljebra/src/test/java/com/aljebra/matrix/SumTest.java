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

import com.aljebra.scalar.Add;
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
 * Tests for {@link Sum}.
 * @since 0.1
 */
public final class SumTest {

    /**
     * {@link Sum} calculates coordinates as the addition of scalars.
     */
    @Test
    public void calculatesSumCoordinates() {
        final int lines = 3;
        final int cols = 4;
        final Iterable<Scalar<Object>> coorsa = new Scalars<>(lines * cols);
        final Iterable<Scalar<Object>> coorsb = new Scalars<>(lines * cols);
        final FixedMatrix<Object> first = new FixedMatrix<>(lines, cols, coorsa);
        final FixedMatrix<Object> second = new FixedMatrix<>(lines, cols, coorsb);
        final Matrix<Object> sum = new Sum<>(Arrays.asList(first, second));
        final Scalar<Object>[] expected = new com.aljebra.vector.Sum<Object>(
            Arrays.asList(
                new FixedVector<Object>(coorsa),
                new FixedVector<Object>(coorsb)
            )
        ).coords();
        MatcherAssert.assertThat(sum.lines(), Matchers.equalTo(lines));
        MatcherAssert.assertThat(sum.columns(), Matchers.equalTo(cols));
        MatcherAssert.assertThat(sum.coords(), Matchers.equalTo(expected));
    }

    /**
     * {@link Sum} transforms a vector as the sum
     * of the transformations (linear).
     */
    @Test
    public void appliesSumTransformation() {
        final int lines = 3;
        final int cols = 4;
        final FixedMatrix<Object> first = new FixedMatrix<>(
            lines, cols, new Scalars<>(lines * cols)
        );
        final FixedMatrix<Object> second = new FixedMatrix<>(
            lines, cols, new Scalars<>(lines * cols)
        );
        final Vect<Object> input = new FixedVector<>(new Scalars<>(cols));
        MatcherAssert.assertThat(
            new Sum<Object>(Arrays.asList(first, second)).apply(input),
            Matchers.equalTo(
                new com.aljebra.vector.Sum<Object>(
                    Arrays.asList(first.apply(input), second.apply(input))
                )
            )
        );
    }

    /**
     * {@link Sum} can return lines and columns.
     */
    @Test
    public void returnsLinesAndColumns() {
        final int lines = 2;
        final int cols = 2;
        Iterator<Scalar<Object>> scalars = new Scalars<>(lines * cols).iterator();
        final Scalar<Object> scalara = scalars.next();
        final Scalar<Object> scalarb = scalars.next();
        final Scalar<Object> scalarc = scalars.next();
        final Scalar<Object> scalard = scalars.next();
        scalars = new Scalars<>(lines * cols).iterator();
        final Scalar<Object> scalare = scalars.next();
        final Scalar<Object> scalarf = scalars.next();
        final Scalar<Object> scalarg = scalars.next();
        final Scalar<Object> scalarh = scalars.next();
        final Matrix<Object> matrix = new Sum<>(
            Arrays.asList(
                new FixedMatrix<Object>(
                    lines, cols, Arrays.asList(scalara, scalarb, scalarc, scalard)
                ),
                new FixedMatrix<Object>(
                    lines, cols, Arrays.asList(scalare, scalarf, scalarg, scalarh)
                )
            )
        );
        MatcherAssert.assertThat(
            matrix.line(1),
            Matchers.equalTo(
                new Scalar[] {
                    new Add<Object>(Arrays.asList(scalara, scalare)),
                    new Add<Object>(Arrays.asList(scalarc, scalarg)),
                }
            )
        );
        MatcherAssert.assertThat(
            matrix.line(2),
            Matchers.equalTo(
                new Scalar[] {
                    new Add<Object>(Arrays.asList(scalarb, scalarf)),
                    new Add<Object>(Arrays.asList(scalard, scalarh)),
                }
            )
        );
        MatcherAssert.assertThat(
            matrix.column(1),
            Matchers.equalTo(
                new Scalar[] {
                    new Add<Object>(Arrays.asList(scalara, scalare)),
                    new Add<Object>(Arrays.asList(scalarb, scalarf)),
                }
            )
        );
        MatcherAssert.assertThat(
            matrix.column(2),
            Matchers.equalTo(
                new Scalar[] {
                    new Add<Object>(Arrays.asList(scalarc, scalarg)),
                    new Add<Object>(Arrays.asList(scalard, scalarh)),
                }
            )
        );
    }

    /**
     * {@link Sum} respects equals with disregard to operands order
     * (commutative).
     */
    @Test
    public void equalsWhenOperandOrderChanges() {
        final int lines = 3;
        final int cols = 4;
        final FixedMatrix<Object> first = new FixedMatrix<>(
            lines, cols, new Scalars<>(lines * cols)
        );
        final FixedMatrix<Object> second = new FixedMatrix<>(
            lines, cols, new Scalars<>(lines * cols)
        );
        final FixedMatrix<Object> third = new FixedMatrix<>(
            lines, cols, new Scalars<>(lines * cols)
        );
        MatcherAssert.assertThat(
            new Sum<Object>(Arrays.asList(first, second, third)),
            Matchers.allOf(
                Matchers.equalTo(new Sum<Object>(Arrays.asList(first, third, second))),
                Matchers.equalTo(new Sum<Object>(Arrays.asList(second, third, first))),
                Matchers.equalTo(new Sum<Object>(Arrays.asList(second, first, third))),
                Matchers.equalTo(new Sum<Object>(Arrays.asList(third, second, first))),
                Matchers.equalTo(new Sum<Object>(Arrays.asList(third, first, second)))
            )
        );
    }

}
