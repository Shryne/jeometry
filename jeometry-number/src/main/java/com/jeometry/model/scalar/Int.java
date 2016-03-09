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
package com.jeometry.model.scalar;

import com.jeometry.model.algebra.field.Field;
import com.jeometry.model.algebra.scalar.Scalar;

/**
 * A {@link Field} implementation based on integers.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Int implements Field {

    /**
     * Minimum value to generate when randomizing a scalar.
     */
    private static final int MINBOUND = -1000;
    /**
     * Maximum value to generate when randomizing a scalar.
     */
    private static final int MAXBOUND = 1000;
    
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
    public Int() {
        this(new java.util.Random());
    }

    /**
     * Constructor.
     * @param rand Randomizer
     */
    public Int(java.util.Random rand) {
        super();
        this.rand = rand;
    }

    @Override
    public Scalar random() {
        return new Scalar.Default<Integer>(
            this.rand.nextInt()
            * (Int.MAXBOUND - Int.MINBOUND)
            + Int.MINBOUND
        );
    }

    @Override
    public Scalar other(Scalar x) {
        Scalar result = this.random();
        while (this.equals(result, x)) {
            result = this.random();
        }
        return result;
    }

    @Override
    public Scalar addIdentity() {
        return new Scalar.Default<Integer>(new Integer(0));
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Scalar x, Scalar y) {
        if (Scalar.Default.class.isAssignableFrom(x.getClass())
            && Scalar.Default.class.isAssignableFrom(y.getClass())) {
            return Math.abs(
                ((Scalar.Default<Integer>)x).value()
                - ((Scalar.Default<Integer>)y).value()
            ) < Int.TOLERANCE;
        }
        return false;
    }

}
