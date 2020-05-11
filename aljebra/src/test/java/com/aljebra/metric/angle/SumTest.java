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
import com.aljebra.metric.MkProduct;
import java.util.Arrays;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link Sum}.
 * @since 0.1
 */
public final class SumTest {

    /**
     * {@link Sum} resolves to angles sum.
     */
    @Test
    public void resolvesAngleSum() {
        final Degrees<Object> first = new Degrees.Default<>(Math.random());
        final Degrees<Object> second = new Degrees.Default<>(Math.random());
        final Degrees<Object> third = new Degrees.Default<>(Math.random());
        final InnerProduct<Object> pdt = new MkProduct<>();
        final double error = 1.e-6;
        MatcherAssert.assertThat(
            new Sum<>(Arrays.asList(first, second, third)).resolve(pdt).doubleValue(),
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
        final Degrees<Object> first = new Degrees.Default<>(Math.random());
        final Degrees<Object> second = new Degrees.Default<>(Math.random());
        final Degrees<Object> third = new Degrees.Default<>(Math.random());
        MatcherAssert.assertThat(
            new Sum<>(Arrays.asList(first, second, third)),
            Matchers.allOf(
                Matchers.equalTo(new Sum<>(Arrays.asList(first, third, second))),
                Matchers.equalTo(new Sum<>(Arrays.asList(second, third, first))),
                Matchers.equalTo(new Sum<>(Arrays.asList(second, first, third))),
                Matchers.equalTo(new Sum<>(Arrays.asList(third, second, first))),
                Matchers.equalTo(new Sum<>(Arrays.asList(third, first, second)))
            )
        );
    }

    /**
     * {@link Sum} toString prints underlying angles.
     */
    @Test
    public void toStringPrintsDegrees() {
        final Degrees<Object> first = new Degrees.Default<>(Math.random());
        final Degrees<Object> second = new Degrees.Default<>(Math.random());
        MatcherAssert.assertThat(
            new Sum<>(Arrays.asList(first, second)).toString(),
            Matchers.allOf(
                Matchers.containsString(first.toString()),
                Matchers.containsString(second.toString())
            )
        );
    }
}
