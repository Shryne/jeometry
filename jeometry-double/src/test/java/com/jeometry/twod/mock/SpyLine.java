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
import com.jeometry.twod.line.Line;
import com.jeometry.twod.line.RandomLine;

/**
 * Mock decorator for line with spying (verifying) capabilities on the methods: point and direction.
 * @param <T> scalar types
 * @since 0.1
 */
public final class SpyLine<T> implements Line<T> {

    /**
     * Decorated line.
     */
    private final Line<T> org;

    /**
     * Boolean indicating if point method was called.
     */
    private Boolean pnt;

    /**
     * Boolean indicating if direction method was called.
     */
    private Boolean dir;

    /**
     * Ctor. Decorates a random generated line.
     */
    public SpyLine() {
        this(new RandomLine<>());
    }

    /**
     * Ctor.
     * @param origin Decorated line
     */
    public SpyLine(final Line<T> origin) {
        this.org = origin;
        this.pnt = Boolean.FALSE;
        this.dir = Boolean.FALSE;
    }

    @Override
    public Vect<T> direction() {
        this.dir = Boolean.TRUE;
        return this.org.direction();
    }

    @Override
    public Vect<T> point() {
        this.pnt = Boolean.TRUE;
        return this.org.point();
    }

    /**
     * Accessor for a boolean indicating if point method was called.
     * @return True if point method was called.
     */
    public Boolean pointed() {
        return this.pnt;
    }

    /**
     * Accessor for a boolean indicating if direction method was called.
     * @return True if direction method was called.
     */
    public Boolean directioned() {
        return this.dir;
    }
}
