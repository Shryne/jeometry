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
import com.aljebra.scalar.Random;
import com.aljebra.scalar.Scalar;
import com.jeometry.twod.line.Line;
import com.jeometry.twod.line.RandomLine;
import com.jeometry.twod.line.analytics.PointInLine;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link InLinePoint}.
 * @since 0.1
 */
public final class InLinePointTest {

    /**
     * {@link InLinePoint} constructs a point belonging to the line.
     */
    @Test
    public void buildsAPointInLine() {
        final Line<Double> any = new RandomLine<>();
        MatcherAssert.assertThat(
            new PointInLine<>(new InLinePoint<>(any), any).resolve(new Decimal()),
            Matchers.is(true)
        );
    }

    /**
     * {@link InLinePoint} constructs a point belonging to the line with a given abscissa.
     */
    @Test
    public void buildsAPointInLineWithAbscissa() {
        final Line<Double> any = new RandomLine<>();
        final Scalar<Double> scalar = new Random<>();
        final InLinePoint<Double> point = new InLinePoint<>(any, scalar);
        MatcherAssert.assertThat(
            new PointInLine<>(point, any).resolve(new Decimal()),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new PointInLine<>(new XyPoint<>(scalar, point.ycoor()), any).resolve(new Decimal()),
            Matchers.is(true)
        );
    }
}
