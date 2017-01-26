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
package com.aljebra.scalar.condition;

import com.aljebra.field.Field;
import com.aljebra.scalar.Scalar;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link Ternary}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class TernaryTest {

    /**
     * {@link Ternary} evaluates to the first scalar
     * returns true if all predicates are true.
     */
    @Test
    public void valueToFirstScalarWhenTrue() {
        final Scalar first = Mockito.mock(Scalar.class);
        final Scalar second = Mockito.mock(Scalar.class);
        final Field<?> field = Mockito.mock(Field.class);
        new Ternary(new True(), first, second).value(field);
        Mockito.verify(field).actual(first);
        Mockito.verify(field, Mockito.never()).actual(second);
    }

    /**
     * {@link Ternary} evaluates to the second scalar
     * if the predicate resolves to false.
     */
    @Test
    public void valueToSecondScalarWhenFalse() {
        final Scalar first = Mockito.mock(Scalar.class);
        final Scalar second = Mockito.mock(Scalar.class);
        final Field<?> field = Mockito.mock(Field.class);
        new Ternary(new False(), first, second).value(field);
        Mockito.verify(field).actual(second);
        Mockito.verify(field, Mockito.never()).actual(first);
    }
}
