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
import com.jeometry.twod.segment.RandomSegment;
import com.jeometry.twod.segment.Segment;

/**
 * Mock decorator for segment with spying (verifying) capabilities on the methods: start and end.
 * @param <T> scalar types
 * @since 0.1
 */
public final class SpySegment<T> implements Segment<T> {

    /**
     * Decorated segment.
     */
    private final Segment<T> org;

    /**
     * Boolean indicating if start method was called.
     */
    private Boolean strt;

    /**
     * Boolean indicating if end method was called.
     */
    private Boolean endd;

    /**
     * Ctor. Decorates a random generated line.
     */
    public SpySegment() {
        this(new RandomSegment<>());
    }

    /**
     * Ctor.
     * @param origin Decorated Segment
     */
    public SpySegment(final Segment<T> origin) {
        this.org = origin;
        this.strt = Boolean.FALSE;
        this.endd = Boolean.FALSE;
    }

    @Override
    public Vect<T> start() {
        this.strt = Boolean.TRUE;
        return this.org.start();
    }

    @Override
    public Vect<T> end() {
        this.endd = Boolean.TRUE;
        return this.org.end();
    }

    /**
     * Accessor for a boolean indicating if start method was called.
     * @return True if start method was called.
     */
    public Boolean started() {
        return this.strt;
    }

    /**
     * Accessor for a boolean indicating if end method was called.
     * @return True if end method was called.
     */
    public Boolean ended() {
        return this.endd;
    }
}
