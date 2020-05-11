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
package com.aljebra.field.mock;

import com.aljebra.field.FieldMultiplication;

/**
 * Mock FieldMultiplication. Gives the ability to see:
 * - whether neutral or inverse methods are called;
 * - the number of times the multiply method was called.
 * @param <T> scalar types
 * @since 0.1
 */
public final class MkMultiplication<T> implements FieldMultiplication<T> {

    /**
     * Boolean indicating if inverse method was called.
     */
    private Boolean inver;

    /**
     * Integer indicating the number of times the multiply method was called.
     */
    private Integer mult;

    /**
     * Boolean indicating if neutral method was called.
     */
    private Boolean neutrld;

    /**
     * Neutral element of the multiplication.
     */
    private final T element;

    /**
     * Ctor.
     * @param elt Neutral element of the multiplication.
     */
    public MkMultiplication(final T elt) {
        this.element = elt;
        this.neutrld = Boolean.FALSE;
        this.mult = 0;
        this.inver = Boolean.FALSE;
    }

    @Override
    public T neutral() {
        this.neutrld = Boolean.TRUE;
        return this.element;
    }

    @Override
    public T inverse(final T elt) {
        this.inver = Boolean.TRUE;
        return elt;
    }

    @Override
    public T multiply(final T operand, final T second) {
        this.mult = this.mult + 1;
        return operand;
    }

    /**
     * Accessor for a boolean indicating if inverse method was called.
     * @return True if inverse method was called, false otherwise
     */
    public Boolean inverted() {
        return this.inver;
    }

    /**
     * Accessor for a number indicating the times the multiply method was called.
     * @return Times the multiply method was called
     */
    public Integer multiplied() {
        return this.mult;
    }

    /**
     * Accessor for a boolean indicating if neutral method was called.
     * @return True if neutral method was called, false otherwise
     */
    public Boolean neutraled() {
        return this.neutrld;
    }
}
