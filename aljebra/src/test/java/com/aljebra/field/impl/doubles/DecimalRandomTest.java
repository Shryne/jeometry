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

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@link DecimalRandom}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class DecimalRandomTest {

    /**
     * Minimal double bound.
     */
    private static final double MIN = -1000;

    /**
     * Maximal double bound.
     */
    private static final double MAX = 1000;

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * DecimalRandomizer respects upper bound.
     */
    @Test
    public void returnsLowerDouble() {
        final DecimalRandom rand = new DecimalRandom(
            DecimalRandomTest.MIN, DecimalRandomTest.MAX
        );
        final Double value = DecimalRandomTest.random();
        MatcherAssert.assertThat(
            rand.lower(value), Matchers.lessThanOrEqualTo(value)
        );
    }

    /**
     * DecimalRandomizer respects lower bound.
     */
    @Test
    public void returnsGreaterDouble() {
        final DecimalRandom rand = new DecimalRandom(
            DecimalRandomTest.MIN, DecimalRandomTest.MAX
        );
        final Double value = DecimalRandomTest.random();
        MatcherAssert.assertThat(
            rand.greater(value), Matchers.greaterThanOrEqualTo(value)
        );
    }

    /**
     * DecimalRandomizer generates a double between two doubles.
     */
    @Test
    public void returnsBetweenDouble() {
        final DecimalRandom rand = new DecimalRandom(
            DecimalRandomTest.MIN, DecimalRandomTest.MAX
        );
        final Double value = DecimalRandomTest.random();
        MatcherAssert.assertThat(
            rand.between(value, value + 1),
            Matchers.allOf(
                Matchers.greaterThanOrEqualTo(value),
                Matchers.lessThanOrEqualTo(value + 1)
            )
        );
    }

    /**
     * DecimalRandomizer generates a double between two equal doubles.
     */
    @Test
    public void returnsBetweenEqualDouble() {
        final DecimalRandom rand = new DecimalRandom(
            DecimalRandomTest.MIN, DecimalRandomTest.MAX
        );
        final Double value = DecimalRandomTest.random();
        MatcherAssert.assertThat(
            rand.between(value, value),
            Matchers.equalTo(value)
        );
    }

    /**
     * DecimalRandomizer throws an exception if between first argument
     * is greater than between second argument.
     */
    @Test
    public void errorsWhenLowerGreaterThanUpper() {
        this.thrown.expect(IllegalArgumentException.class);
        final DecimalRandom rand = new DecimalRandom(
            DecimalRandomTest.MIN, DecimalRandomTest.MAX
        );
        final Double value = DecimalRandomTest.random();
        rand.between(value, value - 1);
    }

    /**
     * Generates a random double between DecimalRandomizerTest bounds.
     * @return A random double
     */
    private static Double random() {
        return Math.random()
            * (DecimalRandomTest.MAX - DecimalRandomTest.MIN)
            + DecimalRandomTest.MIN;
    }
}
