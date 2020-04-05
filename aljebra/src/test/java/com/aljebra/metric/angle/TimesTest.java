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
 * Tests for {@link Times}.
 * @since 0.1
 */
public final class TimesTest {

    /**
     * {@link Times} resolves to angle times a number.
     */
    @Test
    public void resolvesAngleTimes() {
        final Degrees first = new Degrees.Default(Math.random());
        final Degrees second = new Degrees.Default(Math.random());
        final Degrees third = new Degrees.Default(Math.random());
        @SuppressWarnings("unchecked")
        final InnerProduct<Object> pdt = Mockito.mock(InnerProduct.class);
        final double factor = Math.random();
        final double error = 1.e-6;
        MatcherAssert.assertThat(
            new Times(first, factor).resolve(pdt).doubleValue(),
            Matchers.closeTo(first.resolve(pdt).doubleValue() * factor, error)
        );
        MatcherAssert.assertThat(
            new Times(second, factor).resolve(pdt).doubleValue(),
            Matchers.closeTo(second.resolve(pdt).doubleValue() * factor, error)
        );
        MatcherAssert.assertThat(
            new Times(third, factor).resolve(pdt).doubleValue(),
            Matchers.closeTo(third.resolve(pdt).doubleValue() * factor, error)
        );
    }

    /**
     * {@link Times} toString prints underlying degrees and multiple.
     */
    @Test
    public void toStringPrintsDegrees() {
        final Degrees deg = new Degrees.Default(Math.random());
        final Number multiple = Math.random();
        MatcherAssert.assertThat(
            new Times(deg, multiple).toString(),
            Matchers.allOf(
                Matchers.containsString(deg.toString()),
                Matchers.containsString(multiple.toString())
            )
        );
    }

    /**
     * {@link Times} respects equality with the same underlying degrees.
     */
    @Test
    public void equalsRespectsDegrees() {
        final Degrees deg = new Degrees.Default(Math.random());
        final Number multiple = Math.random();
        MatcherAssert.assertThat(
            new Times(deg, multiple),
            Matchers.equalTo(new Times(deg, multiple))
        );
    }

    /**
     * {@link Times} respects hashcode with the same underlying degrees.
     */
    @Test
    public void hashCodeRespectsDegrees() {
        final Degrees deg = new Degrees.Default(Math.random());
        final Number multiple = Math.random();
        MatcherAssert.assertThat(
            new Times(deg, multiple).hashCode(),
            Matchers.equalTo(new Times(deg, multiple).hashCode())
        );
    }
}
