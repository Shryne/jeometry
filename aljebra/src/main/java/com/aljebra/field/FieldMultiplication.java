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

/**
 * Interface describing a field multiplication operation.
 * @param <T> The actual objects constituting the field.
 * @since 0.1
 */
public interface FieldMultiplication<T> {

    /**
     * Calculates multiplication.
     * @param operand First multiplication operand
     * @param second Second multiplication operand
     * @return Multiplication result
     */
    T multiply(T operand, T second);

    /**
     * Gives the neutral element of multiplication.
     * @return The addition identity element
     */
    T neutral();

    /**
     * Calculates the inverse of an element regarding
     * to multiplication operation.
     * @param elt Element to inverse
     * @return Inverse of the passed element
     */
    T inverse(T elt);
}
