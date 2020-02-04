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
package com.aljebra.metric.scalar;

import com.aljebra.field.Field;
import com.aljebra.field.MetricSpaceField;
import com.aljebra.metric.InnerProduct;
import com.aljebra.vector.Vect;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 * Tests for {@link Norm}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class NormTest {

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * {@link Norm} rely on InnerProduct for resolution of actual value.
     */
    @Test
    public void delegatesToInnerProduct() {
        final Vect first = Mockito.mock(Vect.class);
        final MetricSpaceField<?> field = Mockito.mock(MetricSpaceField.class);
        final InnerProduct pdt = Mockito.mock(InnerProduct.class);
        Mockito.when(field.product()).thenReturn(pdt);
        new Norm(first).value(field);
        Mockito.verify(pdt).norm(first);
    }

    /**
     * {@link Norm} throws exception when not operating on a metric space.
     */
    @Test
    public void errorsWhenNoMetricSpace() {
        this.thrown.expect(UnsupportedOperationException.class);
        final Field<?> field = Mockito.mock(Field.class);
        new Norm(Mockito.mock(Vect.class)).value(field);
    }
}
