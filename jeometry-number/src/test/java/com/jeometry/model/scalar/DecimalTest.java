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

import com.jeometry.model.algebra.scalar.Add;
import com.jeometry.model.algebra.scalar.Diff;
import com.jeometry.model.algebra.scalar.Division;
import com.jeometry.model.algebra.scalar.Multiplication;
import com.jeometry.model.algebra.scalar.Scalar;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link Decimal}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class DecimalTest {

    /**
     * {@link Decimal} field can calculate sum.
     */
    @Test
    public void calculatesSum() {
        final Scalar first = new Scalar.Default<Double>(1.);
        final Scalar second = new Scalar.Default<Double>(2.);
        MatcherAssert.assertThat(
            new Decimal().actual(new Add(first, second)), Matchers.equalTo(3.)
        );
    }

    /**
     * {@link Decimal} field can calculate difference.
     */
    @Test
    public void calculatesDiff() {
        final Scalar first = new Scalar.Default<Double>(1.);
        final Scalar second = new Scalar.Default<Double>(2.);
        MatcherAssert.assertThat(
            new Decimal().actual(new Diff(first, second)), Matchers.equalTo(-1.)
        );
    }

    /**
     * {@link Decimal} field can calculate multiplication.
     */
    @Test
    public void calculatesMult() {
        final Scalar first = new Scalar.Default<Double>(1.);
        final Scalar second = new Scalar.Default<Double>(2.);
        MatcherAssert.assertThat(
            new Decimal().actual(new Multiplication(first, second)),
            Matchers.equalTo(2.)
        );
    }

    /**
     * {@link Decimal} field can calculate division.
     */
    @Test
    public void calculatesDiv() {
        final Scalar first = new Scalar.Default<Double>(1.);
        final Scalar second = new Scalar.Default<Double>(2.);
        MatcherAssert.assertThat(
            new Decimal().actual(new Division(first, second)),
            Matchers.equalTo(0.5)
        );
    }
}
