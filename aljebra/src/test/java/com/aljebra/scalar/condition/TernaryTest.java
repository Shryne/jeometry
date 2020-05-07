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
package com.aljebra.scalar.condition;

import com.aljebra.field.SpyField;
import com.aljebra.scalar.Scalar;
import java.util.List;
import java.util.Optional;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link Ternary}.
 * @since 0.1
 */
public final class TernaryTest {

    /**
     * {@link Ternary} evaluates to the first scalar
     * returns true if all predicates are true.
     */
    @Test
    public void valueToFirstScalarWhenTrue() {
        final Scalar<Object> first = new Scalar.Default<>(new Object());
        final Scalar<Object> second = new Scalar.Default<>(new Object());
        final SpyField<Object> field = new SpyField<>(new Object(), new Object());
        new Ternary<>(new True(), first, second).value(field);
        final Optional<List<Scalar<Object>>> params = field.calls().actuals();
        MatcherAssert.assertThat(params.isPresent(), Matchers.equalTo(true));
        MatcherAssert.assertThat(params.get().contains(first), Matchers.equalTo(true));
        MatcherAssert.assertThat(params.get().contains(second), Matchers.equalTo(false));
    }

    /**
     * {@link Ternary} evaluates to the second scalar
     * if the predicate resolves to false.
     */
    @Test
    public void valueToSecondScalarWhenFalse() {
        final Scalar<Object> first = new Scalar.Default<>(new Object());
        final Scalar<Object> second = new Scalar.Default<>(new Object());
        final SpyField<Object> field = new SpyField<>(new Object(), new Object());
        new Ternary<>(new False(), first, second).value(field);
        final Optional<List<Scalar<Object>>> params = field.calls().actuals();
        MatcherAssert.assertThat(params.isPresent(), Matchers.equalTo(true));
        MatcherAssert.assertThat(params.get().contains(first), Matchers.equalTo(false));
        MatcherAssert.assertThat(params.get().contains(second), Matchers.equalTo(true));
    }
}
