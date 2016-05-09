/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2016-2016, Hamdi Douss
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
import com.aljebra.field.impl.doubles.Decimal;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 * Tests for {@link MultInverse}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class MultInverseTest {

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * {@link MultInverse} relies on field multiplication
     * to calculate actual value.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void multInverseDelegatesToFieldMultiplication() {
        final Field<Object> field = Mockito.mock(Field.class);
        final FieldMultiplication<Object> mult = Mockito.mock(
            FieldMultiplication.class
        );
        Mockito.when(field.multiplication()).thenReturn(mult);
        final Scalar scalar = Mockito.mock(Scalar.class);
        new MultInverse(scalar).value(field);
        Mockito.verify(field).multiplication();
        Mockito.verify(mult).inverse(Mockito.any());
    }

    /**
     * {@link MultInverse} throws exception when evaluating addition identity
     * inverse.
     */
    @Test
    public void throwsExceptionWhenInvertingAddIdentity() {
        this.thrown.expect(IllegalArgumentException.class);
        new MultInverse(new AddIdentity()).value(new Decimal());
    }

    /**
     * {@link MultInverse} respects equals and hashcode
     * regarding the scalar to inverse.
     */
    @Test
    public void respectsEqualAndHashcode() {
        final Scalar first = Mockito.mock(Scalar.class);
        final Scalar second = Mockito.mock(Scalar.class);
        MatcherAssert.assertThat(
            new MultInverse(first),
            Matchers.equalTo(new MultInverse(first))
        );
        MatcherAssert.assertThat(
            new MultInverse(first),
            Matchers.not(Matchers.equalTo(new MultInverse(second)))
        );
        MatcherAssert.assertThat(
            new MultInverse(first).hashCode(),
            Matchers.equalTo(
                new MultInverse(first).hashCode()
            )
        );
    }
}
