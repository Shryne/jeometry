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

import com.aljebra.scalar.Throwing;
import com.aljebra.vector.FixedVector;
import com.aljebra.vector.Vect;
import com.jeometry.twod.circle.Circle;
import com.jeometry.twod.circle.analytics.PointInCircle;
import com.jeometry.twod.point.InCirclePoint;
import lombok.ToString;

/**
 * A segment defined by being a circle chord.
 * @param <T> scalar types
 * @since 0.1
 */
@ToString(includeFieldNames = false)
public class CircleChord<T> extends PtsSegment<T> {

    /**
     * Constructor.
     * @param circle Circle
     */
    public CircleChord(final Circle<T> circle) {
        this(circle, new InCirclePoint<>(circle));
    }

    /**
     * Constructor. Builds a circle chord passing by a point. The given point
     * should belong to the circle or a runtime exception will be thrown
     * on evaluation.
     * @param circle Circle
     * @param point Point belonging to the circle
     */
    public CircleChord(final Circle<T> circle, final Vect<T> point) {
        super(CircleChord.extremity(point, circle), new InCirclePoint<>(circle));
    }

    /**
     * Checks and returns the passed point if it belongs to the circle,
     * otherwise it returns a {@link Throwing} coordinates vector.
     * @param point Point to be checked
     * @param circle Circle
     * @param <T> scalar types
     * @return The passed point if it belongs to the circle
     */
    @SuppressWarnings("unchecked")
    private static <T> Vect<T> extremity(final Vect<T> point, final Circle<T> circle) {
        final PointInCircle predicate = new PointInCircle(point, circle);
        final IllegalArgumentException err = new IllegalArgumentException(
            String.format(
                "Unable to build a chord of %s passing by the point %s.",
                circle, point
            )
        );
        return new FixedVector<>(
            predicate.ifElse(point.coords()[0], err),
            predicate.ifElse(point.coords()[1], err)
        );
    }

}
