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
package com.jeometry.twod.line.analytics;

import com.aljebra.field.Field;
import com.aljebra.scalar.Scalar;
import com.aljebra.scalar.condition.Equals;
import com.aljebra.scalar.condition.Predicate;
import com.aljebra.vector.Vect;
import com.jeometry.twod.line.Line;

/**
 * A predicate to determine if a point belongs to a line.
 * @param <T> scalar types
 * @since 0.1
 */
public final class PointInLine<T> implements Predicate<Field<T>> {

    /**
     * Point.
     */
    private final Vect<T> point;

    /**
     * Line.
     */
    private final Line<T> line;

    /**
     * Constructor.
     * @param point Point
     * @param line Line
     */
    public PointInLine(final Vect<T> point, final Line<T> line) {
        this.point = point;
        this.line = line;
    }

    @Override
    public boolean resolve(final Field<T> field) {
        final Scalar<T> xcoor = this.point.coords()[0];
        final Predicate<Field<T>> result;
        if (new Vertical<>(this.line).resolve(field)) {
            result = new Equals<>(xcoor, this.line.point().coords()[0]);
        } else {
            result = new Equals<>(
                this.point.coords()[1],
                new LinePointOrdinate<T>(this.line, xcoor)
            );
        }
        return result.resolve(field);
    }

}
