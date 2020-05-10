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
package com.aljebra.field.impl.doubles;

import com.aljebra.field.mock.MkOrderedRandom;
import com.aljebra.scalar.Scalar;
import com.aljebra.scalar.Scalar.Default;
import com.aljebra.scalar.mock.SpyScalar;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.hamcrest.MatcherAssert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@link Decimal}.
 * @since 0.1
 */
public final class DecimalTest {

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * Decimal can generate a different scalar.
     */
    @Test
    public void returnsDifferentScalar() {
        final double first = new Random().nextDouble();
        final MkOrderedRandom<Double> rand = new MkOrderedRandom<>(
            Optional.of(Arrays.asList(first, Math.random()))
        );
        final Decimal field = new Decimal(rand);
        final Scalar<Double> scalar = new Scalar.Default<>(first);
        MatcherAssert.assertThat(
            "Generated scalar should be different than the passed one",
            !field.equals(scalar, field.other(scalar))
        );
    }

    /**
     * Decimal delegates actual value calculations
     * for other scalar implementation.
     */
    @Test
    public void delegatesActualValueForScalars() {
        final Decimal field = new Decimal();
        final SpyScalar<Double> scalar = new SpyScalar<>(new Scalar.Default<>(0.));
        field.actual(scalar);
        MatcherAssert.assertThat(
            "Expecting call to value method with field as parameter",
            scalar.field().isPresent() && scalar.field().get().equals(field)
        );
    }

    /**
     * Decimal delegates randomization to ordered randomizer.
     */
    @Test
    public void delegatesRandomization() {
        final MkOrderedRandom<Double> rand = new MkOrderedRandom<>();
        final Decimal field = new Decimal(rand);
        final Default<Double> zero = new Scalar.Default<>(0.);
        field.between(zero, zero);
        field.greater(zero);
        field.lower(zero);
        final Optional<List<Double>> params = rand.between();
        MatcherAssert.assertThat(
            "Expecting call to between method with zero as parameters",
            params.isPresent() && params.get().get(0).equals(0.) && params.get().get(1).equals(0.)
        );
        MatcherAssert.assertThat(
            "Expecting call to greater method with zero as parameter",
            rand.greater().isPresent() && rand.greater().get().equals(0.)
        );
        MatcherAssert.assertThat(
            "Expecting call to lower method with zero as parameter",
            rand.lower().isPresent() && rand.lower().get().equals(0.)
        );
    }
}
