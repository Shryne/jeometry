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
import com.aljebra.metric.MockProduct;
import com.aljebra.scalar.Random;
import com.aljebra.vector.FixedVector;
import com.aljebra.vector.Vect;
import java.util.Arrays;
import java.util.Optional;
import org.hamcrest.MatcherAssert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 * Tests for {@link Norm}.
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
    @SuppressWarnings("unchecked")
    @Test
    public void delegatesToInnerProduct() {
        final Vect<Object> first = new FixedVector<>(Arrays.asList(new Random<>()));
        final MockProduct<Object> pdt = new MockProduct<>();
        final MetricSpaceField<Object> field = Mockito.mock(MetricSpaceField.class);
        Mockito.when(field.product()).thenReturn(pdt);
        new Norm<>(first).value(field);
        final Optional<Vect<Object>> params = pdt.norm();
        MatcherAssert.assertThat(
            "Expecting call to norm method with vector as parameters",
            params.isPresent() && params.get().equals(first)
        );
    }

    /**
     * {@link Norm} throws exception when not operating on a metric space.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void errorsWhenNoMetricSpace() {
        this.thrown.expect(UnsupportedOperationException.class);
        final Field<Object> field = Mockito.mock(Field.class);
        new Norm<>(new FixedVector<>(Arrays.asList(new Random<>()))).value(field);
    }
}
