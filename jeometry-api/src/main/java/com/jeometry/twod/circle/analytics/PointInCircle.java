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
package com.jeometry.twod.circle.analytics;

import com.aljebra.field.Field;
import com.aljebra.metric.scalar.Norm;
import com.aljebra.scalar.condition.Predicate;
import com.aljebra.vector.Minus;
import com.aljebra.vector.Vect;
import com.jeometry.twod.circle.Circle;

/**
 * A predicate to determine if a point belongs to a circle.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class PointInCircle<T> implements Predicate<T> {

    /**
     * Point.
     */
    private final Vect point;

    /**
     * Circle.
     */
    private final Circle circle;

    /**
     * Constructor.
     * @param point Point
     * @param circle Circle
     */
    public PointInCircle(final Vect point, final Circle circle) {
        this.point = point;
        this.circle = circle;
    }

    @Override
    public boolean resolve(final Field<T> field) {
        return field.equals(
            this.circle.radius(),
            new Norm(new Minus(this.point, this.circle.center()))
        );
    }

}
