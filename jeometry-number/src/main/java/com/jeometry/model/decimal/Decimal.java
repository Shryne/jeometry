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
public final class Decimal extends AbstractField<Double> {

    /**
     * Minimum value to generate when randomizing a scalar.
     */
    private static final int MINBOUND = -10;
    /**
     * Maximum value to generate when randomizing a scalar.
     */
    private static final int MAXBOUND = 10;

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
        return new Scalar.Default<Double>(
            this.rand.nextDouble()
            * (Decimal.MAXBOUND - Decimal.MINBOUND)
            + Decimal.MINBOUND
        );
    }

    @Override
    public Scalar addIdentity() {
        return new Scalar.Default<Double>(new Double(0));
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(final Scalar first, final Scalar second) {
        boolean result = false;
        if (Scalar.Default.class.isAssignableFrom(first.getClass())
            && Scalar.Default.class.isAssignableFrom(second.getClass())) {
            result = Math.abs(
                ((Scalar.Default<Double>) first).value()
                - ((Scalar.Default<Double>) second).value()
            ) < Decimal.TOLERANCE;
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Scalar calculate(final Add add) {
        final Scalar[] ops = add.operands();
        Double sum = 0.;
        for (final Scalar scalar : ops) {
            if (Scalar.Default.class.isAssignableFrom(scalar.getClass())) {
                sum += ((Scalar.Default<Double>) scalar).value();
            } else {
                sum += this.actual(scalar);
            }
        }
        return new Scalar.Default<Double>(sum);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Scalar calculate(final Multiplication mult) {
        final Scalar[] ops = mult.operands();
        Double product = 1.;
        for (final Scalar scalar : ops) {
            if (Scalar.Default.class.isAssignableFrom(scalar.getClass())) {
                product *= ((Scalar.Default<Double>) scalar).value();
            } else {
                product *= this.actual(scalar);
            }
        }
        return new Scalar.Default<Double>(product);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Scalar calculate(final Division div) {
        final Scalar dividend = div.first();
        final Scalar divisor = div.second();
        final Double first;
        final Double second;
        if (Scalar.Default.class.isAssignableFrom(dividend.getClass())) {
            first = ((Scalar.Default<Double>) dividend).value();
        } else {
            first = this.actual(dividend);
        }
        if (Scalar.Default.class.isAssignableFrom(divisor.getClass())) {
            second = ((Scalar.Default<Double>) divisor).value();
        } else {
            second = this.actual(divisor);
        }
        return new Scalar.Default<Double>(first / second);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Scalar calculate(final Diff diff) {
        final Scalar minuend = diff.first();
        final Scalar subtrahend = diff.second();
        final Double first;
        final Double second;
        if (Scalar.Default.class.isAssignableFrom(minuend.getClass())) {
            first = ((Scalar.Default<Double>) minuend).value();
        } else {
            first = this.actual(minuend);
        }
        if (Scalar.Default.class.isAssignableFrom(subtrahend.getClass())) {
            second = ((Scalar.Default<Double>) subtrahend).value();
        } else {
            second = this.actual(subtrahend);
        }
        return new Scalar.Default<Double>(first - second);
    }

}
