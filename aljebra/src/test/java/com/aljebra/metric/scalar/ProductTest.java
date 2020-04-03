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
package com.aljebra.metric.scalar;

import com.aljebra.field.Field;
import com.aljebra.field.MetricSpaceField;
import com.aljebra.metric.InnerProduct;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.Vect;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 * Tests for {@link Product}.
 * @since 0.1
 */
public final class ProductTest {

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * {@link Product} rely on InnerProduct for resolution of actual value.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void delegatesToInnerProduct() {
        final int dim = 6;
        final Vect<Object> first = Mockito.mock(Vect.class);
        final Vect<Object> second = Mockito.mock(Vect.class);
        Mockito.when(first.coords()).thenReturn(ProductTest.scalars(dim));
        Mockito.when(second.coords()).thenReturn(ProductTest.scalars(dim));
        final MetricSpaceField<Object> field = Mockito.mock(MetricSpaceField.class);
        final InnerProduct<Object> pdt = Mockito.mock(InnerProduct.class);
        Mockito.when(field.product()).thenReturn(pdt);
        new Product<>(first, second).value(field);
        Mockito.verify(pdt).product(first, second);
    }

    /**
     * {@link Product} throws exception when vectors don't have the same size.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void errorsWhenNotSameSize() {
        this.thrown.expect(IllegalArgumentException.class);
        final int dim = 6;
        final Vect<Object> first = Mockito.mock(Vect.class);
        final Vect<Object> second = Mockito.mock(Vect.class);
        Mockito.when(first.coords()).thenReturn(ProductTest.scalars(dim));
        Mockito.when(second.coords()).thenReturn(ProductTest.scalars(dim + 1));
        final MetricSpaceField<Object> field = Mockito.mock(MetricSpaceField.class);
        new Product<>(first, second).value(field);
    }

    /**
     * {@link Product} throws exception when not operating on a metric space.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void errorsWhenNoMetricSpace() {
        this.thrown.expect(UnsupportedOperationException.class);
        final int dim = 6;
        final Vect<Object> first = Mockito.mock(Vect.class);
        final Vect<Object> second = Mockito.mock(Vect.class);
        Mockito.when(first.coords()).thenReturn(ProductTest.scalars(dim));
        Mockito.when(second.coords()).thenReturn(ProductTest.scalars(dim));
        final Field<Object> field = Mockito.mock(Field.class);
        new Product<>(first, second).value(field);
    }

    /**
     * Mocks an array of {@link Scalar} with a given length.
     * @param length Array length
     * @return An array of scalars
     */
    @SuppressWarnings("unchecked")
    private static Scalar<Object>[] scalars(final int length) {
        final Scalar<Object>[] result = new Scalar[length];
        for (int idx = 0; idx < result.length; ++idx) {
            result[idx] = Mockito.mock(Scalar.class);
        }
        return result;
    }
}
