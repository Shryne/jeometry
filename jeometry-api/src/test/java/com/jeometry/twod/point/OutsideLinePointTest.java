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

import com.aljebra.field.impl.doubles.Decimal;
import com.jeometry.twod.line.Line;
import com.jeometry.twod.line.RandomLine;
import com.jeometry.twod.line.analytics.PointInLine;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link OutsideLinePoint}.
 * @since 0.1
 */
public final class OutsideLinePointTest {

    /**
     * {@link OutsideLinePoint} constructs a point not belonging to the line.
     */
    @Test
    public void buildsAPointOutsideLine() {
        final Line<Double> any = new RandomLine<>();
        MatcherAssert.assertThat(
            new PointInLine<>(
                new OutsideLinePoint<>(any), any
            ).resolve(new Decimal()),
            Matchers.is(false)
        );
    }

    /**
     * {@link OutsideLinePoint} toString prints underlying coordinates.
     */
    @Test
    public void toStringPrintsCoordinates() {
        final OutsideLinePoint<Object> point = new OutsideLinePoint<>(new RandomLine<>());
        MatcherAssert.assertThat(
            point.toString(), Matchers.allOf(
                Matchers.containsString(point.coords()[0].toString()),
                Matchers.containsString(point.coords()[1].toString())
            )
        );
    }
}
