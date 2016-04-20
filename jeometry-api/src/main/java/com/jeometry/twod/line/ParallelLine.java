/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2016, Hamdi Douss
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
import com.jeometry.twod.point.OutsideLinePoint;
import lombok.ToString;

/**
 * A line defined by being parallel to another line.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ToString(includeFieldNames = false)
public final class ParallelLine implements Line {
    /**
     * The line to be parallel to.
     */
    private final Line parallel;

    /**
     * A point by which this line passes.
     */
    private final Vect pnt;

    /**
     * Constructor. Builds a random parallel line.
     * @param parallel The line to be parallel to
     */
    public ParallelLine(final Line parallel) {
        this(parallel, new OutsideLinePoint(parallel));
    }

    /**
     * Constructor. Builds a parallel line passing by the given point.
     * @param parallel The line to be parallel to
     * @param point The point to pass by
     */
    public ParallelLine(final Line parallel, final Vect point) {
        this.pnt = point;
        this.parallel = parallel;
    }

    @Override
    public Vect direction() {
        return this.parallel.direction();
    }

    @Override
    public Vect point() {
        return this.pnt;
    }

}
