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
package com.aljebra.vector;

import com.aljebra.field.impl.doubles.Decimal;
import com.aljebra.scalar.Different;
import com.aljebra.scalar.Scalar;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link VectEquals}.
 * @since 0.1
 */
public final class VectEqualsTest {

    /**
     * Max scalar array length to generate(mock).
     */
    private static final int COORDS_LENGTH = 10;

    /**
     * {@link VectEquals} resolves to true if vectors are equal.
     */
    @Test
    public void resolvesToTrueWhenEqual() {
        final List<Scalar<Double>> coords = VectEqualsTest.scalars();
        MatcherAssert.assertThat(
            new VectEquals<>(
                new FixedVector<>(coords), new FixedVector<>(coords)
            ).resolve(new Decimal()),
            Matchers.is(true)
        );
    }

    /**
     * {@link VectEquals} resolves to false if vectors are not equal.
     */
    @Test
    public void resolvesToFalseWhenNotEqual() {
        final List<Scalar<Double>> coords = VectEqualsTest.scalars();
        final List<Scalar<Double>> second = new ArrayList<>(coords);
        second.remove(0);
        second.add(0, new Different<>(coords.get(0)));
        MatcherAssert.assertThat(
            new VectEquals<>(
                new FixedVector<>(coords), new FixedVector<>(second)
            ).resolve(new Decimal()),
            Matchers.is(false)
        );
    }

    /**
     * {@link VectEquals} resolves to false if vectors
     * do not have the same dimension.
     */
    @Test
    public void resolvesToFalseWhenNotSameDimension() {
        final List<Scalar<Double>> coords = VectEqualsTest.scalars();
        final List<Scalar<Double>> second = new ArrayList<>(coords);
        second.remove(second.size() - 1);
        MatcherAssert.assertThat(
            new VectEquals<>(
                new FixedVector<>(coords), new FixedVector<>(second)
            ).resolve(new Decimal()),
            Matchers.is(false)
        );
    }

    /**
     * Mocks an array of {@link Scalar} with a random length.
     * @return An array of scalars.
     */
    private static List<Scalar<Double>> scalars() {
        final int lgth = 2 + new Random().nextInt(VectEqualsTest.COORDS_LENGTH);
        final List<Scalar<Double>> result = new ArrayList<>(lgth);
        for (int idx = 0; idx < lgth; ++idx) {
            result.add(VectEqualsTest.random());
        }
        return result;
    }

    /**
     * Builds a random scalar.
     * @return A random scalar.
     */
    private static Scalar<Double> random() {
        return new com.aljebra.scalar.Random<>();
    }
}
