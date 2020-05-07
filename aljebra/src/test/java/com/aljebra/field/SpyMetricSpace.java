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
import com.aljebra.scalar.Scalar;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Mock decorator for metric space fields with spying (verifying) capabilities
 * on the methods between, greater and lower.
 * It holds the parameters with which the methods were last called as optionals.
 * If the optional is empty, that means the method was never called.
 * @param <T> scalar types
 * @since 0.1
 */
public final class SpyMetricSpace<T> extends AbstractField<T> implements MetricSpaceField<T> {

    /**
     * Decorated field.
     */
    private final MetricSpaceField<T> origin;

    /**
     * An optional holding the last scalars passed as parameter when calling between method.
     * The optional is empty if the method was never called.
     */
    private Optional<List<Scalar<T>>> bet;

    /**
     * An optional holding the last scalar passed as parameter when calling greater method.
     * The optional is empty if the method was never called.
     */
    private Optional<Scalar<T>> great;

    /**
     * An optional holding the last scalar passed as parameter when calling lower method.
     * The optional is empty if the method was never called.
     */
    private Optional<Scalar<T>> low;

    /**
     * Ctor. Using MkField as underlying field.
     * @param addelt Neutral addition element
     * @param mulelt Neutral multiplication element
     */
    public SpyMetricSpace(final T addelt, final T mulelt) {
        this(new MkField<>(addelt, mulelt));
    }

    /**
     * Ctor.
     * @param origin Field to decorate
     */
    public SpyMetricSpace(final MetricSpaceField<T> origin) {
        super(origin.addition(), origin.multiplication());
        this.origin = origin;
    }

    @Override
    public Scalar<T> between(final Scalar<T> lower, final Scalar<T> upper) {
        this.bet = Optional.of(Arrays.asList(lower, upper));
        return this.origin.between(lower, upper);
    }

    @Override
    public Scalar<T> greater(final Scalar<T> lower) {
        this.great = Optional.of(lower);
        return this.origin.greater(lower);
    }

    @Override
    public Scalar<T> lower(final Scalar<T> upper) {
        this.low = Optional.of(upper);
        return this.origin.lower(upper);
    }

    @Override
    public Scalar<T> random() {
        return this.origin.random();
    }

    @Override
    public boolean equals(final Scalar<T> scalar, final Scalar<T> other) {
        return this.origin.equals(scalar, other);
    }

    @Override
    public InnerProduct<T> product() {
        return this.origin.product();
    }

    /**
     * Accessor for the last scalars passed when calling between method.
     * @return An optional probably containing two scalars, or empty if the method was never called
     */
    public Optional<List<Scalar<T>>> between() {
        return this.bet;
    }

    /**
     * Accessor for the last scalar passed when calling greater method.
     * @return An optional probably containing a scalar, or empty if the method was never called
     */
    public Optional<Scalar<T>> greater() {
        return this.great;
    }

    /**
     * Accessor for the last scalar passed when calling lower method.
     * @return An optional probably containing a scalar, or empty if the method was never called
     */
    public Optional<Scalar<T>> lower() {
        return this.low;
    }

}
