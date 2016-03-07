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
import com.jeometry.model.scalar.Multiplication;
import com.jeometry.model.scalar.Scalar;

/**
 * A vector represented as the multiplication of a vector by a scalar.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class Times implements Vector {

    /**
     * Vector.
     */
    private final transient Vector vector;

    /**
     * Scalar.
     */
    private final transient Scalar scalar;

    /**
     * Constructor.
     * @param vector Vector to multiply
     * @param scalar Scalar by which to multiply
     */
    public Times(final Vector vector, final Scalar scalar) {
        super();
        this.vector = vector;
        this.scalar = scalar;
    }

    @Override
    public Scalar xcoor() {
        return new Multiplication(this.vector.xcoor(), this.scalar);
    }

    @Override
    public Scalar ycoor() {
        return new Multiplication(this.vector.ycoor(), this.scalar);
    }

}
