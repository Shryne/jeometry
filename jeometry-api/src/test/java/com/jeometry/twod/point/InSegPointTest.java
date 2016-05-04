/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2016, Hamdi Douss
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
import com.aljebra.vector.Vect;
import com.jeometry.twod.line.analytics.Intercept;
import com.jeometry.twod.line.analytics.Slope;
import com.jeometry.twod.line.analytics.Vertical;
import com.jeometry.twod.segment.PtsSegment;
import com.jeometry.twod.segment.Segment;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link InSegPoint}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class InSegPointTest {

    /**
     * {@link InSegPoint} constructs a point belonging to the segment.
     */
    @Test
    public void buildsAPointInSeg() {
        final Segment any = new PtsSegment(
            new RandomPoint(), new RandomPoint()
        );
        MatcherAssert.assertThat(
            InSegPointTest.belongs(new InSegPoint(any), any),
            Matchers.is(true)
        );
    }

    /**
     * Checks if the given point belongs to the segment.
     * @param pnt Point
     * @param any Segment
     * @return True if the point belongs to the segment
     */
    private static boolean belongs(final Vect pnt, final Segment any) {
        final Field<Double> dec = new Decimal();
        final double xcoor = pnt.coords()[0].value(dec);
        final Double ycoor = pnt.coords()[1].value(dec);
        final boolean result;
        final Double startx = any.start().coords()[0].value(dec);
        final Double starty = any.start().coords()[1].value(dec);
        final Double endx = any.end().coords()[0].value(dec);
        final Double endy = any.end().coords()[1].value(dec);
        if (new Vertical(any).resolve(dec)) {
            result = startx == xcoor;
        } else {
            final double error = 1.e-6;
            result = Math.abs(
                ycoor - (xcoor * new Slope(any).value(dec)
                + new Intercept(any).value(dec))
            ) < error;
        }
        final boolean between = xcoor >= Math.min(startx, endx)
            && xcoor <= Math.max(startx, endx)
            && ycoor >= Math.min(starty, endy)
            && ycoor <= Math.max(starty, endy);
        return result && between;
    }
}
