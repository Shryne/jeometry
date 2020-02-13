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
package com.aljebra.vector;

import com.aljebra.scalar.Diff;
import com.aljebra.scalar.Scalar;
import java.util.Random;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 * Tests for {@link Minus}.
 * @since 0.1
 */
public final class MinusTest {

    /**
     * Max scalar array length to generate(mock).
     */
    private static final int COORDS_LENGTH = 10;

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * {@link Minus} throws exception if the two vectors don't have
     * the same dimension.
     */
    @Test
    public void errorsWhenNotSameSize() {
        this.thrown.expect(IllegalArgumentException.class);
        final Vect vecta = Mockito.mock(Vect.class);
        final Vect vectb = Mockito.mock(Vect.class);
        final Scalar[] acoords = MinusTest.scalars();
        final Scalar[] bcoords = MinusTest.scalars(acoords.length + 1);
        Mockito.when(vectb.coords()).thenReturn(bcoords);
        Mockito.when(vecta.coords()).thenReturn(acoords);
        new Minus(vecta, vectb);
    }

    /**
     * {@link Minus} coordinates equals to the field substraction
     * of vectors coordinates.
     */
    @Test
    public void coordsEqualCoordsDiff() {
        final Vect vecta = Mockito.mock(Vect.class);
        final Vect vectb = Mockito.mock(Vect.class);
        final Scalar[] acoords = MinusTest.scalars();
        final Scalar[] bcoords = MinusTest.scalars(acoords.length);
        Mockito.when(vectb.coords()).thenReturn(bcoords);
        Mockito.when(vecta.coords()).thenReturn(acoords);
        final Scalar[] expected = new Scalar[acoords.length];
        for (int idx = 0; idx < expected.length; ++idx) {
            expected[idx] = MinusTest.minus(acoords[idx], bcoords[idx]);
        }
        MatcherAssert.assertThat(
            new Minus(vecta, vectb).coords(), Matchers.equalTo(expected)
        );
    }

    /**
     * Calculates the difference between two scalars.
     * @param scalar First scalar
     * @param another Second scalar
     * @return A scalar representing the difference between two scalars
     */
    private static Scalar minus(final Scalar scalar, final Scalar another) {
        return new Diff(scalar, another);
    }

    /**
     * Mocks an array of {@link Scalar} with a random length.
     * @return An array of scalars.
     */
    private static Scalar[] scalars() {
        return MinusTest.scalars(new Random().nextInt(MinusTest.COORDS_LENGTH));
    }

    /**
     * Mocks an array of {@link Scalar} with the given length.
     * @param length Array length to generate
     * @return An array of scalars.
     */
    private static Scalar[] scalars(final int length) {
        final Scalar[] result = new Scalar[length];
        for (int idx = 0; idx < result.length; ++idx) {
            result[idx] = Mockito.mock(Scalar.class);
        }
        return result;
    }

}
