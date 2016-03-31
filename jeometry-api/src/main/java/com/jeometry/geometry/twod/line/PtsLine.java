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
package com.jeometry.geometry.twod.line;

import com.jeometry.model.algebra.vector.Minus;
import com.jeometry.model.algebra.vector.Vect;
import lombok.ToString;

/**
 * A line passing by two points (vectors).
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ToString(includeFieldNames = false)
public final class PtsLine implements Line {
    /**
     * First point by which this line passes.
     */
    private final Vect pnta;

    /**
     * Second point by which this line passes.
     */
    private final Vect pntb;

    /**
     * Constructor.
     * @param pnta First point by which this line passes
     * @param pntb Second point by which this line passes
     */
    public PtsLine(final Vect pnta, final Vect pntb) {
        this.pnta = pnta;
        this.pntb = pntb;
    }

    @Override
    public Vect direction() {
        return new Minus(this.pnta, this.pntb);
    }

    @Override
    public Vect point() {
        return this.pnta;
    }

}
