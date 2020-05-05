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
import com.aljebra.metric.MockProduct;
import com.aljebra.scalar.Random;
import com.aljebra.vector.FixedVector;
import java.util.Arrays;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link Right}.
 * @since 0.1
 */
public final class RightTest {

    /**
     * {@link Right} resolves to an right angle.
     */
    @Test
    public void resolvesRightAngle() {
        final double error = 1.e-6;
        final InnerProduct<Object> product = new MockProduct<>();
        MatcherAssert.assertThat(
            new Right().resolve(product).doubleValue(),
            Matchers.closeTo(Math.PI / 2, error)
        );
        MatcherAssert.assertThat(
            new Right().right(product), Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new Right().flat(product), Matchers.is(false)
        );
    }

    /**
     * {@link Right} respects equal.
     */
    @Test
    public void respectsEqual() {
        MatcherAssert.assertThat(
            new Right(), Matchers.equalTo(new Right())
        );
        MatcherAssert.assertThat(
            new Right().hashCode(),
            Matchers.equalTo(new Right().hashCode())
        );
        MatcherAssert.assertThat(
            new Right(),
            Matchers.not(
                Matchers.equalTo(
                    new VectsDegrees(
                        new FixedVector<>(Arrays.asList(new Random<>())),
                        new FixedVector<>(Arrays.asList(new Random<>()))
                    )
                )
            )
        );
    }
}
