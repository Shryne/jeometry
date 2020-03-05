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
import com.aljebra.scalar.condition.And;
import com.aljebra.scalar.condition.Equals;
import com.aljebra.scalar.condition.Not;
import com.aljebra.scalar.condition.Predicate;
import com.jeometry.twod.line.Line;
import com.jeometry.twod.line.RayLine;
import com.jeometry.twod.line.SgtLine;
import com.jeometry.twod.ray.Ray;
import com.jeometry.twod.segment.Segment;

/**
 * A predicate to determine if two lines have the same direction.
 * @param <T> scalar types
 * @since 0.1
 */
public final class Parallel<T> implements Predicate<T> {

    /**
     * First line.
     */
    private final Line first;

    /**
     * Second line.
     */
    private final Line second;

    /**
     * Constructor.
     * @param first First line
     * @param second Second line
     */
    public Parallel(final Line first, final Line second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Constructor.
     * @param first First ray
     * @param second Second ray
     */
    public Parallel(final Ray first, final Ray second) {
        this(new RayLine(first), new RayLine(second));
    }

    /**
     * Constructor.
     * @param first First segment
     * @param second Second segment
     */
    public Parallel(final Segment first, final Segment second) {
        this(new SgtLine(first), new SgtLine(second));
    }

    @Override
    public boolean resolve(final Field<T> field) {
        return new And(
            new Vertical(this.first), new Vertical(this.second)
            ).resolve(field) || new And(
                new Not(new Vertical(this.first)),
                new Not(new Vertical(this.second))
            ).resolve(field) && new Equals(
                new Slope(this.first), new Slope(this.second)
            ).resolve(field);
    }

}
