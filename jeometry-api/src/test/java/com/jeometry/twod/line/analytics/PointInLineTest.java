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
import com.aljebra.vector.Vect;
import com.jeometry.twod.line.Line;
import com.jeometry.twod.line.PtDirLine;
import com.jeometry.twod.point.DifferentPoint;
import com.jeometry.twod.point.RandomPoint;
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
        final Line<Double> line = new PtDirLine(
            new RandomPoint(), new DifferentPoint(PointInLineTest.point(0., 1.))
        );
        final Decimal dec = new Decimal();
        final Double startx = line.point().coords()[0].value(dec);
        final Double starty = line.point().coords()[1].value(dec);
        final Double dirx = line.direction().coords()[0].value(dec);
        final Double diry = line.direction().coords()[1].value(dec);
        final Double abscissa = new RandomPoint<Double>().xcoor().value(dec);
        final Double ordinate = abscissa * diry / dirx
            + starty - startx * diry / dirx;
        MatcherAssert.assertThat(
            new PointInLine(
                PointInLineTest.point(abscissa, ordinate), line
            ).resolve(dec),
            Matchers.is(true)
        );
    }

    /**
     * {@link PointInLine} resolves to false when point is outside line.
     */
    @Test
    public void resolvesFalseWhenOutLine() {
        final Line<Double> line = new PtDirLine(
            new RandomPoint(), new DifferentPoint(PointInLineTest.point(0., 1.))
        );
        final Decimal dec = new Decimal();
        final Double startx = line.point().coords()[0].value(dec);
        final Double starty = line.point().coords()[1].value(dec);
        final Double dirx = line.direction().coords()[0].value(dec);
        final Double diry = line.direction().coords()[1].value(dec);
        final Double abscissa = new RandomPoint<Double>().xcoor().value(dec);
        final Double ordinate = abscissa * diry / dirx
            + starty - startx * diry / dirx;
        MatcherAssert.assertThat(
            new PointInLine(
                new DifferentPoint(PointInLineTest.point(abscissa, ordinate)),
                line
            ).resolve(dec),
            Matchers.is(false)
        );
    }

    /**
     * {@link PointInLine} resolves to true when line is vertical and point
     * is inside line.
     */
    @Test
    public void resolvesTrueWhenInVerticalLine() {
        final RandomPoint point = new RandomPoint();
        MatcherAssert.assertThat(
            new PointInLine(
                new XyPoint(point.xcoor(), point.xcoor()),
                new PtDirLine(point, PointInLineTest.point(0., 1.))
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
        final RandomPoint point = new RandomPoint();
        MatcherAssert.assertThat(
            new PointInLine(
                new DifferentPoint(point),
                new PtDirLine(point, PointInLineTest.point(0., 1.))
            ).resolve(new Decimal()),
            Matchers.is(false)
        );
    }

    /**
     * Builds a point from double coordinates.
     * @param abscissa Abscissa
     * @param ordinate Ordinate
     * @return A point with double coordinates
     */
    private static Vect point(final Double abscissa, final Double ordinate) {
        return new XyPoint(
            new Scalar.Default<Double>(abscissa),
            new Scalar.Default<Double>(ordinate)
        );
    }
}
