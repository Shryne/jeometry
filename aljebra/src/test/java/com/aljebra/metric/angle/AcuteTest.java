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

import com.aljebra.metric.MockProduct;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link Acute}.
 * @since 0.1
 */
public final class AcuteTest {

    /**
     * {@link Acute} resolves to an acute angle.
     */
    @Test
    public void resolvesAcuteAngle() {
        final double angle = new Acute().resolve(new MockProduct<Object>()).doubleValue();
        MatcherAssert.assertThat(angle, Matchers.greaterThanOrEqualTo(0.));
        MatcherAssert.assertThat(
            angle, Matchers.lessThanOrEqualTo(Math.PI / 2)
        );
    }

    /**
     * {@link Acute} to string prints the generated angle.
     */
    @Test
    public void toStringPrintsAngle() {
        final Acute acute = new Acute();
        final Double angle = acute.resolve(new MockProduct<Object>()).doubleValue();
        final int precision = 6;
        MatcherAssert.assertThat(
            acute.toString(),
            Matchers.containsString(
                Double.toString(angle * 2 / Math.PI).substring(0, precision)
            )
        );
    }
}
