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
package com.jeometry.twod.mock;

import com.aljebra.scalar.Random;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.Vect;
import com.jeometry.twod.arc.Arc;
import com.jeometry.twod.arc.RadAngleArc;
import com.jeometry.twod.circle.PtRadCircle;
import com.jeometry.twod.point.RandomPoint;

/**
 * Mock decorator for arc with spying (verifying) capabilities on the method: center.
 * @param <T> scalar types
 * @since 0.4
 */
public final class SpyArc<T> implements Arc<T> {

    /**
     * Decorated arc.
     */
    private final Arc<T> org;

    /**
     * Boolean indicating if center method was called.
     */
    private Boolean cnt;

    /**
     * Ctor. Decorates a random 2D-arc.
     */
    public SpyArc() {
        this(new RadAngleArc<>(new PtRadCircle<>(new RandomPoint<>(), new Random<>()), 0, 1));
    }

    /**
     * Ctor.
     * @param origin Decorated circle
     */
    public SpyArc(final Arc<T> origin) {
        this.org = origin;
        this.cnt = Boolean.FALSE;
    }

    @Override
    public Vect<T> center() {
        this.cnt = Boolean.TRUE;
        return this.org.center();
    }

    @Override
    public Scalar<T> radius() {
        return this.org.radius();
    }

    @Override
    public Number start() {
        return this.org.start();
    }

    @Override
    public Number end() {
        return this.org.end();
    }

    /**
     * Accessor for a boolean indicating if center method was called.
     * @return True if point method was called.
     */
    public Boolean centered() {
        return this.cnt;
    }

}
