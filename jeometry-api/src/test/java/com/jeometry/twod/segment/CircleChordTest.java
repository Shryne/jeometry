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
package com.jeometry.twod.segment;

import com.aljebra.field.Field;
import com.aljebra.field.impl.doubles.Decimal;
import com.aljebra.vector.Vect;
import com.jeometry.twod.circle.Circle;
import com.jeometry.twod.circle.PtsCircle;
import com.jeometry.twod.circle.analytics.PointInCircle;
import com.jeometry.twod.point.InCirclePoint;
import com.jeometry.twod.point.RandomPoint;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@link CircleChord}.
 * @since 0.1
 */
public final class CircleChordTest {

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * {@link CircleChord} constructs a random chord of the circle.
     */
    @Test
    public void buildsARandomCircleChord() {
        final Circle<Double> any = new PtsCircle<>(new RandomPoint<>(), new RandomPoint<>());
        final Segment<Double> chord = new CircleChord<>(any);
        final Decimal field = new Decimal();
        MatcherAssert.assertThat(
            new PointInCircle<>(chord.start(), any).resolve(field),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new PointInCircle<>(chord.end(), any).resolve(field),
            Matchers.is(true)
        );
    }

    /**
     * {@link CircleChord} constructs a circle chord
     * passing by another point of the circle.
     */
    @Test
    public void buildsAChordPassingByPoint() {
        final Circle<Double> any = new PtsCircle<>(new RandomPoint<>(), new RandomPoint<>());
        final InCirclePoint<Double> point = new InCirclePoint<>(any);
        final Segment<Double> chord = new CircleChord<>(any, point);
        final Decimal field = new Decimal();
        MatcherAssert.assertThat(
            new PointInCircle<>(chord.start(), any).resolve(field),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new PointInCircle<>(chord.end(), any).resolve(field),
            Matchers.is(true)
        );
    }

    /**
     * {@link CircleChord} coordinates evaluation throws exception
     * if the passed point is outside the circle.
     */
    @Test
    public void errorsIfOutsideCircle() {
        this.thrown.expect(IllegalArgumentException.class);
        final Circle<Double> any = new PtsCircle<>(new RandomPoint<>(), new RandomPoint<>());
        RandomPoint<Double> out = new RandomPoint<>();
        final Decimal field = new Decimal();
        while (CircleChordTest.pointInCircle(any, out, field)) {
            out = CircleChordTest.point();
        }
        final Segment<Double> chord = new CircleChord<>(any, out);
        chord.start().coords()[0].value(field);
        chord.end().coords()[0].value(field);
    }

    /**
     * Generates a point.
     * @return A generated point
     */
    private static RandomPoint<Double> point() {
        return new RandomPoint<Double>();
    }

    /**
     * Checks if the point belongs to the circle.
     * @param circle Circle
     * @param point Point
     * @param field Field to resolve to
     * @return True if the point belongs to the circle
     */
    private static boolean pointInCircle(final Circle<Double> circle, final Vect<Double> point,
        final Field<Double> field) {
        return new PointInCircle<>(point, circle).resolve(field);
    }

}
