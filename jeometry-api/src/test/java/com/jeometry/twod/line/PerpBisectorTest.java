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
package com.jeometry.twod.line;

import com.aljebra.field.impl.doubles.Decimal;
import com.aljebra.scalar.Scalar;
import com.jeometry.twod.line.analytics.Perpendicular;
import com.jeometry.twod.point.LineIntersectPoint;
import com.jeometry.twod.point.RandomPoint;
import com.jeometry.twod.segment.PtsSegment;
import com.jeometry.twod.segment.Segment;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link PerpBisector}.
 * @since 0.1
 */
public final class PerpBisectorTest {

    /**
     * {@link PerpBisector} builds a perpendicular line.
     */
    @Test
    public void buildsPerpendicularLine() {
        final Segment seg = new PtsSegment(
            new RandomPoint(), new RandomPoint()
        );
        MatcherAssert.assertThat(
            new Perpendicular(
                new SgtLine(seg), new PerpBisector(seg)
            ).resolve(new Decimal()),
            Matchers.is(true)
        );
    }

    /**
     * {@link PerpBisector} intersects with the midpoint.
     */
    @Test
    public void intersectsInMidpoint() {
        final Decimal dec = new Decimal();
        final RandomPoint<Double> start = new RandomPoint();
        final RandomPoint<Double> end = new RandomPoint();
        final Segment seg = new PtsSegment(start, end);
        final Scalar<Double>[] intersect = new LineIntersectPoint(
            new SgtLine(seg), new PerpBisector(seg)
        ).coords();
        final double intersectx = intersect[0].value(dec);
        final double intersecty = intersect[1].value(dec);
        final double error = 1.e-6;
        MatcherAssert.assertThat(
            end.xcoor().value(dec) - intersectx,
            Matchers.closeTo(intersectx - start.xcoor().value(dec), error)
        );
        MatcherAssert.assertThat(
            end.ycoor().value(dec) - intersecty,
            Matchers.closeTo(intersecty - start.ycoor().value(dec), error)
        );
    }

}
