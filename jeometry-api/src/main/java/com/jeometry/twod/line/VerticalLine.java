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

import com.aljebra.scalar.Scalar;
import com.aljebra.vector.Vect;
import com.jeometry.twod.point.RandomPoint;
import com.jeometry.twod.point.VertPoint;
import com.jeometry.twod.point.XPoint;

/**
 * A vertical line.
 * @param <T> scalar types
 * @since 0.1
 */
public final class VerticalLine<T> extends PtDirLine<T> {

    /**
     * Constructor. Builds a random vertical line.
     */
    public VerticalLine() {
        this(new RandomPoint<>());
    }

    /**
     * Constructor. Builds a vertical line passing by the given abscissa.
     * @param abscissa Line coordinate on the x-axis
     */
    public VerticalLine(final Scalar<T> abscissa) {
        this(new XPoint<>(abscissa));
    }

    /**
     * Constructor. Builds a vertical line passing by the given point.
     * @param point Point the vertical line will pass by
     */
    public VerticalLine(final Vect<T> point) {
        super(point, new VertPoint<>());
    }
}
