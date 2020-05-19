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
import com.aljebra.scalar.mock.Scalars;
import java.util.Iterator;
import java.util.Random;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

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
     * {@link Minus} coordinates equals to the field substraction
     * of vectors coordinates.
     */
    @Test
    public void coordsEqualCoordsDiff() {
        final int dim = 1 + new Random().nextInt(MinusTest.COORDS_LENGTH);
        final Iterable<Scalar<Object>> acoords = new Scalars<>(dim);
        final Iterable<Scalar<Object>> bcoords = new Scalars<>(dim);
        final Vect<Object> vecta = new FixedVector<>(acoords);
        final Vect<Object> vectb = new FixedVector<>(bcoords);
        final Iterator<Scalar<Object>> aiterator = acoords.iterator();
        final Iterator<Scalar<Object>> biterator = bcoords.iterator();
        final Scalar<Object>[] actual = new Minus<>(vecta, vectb).coords();
        for (int idx = 0; idx < dim; ++idx) {
            MatcherAssert.assertThat(
                actual[idx], Matchers.equalTo(MinusTest.minus(aiterator.next(), biterator.next()))
            );
        }
    }

    /**
     * {@link Minus} toString prints underlying vectors.
     */
    @Test
    public void printsAttributes() {
        final int dim = 1 + new Random().nextInt(MinusTest.COORDS_LENGTH);
        final Vect<Object> vecta = new FixedVector<>(new Scalars<>(dim));
        final Vect<Object> vectb = new FixedVector<>(new Scalars<>(dim));
        MatcherAssert.assertThat(
            new Minus<>(vecta, vectb).toString(),
            Matchers.allOf(
                Matchers.containsString(vecta.toString()),
                Matchers.containsString(vectb.toString())
            )
        );
    }

    /**
     * {@link Minus} equality is based on operand vectors.
     */
    @Test
    public void respectsEqualAndHashcode() {
        final int dim = 1 + new Random().nextInt(MinusTest.COORDS_LENGTH);
        final Vect<Object> vecta = new FixedVector<>(new Scalars<>(dim));
        final Vect<Object> vectb = new FixedVector<>(new Scalars<>(dim));
        MatcherAssert.assertThat(
            new Minus<>(vecta, vectb),
            Matchers.equalTo(new Minus<>(vecta, vectb))
        );
        MatcherAssert.assertThat(
            new Minus<>(vecta, vectb).hashCode(),
            Matchers.equalTo(new Minus<>(vecta, vectb).hashCode())
        );
    }

    /**
     * Calculates the difference between two scalars.
     * @param scalar First scalar
     * @param another Second scalar
     * @return A scalar representing the difference between two scalars
     */
    private static Scalar<Object> minus(final Scalar<Object> scalar, final Scalar<Object> another) {
        return new Diff<>(scalar, another);
    }

}
