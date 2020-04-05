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

import com.aljebra.field.impl.doubles.Decimal;
import com.aljebra.scalar.Scalar;
import java.util.Arrays;
import java.util.Random;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link Opposite}.
 * @since 0.1
 */
public final class OppositeTest {

    /**
     * Max scalar array length to generate(mock).
     */
    private static final int COORDS_LENGTH = 10;

    /**
     * {@link Opposite} coordinates summed with original coordinates gives a
     * zeroed vector.
     */
    @Test
    public void coordsEqualCoordsMult() {
        final Vect<Double> vecta = new FixedVector<>(OppositeTest.scalars());
        final Scalar<Double>[] sum = new Sum<>(
            Arrays.asList(new Opposite<>(vecta), vecta)
        ).coords();
        final Decimal field = new Decimal();
        for (final Scalar<Double> scalar : sum) {
            MatcherAssert.assertThat(field.actual(scalar), Matchers.is(0.));
        }
    }

    /**
     * Builds an array of {@link Scalar} with a random length.
     * @return An array of scalars.
     */
    private static Scalar<Double>[] scalars() {
        @SuppressWarnings("unchecked")
        final Scalar<Double>[] result = new Scalar[new Random()
            .nextInt(OppositeTest.COORDS_LENGTH)];
        for (int idx = 0; idx < result.length; ++idx) {
            result[idx] = OppositeTest.scalar();
        }
        return result;
    }

    /**
     * Builds a random scalar.
     * @return A random scalar
     */
    private static Scalar<Double> scalar() {
        return new com.aljebra.scalar.Random<>();
    }

}
