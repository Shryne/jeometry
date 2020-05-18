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
package com.aljebra.scalar;

import com.aljebra.field.impl.doubles.Decimal;
import com.aljebra.field.mock.MkField;
import com.aljebra.field.mock.MkMultiplication;
import com.aljebra.field.mock.SpyField;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@link Division}.
 * @since 0.1
 */
public final class DivisionTest {

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * {@link Division} respects equals and hashcode on divisor and dividend.
     */
    @Test
    public void respectsEqualAndHashcode() {
        MatcherAssert.assertThat(
            new Division<>(
                new Scalar.Default<Double>(0.), new Scalar.Default<Double>(1.)
            ),
            Matchers.equalTo(
                new Division<>(
                    new Scalar.Default<Double>(0.),
                    new Scalar.Default<Double>(1.)
                )
            )
        );
        final String divisor = "test";
        final String dividend = "test2";
        MatcherAssert.assertThat(
            new Division<>(
                new Scalar.Default<String>(divisor),
                new Scalar.Default<String>(dividend)
            ),
            Matchers.not(
                Matchers.equalTo(
                    new Division<>(
                        new Scalar.Default<String>(dividend),
                        new Scalar.Default<String>(dividend)
                    )
                )
            )
        );
        MatcherAssert.assertThat(
            new Division<>(
                new Scalar.Default<String>(divisor),
                new Scalar.Default<String>(dividend)
            ).hashCode(),
            Matchers.equalTo(
                new Division<>(
                    new Scalar.Default<String>(divisor),
                    new Scalar.Default<String>(dividend)
                ).hashCode()
            )
        );
    }

    /**
     * {@link Division} relies on field multiplication
     * to calculate actual value.
     */
    @Test
    public void divisionDelegatesToFieldMultiplication() {
        final MkMultiplication<Object> mult = new MkMultiplication<>(new Object());
        final SpyField<Object> field = new SpyField<>(
            new MkField<Object>(new Object(), mult)
        );
        new Division<>(
            new Scalar.Default<>(new Object()), new Scalar.Default<>(new Object())
        ).value(field);
        MatcherAssert.assertThat(
            field.calls().multiplicationed(), Matchers.is(true)
        );
        MatcherAssert.assertThat(
            mult.inverted(), Matchers.is(true)
        );
        MatcherAssert.assertThat(
            mult.multiplied(), Matchers.greaterThan(0)
        );
    }

    /**
     * {@link Division} returns dividend and divisor.
     */
    @Test
    public void givesAccessToDividendAndDivisor() {
        final Scalar<Object> first = new Scalar.Default<>(new Object());
        final Scalar<Object> second = new Scalar.Default<>(new Object());
        final Division<Object> division = new Division<>(first, second);
        MatcherAssert.assertThat(
            division.first(), Matchers.equalTo(first)
        );
        MatcherAssert.assertThat(
            division.second(), Matchers.equalTo(second)
        );
    }

    /**
     * {@link Division} by addition identity throws exception.
     */
    @Test
    public void throwsExceptionWhenDivideByZero() {
        this.thrown.expect(IllegalArgumentException.class);
        final Scalar<Double> first = new Scalar.Default<>(Math.random());
        final Scalar<Double> second = new AddIdentity<>();
        new Division<>(first, second).value(new Decimal());
    }
}
