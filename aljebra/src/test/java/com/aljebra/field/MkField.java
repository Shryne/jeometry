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

import com.aljebra.metric.InnerProduct;
import com.aljebra.scalar.Random;
import com.aljebra.scalar.Scalar;

/**
 * Mock MetricSpaceField. Gives the ability to pass the inner product to use.
 * @param <T> scalar types
 * @since 0.1
 */
public final class MkField<T> extends AbstractOrderedField<T> implements MetricSpaceField<T> {

    /**
     * Inner product to use.
     */
    private final InnerProduct<T> pdt;

    /**
     * Ctor.
     * @param add Addition operation
     * @param mul Multiplication operation
     * @param pdt Inner product
     */
    public MkField(final FieldAddition<T> add,
        final FieldMultiplication<T> mul, final InnerProduct<T> pdt) {
        super(add, mul, new SpyOrderedRandom<T>());
        this.pdt = pdt;
    }

    @Override
    public Scalar<T> random() {
        return new Random<>();
    }

    @Override
    public boolean equals(final Scalar<T> scalar, final Scalar<T> other) {
        return false;
    }

    @Override
    public InnerProduct<T> product() {
        return this.pdt;
    }
}
