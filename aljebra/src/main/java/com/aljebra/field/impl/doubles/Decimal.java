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
package com.aljebra.field.impl.doubles;

import com.aljebra.field.AbstractOrderedField;
import com.aljebra.field.Field;
import com.aljebra.field.MetricSpaceField;
import com.aljebra.field.OrderedRandom;
import com.aljebra.metric.InnerProduct;
import com.aljebra.scalar.Scalar;

/**
 * A {@link Field} implementation based on double.
 * @since 0.1
 */
public final class Decimal
    extends AbstractOrderedField<Double> implements MetricSpaceField<Double> {

    /**
     * Minimum value to generate when randomizing a scalar.
     */
    private static final double MINBOUND = -10;

    /**
     * Maximum value to generate when randomizing a scalar.
     */
    private static final double MAXBOUND = 10;

    /**
     * A tolerance threshold to consider two values as equal.
     */
    private static final double TOLERANCE = 1.E-6;

    /**
     * Constructor.
     */
    public Decimal() {
        this(new DecimalRandom(Decimal.MINBOUND, Decimal.MAXBOUND));
    }

    /**
     * Constructor.
     * @param rand Randomizer
     */
    public Decimal(final OrderedRandom<Double> rand) {
        super(new DblAddition(), new DblMult(), rand);
    }

    @Override
    public Scalar random() {
        return this.between(
            new Scalar.Default<Double>(Decimal.MINBOUND),
            new Scalar.Default<Double>(Decimal.MAXBOUND)
        );
    }

    @Override
    public boolean equals(final Scalar first, final Scalar second) {
        return Math.abs(
            this.actual(first) - this.actual(second)
        ) < Decimal.TOLERANCE;
    }

    @Override
    public InnerProduct product() {
        return new Dot();
    }
}
