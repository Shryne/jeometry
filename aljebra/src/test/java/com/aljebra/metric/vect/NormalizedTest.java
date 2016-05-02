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
package com.aljebra.metric.vect;

import com.aljebra.field.Field;
import com.aljebra.field.impl.doubles.Decimal;
import com.aljebra.field.impl.doubles.Dot;
import com.aljebra.metric.InnerProduct;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.Vect;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link Normalized}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class NormalizedTest {

    /**
     * {@link Normalized} gives a normalized vector.
     */
    @Test
    public void normalizesVector() {
        final int dim = 6;
        final Vect first = Mockito.mock(Vect.class);
        final Vect second = Mockito.mock(Vect.class);
        Mockito.when(first.coords()).thenReturn(NormalizedTest.scalars(dim));
        Mockito.when(second.coords()).thenReturn(NormalizedTest.scalars(dim));
        final InnerProduct pdt = new Dot();
        final Field<Double> dec = new Decimal();
        final double error = 1.e-6;
        MatcherAssert.assertThat(
            dec.actual(pdt.norm(new Normalized(first))),
            Matchers.closeTo(1., error)
        );
        MatcherAssert.assertThat(
            dec.actual(pdt.norm(new Normalized(second))),
            Matchers.closeTo(1., error)
        );
    }

    /**
     * Mocks an array of {@link Scalar} with a given length.
     * @param length Array length
     * @return An array of scalars
     */
    private static Scalar[] scalars(final int length) {
        final Scalar[] result = new Scalar[length];
        for (int idx = 0; idx < result.length; ++idx) {
            result[idx] = NormalizedTest.scalar(Math.random());
        }
        return result;
    }

    /**
     * Returns a default scalar of the double.
     * @param num A double
     * @return A scalar
     */
    private static Scalar scalar(final double num) {
        return new Scalar.Default<Double>(num);
    }
}
