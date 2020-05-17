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
import com.aljebra.scalar.Different;
import com.jeometry.twod.line.Line;
import com.jeometry.twod.line.RandomLine;
import com.jeometry.twod.line.VerticalLine;
import com.jeometry.twod.point.InLinePoint;
import com.jeometry.twod.point.XyPoint;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link PointInLine}.
 * @since 0.1
 */
public final class PointInLineTest {

    /**
     * {@link PointInLine} resolves to true when point is in line.
     */
    @Test
    public void resolvesTrueWhenInLine() {
        final Line<Double> line = new RandomLine<>();
        MatcherAssert.assertThat(
            new PointInLine<>(
                new InLinePoint<>(line), line
            ).resolve(new Decimal()),
            Matchers.is(true)
        );
    }

    /**
     * {@link PointInLine} resolves to false when point is outside line.
     */
    @Test
    public void resolvesFalseWhenOutLine() {
        final Line<Double> line = new RandomLine<>();
        final XyPoint<Double> point = new InLinePoint<>(line);
        MatcherAssert.assertThat(
            new PointInLine<>(
                new XyPoint<>(point.xcoor(), new Different<>(point.ycoor())),
                line
            ).resolve(new Decimal()),
            Matchers.is(false)
        );
    }

    /**
     * {@link PointInLine} resolves to true when line is vertical and point
     * is inside line.
     */
    @Test
    public void resolvesTrueWhenInVerticalLine() {
        final Line<Double> line = new VerticalLine<>();
        MatcherAssert.assertThat(
            new PointInLine<>(
                new InLinePoint<>(line), line
            ).resolve(new Decimal()),
            Matchers.is(true)
        );
    }

    /**
     * {@link PointInLine} resolves to false when line is vertical and point
     * is not inside line.
     */
    @Test
    public void resolvesFalseWhenOutVerticalLine() {
        final Line<Double> line = new VerticalLine<>();
        final XyPoint<Double> point = new InLinePoint<>(line);
        MatcherAssert.assertThat(
            new PointInLine<>(
                new XyPoint<>(new Different<>(point.xcoor()), point.ycoor()),
                line
            ).resolve(new Decimal()),
            Matchers.is(false)
        );
    }

}
