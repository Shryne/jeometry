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

import com.aljebra.scalar.Scalar;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Holder object for method calling. Used for spying purpose to record and report method calls
 * and parameters.
 * It holds the parameter with which the method `other` were last called as optional.
 * It holds the subsequent parameters with which the method `actual` is called as optional.
 * If the optional is empty, that means the method was never called.
 * @param <T> scalar types
 * @since 0.3
 */
public final class FieldCalls<T> {

    /**
     * Boolean indicating if addition method was called.
     */
    private Boolean add;

    /**
     * Boolean indicating if multiplication method was called.
     */
    private Boolean mlt;

    /**
     * Boolean indicating if random method was called.
     */
    private Boolean rnd;

    /**
     * An optional holding the last scalar passed as parameter when calling other method.
     * The optional is empty if the method was never called.
     */
    private Optional<Scalar<T>> othr;

    /**
     * An optional holding a list of the subsequent scalars passed as parameter when calling
     * actual method.
     * The optional is empty if the method was never called.
     */
    private Optional<List<Scalar<T>>> actls;

    /**
     * Ctor.
     */
    public FieldCalls() {
        this.add = Boolean.FALSE;
        this.mlt = Boolean.FALSE;
        this.rnd = Boolean.FALSE;
        this.othr = Optional.empty();
        this.actls = Optional.empty();
    }

    /**
     * Register a call to random method.
     */
    public void random() {
        this.rnd = Boolean.TRUE;
    }

    /**
     * Register a call to other method with the passed param.
     * @param scalar Parameter with which other method was called
     */
    public void other(final Scalar<T> scalar) {
        this.othr = Optional.of(scalar);
    }

    /**
     * Register a call to actual method with the passed param.
     * @param scalar Parameter with which actual method was called
     */
    public void actual(final Scalar<T> scalar) {
        if (this.actls.isPresent()) {
            this.actls.get().add(scalar);
        } else {
            final List<Scalar<T>> list = new ArrayList<>(1);
            list.add(scalar);
            this.actls = Optional.of(list);
        }
    }

    /**
     * Register a call to addition method.
     */
    public void addition() {
        this.add = Boolean.TRUE;
    }

    /**
     * Register a call to multiplication method.
     */
    public void multiplication() {
        this.mlt = Boolean.TRUE;
    }

    /**
     * Accessor for the last scalar passed when calling other method.
     * @return An optional probably containing a scalar, or empty if the method was never called
     */
    public Optional<Scalar<T>> other() {
        return this.othr;
    }

    /**
     * Accessor for a boolean indicating if addition method was called.
     * @return True if addition method was called, false otherwise
     */
    public Boolean additioned() {
        return this.add;
    }

    /**
     * Accessor for a boolean indicating if multiplication method was called.
     * @return True if addition multiplication was called, false otherwise
     */
    public Boolean multiplicationed() {
        return this.mlt;
    }

    /**
     * Accessor for a boolean indicating if random method was called.
     * @return True if random multiplication was called, false otherwise
     */
    public Boolean randomed() {
        return this.rnd;
    }

    /**
     * Accessor for the subsequent scalars passed when calling actual method.
     * @return An optional probably containing a list of scalars,
     *  or empty if the method was never called
     */
    public Optional<List<Scalar<T>>> actuals() {
        return this.actls;
    }
}
