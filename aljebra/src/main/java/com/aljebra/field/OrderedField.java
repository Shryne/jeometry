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
 * Interface describing an ordered field of scalars.
 * Giving the capability to:<br>
 * <ul>
 * <li>Generate random ordered scalars</li>
 * <li>Compare two scalars by order</li>
 * </ul>
 * @param <T> The actual objects constituting the field.
 * @since 0.1
 */
public interface OrderedField<T> extends Field<T> {

    /**
     * Generates a random scalar "between" two other scalars. The word "between"
     * is defined as greater than the smaller scalar and smaller than
     * the greater scalar.
     * @param lower Lower bound scalar
     * @param upper Upper bound scalar
     * @return A random scalar between two scalars
     */
    Scalar between(Scalar lower, Scalar upper);

    /**
     * Generates a random scalar greater than the given scalar.
     * @param lower Lower bound scalar
     * @return A random scalar
     */
    Scalar greater(Scalar lower);

    /**
     * Generates a random scalar smaller than the given scalar.
     * @param upper Upper bound scalar
     * @return A random scalar
     */
    Scalar lower(Scalar upper);

}
