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
package com.aljebra.metric;

import com.aljebra.metric.angle.Degrees;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.Vect;

/**
 * Inner product interface. Represents an inner product operation and its
 * induced properties.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface InnerProduct {

    /**
     * Calculates the inner product of two vectors.
     * @param first First operand
     * @param second Second operand
     * @return A scalar representing the inner product
     */
    Scalar product(final Vect first, final Vect second);

    /**
     * Calculates the angle between two vectors.
     * @param first First operand
     * @param second Second operand
     * @return The angle between two vectors
     */
    Degrees angle(final Vect first, final Vect second);

    /**
     * Calculates the norm of a vector.
     * @param vect Input vector
     * @return A scalar representing the norm
     */
    Scalar norm(final Vect vect);

    /**
     * Rotates a vector with a given angle.
     * @param vect Input vector
     * @param angle Angle to form with the input vector
     * @return A vector
     */
    Vect rot(final Vect vect, final Degrees angle);

    /**
     * Calculates the actual value of the angle.
     * @param angle Angle to resolve
     * @return A number representing the angle
     */
    default Number resolve(final Degrees angle) {
        return angle.resolve(this);
    }
}
