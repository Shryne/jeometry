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
package com.jeometry.twod.angle;

import com.aljebra.metric.angle.VectsDegrees;
import com.aljebra.metric.vect.RotateVect;
import com.aljebra.vector.Vect;
import com.jeometry.twod.point.RandomPoint;
import lombok.ToString;

/**
 * An angle defined by being equal to another angle.
 * @param <T> scalar types
 * @since 0.1
 */
@ToString
public final class EqualAngle<T> implements Angle<T> {

    /**
     * Angle origin.
     */
    private final Vect<T> org;

    /**
     * Starting angle vector.
     */
    private final Vect<T> frst;

    /**
     * Ending angle vector.
     */
    private final Vect<T> scnd;

    /**
     * Constructor.
     * @param origin Angle summit (vertex)
     * @param start Starting angle vector
     * @param angle Angle measure
     */
    public EqualAngle(final Vect<T> origin, final Vect<T> start, final Angle<T> angle) {
        this.org = origin;
        this.frst = start;
        this.scnd = new RotateVect<T>(
            start, new VectsDegrees(angle.start(), angle.end())
        );
    }

    /**
     * Constructor.
     * @param angle Angle measure
     */
    public EqualAngle(final Angle<T> angle) {
        this(new RandomPoint<T>(), new RandomPoint<T>(), angle);
    }

    @Override
    public Vect<T> origin() {
        return this.org;
    }

    @Override
    public Vect<T> start() {
        return this.frst;
    }

    @Override
    public Vect<T> end() {
        return this.scnd;
    }

}
