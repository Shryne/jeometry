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

import com.aljebra.field.impl.doubles.Decimal;
import com.jeometry.twod.line.IntersectingLine;
import com.jeometry.twod.line.Line;
import com.jeometry.twod.line.ParallelLine;
import com.jeometry.twod.line.RandomLine;
import com.jeometry.twod.line.VerticalLine;
import com.jeometry.twod.line.analytics.PointInLine;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@link LineIntersectPoint}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class LineIntersectPointTest {

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * {@link LineIntersectPoint} constructs a point belonging to two lines.
     */
    @Test
    public void buildsAPointInLines() {
        final Line any = new RandomLine();
        final Line other = new IntersectingLine(any);
        final LineIntersectPoint pnt = new LineIntersectPoint(any, other);
        final Decimal field = new Decimal();
        MatcherAssert.assertThat(
            new PointInLine(pnt, any).resolve(field),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new PointInLine(pnt, other).resolve(field),
            Matchers.is(true)
        );
    }

    /**
     * {@link LineIntersectPoint} constructs a point belonging to two lines
     * if one of the lines is vertical.
     */
    @Test
    public void buildsAPointInLinesWhenVertical() {
        final Line vertical = new VerticalLine();
        final Line any = new IntersectingLine(vertical);
        final LineIntersectPoint pnt = new LineIntersectPoint(any, vertical);
        final LineIntersectPoint other = new LineIntersectPoint(vertical, any);
        final Decimal field = new Decimal();
        MatcherAssert.assertThat(
            new PointInLine(pnt, any).resolve(field),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new PointInLine(pnt, vertical).resolve(field),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new PointInLine(other, any).resolve(field),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new PointInLine(other, vertical).resolve(field),
            Matchers.is(true)
        );
    }

    /**
     * {@link LineIntersectPoint} coordinates evaluation throws exception
     * if the two lines are parallel.
     */
    @Test
    public void errorsIfparallel() {
        this.thrown.expect(IllegalStateException.class);
        final Line any = new RandomLine();
        final LineIntersectPoint pnt = new LineIntersectPoint(
            any, new ParallelLine(any)
        );
        new PointInLine(pnt, any).resolve(new Decimal());
    }

}
