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

import com.aljebra.field.OrderedRandomizer;
import com.aljebra.scalar.Add;
import com.aljebra.scalar.Multiplication;
import com.aljebra.scalar.Scalar;
import com.aljebra.scalar.Scalar.Default;
import java.util.Random;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link Decimal}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class DecimalTest {

    /**
     * Decimal returns a different scalar.
     */
    @Test
    public void returnsDifferentScalar() {
        final Decimal field = new Decimal();
        final Scalar scalar = new Scalar.Default<Double>(
            new Random().nextDouble()
        );
        MatcherAssert.assertThat(
            "Generated scalar should be different than the passed one",
            !field.equals(scalar, field.other(scalar))
        );
    }

    /**
     * Decimal calculates actual value for default scalar implementation.
     */
    @Test
    public void calculatesActualValueForDefault() {
        final Decimal field = new Decimal();
        final Scalar.Default<?> scalar = Mockito.mock(Scalar.Default.class);
        MatcherAssert.assertThat(
            field.actual(scalar), Matchers.equalTo(scalar.value())
        );
        Mockito.verify(scalar, Mockito.never()).value(Mockito.any());
    }

    /**
     * Decimal delegates actual value calculations
     * for other scalar implementation.
     */
    @Test
    public void delegatesActualValueForScalars() {
        final Decimal field = new Decimal();
        final Scalar scalar = Mockito.mock(Scalar.class);
        field.actual(scalar);
        Mockito.verify(scalar).value(Mockito.eq(field));
    }

    /**
     * Decimal delegates randomization to ordered randomizer.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void delegatesRandomization() {
        final OrderedRandomizer<Double> rand = Mockito.mock(
            OrderedRandomizer.class
        );
        final Decimal field = new Decimal(rand);
        final Default<Double> zero = new Scalar.Default<Double>(0.);
        field.between(zero, zero);
        field.greater(zero);
        field.lower(zero);
        Mockito.verify(rand).between(0., 0.);
        Mockito.verify(rand).greater(0.);
        Mockito.verify(rand).lower(0.);
    }

    /**
     * Decimal can calculate multiplication.
     */
    @Test
    public void calculatesMultiplication() {
        final Decimal field = new Decimal();
        final double first = Math.random();
        final double second = Math.random();
        MatcherAssert.assertThat(
            field.actual(
                new Multiplication(
                    new Scalar.Default<Double>(first),
                    new Scalar.Default<Double>(second)
                )
            ),
            Matchers.equalTo(first * second)
        );
    }

    /**
     * Decimal can calculate addition.
     */
    @Test
    public void calculatesAddition() {
        final Decimal field = new Decimal();
        final double first = Math.random();
        final double second = Math.random();
        MatcherAssert.assertThat(
            field.actual(
                new Add(
                    new Scalar.Default<Double>(first),
                    new Scalar.Default<Double>(second)
                )
            ),
            Matchers.equalTo(first + second)
        );
    }
}
