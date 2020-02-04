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
package com.jeometry.twod.segment;

import com.aljebra.scalar.Throwing;
import com.aljebra.vector.FixedVector;
import com.aljebra.vector.Vect;
import com.jeometry.twod.circle.Circle;
import com.jeometry.twod.circle.analytics.PointInCircle;
import com.jeometry.twod.point.InCirclePoint;
import com.jeometry.twod.point.PtReflectionPoint;
import lombok.ToString;

/**
 * A segment defined by being a circle diameter.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ToString(includeFieldNames = false)
public class CircleDiameter extends PtsSegment {

    /**
     * Constructor.
     * @param circle Circle
     */
    public CircleDiameter(final Circle circle) {
        this(circle, new InCirclePoint(circle));
    }

    /**
     * Constructor. Builds a circle diameter passing by a point. The given point
     * should belong to the circle or a runtime exception will be thrown
     * on evaluation.
     * @param circle Circle
     * @param point Point belonging to the circle
     */
    public CircleDiameter(final Circle circle, final Vect point) {
        super(
            CircleDiameter.extremity(point, circle),
            new PtReflectionPoint(circle.center(), point)
        );
    }

    /**
     * Checks and returns the passed point if it belongs to the circle,
     * otherwise it returns a {@link Throwing} coordinates vector.
     * @param point Point to be checked
     * @param circle Circle
     * @return The passed point if it belongs to the circle
     */
    private static Vect extremity(final Vect point, final Circle circle) {
        final PointInCircle predicate = new PointInCircle(point, circle);
        final IllegalArgumentException err = new IllegalArgumentException(
            String.format(
                "Unable to build a chord of %s passing by the point %s.",
                circle, point
            )
        );
        return new FixedVector(
            predicate.ifElse(point.coords()[0], err),
            predicate.ifElse(point.coords()[1], err)
        );
    }

}
