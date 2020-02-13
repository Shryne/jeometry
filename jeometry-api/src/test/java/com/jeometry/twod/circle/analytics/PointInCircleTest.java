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
package com.jeometry.twod.circle.analytics;

import com.aljebra.field.Field;
import com.aljebra.field.impl.doubles.Decimal;
import com.aljebra.metric.scalar.Norm;
import com.aljebra.vector.Minus;
import com.aljebra.vector.Vect;
import com.jeometry.twod.circle.Circle;
import com.jeometry.twod.circle.PtsCircle;
import com.jeometry.twod.point.InCirclePoint;
import com.jeometry.twod.point.RandomPoint;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link PointInCircle}.
 * @since 0.1
 */
public final class PointInCircleTest {

    /**
     * {@link PointInCircle} resolves to true when point is in circle.
     */
    @Test
    public void resolvesTrueWhenInCircle() {
        final RandomPoint point = new RandomPoint();
        final Circle any = new PtsCircle(new RandomPoint(), point);
        final Decimal field = new Decimal();
        MatcherAssert.assertThat(
            new PointInCircle(point, any).resolve(field),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new PointInCircle(
                new InCirclePoint(any), any
            ).resolve(field),
            Matchers.is(true)
        );
    }

    /**
     * {@link PointInCircle} resolves to false when point is not in circle.
     */
    @Test
    public void resolvesFalseWhenOutsideCircle() {
        final Circle any = new PtsCircle(new RandomPoint(), new RandomPoint());
        final Decimal field = new Decimal();
        RandomPoint point = new RandomPoint();
        while (PointInCircleTest.pointInCircle(any, point, field)) {
            point = PointInCircleTest.point();
        }
        MatcherAssert.assertThat(
            new PointInCircle(point, any).resolve(field),
            Matchers.is(false)
        );
    }

    /**
     * Generates a point.
     * @return A generated point
     */
    private static RandomPoint point() {
        return new RandomPoint();
    }

    /**
     * Checks if the point belongs to the circle.
     * @param circle Circle
     * @param point Point
     * @param field Field to resolve to
     * @return True if the point belongs to the circle
     */
    private static boolean pointInCircle(final Circle circle, final Vect point,
        final Field<?> field) {
        return field.equals(
            new Norm(new Minus(circle.center(), point)), circle.radius()
        );
    }
}
