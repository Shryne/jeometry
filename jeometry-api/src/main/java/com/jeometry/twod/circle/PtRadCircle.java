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
package com.jeometry.twod.circle;

import com.aljebra.scalar.Scalar;
import com.aljebra.vector.Vect;
import lombok.ToString;

/**
 * Circle implementation class describing a circle by a center and a radius.
 * @param <T> scalar types
 * @since 0.1
 */
@ToString
public class PtRadCircle<T> implements Circle<T> {

    /**
     * Circle radius.
     */
    private final Scalar<T> rdx;

    /**
     * Circle center.
     */
    private final Vect<T> cntr;

    /**
     * Ctor.
     * @param center Circle center
     * @param radius Circle radius
     */
    public PtRadCircle(final Vect<T> center, final Scalar<T> radius) {
        this.rdx = radius;
        this.cntr = center;
    }

    @Override
    public final Vect<T> center() {
        return this.cntr;
    }

    @Override
    public final Scalar<T> radius() {
        return this.rdx;
    }

}
