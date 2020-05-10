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

import com.aljebra.scalar.AddIdentity;
import com.aljebra.vector.Vect;
import com.jeometry.twod.point.PtReflectionPoint;
import com.jeometry.twod.point.XyPoint;
import java.util.ArrayList;
import java.util.List;
import lombok.ToString;

/**
 * A polyline defined as the reflection of a polyline, across a given point.
 * @param <T> scalar types
 * @since 0.1
 */
@ToString(includeFieldNames = false)
public final class PtReflectionPolyLine<T> extends PtsPolyline<T> {

    /**
     * Constructor.
     * @param center Reflection center
     * @param input The polyline to reflect
     */
    public PtReflectionPolyLine(final Vect<T> center, final Polyline<T> input) {
        super(PtReflectionPolyLine.reflected(center, input.points()));
    }

    /**
     * Constructor. Builds the reflection polyline across the origin.
     * @param input The line to reflect
     */
    public PtReflectionPolyLine(final Polyline<T> input) {
        this(new XyPoint<>(new AddIdentity<>(), new AddIdentity<>()), input);
    }

    /**
     * Builds a list of point-reflected points across a center.
     * @param center Reflection center
     * @param pts The points to reflect
     * @param <T> scalar types
     * @return An array of reflected points
     */
    private static <T> List<Vect<T>> reflected(final Vect<T> center, final List<Vect<T>> pts) {
        final List<Vect<T>> result = new ArrayList<>(pts.size());
        for (final Vect<T> point : pts) {
            result.add(PtReflectionPolyLine.reflected(center, point));
        }
        return result;
    }

    /**
     * Builds a point-reflected point across a center.
     * @param center Reflection center
     * @param point The point to reflect
     * @param <T> scalar types
     * @return The reflected point
     */
    private static <T> Vect<T> reflected(final Vect<T> center, final Vect<T> point) {
        return new PtReflectionPoint<T>(center, point);
    }
}
