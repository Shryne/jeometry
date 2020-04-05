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

import com.aljebra.vector.Vect;
import lombok.ToString;

/**
 * An angle defined by a point, a vector porting the starting ray,
 * and a vector porting the ending ray.
 * @param <T> scalar types
 * @since 0.1
 */
@ToString
public class VectsAngle<T> implements Angle<T> {

    /**
     * Angle origin.
     */
    private final Vect<T> org;

    /**
     * Vector porting the starting ray.
     */
    private final Vect<T> frst;

    /**
     * Vector porting the ending ray.
     */
    private final Vect<T> scnd;

    /**
     * Constructor.
     * @param origin Angle summit (vertex)
     * @param start Vector porting the starting ray
     * @param end Vector porting the ending ray
     */
    public VectsAngle(final Vect<T> origin, final Vect<T> start, final Vect<T> end) {
        this.org = origin;
        this.frst = start;
        this.scnd = end;
    }

    @Override
    public final Vect<T> origin() {
        return this.org;
    }

    @Override
    public final Vect<T> start() {
        return this.frst;
    }

    @Override
    public final Vect<T> end() {
        return this.scnd;
    }
}
