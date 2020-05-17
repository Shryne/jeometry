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

import com.aljebra.field.mock.MkField;
import com.aljebra.field.mock.MkMultiplication;
import com.aljebra.field.mock.SpyField;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link Multiplication}.
 * @since 0.1
 */
public final class MultiplicationTest {
    /**
     * {@link Multiplication} respects equals with disregard
     * to order of operands.
     */
    @Test
    public void multiplicationIsCommutative() {
        final Scalar<Object> first = new Scalar.Default<>(new Object());
        final Scalar<Object> second = new Scalar.Default<>(new Object());
        MatcherAssert.assertThat(
            new Multiplication<>(first, second),
            Matchers.equalTo(new Multiplication<>(second, first))
        );
    }

    /**
     * {@link Multiplication} relies on field multiplication
     * to calculate actual value.
     */
    @Test
    public void multiplicationDelegatesToFieldMultiplication() {
        final Scalar<Object> first = new Scalar.Default<>(new Object());
        final Scalar<Object> second = new Scalar.Default<>(new Object());
        final MkMultiplication<Object> mult = new MkMultiplication<>(new Object());
        final SpyField<Object> field = new SpyField<>(
            new MkField<Object>(new Object(), mult)
        );
        final List<Scalar<Object>> operands = Arrays.asList(first, second);
        new Multiplication<>(operands).value(field);
        MatcherAssert.assertThat(
            field.calls().multiplicationed(), Matchers.is(true)
        );
        MatcherAssert.assertThat(
            mult.neutraled(), Matchers.is(true)
        );
        MatcherAssert.assertThat(
            mult.multiplied(), Matchers.is(operands.size())
        );
    }
}
