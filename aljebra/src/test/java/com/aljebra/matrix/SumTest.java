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

import com.aljebra.scalar.Add;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.FixedVector;
import com.aljebra.vector.Vect;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 * Tests for {@link Sum}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class SumTest {

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * {@link Sum} calculates coordinates as the addition of scalars.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void calculatesSumCoordinates() {
        final int lines = 3;
        final int cols = 4;
        final Scalar<Object>[] coorsa = SumTest.scalars(lines * cols);
        final Scalar<Object>[] coorsb = SumTest.scalars(lines * cols);
        final FixedMatrix<Object> first = new FixedMatrix<Object>(lines, cols, coorsa);
        final FixedMatrix<Object> second = new FixedMatrix<Object>(lines, cols, coorsb);
        final Matrix<Object> sum = new Sum<Object>(first, second);
        final Scalar<Object>[] expected = new com.aljebra.vector.Sum<Object>(
            new FixedVector<Object>(coorsa), new FixedVector<Object>(coorsb)
        ).coords();
        MatcherAssert.assertThat(sum.lines(), Matchers.equalTo(lines));
        MatcherAssert.assertThat(sum.columns(), Matchers.equalTo(cols));
        MatcherAssert.assertThat(sum.coords(), Matchers.equalTo(expected));
    }

    /**
     * {@link Sum} transforms a vector as the sum
     * of the transformations (linear).
     */
    @SuppressWarnings("unchecked")
    @Test
    public void appliesSumTransformation() {
        final int lines = 3;
        final int cols = 4;
        final FixedMatrix<Object> first = new FixedMatrix<Object>(
            lines, cols, SumTest.scalars(lines * cols)
        );
        final FixedMatrix<Object> second = new FixedMatrix<Object>(
            lines, cols, SumTest.scalars(lines * cols)
        );
        final Vect<Object> input = Mockito.mock(Vect.class);
        Mockito.when(input.coords()).thenReturn(SumTest.scalars(cols));
        MatcherAssert.assertThat(
            new Sum<Object>(first, second).apply(input),
            Matchers.equalTo(
                new com.aljebra.vector.Sum<Object>(
                    first.apply(input), second.apply(input)
                )
            )
        );
    }

    /**
     * {@link Sum} can return lines and columns.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void returnsLinesAndColumns() {
        final Scalar<Object> scalara = Mockito.mock(Scalar.class);
        final Scalar<Object> scalarb = Mockito.mock(Scalar.class);
        final Scalar<Object> scalarc = Mockito.mock(Scalar.class);
        final Scalar<Object> scalard = Mockito.mock(Scalar.class);
        final Scalar<Object> scalare = Mockito.mock(Scalar.class);
        final Scalar<Object> scalarf = Mockito.mock(Scalar.class);
        final Scalar<Object> scalarg = Mockito.mock(Scalar.class);
        final Scalar<Object> scalarh = Mockito.mock(Scalar.class);
        final Matrix<Object> matrix = new Sum<Object>(
            new FixedMatrix<Object>(
                2, 2, scalara, scalarb, scalarc, scalard
            ),
            new FixedMatrix<Object>(
                2, 2, scalare, scalarf, scalarg, scalarh
            )
        );
        MatcherAssert.assertThat(
            matrix.line(1),
            Matchers.equalTo(
                new Scalar[] {
                    new Add<Object>(scalara, scalare), new Add<Object>(scalarc, scalarg),
                }
            )
        );
        MatcherAssert.assertThat(
            matrix.line(2),
            Matchers.equalTo(
                new Scalar[] {
                    new Add<Object>(scalarb, scalarf), new Add<Object>(scalard, scalarh),
                }
            )
        );
        MatcherAssert.assertThat(
            matrix.column(1),
            Matchers.equalTo(
                new Scalar[] {
                    new Add<Object>(scalara, scalare), new Add<Object>(scalarb, scalarf),
                }
            )
        );
        MatcherAssert.assertThat(
            matrix.column(2),
            Matchers.equalTo(
                new Scalar[] {
                    new Add<Object>(scalarc, scalarg), new Add<Object>(scalard, scalarh),
                }
            )
        );
    }

    /**
     * {@link Sum} throws exception if the two matrices don't have
     * the same size.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void errorsWhenNotSameSize() {
        this.thrown.expect(IllegalArgumentException.class);
        final Matrix<Object> mata = Mockito.mock(Matrix.class);
        final Matrix<Object> matb = Mockito.mock(Matrix.class);
        Mockito.when(mata.lines()).thenReturn(1);
        Mockito.when(mata.columns()).thenReturn(1);
        Mockito.when(matb.lines()).thenReturn(1);
        Mockito.when(matb.columns()).thenReturn(2);
        new Sum<Object>(mata, matb);
    }

    /**
     * {@link Sum} respects equals with disregard to operands order
     * (commutative).
     */
    @SuppressWarnings("unchecked")
    @Test
    public void equalsWhenOperandOrderChanges() {
        final int lines = 3;
        final int cols = 4;
        final FixedMatrix<Object> first = new FixedMatrix<Object>(
            lines, cols, SumTest.scalars(lines * cols)
        );
        final FixedMatrix<Object> second = new FixedMatrix<Object>(
            lines, cols, SumTest.scalars(lines * cols)
        );
        final FixedMatrix<Object> third = new FixedMatrix<Object>(
            lines, cols, SumTest.scalars(lines * cols)
        );
        MatcherAssert.assertThat(
            new Sum<Object>(first, second, third),
            Matchers.allOf(
                Matchers.equalTo(new Sum<Object>(first, third, second)),
                Matchers.equalTo(new Sum<Object>(second, third, first)),
                Matchers.equalTo(new Sum<Object>(second, first, third)),
                Matchers.equalTo(new Sum<Object>(third, second, first)),
                Matchers.equalTo(new Sum<Object>(third, first, second))
            )
        );
    }

    /**
     * Mocks an array of {@link Scalar} with a given length.
     * @param length Array length
     * @return An array of scalars
     */
    @SuppressWarnings("unchecked")
    private static Scalar<Object>[] scalars(final int length) {
        final Scalar<Object>[] result = new Scalar[length];
        for (int idx = 0; idx < result.length; ++idx) {
            result[idx] = Mockito.mock(Scalar.class);
        }
        return result;
    }

}
