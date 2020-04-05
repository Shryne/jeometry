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
import com.aljebra.metric.scalar.Norm;
import com.aljebra.vector.Minus;
import com.aljebra.vector.Vect;
import com.jeometry.twod.circle.Circle;
import com.jeometry.twod.circle.PtsCircle;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link InCirclePoint}.
 * @since 0.1
 */
public final class InCirclePointTest {

    /**
     * {@link InCirclePoint} constructs a point belonging to the circle.
     */
    @Test
    public void buildsAPointInCircle() {
        final Circle<Double> any = new PtsCircle<>(
            new RandomPoint<>(), new RandomPoint<>()
        );
        MatcherAssert.assertThat(
            InCirclePointTest.belongs(new InCirclePoint<>(any), any),
            Matchers.is(true)
        );
    }

    /**
     * Checks if the given point belongs to the circle.
     * @param pnt Point
     * @param any Circle
     * @return True if the point belongs to the circle
     */
    private static boolean belongs(final Vect<Double> pnt, final Circle<Double> any) {
        return new Decimal().equals(
            new Norm<>(new Minus<>(any.center(), pnt)), any.radius()
        );
    }
}
