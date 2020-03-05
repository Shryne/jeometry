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

import com.aljebra.field.AbstractField;
import com.aljebra.field.Field;
import com.aljebra.scalar.Scalar;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 * Tests for {@link Not}.
 * @since 0.1
 */
public final class NotTest {

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * {@link Not} resolves to false if predicate resolves to true.
     */
    @Test
    public void resolvesToTrueWhenPredicateIsFalse() {
        MatcherAssert.assertThat(
            new Not(new True()).resolve(Mockito.mock(Field.class)),
            Matchers.is(false)
        );
    }

    /**
     * {@link Not} resolves to true if predicate resolves to false.
     */
    @Test
    public void resolvesToFalseWhenPredicateIsTrue() {
        MatcherAssert.assertThat(
            new Not(new False()).resolve(Mockito.mock(Field.class)),
            Matchers.is(true)
        );
    }

    /**
     * {@link Not} can build a ternary.
     */
    @Test
    public void buildsTernary() {
        final Scalar first = Mockito.mock(Scalar.class);
        final Scalar second = Mockito.mock(Scalar.class);
        final Field<?> field = Mockito.mock(Field.class);
        new Not(new False()).ifElse(first, second).value(field);
        Mockito.verify(field).actual(first);
        Mockito.verify(field, Mockito.never()).actual(second);
    }

    /**
     * {@link Not} can build a throwing ternary.
     */
    @Test
    public void buildsThrowing() {
        final RuntimeException err = new RuntimeException();
        this.thrown.expect(err.getClass());
        final Field<?> field = Mockito.mock(AbstractField.class);
        new Not(new True()).ifElse(
            Mockito.mock(Scalar.class), err
        ).value(field);
    }
}
