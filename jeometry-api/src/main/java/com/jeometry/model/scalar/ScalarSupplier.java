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

/**
 * Interface describing a field of scalars, giving the capability to:<br>
 * <ul>
 * <li>Generate random scalars</li>
 * <li>Define the sum identity</li>
 * <li>Compare two scalars</li>
 * </ul>
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @since 0.1
 */
public interface ScalarSupplier {

    /**
     * Generates a random scalar.
     * @return A random scalar
     */
    Scalar random();

    /**
     * Generates a random scalar other than a given scalar.
     * @param scalar The scalar to be different to.
     * @return A random scalar different from given one.
     */
    Scalar other(final Scalar scalar);

    /**
     * Gives a scalar representing the sum identity.
     * @return The sum identity scalar.
     */
    Scalar addIdentity();

    /**
     * Operates equality on scalars.
     * @param scalar First scalar
     * @param other Second scalar
     * @return True if the two scalars are considered equal
     */
    boolean equals(final Scalar scalar, final Scalar other);
}
