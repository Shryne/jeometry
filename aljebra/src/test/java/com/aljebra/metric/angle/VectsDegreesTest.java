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
import com.aljebra.vector.Vect;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link VectsDegrees}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class VectsDegreesTest {

    /**
     * {@link VectsDegrees} rely on InnerProduct to calculate
     * angle between vectors.
     */
    @Test
    public void delegatesToInnerProduct() {
        final Vect first = Mockito.mock(Vect.class);
        final Vect second = Mockito.mock(Vect.class);
        final InnerProduct pdt = Mockito.mock(InnerProduct.class);
        Mockito.when(
            pdt.angle(Mockito.any(), Mockito.any())
        ).thenReturn(Mockito.mock(Degrees.class));
        new VectsDegrees(first, second).resolve(pdt);
        Mockito.verify(pdt).angle(first, second);
    }

    /**
     * {@link VectsDegrees} respects equals and hashcode on vectors.
     */
    @Test
    public void respectsEqual() {
        final Vect first = Mockito.mock(Vect.class);
        final Vect second = Mockito.mock(Vect.class);
        MatcherAssert.assertThat(
            new VectsDegrees(first, second),
            Matchers.equalTo(new VectsDegrees(first, second))
        );
        MatcherAssert.assertThat(
            new VectsDegrees(first, second).hashCode(),
            Matchers.equalTo(new VectsDegrees(first, second).hashCode())
        );
        MatcherAssert.assertThat(
            new VectsDegrees(first, second),
            Matchers.not(
                Matchers.equalTo(new VectsDegrees(second, first))
            )
        );
    }
}
