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

import com.aljebra.field.mock.SpyField;
import com.aljebra.field.mock.SpyMetricSpace;
import java.util.List;
import java.util.Optional;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@link Between}.
 * @since 0.1
 */
public final class BetweenTest {

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * {@link Between} relies on ordered field to calculate actual value.
     */
    @Test
    public void betweenDelegatesToOrderedField() {
        final Scalar<Object> first = new Scalar.Default<>(new Object());
        final Scalar<Object> second = new Scalar.Default<>(new Object());
        final SpyMetricSpace<Object> field = new SpyMetricSpace<>(new Object(), new Object());
        new Between<>(first, second).value(field);
        final Optional<List<Scalar<Object>>> params = field.between();
        MatcherAssert.assertThat(params.isPresent(), Matchers.is(true));
        MatcherAssert.assertThat(params.get().get(0), Matchers.is(first));
        MatcherAssert.assertThat(params.get().get(1), Matchers.is(second));
    }

    /**
     * {@link Between} throws exception if the field is not ordered.
     */
    @Test
    public void betweenThrowsExceptionWhenUnorderedField() {
        this.thrown.expect(UnsupportedOperationException.class);
        new Between<>(
            new Scalar.Default<>(new Object()), new Scalar.Default<>(new Object())
        ).value(new SpyField<>(new Object(), new Object()));
    }

    /**
     * {@link Between} toString prints bounds.
     */
    @Test
    public void printsAttributes() {
        final Scalar<Object> first = new Scalar.Default<>(new Object());
        final Scalar<Object> second = new Scalar.Default<>(new Object());
        MatcherAssert.assertThat(
            new Between<>(first, second).toString(),
            Matchers.allOf(
                Matchers.containsString(first.toString()),
                Matchers.containsString(second.toString())
            )
        );
    }
}
