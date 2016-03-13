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
package com.jeometry.model.algebra.vector;

import com.jeometry.model.algebra.scalar.Multiplication;
import com.jeometry.model.algebra.scalar.Scalar;

/**
 * A vector represented as the multiplication of a vector by a scalar.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class Times implements Vect {

    /**
     * Vector.
     */
    private final transient Vect vector;

    /**
     * Scalar.
     */
    private final transient Scalar scalar;

    /**
     * Constructor.
     * @param vector Vector to multiply
     * @param scalar Scalar by which to multiply
     */
    public Times(final Vect vector, final Scalar scalar) {
        super();
        this.vector = vector;
        this.scalar = scalar;
    }

    @Override
    public Scalar[] coords() {
        final Scalar[] coors = this.vector.coords();
        final Scalar[] result = new Scalar[coors.length];
        for (int idx = 0; idx < coors.length; ++idx) {
            result[idx] = new Multiplication(coors[idx], this.scalar);
        }
        return result;
    }
}
