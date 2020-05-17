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
import com.jeometry.twod.line.SgtLine;
import com.jeometry.twod.line.analytics.PointInLine;
import com.jeometry.twod.segment.RandomSegment;
import com.jeometry.twod.segment.Segment;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link InSegPoint}.
 * @since 0.1
 */
public final class InSegPointTest {

    /**
     * {@link InSegPoint} constructs a point belonging to the segment.
     */
    @Test
    public void buildsAPointInSeg() {
        final Segment<Double> any = new RandomSegment<>();
        MatcherAssert.assertThat(
            InSegPointTest.belongs(new InSegPoint<>(any), any),
            Matchers.is(true)
        );
    }

    /**
     * {@link InSegPoint} toString prints underlying coordinates.
     */
    @Test
    public void toStringPrintsCoordinates() {
        final InSegPoint<Double> point = new InSegPoint<>(new RandomSegment<>());
        MatcherAssert.assertThat(
            point.toString(), Matchers.allOf(
                Matchers.containsString(point.xcoor().toString()),
                Matchers.containsString(point.ycoor().toString())
            )
        );
    }

    /**
     * Checks if the given point belongs to the segment.
     * @param pnt Point
     * @param any Segment
     * @return True if the point belongs to the segment
     */
    private static boolean belongs(final XyPoint<Double> pnt, final Segment<Double> any) {
        final Field<Double> dec = new Decimal();
        final double xcoor = pnt.xcoor().value(dec);
        final Double ycoor = pnt.ycoor().value(dec);
        final Scalar<Double>[] scoords = any.start().coords();
        final Scalar<Double>[] ecoords = any.end().coords();
        final Double startx = scoords[0].value(dec);
        final Double starty = scoords[1].value(dec);
        final Double endx = ecoords[0].value(dec);
        final Double endy = ecoords[1].value(dec);
        final boolean between = xcoor >= Math.min(startx, endx)
            && xcoor <= Math.max(startx, endx)
            && ycoor >= Math.min(starty, endy)
            && ycoor <= Math.max(starty, endy);
        return between && new PointInLine<>(pnt, new SgtLine<>(any)).resolve(dec);
    }
}
