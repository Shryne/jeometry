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
package com.jeometry.op;

import com.jeometry.model.Vector;
import com.jeometry.model.scalar.Add;
import com.jeometry.model.scalar.Scalar;

/**
 * A vector represented as the sum of two vectors.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class Sum implements Vector {

    /**
     * First operand.
     */
    private Vector first;

    /**
     * Second operand.
     */
    private Vector second;

    /**
     * Constructor.
     * @param second First operand
     * @param first Second operand
     */
    public Sum(final Vector first, final Vector second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Scalar x() {
        return new Add(this.first.x(), this.second.x());
    }

    @Override
    public Scalar y() {
        return new Add(this.first.y(), this.second.y());
    }

}
