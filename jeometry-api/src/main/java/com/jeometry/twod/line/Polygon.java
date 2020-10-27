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
import com.google.common.collect.Lists;
import java.util.List;

/**
 * Closed polyline implementation.
 * @param <T> scalar types
 * @since 0.1
 */
public final class Polygon<T> extends PtsPolyline<T> {

    /**
     * Constructor.
     * @param points Points sequence forming the polygon.
     */
    public Polygon(final Iterable<? extends Vect<T>> points) {
        super(Polygon.closed(points));
    }

    /**
     * Creates a closed sequence of points by adding the first point
     * to the end of the sequence.
     * @param points Points sequence
     * @param <T> scalar types
     * @return Closed points sequence
     */
    private static <T> List<Vect<T>> closed(final Iterable<? extends Vect<T>> points) {
        final List<Vect<T>> pts = Lists.newArrayList(points);
        pts.add(points.iterator().next());
        return pts;
    }

}
