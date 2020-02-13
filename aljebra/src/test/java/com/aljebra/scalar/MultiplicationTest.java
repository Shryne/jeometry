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
import com.aljebra.field.FieldMultiplication;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

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
        final Scalar first = Mockito.mock(Scalar.class);
        final Scalar second = Mockito.mock(Scalar.class);
        MatcherAssert.assertThat(
            new Multiplication(first, second),
            Matchers.equalTo(new Multiplication(second, first))
        );
    }

    /**
     * {@link Multiplication} relies on field multiplication
     * to calculate actual value.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void multiplicationDelegatesToFieldMultiplication() {
        final Scalar first = Mockito.mock(Scalar.class);
        final Scalar second = Mockito.mock(Scalar.class);
        final Field<Object> field = Mockito.mock(Field.class);
        final FieldMultiplication<Object> mult = Mockito.mock(
            FieldMultiplication.class
        );
        Mockito.when(field.multiplication()).thenReturn(mult);
        new Multiplication(first, second).value(field);
        Mockito.verify(field).multiplication();
        Mockito.verify(mult).neutral();
        Mockito.verify(mult, Mockito.times(2)).multiply(
            Mockito.any(), Mockito.any()
        );
    }
}
