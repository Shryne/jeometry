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
 * Tests for {@link DblMult}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class DblMultTest {

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * DblMult can calculate multiplication.
     */
    @Test
    public void calculatesMultiplication() {
        final double first = Math.random();
        final double second = Math.random();
        MatcherAssert.assertThat(
            new DblMult().multiply(first, second),
            Matchers.equalTo(first * second)
        );
    }

    /**
     * DblMult can calculate multiplication inverse.
     */
    @Test
    public void calculatesMultiplicationInverse() {
        final double epsilon = 1.e-6;
        final double first = Math.random() + epsilon;
        MatcherAssert.assertThat(
            new DblMult().inverse(first),
            Matchers.closeTo(1 / first, epsilon)
        );
    }

    /**
     * DblMult throws exception when trying to calculate
     * multiplication inverse of zero.
     */
    @Test
    public void errorsWhenZeroMultInverse() {
        this.thrown.expect(IllegalArgumentException.class);
        new DblMult().inverse(0.);
    }

}
