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

import com.aljebra.scalar.Add;
import com.aljebra.scalar.Scalar;
import java.util.Arrays;
import java.util.Random;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link Sum}.
 * @since 0.1
 */
public final class SumTest {

    /**
     * Max scalar array length to generate(mock).
     */
    private static final int COORDS_LENGTH = 10;

    /**
     * {@link Sum} coordinates equals to the field additions
     * of vectors coordinates.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void coordsEqualCoordsSum() {
        final Vect<Object> vecta = Mockito.mock(Vect.class);
        final Vect<Object> vectb = Mockito.mock(Vect.class);
        final Scalar<Object>[] acoords = SumTest.scalars();
        final Scalar<Object>[] bcoords = SumTest.scalars(acoords.length);
        Mockito.when(vectb.coords()).thenReturn(bcoords);
        Mockito.when(vecta.coords()).thenReturn(acoords);
        final Scalar<Object>[] expected = Arrays.copyOf(acoords, acoords.length);
        for (int idx = 0; idx < expected.length; ++idx) {
            expected[idx] = SumTest.sum(acoords[idx], bcoords[idx]);
        }
        MatcherAssert.assertThat(
            new Sum<>(Arrays.asList(vecta, vectb)).coords(), Matchers.equalTo(expected)
        );
    }

    /**
     * Calculates the sum of two scalars.
     * @param scalar First scalar
     * @param another Second scalar
     * @return A scalar representing the sum if the two passed scalars
     */
    private static Scalar<Object> sum(final Scalar<Object> scalar, final Scalar<Object> another) {
        return new Add<>(Arrays.asList(scalar, another));
    }

    /**
     * Mocks an array of {@link Scalar} with a random length.
     * @return An array of scalars.
     */
    private static Scalar<Object>[] scalars() {
        return SumTest.scalars(new Random().nextInt(SumTest.COORDS_LENGTH) + 1);
    }

    /**
     * Mocks an array of {@link Scalar} with the given length.
     * @param length Array length to generate
     * @return An array of scalars.
     */
    @SuppressWarnings("unchecked")
    private static Scalar<Object>[] scalars(final int length) {
        final Scalar<Object>[] result = new Scalar[length];
        for (int idx = 0; idx < result.length; ++idx) {
            result[idx] = new Scalar.Default<>(new Object());
        }
        return result;
    }

}
