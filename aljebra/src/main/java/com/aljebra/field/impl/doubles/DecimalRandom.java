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

import com.aljebra.field.OrderedRandom;
import java.util.Random;

/**
 * A Decimal randomizer. Implementation generates doubles within a minimum
 * and a maximum bounds.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class DecimalRandom implements OrderedRandom<Double> {

    /**
     * Minimum value to generate when randomizing a double.
     */
    private final double min;

    /**
     * Maximum value to generate when randomizing a double.
     */
    private final double max;

    /**
     * Randomizer.
     */
    private final transient Random rand;

    /**
     * Ctor.
     * @param min Minimum value to generate when randomizing a double
     * @param max Maximum value to generate when randomizing a double
     */
    public DecimalRandom(final double min, final double max) {
        this.min = min;
        this.max = max;
        this.rand = new Random();
    }

    @Override
    public Double between(final Double lower, final Double upper) {
        if (lower > upper) {
            throw new IllegalArgumentException(
                String.format(
                    "Could not get an element greater than %s and less than %s",
                    lower, upper
                )
            );
        }
        return this.rand.nextDouble() * (upper - lower) + lower;
    }

    @Override
    public Double greater(final Double lower) {
        return this.between(lower, this.max);
    }

    @Override
    public Double lower(final Double upper) {
        return this.between(this.min, upper);
    }

}
