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
package com.jeometry.model.decimal;

import com.jeometry.model.algebra.field.AbstractField;
import com.jeometry.model.algebra.field.Field;
import com.jeometry.model.algebra.field.OrderedField;
import com.jeometry.model.algebra.scalar.Add;
import com.jeometry.model.algebra.scalar.Diff;
import com.jeometry.model.algebra.scalar.Division;
import com.jeometry.model.algebra.scalar.Multiplication;
import com.jeometry.model.algebra.scalar.Scalar;

/**
 * A {@link Field} implementation based on double.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class Decimal
    extends AbstractField<Double> implements OrderedField<Double> {

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
    private static final double TOLERANCE = 1.E-3;

    /**
     * Randomizer.
     */
    private final transient java.util.Random rand;

    /**
     * Constructor.
     */
    public Decimal() {
        this(new java.util.Random());
    }

    /**
     * Constructor.
     * @param rand Randomizer
     */
    public Decimal(final java.util.Random rand) {
        super();
        this.rand = rand;
    }

    @Override
    public Scalar random() {
        return this.between(
            new Scalar.Default<Double>(Decimal.MINBOUND),
            new Scalar.Default<Double>(Decimal.MAXBOUND)
        );
    }

    @Override
    public Scalar addIdentity() {
        return new Scalar.Default<Double>(Double.valueOf(0));
    }

    @Override
    public boolean equals(final Scalar first, final Scalar second) {
        return Math.abs(
            this.actual(first) - this.actual(second)
        ) < Decimal.TOLERANCE;
    }

    @Override
    public Scalar calculate(final Add add) {
        final Scalar[] ops = add.operands();
        Double sum = 0.;
        for (final Scalar scalar : ops) {
            sum += this.actual(scalar);
        }
        return new Scalar.Default<Double>(sum);
    }

    @Override
    public Scalar calculate(final Multiplication mult) {
        final Scalar[] ops = mult.operands();
        Double product = 1.;
        for (final Scalar scalar : ops) {
            product *= this.actual(scalar);
        }
        return new Scalar.Default<Double>(product);
    }

    @Override
    public Scalar calculate(final Division div) {
        final Scalar dividend = div.first();
        final Scalar divisor = div.second();
        return new Scalar.Default<Double>(
            this.actual(dividend) / this.actual(divisor)
        );
    }

    @Override
    public Scalar calculate(final Diff diff) {
        final Scalar minuend = diff.first();
        final Scalar subtrahend = diff.second();
        return new Scalar.Default<Double>(
            this.actual(minuend) - this.actual(subtrahend)
        );
    }

    @Override
    public Scalar multIdentity() {
        return new Scalar.Default<Double>(Double.valueOf(1));
    }

    @Override
    public Scalar between(final Scalar lower, final Scalar upper) {
        final Double min = this.actual(lower);
        final Double max = this.actual(upper);
        return new Scalar.Default<Double>(
            this.rand.nextDouble() * (max - min) + min
        );
    }

    @Override
    public Scalar greater(final Scalar lower) {
        return this.between(
            lower, new Scalar.Default<Double>(Decimal.MAXBOUND)
        );
    }

    @Override
    public Scalar lower(final Scalar upper) {
        return this.between(
            new Scalar.Default<Double>(Decimal.MINBOUND), upper
        );
    }
}
