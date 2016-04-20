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
package com.aljebra.field.impl.doubles;

import com.aljebra.field.AbstractOrderedField;
import com.aljebra.field.Field;
import com.aljebra.field.FieldAddition;
import com.aljebra.field.FieldMultiplication;
import com.aljebra.field.MetricSpaceField;
import com.aljebra.field.OrderedRandomizer;
import com.aljebra.metric.InnerProduct;
import com.aljebra.scalar.Scalar;

/**
 * A {@link Field} implementation based on double.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
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
    private static final double TOLERANCE = 1.E-3;

    /**
     * Constructor.
     */
    public Decimal() {
        this(new DecimalRandomizer(Decimal.MINBOUND, Decimal.MAXBOUND));
    }

    /**
     * Constructor.
     * @param rand Randomizer
     */
    public Decimal(final OrderedRandomizer<Double> rand) {
        super(new Decimal.Addition(), new Decimal.Mult(), rand);
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

    /**
     * Double multiplication implementation.
     * @author Hamdi Douss (douss.hamdi@gmail.com)
     * @version $Id$
     * @since 0.1
     */
    private static class Mult implements FieldMultiplication<Double> {

        @Override
        public Double multiply(final Double operand, final Double second) {
            return operand * second;
        }

        @Override
        public Double neutral() {
            return 1.;
        }

        @Override
        public Double inverse(final Double elt) {
            if (Double.valueOf(0).equals(elt)) {
                throw new IllegalArgumentException("Division by zero");
            }
            return 1. / elt;
        }
    }

    /**
     * Double addition implementation.
     * @author Hamdi Douss (douss.hamdi@gmail.com)
     * @version $Id$
     * @since 0.1
     */
    private static class Addition implements FieldAddition<Double> {

        @Override
        public Double add(final Double operand, final Double second) {
            return operand + second;
        }

        @Override
        public Double neutral() {
            return 0.;
        }

        @Override
        public Double inverse(final Double elt) {
            return -elt;
        }
    }
}
