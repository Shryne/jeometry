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
package com.jeometry.twod.line;

import com.aljebra.metric.vect.RotateVect;
import com.aljebra.vector.Vect;
import com.jeometry.twod.point.RandomPoint;
import lombok.ToString;

/**
 * A line defined by being perpendicular to another line.
 * @param <T> scalar types
 * @since 0.1
 */
@ToString(includeFieldNames = false)
public final class PerpLine<T> implements Line<T> {

    /**
     * The line to be perpendicular to.
     */
    private final Line<T> perp;

    /**
     * A point by which this line passes.
     */
    private final Vect<T> pnt;

    /**
     * Constructor. Builds a random perpendicular line.
     * @param perp The line to be perpendicular to
     */
    public PerpLine(final Line<T> perp) {
        this(perp, new RandomPoint<T>());
    }

    /**
     * Constructor. Builds a perpendicular line passing by the given point.
     * @param perp The line to be parallel to
     * @param point Point to pass by
     */
    public PerpLine(final Line<T> perp, final Vect<T> point) {
        this.perp = perp;
        this.pnt = point;
    }

    @Override
    public Vect<T> direction() {
        return new RotateVect<T>(this.perp.direction(), Math.PI / 2);
    }

    @Override
    public Vect<T> point() {
        return this.pnt;
    }

}
