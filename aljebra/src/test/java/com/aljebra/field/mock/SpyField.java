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

import com.aljebra.field.Field;
import com.aljebra.field.FieldAddition;
import com.aljebra.field.FieldMultiplication;
import com.aljebra.field.MetricSpaceField;
import com.aljebra.scalar.Scalar;

/**
 * Mock decorator for fields with spying (verifying) capabilities on the methods: other, addition,
 * multiplication, random and actual.
 * It holds an instance of {@link FieldCalls} to record and report method calls and parameters.
 * @param <T> scalar types
 * @since 0.3
 */
public final class SpyField<T> implements Field<T> {

    /**
     * Decorated field.
     */
    private final Field<T> origin;

    /**
     * Field calls.
     */
    private final FieldCalls<T> cals;

    /**
     * Ctor. Using MkField as underlying field.
     * @param addelt Neutral addition element
     * @param mulelt Neutral multiplication element
     */
    public SpyField(final T addelt, final T mulelt) {
        this(new MkField<>(addelt, mulelt));
    }

    /**
     * Ctor.
     * @param origin Field to decorate
     */
    public SpyField(final MetricSpaceField<T> origin) {
        this.origin = origin;
        this.cals = new FieldCalls<>();
    }

    @Override
    public Scalar<T> random() {
        this.cals.random();
        return this.origin.random();
    }

    @Override
    public boolean equals(final Scalar<T> scalar, final Scalar<T> other) {
        return this.origin.equals(scalar, other);
    }

    @Override
    public Scalar<T> other(final Scalar<T> scalar) {
        this.cals.other(scalar);
        return this.origin.other(scalar);
    }

    @Override
    public T actual(final Scalar<T> scalar) {
        this.cals.actual(scalar);
        return this.origin.actual(scalar);
    }

    @Override
    public FieldAddition<T> addition() {
        this.cals.addition();
        return this.origin.addition();
    }

    @Override
    public FieldMultiplication<T> multiplication() {
        this.cals.multiplication();
        return this.origin.multiplication();
    }

    /**
     * Accessor for field calls object.
     * @return A field method calls
     */
    public FieldCalls<T> calls() {
        return this.cals;
    }
}
