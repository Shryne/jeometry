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
package com.aljebra.scalar.condition;

import com.aljebra.field.Field;
import com.aljebra.scalar.Scalar;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A scalar whose actual value depends on a condition. Acts like
 * a ternary operator.
 * @param <T> scalar types
 * @since 0.1
 */
@EqualsAndHashCode
@ToString
public final class Ternary<T> implements Scalar<T> {

    /**
     * Predicate to evaluate.
     */
    private final Predicate pred;

    /**
     * Scalar to evaluate to if predicate is verified.
     */
    private final Scalar<T> right;

    /**
     * Scalar to evaluate to if predicate is not verified.
     */
    private final Scalar<T> wrong;

    /**
     * Constructor.
     * @param pred Predicate to evaluate
     * @param oks Scalar to evaluate to if predicate is true
     * @param kos Scalar to evaluate to if predicate is false
     */
    public Ternary(final Predicate pred, final Scalar<T> oks, final Scalar<T> kos) {
        this.pred = pred;
        this.right = oks;
        this.wrong = kos;
    }

    @Override
    public T value(final Field<T> field) {
        final T result;
        if (this.pred.resolve(field)) {
            result = field.actual(this.right);
        } else {
            result = field.actual(this.wrong);
        }
        return result;
    }

}
