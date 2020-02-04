/**
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
import com.jeometry.twod.segment.PtsSegment;
import com.jeometry.twod.segment.Segment;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link MidSegPoint}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class MidSegPointTest {

    /**
     * {@link MidSegPoint} constructs segment midpoint.
     */
    @Test
    public void buildsMidPointSeg() {
        final Segment any = new PtsSegment(
            new RandomPoint(), new RandomPoint()
        );
        final Field<Double> dec = new Decimal();
        final double error = 1.e-6;
        final Scalar[] coords = new MidSegPoint(any).coords();
        MatcherAssert.assertThat(
            coords[0].value(dec),
            Matchers.closeTo(MidSegPointTest.mid(any)[0], error)
        );
        MatcherAssert.assertThat(
            coords[1].value(dec),
            Matchers.closeTo(MidSegPointTest.mid(any)[1], error)
        );
    }

    /**
     * Checks if the given point belongs to the segment.
     * @param any Segment
     * @return True if the point belongs to the segment
     */
    private static Double[] mid(final Segment any) {
        final Field<Double> dec = new Decimal();
        final Double startx = any.start().coords()[0].value(dec);
        final Double starty = any.start().coords()[1].value(dec);
        final Double endx = any.end().coords()[0].value(dec);
        final Double endy = any.end().coords()[1].value(dec);
        return new Double[]{(endx + startx) / 2., (endy + starty) / 2.};
    }
}
