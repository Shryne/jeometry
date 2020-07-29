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

import com.aljebra.field.mock.MkField;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link IsFlat}.
 * @since 0.4
 */
public final class IsFlatTest {

    /**
     * {@link IsFlat} resolves to true if given a flat angle.
     */
    @Test
    public void resolvesTrueIfFlat() {
        final MkField<Object> field = new MkField<>(new Object(), new Object());
        MatcherAssert.assertThat(
            new IsFlat<>(new Degrees.Default<>(Math.PI)).resolve(field), Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new IsFlat<>(new Flat<>()).resolve(field), Matchers.is(true)
        );
    }

    /**
     * {@link IsFlat} resolves to false if given a non-flat angle.
     */
    @Test
    public void resolvesFalseIfNonFlat() {
        final MkField<Object> field = new MkField<>(new Object(), new Object());
        MatcherAssert.assertThat(
            new IsFlat<>(new Degrees.Default<>(0)).resolve(field), Matchers.is(false)
        );
        MatcherAssert.assertThat(
            new IsFlat<>(new Right<>()).resolve(field), Matchers.is(false)
        );
    }
}
