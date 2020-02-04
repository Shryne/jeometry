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
package com.jeometry.twod.line.analytics;

import com.aljebra.field.Field;
import com.aljebra.field.MetricSpaceField;
import com.aljebra.metric.angle.VectsDegrees;
import com.aljebra.scalar.condition.Predicate;
import com.aljebra.vector.Vect;
import com.jeometry.twod.line.Line;

/**
 * A predicate to determine if two vectors or lines are perpendicular,
 * according to euclidean geometry.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class Perpendicular implements Predicate {

    /**
     * First vector.
     */
    private final Vect first;

    /**
     * Second vector.
     */
    private final Vect second;

    /**
     * Constructor.
     * @param first First line
     * @param second Second line
     */
    public Perpendicular(final Line first, final Line second) {
        this(first.direction(), second.direction());
    }

    /**
     * Constructor.
     * @param first First vector
     * @param second Second vector
     */
    public Perpendicular(final Vect first, final Vect second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean resolve(final Field<?> field) {
        if (field instanceof MetricSpaceField<?>) {
            final Double degrees = new VectsDegrees(
                this.first, this.second
            ).resolve(((MetricSpaceField<?>) field).product()).doubleValue();
            final double error = 1.e-6;
            return Math.abs(degrees - Math.PI / 2) < error
                || Math.abs(degrees + Math.PI / 2) < error;
        } else {
            throw new UnsupportedOperationException(
                String.format("Field %s is not a metric space field", field)
            );
        }
    }

}
