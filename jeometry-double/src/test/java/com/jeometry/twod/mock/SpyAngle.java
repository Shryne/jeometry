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

import com.aljebra.vector.Vect;
import com.jeometry.twod.angle.Angle;
import com.jeometry.twod.angle.VectsAngle;
import com.jeometry.twod.point.RandomPoint;

/**
 * Mock decorator for angle with spying (verifying) capabilities on the method: start.
 * @param <T> scalar types
 * @since 0.3
 */
public final class SpyAngle<T> implements Angle<T> {

    /**
     * Decorated angle.
     */
    private final Angle<T> org;

    /**
     * Boolean indicating if start method was called.
     */
    private Boolean str;

    /**
     * Boolean indicating if origin method was called.
     */
    private Boolean orig;

    /**
     * Ctor. Decorated angle is a random 2D-angle.
     */
    public SpyAngle() {
        this(
            new VectsAngle<>(new RandomPoint<>(), new RandomPoint<>(), new RandomPoint<>())
        );
    }

    /**
     * Ctor.
     * @param origin Decorated angle
     */
    public SpyAngle(final Angle<T> origin) {
        this.org = origin;
        this.str = Boolean.FALSE;
        this.orig = Boolean.FALSE;
    }

    @Override
    public Vect<T> start() {
        this.str = Boolean.TRUE;
        return this.org.start();
    }

    @Override
    public Vect<T> end() {
        return this.org.start();
    }

    @Override
    public Vect<T> origin() {
        this.orig = Boolean.TRUE;
        return this.org.start();
    }

    /**
     * Accessor for a boolean indicating if start method was called.
     * @return True if start method was called.
     */
    public Boolean started() {
        return this.str;
    }

    /**
     * Accessor for a boolean indicating if origin method was called.
     * @return True if origin method was called.
     */
    public Boolean origined() {
        return this.orig;
    }
}
