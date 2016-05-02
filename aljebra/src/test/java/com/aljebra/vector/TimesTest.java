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
package com.aljebra.vector;

import com.aljebra.scalar.Multiplication;
import com.aljebra.scalar.Scalar;
import java.util.Random;
import java.util.stream.IntStream;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link Times}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class TimesTest {

    /**
     * Max scalar array length to generate(mock).
     */
    private static final int COORDS_LENGTH = 10;

    /**
     * {@link Times} coordinates equals to the field multiplication
     * of vector coordinates by the scalar.
     */
    @Test
    public void coordsEqualCoordsMult() {
        final Vect vecta = Mockito.mock(Vect.class);
        final Scalar factor = TimesTest.scalars(1)[0];
        final Scalar[] acoords = TimesTest.scalars();
        Mockito.when(vecta.coords()).thenReturn(acoords);
        final Matcher<Scalar[]> expected = TimesTest.matchers(
            IntStream.range(0, acoords.length)
            .mapToObj(i -> new Multiplication(acoords[i], factor))
            .toArray(Scalar[]::new)
        );
        MatcherAssert.assertThat(new Times(vecta, factor).coords(), expected);
    }

    /**
     * Mocks an array of {@link Scalar} with a random length.
     * @return An array of scalars.
     */
    private static Scalar[] scalars() {
        return TimesTest.scalars(new Random().nextInt(TimesTest.COORDS_LENGTH));
    }

    /**
     * Mocks an array of {@link Scalar} with the given length.
     * @param length Array length to generate
     * @return An array of scalars.
     */
    private static Scalar[] scalars(final int length) {
        final Scalar[] result = new Scalar[length];
        for (int idx = 0; idx < result.length; ++idx) {
            result[idx] = Mockito.mock(Scalar.class);
        }
        return result;
    }

    /**
     * Build an equality matcher for elements of a scalar array.
     * @param scalars Scalar array
     * @return A hamcrest equality matcher
     */
    @SuppressWarnings("unchecked")
    private static Matcher<Scalar[]> matchers(final Scalar... scalars) {
        final Matcher<Scalar>[] matchers = new Matcher[scalars.length];
        for (int idx = 0; idx < scalars.length; ++idx) {
            matchers[idx] = Matchers.equalTo(scalars[idx]);
        }
        return Matchers.array(matchers);
    }
}
