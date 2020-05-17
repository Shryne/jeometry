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
import com.aljebra.scalar.Diff;
import com.aljebra.scalar.Multiplication;
import com.aljebra.scalar.Scalar;
import com.jeometry.twod.line.Line;
import com.jeometry.twod.line.RayLine;
import com.jeometry.twod.line.SgtLine;
import com.jeometry.twod.ray.Ray;
import com.jeometry.twod.segment.Segment;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A scalar representing a line y-intercept.
 * @param <T> scalar types
 * @since 0.1
 */
@EqualsAndHashCode
@ToString(includeFieldNames = false)
public final class Intercept<T> implements Scalar<T> {

    /**
     * Line for which to calculate y-intercept.
     */
    private final Line<T> line;

    /**
     * Constructor.
     * @param line Line for which to calculate intercept
     */
    public Intercept(final Line<T> line) {
        this.line = line;
    }

    /**
     * Constructor.
     * @param ray Ray for which to calculate intercept
     */
    public Intercept(final Ray<T> ray) {
        this(new RayLine<>(ray));
    }

    /**
     * Constructor.
     * @param seg Segment for which to calculate intercept
     */
    public Intercept(final Segment<T> seg) {
        this(new SgtLine<>(seg));
    }

    @Override
    public T value(final Field<T> field) {
        if (!new Vertical<>(this.line).resolve(field)) {
            final Scalar<T>[] coords = this.line.point().coords();
            final Scalar<T> slope = new Slope<>(this.line);
            return field.actual(
                new Diff<T>(coords[1], new Multiplication<>(slope, coords[0]))
            );
        }
        throw new IllegalStateException(
            "Line has infinite slope. Could not calculate y-intercept."
        );
    }

}
