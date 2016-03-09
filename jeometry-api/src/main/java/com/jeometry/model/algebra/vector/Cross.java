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

import com.jeometry.model.algebra.scalar.Diff;
import com.jeometry.model.algebra.scalar.Multiplication;
import com.jeometry.model.algebra.scalar.Scalar;

/**
 * Class representing cross operation (vectorial product) between 2 vectors.
 * Since we operate in a 2D space, the main value of the cross operation is a
 * scalar representing the norm of the operation.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class Cross {

    /**
     * First operand.
     */
    private final Vect foperand;

    /**
     * Second operand.
     */
    private final Vect soperand;

    /**
     * Constructor.
     * @param first First operand
     * @param second Second operand
     */
    public Cross(final Vect first, final Vect second) {
        this.foperand = first;
        this.soperand = second;
    }

    /**
     * Gives first operand.
     * @return The first operand of the cross.
     */
    public Vect first() {
        return this.foperand;
    }

    /**
     * Gives second operand.
     * @return The second operand of the cross.
     */
    public Vect second() {
        return this.soperand;
    }

    /**
     * Calculates the norm of the cross product.
     * @return Cross product value.
     */
    public Scalar value() {
        return new Diff(
            new Multiplication(this.foperand.xcoor(), this.soperand.ycoor()),
            new Multiplication(this.soperand.xcoor(), this.foperand.ycoor())
        );
    }

}