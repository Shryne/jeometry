/**
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
 * Tests for {@link Complementary}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class ComplementaryTest {

    /**
     * {@link Complementary} resolves to a complementary angle.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void resolvesComplementaryAngle() {
        final Degrees<Double> first = new Degrees.Default<Double>(Math.random());
        final Degrees<Double> second = new Degrees.Default<Double>(Math.random());
        final Degrees<Double> third = new Degrees.Default<Double>(Math.random());
        final InnerProduct<Double> pdt = Mockito.mock(InnerProduct.class);
        final double error = 1.e-6;
        MatcherAssert.assertThat(
            new Complementary<Double>(first).resolve(pdt).doubleValue()
                + first.resolve(pdt).doubleValue(),
            Matchers.closeTo(Math.PI / 2, error)
        );
        MatcherAssert.assertThat(
            new Complementary<Double>(second).resolve(pdt).doubleValue()
                + second.resolve(pdt).doubleValue(),
            Matchers.closeTo(Math.PI / 2, error)
        );
        MatcherAssert.assertThat(
            new Complementary<Double>(third).resolve(pdt).doubleValue()
                + third.resolve(pdt).doubleValue(),
            Matchers.closeTo(Math.PI / 2, error)
        );
    }
}
