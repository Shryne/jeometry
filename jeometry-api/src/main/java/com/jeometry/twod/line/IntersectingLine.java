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

import com.aljebra.vector.Vect;
import com.jeometry.twod.point.DifferentPoint;
import com.jeometry.twod.point.OutsideLinePoint;
import lombok.ToString;

/**
 * A line intersecting another line. The line is defined by having a different
 * direction than the passed line.
 * @param <T> scalar types
 * @since 0.1
 */
@ToString(includeFieldNames = false)
public final class IntersectingLine<T> implements Line<T> {

    /**
     * A point by which this line passes.
     */
    private final Vect<T> pnt;

    /**
     * Line direction.
     */
    private Vect<T> dir;

    /**
     * Constructor. Builds a random intersecting line.
     * @param intersect The line to intersect
     */
    public IntersectingLine(final Line<T> intersect) {
        this(intersect, new OutsideLinePoint<T>(intersect));
    }

    /**
     * Constructor. Builds an intersecting line passing by the given point.
     * @param intersect The line to be intersect to
     * @param point The point to pass by
     */
    public IntersectingLine(final Line<T> intersect, final Vect<T> point) {
        this.pnt = point;
        this.dir = new DifferentPoint<T>(intersect.direction());
    }

    @Override
    public Vect<T> direction() {
        return this.dir;
    }

    @Override
    public Vect<T> point() {
        return this.pnt;
    }

}
