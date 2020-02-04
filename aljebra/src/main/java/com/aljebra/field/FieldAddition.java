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
 * Interface describing a field addition operation.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @param <T> The actual objects constituting the field.
 * @since 0.1
 */
public interface FieldAddition<T> {

    /**
     * Calculates addition.
     * @param operand First addition operand
     * @param second Second addition operand
     * @return Addition result
     */
    T add(final T operand, final T second);

    /**
     * Gives the neutral element of addition.
     * @return The addition identity element
     */
    T neutral();

    /**
     * Calculates the inverse of an element regarding to addition operation.
     * @param elt Element to inverse
     * @return Inverse of the passed element
     */
    T inverse(final T elt);
}
