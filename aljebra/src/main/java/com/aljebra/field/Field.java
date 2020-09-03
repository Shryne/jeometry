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
package com.aljebra.field;

import com.aljebra.scalar.Scalar;

/**
 * Interface describing a field of scalars. Giving the capability to:<br>
 * <ul>
 * <li>Generate random scalars</li>
 * <li>Define the sum operation</li>
 * <li>Define the multiplication operation</li>
 * <li>Checks two scalars for equality</li>
 * </ul>
 * @param <T> The actual objects constituting the field.
 * @since 0.1
 */
public interface Field<T> {

    /**
     * Generates a random scalar.
     * @return A random scalar
     */
    Scalar<T> random();

    /**
     * Generates a random scalar other than a given scalar.
     * @param scalar The scalar to be different to.
     * @return A random scalar different from given one.
     */
    Scalar<T> other(Scalar<T> scalar);

    /**
     * Operates equality on scalars.
     * @param scalar First scalar
     * @param other Second scalar
     * @return True if the two scalars are considered equal
     */
    boolean equals(Scalar<T> scalar, Scalar<T> other);

    /**
     * Returns the actual object represented by the given scalar.
     * @param scalar Scalar
     * @return The actual object
     */
    T actual(Scalar<T> scalar);

    /**
     * Returns the field addition operation.
     * @return A {@link FieldAddition} object
     */
    FieldAddition<T> addition();

    /**
     * Returns the field multiplication operation.
     * @return A {@link FieldMultiplication} object
     */
    FieldMultiplication<T> multiplication();
}
