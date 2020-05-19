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
import com.aljebra.scalar.mock.Scalars;
import java.util.Iterator;
import java.util.Random;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

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
    @Test
    public void coordsEqualCoordsSum() {
        final int dim = new Random().nextInt(SumTest.COORDS_LENGTH) + 1;
        final Iterable<Scalar<Object>> acoords = new Scalars<>(dim);
        final Iterable<Scalar<Object>> bcoords = new Scalars<>(dim);
        final Vect<Object> vecta = new FixedVector<>(acoords);
        final Vect<Object> vectb = new FixedVector<>(bcoords);
        final Iterator<Scalar<Object>> aiterator = acoords.iterator();
        final Iterator<Scalar<Object>> biterator = bcoords.iterator();
        final Scalar<Object>[] actual = new Sum<>(vecta, vectb).coords();
        for (int idx = 0; idx < dim; ++idx) {
            MatcherAssert.assertThat(
                actual[idx], Matchers.equalTo(SumTest.sum(aiterator.next(), biterator.next()))
            );
        }
    }

    /**
     * {@link Sum} toString prints operands.
     */
    @Test
    public void printsAttributes() {
        final int dim = new Random().nextInt(SumTest.COORDS_LENGTH) + 1;
        final Vect<Object> vecta = new FixedVector<>(new Scalars<>(dim));
        final Vect<Object> vectb = new FixedVector<>(new Scalars<>(dim));
        MatcherAssert.assertThat(
            new Sum<>(vecta, vectb).toString(),
            Matchers.allOf(
                Matchers.containsString(vecta.toString()),
                Matchers.containsString(vectb.toString())
            )
        );
    }

    /**
     * Calculates the sum of two scalars.
     * @param scalar First scalar
     * @param another Second scalar
     * @return A scalar representing the sum if the two passed scalars
     */
    private static Scalar<Object> sum(final Scalar<Object> scalar, final Scalar<Object> another) {
        return new Add<>(scalar, another);
    }

}
