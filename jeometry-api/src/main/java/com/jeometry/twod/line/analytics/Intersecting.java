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
import com.aljebra.scalar.condition.Predicate;
import com.jeometry.twod.line.Line;

/**
 * A predicate to determine if two lines intersect.
 * @param <T> scalar types
 * @since 0.1
 */
public final class Intersecting<T> implements Predicate<Field<T>> {

    /**
     * First line.
     */
    private final Line<T> first;

    /**
     * Second line.
     */
    private final Line<T> second;

    /**
     * Constructor.
     * @param first First line
     * @param second Second line
     */
    public Intersecting(final Line<T> first, final Line<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean resolve(final Field<T> field) {
        return !new Parallel<>(this.first, this.second).resolve(field);
    }

}
