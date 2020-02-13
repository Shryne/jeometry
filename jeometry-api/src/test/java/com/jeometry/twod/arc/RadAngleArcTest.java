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
package com.jeometry.twod.arc;

import com.aljebra.scalar.Random;
import com.aljebra.scalar.Scalar;
import com.jeometry.twod.circle.PtRadCircle;
import com.jeometry.twod.point.RandomPoint;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link RadAngleArc}.
 * @since 0.1
 */
public final class RadAngleArcTest {

    /**
     * {@link RadAngleArc} builds an arc with correct center, radius and angles.
     */
    @Test
    public void buildsArc() {
        final RandomPoint pnt = new RandomPoint();
        final Scalar rdx = new Random();
        final double start = Math.random();
        final double end = Math.random();
        final double error = 1.e-6;
        final Arc arc = new RadAngleArc(new PtRadCircle(pnt, rdx), start, end);
        MatcherAssert.assertThat(
            arc.center(), Matchers.equalTo(pnt)
        );
        MatcherAssert.assertThat(
            arc.radius(), Matchers.equalTo(rdx)
        );
        MatcherAssert.assertThat(
            arc.start().doubleValue(), Matchers.closeTo(start, error)
        );
        MatcherAssert.assertThat(
            arc.end().doubleValue(), Matchers.closeTo(end, error)
        );
    }
}
