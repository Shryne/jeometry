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
import com.jeometry.twod.line.RandomLine;
import com.jeometry.twod.line.VerticalLine;
import com.jeometry.twod.ray.PtDirRay;
import com.jeometry.twod.ray.Ray;
import com.jeometry.twod.segment.RandomSegment;
import com.jeometry.twod.segment.Segment;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@link Slope}.
 * @since 0.1
 */
public final class SlopeTest {

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * {@link Slope} calculates line slope.
     */
    @Test
    public void calculatesSlope() {
        final Line<Double> line = new RandomLine<>();
        final double error = 1.e-6;
        final Decimal dec = new Decimal();
        final Double dirx = line.direction().coords()[0].value(dec);
        final Double diry = line.direction().coords()[1].value(dec);
        MatcherAssert.assertThat(
            new Slope<Double>(line).value(dec), Matchers.closeTo(diry / dirx, error)
        );
    }

    /**
     * {@link Slope} calculates ray slope.
     */
    @Test
    public void calculatesRaySlope() {
        final Line<Double> novertical = new RandomLine<>();
        final Ray<Double> ray = new PtDirRay<>(
            novertical.point(), novertical.direction()
        );
        final double error = 1.e-6;
        final Decimal dec = new Decimal();
        final Double dirx = ray.direction().coords()[0].value(dec);
        final Double diry = ray.direction().coords()[1].value(dec);
        MatcherAssert.assertThat(
            new Slope<Double>(ray).value(dec), Matchers.closeTo(diry / dirx, error)
        );
    }

    /**
     * {@link Slope} calculates segment slope.
     */
    @Test
    public void calculatesSegmentSlope() {
        final Segment<Double> seg = new RandomSegment<>();
        final double error = 1.e-6;
        final Decimal dec = new Decimal();
        final Double startx = seg.start().coords()[0].value(dec);
        final Double starty = seg.start().coords()[1].value(dec);
        final Double endx = seg.end().coords()[0].value(dec);
        final Double endy = seg.end().coords()[1].value(dec);
        MatcherAssert.assertThat(
            new Slope<Double>(seg).value(dec),
            Matchers.closeTo((endy - starty) / (endx - startx), error)
        );
    }

    /**
     * {@link Slope} evaluation throws exception if the line is vertical.
     */
    @Test
    public void errorsWhenVerticalLine() {
        this.thrown.expect(IllegalStateException.class);
        final Line<Double> line = new VerticalLine<>();
        new Slope<>(line).value(new Decimal());
    }

    /**
     * {@link Slope} toString prints line.
     */
    @Test
    public void printsAttributes() {
        final Line<Double> line = new RandomLine<>();
        final String print = new Slope<>(line).toString();
        MatcherAssert.assertThat(
            print, Matchers.containsString(line.toString())
        );
    }
}
