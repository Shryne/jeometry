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
package com.aljebra.scalar;

import com.aljebra.field.Field;
import com.aljebra.field.FieldMultiplication;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A scalar represented as the multiplication of a set of scalars.
 * @param <T> scalar types
 * @since 0.1
 */
@EqualsAndHashCode
@ToString(includeFieldNames = false)
public final class Multiplication<T> implements Scalar<T> {

    /**
     * Multiplication operands.
     */
    private final Multiset<Scalar<T>> opers;

    /**
     * Constructor.
     * @param operands Multiplication operands
     */
    public Multiplication(final Iterable<? extends Scalar<T>> operands) {
        this.opers = HashMultiset.create(operands);
    }

    /**
     * Gives the multiplication operands.
     * @return Operands of the multiplication.
     */
    public Iterable<Scalar<T>> operands() {
        return this.opers;
    }

    @Override
    public T value(final Field<T> field) {
        final FieldMultiplication<T> mult = field.multiplication();
        T result = mult.neutral();
        for (final Scalar<T> operand : this.operands()) {
            result = mult.multiply(result, operand.value(field));
        }
        return result;
    }

}
