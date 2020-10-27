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
package com.jeometry.twod.line.analytics;

import com.aljebra.field.impl.doubles.Decimal;
import com.aljebra.scalar.Scalar;
import com.jeometry.twod.line.RandomLine;
import com.jeometry.twod.line.VerticalLine;
import com.jeometry.twod.point.XPoint;
import com.jeometry.twod.ray.PtsRay;
import com.jeometry.twod.ray.Ray;
import com.jeometry.twod.segment.RandomSegment;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link Vertical}.
 * @since 0.1
 */
public final class VerticalTest {

    /**
     * {@link Vertical} resolves true when the line is vertical.
     */
    @Test
    public void resolvesTrueWhenVertical() {
        MatcherAssert.assertThat(
            new Vertical<Double>(
                new VerticalLine<>()
            ).resolve(new Decimal()), Matchers.is(true)
        );
    }

    /**
     * {@link Vertical} resolves false when the line is not vertical.
     */
    @Test
    public void resolvesFalseWhenNotVertical() {
        MatcherAssert.assertThat(
            new Vertical<Double>(
                new RandomLine<>()
            ).resolve(new Decimal()), Matchers.is(false)
        );
    }

    /**
     * {@link Vertical} resolves false when the segment is not vertical.
     */
    @Test
    public void resolvesFalseWhenNotVerticalSegment() {
        MatcherAssert.assertThat(
            new Vertical<Double>(
                new RandomSegment<>()
            ).resolve(new Decimal()), Matchers.is(false)
        );
    }

    /**
     * {@link Vertical} resolves true when the ray is vertical.
     */
    @Test
    public void resolvesTrueWhenVerticalRay() {
        final Scalar<Double> abscissa = new RandomLine<Double>().direction().coords()[0];
        final Ray<Double> vertical = new PtsRay<>(new XPoint<>(abscissa), new XPoint<>(abscissa));
        MatcherAssert.assertThat(
            new Vertical<Double>(
                vertical
            ).resolve(new Decimal()), Matchers.is(true)
        );
    }
}
