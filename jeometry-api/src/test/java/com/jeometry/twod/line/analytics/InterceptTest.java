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
import com.jeometry.twod.line.Line;
import com.jeometry.twod.line.PtDirLine;
import com.jeometry.twod.point.RandomPoint;
import com.jeometry.twod.point.VertPoint;
import com.jeometry.twod.ray.PtDirRay;
import com.jeometry.twod.ray.Ray;
import com.jeometry.twod.segment.PtVectSegment;
import com.jeometry.twod.segment.Segment;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@link Intercept}.
 * @since 0.1
 */
public final class InterceptTest {

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * {@link Intercept} calculates line y-intercept.
     */
    @Test
    public void calculatesIntercept() {
        final Line<Double> line = new PtDirLine<>(new RandomPoint<>(), new RandomPoint<>());
        final double error = 1.e-6;
        final Decimal dec = new Decimal();
        final Double startx = line.point().coords()[0].value(dec);
        final Double starty = line.point().coords()[1].value(dec);
        final Double dirx = line.direction().coords()[0].value(dec);
        final Double diry = line.direction().coords()[1].value(dec);
        MatcherAssert.assertThat(
            new Intercept<Double>(line).value(dec),
            Matchers.closeTo(starty - startx * diry / dirx, error)
        );
    }

    /**
     * {@link Intercept} calculates ray y-intercept.
     */
    @Test
    public void calculatesRayIntercept() {
        final Ray<Double> ray = new PtDirRay<>(new RandomPoint<>(), new RandomPoint<>());
        final double error = 1.e-6;
        final Decimal dec = new Decimal();
        final Double startx = ray.origin().coords()[0].value(dec);
        final Double starty = ray.origin().coords()[1].value(dec);
        final Double dirx = ray.direction().coords()[0].value(dec);
        final Double diry = ray.direction().coords()[1].value(dec);
        MatcherAssert.assertThat(
            new Intercept<Double>(ray).value(dec),
            Matchers.closeTo(starty - startx * diry / dirx, error)
        );
    }

    /**
     * {@link Intercept} calculates segment y-intercept.
     */
    @Test
    public void calculatesSegmentIntercept() {
        final Segment<Double> seg = new PtVectSegment<>(new RandomPoint<>(), new RandomPoint<>());
        final double error = 1.e-6;
        final Decimal dec = new Decimal();
        final Double startx = seg.start().coords()[0].value(dec);
        final Double starty = seg.start().coords()[1].value(dec);
        final Double endx = seg.end().coords()[0].value(dec);
        final Double endy = seg.end().coords()[1].value(dec);
        MatcherAssert.assertThat(
            new Intercept<Double>(seg).value(dec),
            Matchers.closeTo(
                starty - startx * (endy - starty) / (endx - startx), error
            )
        );
    }

    /**
     * {@link Intercept} evaluation throws exception if the line is vertical.
     */
    @Test
    public void errorsWhenVerticalLine() {
        this.thrown.expect(IllegalStateException.class);
        final Line<Double> line = new PtDirLine<>(new VertPoint<>(), new VertPoint<>());
        new Intercept<>(line).value(new Decimal());
    }
}
