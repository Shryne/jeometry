/**
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
import com.aljebra.field.OrderedField;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 * Tests for {@link Greater}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class GreaterTest {

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * {@link Greater} relies on ordered field to calculate actual value.
     */
    @Test
    public void delegatesToOrderedFieldRandomizer() {
        final Scalar first = Mockito.mock(Scalar.class);
        final OrderedField<?> field = Mockito.mock(OrderedField.class);
        Mockito.when(
            field.greater(Mockito.any())
        ).thenReturn(Mockito.mock(Scalar.class));
        new Greater(first).value(field);
        Mockito.verify(field).greater(first);
    }

    /**
     * {@link Greater} throws exception if the field is not ordered.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void throwsExceptionWhenUnorderedField() {
        this.thrown.expect(UnsupportedOperationException.class);
        new Greater(
            Mockito.mock(Scalar.class)
        ).value(Mockito.mock(Field.class));
    }
}
