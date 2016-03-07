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
package com.jeometry.model.scalar;

import com.jeometry.model.Vector;

/**
 * Class representing dot operation (scalar product) between 2 vectors.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @since 0.1
 */
public final class Dot {

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
     * @param first First operand
     * @param second Second operand
     */
    public Dot(final Vector first, final Vector second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Gives first operand.
     * @return The first operand of the product.
     */
    public Vector first() {
        return this.first;
    }

    /**
     * Gives second operand.
     * @return The second operand of the product.
     */
    public Vector second() {
        return this.second;
    }

    /**
     * Calculates the the dot product.
     * @return Dot product value.
     */
    public Scalar value() {
        return new Add(
            new Multiplication(this.second.x(), this.first.x()),
            new Multiplication(this.second.y(), this.first.y())
        );
    }

}
