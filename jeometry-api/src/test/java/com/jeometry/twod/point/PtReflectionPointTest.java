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
package com.jeometry.twod.point;

import com.aljebra.field.Field;
import com.aljebra.field.impl.doubles.Decimal;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.Minus;
import com.aljebra.vector.Sum;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link PtReflectionPoint}.
 * @since 0.1
 */
public final class PtReflectionPointTest {

    /**
     * {@link PtReflectionPoint} builds the reflection point given the center.
     */
    @Test
    public void buildsReflection() {
        final XyPoint center = new RandomPoint();
        final XyPoint input = new RandomPoint();
        final XyPoint result = new PtReflectionPoint(center, input);
        final XyPoint expected = PtReflectionPointTest.expected(center, input);
        final Field<Double> dec = new Decimal();
        final double error = 1.e-6;
        MatcherAssert.assertThat(
            result.xcoor().value(dec),
            Matchers.closeTo(expected.xcoor().value(dec), error)
        );
        MatcherAssert.assertThat(
            result.ycoor().value(dec),
            Matchers.closeTo(expected.ycoor().value(dec), error)
        );
    }

    /**
     * {@link PtReflectionPoint} builds the reflection point across the origin
     * if not given the center.
     */
    @Test
    public void buildsReflectionAcrossOrigin() {
        final XyPoint input = new RandomPoint();
        final XyPoint result = new PtReflectionPoint(input);
        final XyPoint expected = PtReflectionPointTest.expected(
            new XyPoint(
                new Scalar.Default<Double>(0.), new Scalar.Default<Double>(0.)
            ), input
        );
        final Field<Double> dec = new Decimal();
        final double error = 1.e-6;
        MatcherAssert.assertThat(
            result.xcoor().value(dec),
            Matchers.closeTo(expected.xcoor().value(dec), error)
        );
        MatcherAssert.assertThat(
            result.ycoor().value(dec),
            Matchers.closeTo(expected.ycoor().value(dec), error)
        );
    }

    /**
     * Calculates expected reflection result using the formula (2c - p).
     * @param ctr Reflection center
     * @param input Reflection input
     * @return Reflection result
     */
    private static XyPoint expected(final XyPoint ctr, final XyPoint input) {
        return new XyPoint(new Minus(new Sum(ctr, ctr), input));
    }
}
