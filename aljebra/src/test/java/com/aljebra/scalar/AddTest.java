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
import java.util.Arrays;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link Add}.
 * @since 0.1
 */
public final class AddTest {
    /**
     * {@link Add} respects equals and hashcode with disregard
     * to order of operands.
     */
    @Test
    public void additionIsCommutative() {
        final Scalar<Object> first = new Scalar.Default<>(new Object());
        final Scalar<Object> second = new Scalar.Default<>(new Object());
        MatcherAssert.assertThat(
            new Add<>(Arrays.asList(first, second)),
            Matchers.equalTo(new Add<>(Arrays.asList(second, first)))
        );
        MatcherAssert.assertThat(
            new Add<>(Arrays.asList(first, second)),
            Matchers.not(
                Matchers.equalTo(new Add<>(Arrays.asList(first, first)))
            )
        );
        MatcherAssert.assertThat(
            new Add<>(Arrays.asList(first, second)).hashCode(),
            Matchers.equalTo(new Add<>(Arrays.asList(second, first)).hashCode())
        );
    }

    /**
     * {@link Add} operands are bag-like collection: duplicates counts.
     */
    @Test
    public void additionOperandsAreNotASet() {
        final Scalar<Object> first = new Scalar.Default<>(new Object());
        final Scalar<Object> second = new Scalar.Default<>(new Object());
        MatcherAssert.assertThat(
            new Add<>(Arrays.asList(first, second, first)),
            Matchers.not(Matchers.equalTo(new Add<>(Arrays.asList(second, first))))
        );
    }

    /**
     * {@link Add} give operands access.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void exposesOperands() {
        final Scalar<Object> first = new Scalar.Default<>(new Object());
        final Scalar<Object> second = new Scalar.Default<>(new Object());
        MatcherAssert.assertThat(
            new Add<>(Arrays.asList(first, second)).operands(),
            Matchers.containsInAnyOrder(first, second)
        );
    }

    /**
     * {@link Add} relies on field addition to calculate actual value.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void addDelegatesToFieldAddition() {
        final Scalar<Object> first = new Scalar.Default<>(new Object());
        final Scalar<Object> second = new Scalar.Default<>(new Object());
        final Field<Object> field = Mockito.mock(Field.class);
        final MkAddition<Object> add = new MkAddition<>(new Object());
        Mockito.when(field.addition()).thenReturn(add);
        final List<Scalar<Object>> operands = Arrays.asList(first, second, first);
        new Add<>(operands).value(field);
        Mockito.verify(field).addition();
        MatcherAssert.assertThat(
            add.neutraled(), Matchers.is(true)
        );
        MatcherAssert.assertThat(
            add.added(), Matchers.is(operands.size())
        );
    }
}
