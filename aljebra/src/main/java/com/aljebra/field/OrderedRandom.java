/**
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

/**
 * Interface describing an scalar generator. Scalars are elements of an
 * ordered field and implementations of this interface are capable of generating
 * scalars in an ordered manner.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @param <T> The actual objects constituting the field.
 * @since 0.1
 */
public interface OrderedRandom<T> {

    /**
     * Generates a random scalar "between" two other scalars. The word "between"
     * is defined as greater than the smaller scalar and smaller than
     * the greater scalar.
     * @param lower Lower bound scalar
     * @param upper Upper bound scalar
     * @return A random scalar between two scalars
     */
    T between(final T lower, final T upper);

    /**
     * Generates a random scalar greater than the given scalar.
     * @param lower Lower bound scalar
     * @return A random scalar
     */
    T greater(final T lower);

    /**
     * Generates a random scalar smaller than the given scalar.
     * @param upper Upper bound scalar
     * @return A random scalar
     */
    T lower(final T upper);

}
