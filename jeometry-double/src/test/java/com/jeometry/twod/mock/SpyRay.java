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
import com.jeometry.twod.ray.PtDirRay;
import com.jeometry.twod.ray.Ray;

/**
 * Mock decorator for ray with spying (verifying) capabilities on the methods: origin and direction.
 * @param <T> scalar types
 * @since 0.1
 */
public final class SpyRay<T> implements Ray<T> {

    /**
     * Decorated ray.
     */
    private final Ray<T> org;

    /**
     * Boolean indicating if origin method was called.
     */
    private Boolean orig;

    /**
     * Boolean indicating if direction method was called.
     */
    private Boolean dir;

    /**
     * Ctor. Builds and decorates a ray with the passed origin point and direction.
     * @param point Ray origin
     * @param dir Ray direction
     */
    public SpyRay(final Vect<T> point, final Vect<T> dir) {
        this(new PtDirRay<T>(point, dir));
    }

    /**
     * Ctor.
     * @param origin Decorated ray
     */
    public SpyRay(final Ray<T> origin) {
        this.org = origin;
        this.orig = Boolean.FALSE;
        this.dir = Boolean.FALSE;
    }

    @Override
    public Vect<T> direction() {
        this.dir = Boolean.TRUE;
        return this.org.direction();
    }

    @Override
    public Vect<T> origin() {
        this.orig = Boolean.TRUE;
        return this.org.origin();
    }

    /**
     * Accessor for a boolean indicating if origin method was called.
     * @return True if origin method was called.
     */
    public Boolean origined() {
        return this.orig;
    }

    /**
     * Accessor for a boolean indicating if direction method was called.
     * @return True if direction method was called.
     */
    public Boolean directioned() {
        return this.dir;
    }
}
