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
package com.jeometry.twod.line;

import com.aljebra.vector.Vect;
import com.jeometry.twod.ray.Ray;
import lombok.ToString;

/**
 * A line defined by "porting" a ray. The line has the same direction as the ray
 * and the origin of the ray belongs to the line.
 * @param <T> scalar types
 * @since 0.1
 */
@ToString(includeFieldNames = false)
public final class RayLine<T> implements Line<T> {

    /**
     * Ray.
     */
    private final Ray<T> ray;

    /**
     * Constructor.
     * @param ray Ported ray
     */
    public RayLine(final Ray<T> ray) {
        this.ray = ray;
    }

    @Override
    public Vect<T> direction() {
        return this.ray.direction();
    }

    @Override
    public Vect<T> point() {
        return this.ray.origin();
    }

}
