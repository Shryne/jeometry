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
package com.aljebra.scalar;

import com.aljebra.field.Field;
import com.aljebra.field.FieldMultiplication;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A scalar represented as the division of a scalar by another scalar.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@EqualsAndHashCode
@ToString
public final class Division<T> implements Scalar<T> {
    /**
     * First operand.
     */
    private final Scalar<T> dividend;

    /**
     * Second operand.
     */
    private final Scalar<T> divisor;

    /**
     * Constructor.
     * @param first First operand (dividend)
     * @param second Second operand (divisor)
     */
    public Division(final Scalar<T> first, final Scalar<T> second) {
        this.dividend = first;
        this.divisor = second;
    }

    /**
     * Gives first operand (dividend).
     * @return The first operand of the division.
     */
    public Scalar<T> first() {
        return this.dividend;
    }

    /**
     * Gives second operand (divisor).
     * @return The second operand of the sum.
     */
    public Scalar<T> second() {
        return this.divisor;
    }

    @Override
    public T value(final Field<T> field) {
        final FieldMultiplication<T> mult = field.multiplication();
        final T inverse = mult.inverse(field.actual(this.divisor));
        return mult.multiply(field.actual(this.dividend), inverse);
    }

}
