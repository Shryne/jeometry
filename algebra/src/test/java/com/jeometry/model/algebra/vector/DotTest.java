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
package com.jeometry.model.algebra.vector;

import com.jeometry.model.algebra.scalar.Add;
import com.jeometry.model.algebra.scalar.Multiplication;
import com.jeometry.model.algebra.scalar.Scalar;
import java.util.Random;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link Dot}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class DotTest {
    /**
     * Max scalar array length to generate(mock).
     */
    private static final int COORDS_LENGTH = 10;

    /**
     * {@link Dot} calculates expected value.
     * to order of operands.
     */
    @Test
    public void dotReturnsGoodValue() {
        final Vect vecta = Mockito.mock(Vect.class);
        final Vect vectb = Mockito.mock(Vect.class);
        final Scalar[] coords = DotTest.scalars();
        Mockito.when(vectb.coords()).thenReturn(coords);
        Mockito.when(vecta.coords()).thenReturn(coords);
        final Scalar[] multis = new Scalar[coords.length];
        for (int idx = 0; idx < coords.length; ++idx) {
            multis[idx] = new Multiplication(coords[idx], coords[idx]);
        }
        MatcherAssert.assertThat(
            new Dot(vecta, vectb).value(),
            Matchers.equalTo(new Add(multis))
        );
    }

    /**
     * Mocks an array of {@link Scalar}.
     * @return An array of scalars.
     */
    private static Scalar[] scalars() {
        final int length = new Random().nextInt(DotTest.COORDS_LENGTH);
        final Scalar[] result = new Scalar[length];
        for (int idx = 0; idx < result.length; ++idx) {
            result[idx] = Mockito.mock(Scalar.class);
        }
        return result;
    }
}
