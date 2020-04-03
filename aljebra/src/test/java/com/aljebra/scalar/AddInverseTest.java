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
import com.aljebra.field.FieldAddition;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link AddInverse}.
 * @since 0.1
 */
public final class AddInverseTest {

    /**
     * {@link AddInverse} relies on field addition
     * to calculate actual value.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void addInverseDelegatesToFieldAddition() {
        final Field<Object> field = Mockito.mock(Field.class);
        final FieldAddition<Object> add = Mockito.mock(FieldAddition.class);
        Mockito.when(field.addition()).thenReturn(add);
        final Scalar<Object> scalar = Mockito.mock(Scalar.class);
        new AddInverse<>(scalar).value(field);
        Mockito.verify(field).addition();
        Mockito.verify(add).inverse(Mockito.any());
    }

    /**
     * {@link AddInverse} respects equals and hashcode
     * regarding the scalar to inverse.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void respectsEqualAndHashcode() {
        final Scalar<Object> first = Mockito.mock(Scalar.class);
        final Scalar<Object> second = Mockito.mock(Scalar.class);
        MatcherAssert.assertThat(
            new AddInverse<>(first),
            Matchers.equalTo(new AddInverse<>(first))
        );
        MatcherAssert.assertThat(
            new AddInverse<>(first),
            Matchers.not(Matchers.equalTo(new AddInverse<>(second)))
        );
        MatcherAssert.assertThat(
            new AddInverse<>(first).hashCode(),
            Matchers.equalTo(
                new AddInverse<>(first).hashCode()
            )
        );
    }
}
