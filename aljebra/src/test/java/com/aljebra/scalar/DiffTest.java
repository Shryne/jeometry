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

import com.aljebra.field.Field;
import com.aljebra.field.MkAddition;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link Diff}.
 * @since 0.1
 */
public final class DiffTest {
    /**
     * {@link Diff} respects equals and hashcode on operands.
     */
    @Test
    public void respectsEqual() {
        MatcherAssert.assertThat(
            new Diff<>(
                new Scalar.Default<Double>(0.), new Scalar.Default<Double>(1.)
            ),
            Matchers.equalTo(
                new Diff<>(
                    new Scalar.Default<Double>(0.),
                    new Scalar.Default<Double>(1.)
                )
            )
        );
        final String minuend = "test";
        final String subtrahend = "test2";
        MatcherAssert.assertThat(
            new Diff<>(
                new Scalar.Default<String>(minuend),
                new Scalar.Default<String>(subtrahend)
            ),
            Matchers.equalTo(
                new Diff<>(
                    new Scalar.Default<String>(minuend),
                    new Scalar.Default<String>(subtrahend)
                )
            )
        );
        MatcherAssert.assertThat(
            new Diff<>(
                new Scalar.Default<String>(minuend),
                new Scalar.Default<String>(subtrahend)
            ),
            Matchers.not(
                Matchers.equalTo(
                    new Diff<>(
                        new Scalar.Default<String>(minuend),
                        new Scalar.Default<String>(minuend)
                    )
                )
            )
        );
        MatcherAssert.assertThat(
            new Diff<>(
                new Scalar.Default<String>(minuend),
                new Scalar.Default<String>(subtrahend)
            ).hashCode(),
            Matchers.equalTo(
                new Diff<>(
                    new Scalar.Default<String>(minuend),
                    new Scalar.Default<String>(subtrahend)
                ).hashCode()
            )
        );
    }

    /**
     * {@link Diff} relies on field addition to calculate actual value.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void diffDelegatesToFieldAddition() {
        final Scalar<Object> first = new Scalar.Default<>(new Object());
        final Scalar<Object> second = new Scalar.Default<>(new Object());
        final Field<Object> field = Mockito.mock(Field.class);
        final MkAddition<Object> add = new MkAddition<>(new Object());
        Mockito.when(field.addition()).thenReturn(add);
        new Diff<>(first, second).value(field);
        Mockito.verify(field).addition();
        MatcherAssert.assertThat(
            add.inverted(), Matchers.is(true)
        );
        MatcherAssert.assertThat(
            add.added(), Matchers.greaterThan(0)
        );
    }

    /**
     * {@link Diff} returns minuend and subtrahend.
     */
    @Test
    public void givesAccessToMinuendAndSubtrahend() {
        final Scalar<Object> first = new Scalar.Default<>(new Object());
        final Scalar<Object> second = new Scalar.Default<>(new Object());
        final Diff<Object> difference = new Diff<>(first, second);
        MatcherAssert.assertThat(
            difference.first(), Matchers.equalTo(first)
        );
        MatcherAssert.assertThat(
            difference.second(), Matchers.equalTo(second)
        );
    }
}
