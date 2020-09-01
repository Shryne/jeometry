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

import com.aljebra.metric.MkProduct;
import com.aljebra.scalar.Random;
import com.aljebra.vector.FixedVector;
import com.aljebra.vector.Vect;
import java.util.List;
import java.util.Optional;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link VectsDegrees}.
 * @since 0.1
 */
public final class VectsDegreesTest {

    /**
     * {@link VectsDegrees} rely on InnerProduct to calculate
     * angle between vectors.
     */
    @Test
    public void delegatesToInnerProduct() {
        final Vect<Object> first = new FixedVector<>(new Random<>());
        final Vect<Object> second = new FixedVector<>(new Random<>());
        final MkProduct<Object> pdt = new MkProduct<>();
        new VectsDegrees<>(first, second).resolve(pdt);
        final Optional<List<Vect<Object>>> params = pdt.angle();
        MatcherAssert.assertThat(
            "Expecting call to angle method with vectors as parameters",
            params.isPresent() && params.get().get(0).equals(first)
                && params.get().get(1).equals(second)
        );
    }

    /**
     * {@link VectsDegrees} respects equals and hashcode on vectors.
     */
    @Test
    public void respectsEqual() {
        final Vect<Object> first = new FixedVector<>(new Random<>());
        final Vect<Object> second = new FixedVector<>(new Random<>());
        MatcherAssert.assertThat(
            new VectsDegrees<>(first, second),
            Matchers.equalTo(new VectsDegrees<>(first, second))
        );
        MatcherAssert.assertThat(
            new VectsDegrees<>(first, second).hashCode(),
            Matchers.equalTo(new VectsDegrees<>(first, second).hashCode())
        );
        MatcherAssert.assertThat(
            new VectsDegrees<>(first, second),
            Matchers.not(
                Matchers.equalTo(new VectsDegrees<>(second, first))
            )
        );
    }

    /**
     * {@link VectsDegrees} toString prints underlying vectors.
     */
    @Test
    public void toStringPrintsDegrees() {
        final Vect<Object> first = new FixedVector<>(new Random<>());
        final Vect<Object> second = new FixedVector<>(new Random<>());
        MatcherAssert.assertThat(
            new VectsDegrees<>(first, second).toString(),
            Matchers.allOf(
                Matchers.containsString(first.toString()),
                Matchers.containsString(second.toString())
            )
        );
    }
}
