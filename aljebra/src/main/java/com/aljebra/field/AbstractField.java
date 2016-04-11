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
package com.aljebra.field;

import com.aljebra.scalar.Scalar;

/**
 * Abstract Field implementation based on {@link Default} implementation
 * for returning actual scalar objects.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @param <T> The actual objects constituting the field.
 * @since 0.1
 */
public abstract class AbstractField<T> implements Field<T> {

    /**
     * Field addition.
     */
    private final FieldAddition<T> add;

    /**
     * Field multiplication.
     */
    private final FieldMultiplication<T> mult;

    /**
     * Constructor.
     * @param add Field addition
     * @param mul Field multiplication
     */
    public AbstractField(final FieldAddition<T> add,
        final FieldMultiplication<T> mul) {
        this.add = add;
        this.mult = mul;
    }

    @Override
    public final Scalar other(final Scalar scalar) {
        Scalar result = this.random();
        while (this.equals(result, scalar)) {
            result = this.random();
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final T actual(final Scalar scalar) {
        T result = null;
        if (scalar instanceof Scalar.Default<?>) {
            result = ((Scalar.Default<T>) scalar).value();
        } else {
            result = scalar.value(this);
        }
        return result;
    }

    @Override
    public final FieldAddition<T> addition() {
        return this.add;
    }

    @Override
    public final FieldMultiplication<T> multiplication() {
        return this.mult;
    }
}
