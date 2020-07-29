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
package com.jeometry.twod.point;

import com.aljebra.scalar.Scalar;
import com.jeometry.twod.line.Line;
import com.jeometry.twod.line.analytics.LinePointOrdinate;

/**
 * A point defined by belonging to a line. The point is dynamic regarding to the
 * passed line, which means that it is ensured that this point remains belonging
 * to the line even if modifications occur on the line.
 * @param <T> scalar types
 * @since 0.1
 */
public final class InLinePoint<T> extends XyPoint<T> {

    /**
     * Constructor.
     * @param line The line to belong to
     */
    public InLinePoint(final Line<T> line) {
        this(line, line.point().coords()[0]);
    }

    /**
     * Constructor. Builds a point in the line having the given abscissa.
     * @param line The line to belong to
     * @param abscissa Abscissa of the point to build
     */
    public InLinePoint(final Line<T> line, final Scalar<T> abscissa) {
        super(abscissa, new LinePointOrdinate<>(line, abscissa));
    }
}
