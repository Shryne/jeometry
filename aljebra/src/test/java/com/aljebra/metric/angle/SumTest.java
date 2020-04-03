/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2020, Hamdi Douss
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
package com.aljebra.metric.angle;

import com.aljebra.metric.InnerProduct;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link Sum}.
 * @since 0.1
 */
public final class SumTest {

    /**
     * {@link Sum} resolves to angles sum.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void resolvesAngleSum() {
        final Degrees first = new Degrees.Default(Math.random());
        final Degrees second = new Degrees.Default(Math.random());
        final Degrees third = new Degrees.Default(Math.random());
        final InnerProduct<Object> pdt = Mockito.mock(InnerProduct.class);
        final double error = 1.e-6;
        MatcherAssert.assertThat(
            new Sum(first, second, third).resolve(pdt).doubleValue(),
            Matchers.closeTo(
                first.resolve(pdt).doubleValue()
                    + second.resolve(pdt).doubleValue()
                    + third.resolve(pdt).doubleValue(),
                error
            )
        );
    }

    /**
     * {@link Sum} respects equals with disregard to operands order
     * (commutative).
     */
    @Test
    public void equalsWhenOperandOrderChanges() {
        final Degrees first = Mockito.mock(Degrees.class);
        final Degrees second = Mockito.mock(Degrees.class);
        final Degrees third = Mockito.mock(Degrees.class);
        MatcherAssert.assertThat(
            new Sum(first, second, third),
            Matchers.allOf(
                Matchers.equalTo(new Sum(first, third, second)),
                Matchers.equalTo(new Sum(second, third, first)),
                Matchers.equalTo(new Sum(second, first, third)),
                Matchers.equalTo(new Sum(third, second, first)),
                Matchers.equalTo(new Sum(third, first, second))
            )
        );
    }
}
