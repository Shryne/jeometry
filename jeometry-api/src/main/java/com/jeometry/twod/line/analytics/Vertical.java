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
import com.aljebra.scalar.AddIdentity;
import com.aljebra.scalar.condition.Predicate;
import com.jeometry.twod.line.Line;
import com.jeometry.twod.line.RayLine;
import com.jeometry.twod.line.SgtLine;
import com.jeometry.twod.ray.Ray;
import com.jeometry.twod.segment.Segment;
import lombok.EqualsAndHashCode;

/**
 * A predicate to determine if a line is vertical.
 * @param <T> scalar types
 * @since 0.1
 */
@EqualsAndHashCode
public final class Vertical<T> implements Predicate<Field<T>> {

    /**
     * Line.
     */
    private final Line<T> line;

    /**
     * Constructor.
     * @param line Line to check
     */
    public Vertical(final Line<T> line) {
        this.line = line;
    }

    /**
     * Constructor.
     * @param ray Ray to check
     */
    public Vertical(final Ray<T> ray) {
        this(new RayLine<>(ray));
    }

    /**
     * Constructor.
     * @param seg Segment to check
     */
    public Vertical(final Segment<T> seg) {
        this(new SgtLine<>(seg));
    }

    @Override
    public boolean resolve(final Field<T> field) {
        return field.equals(
            this.line.direction().coords()[0], new AddIdentity<>()
        );
    }

}
