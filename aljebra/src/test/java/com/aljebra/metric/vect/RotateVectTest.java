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
package com.aljebra.metric.vect;

import com.aljebra.field.Field;
import com.aljebra.field.impl.doubles.Dot;
import com.aljebra.metric.InnerProduct;
import com.aljebra.metric.angle.Degrees;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.Vect;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 * Tests for {@link RotateVect}.
 * @since 0.1
 */
public final class RotateVectTest {

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * {@link RotateVect} rotates vector.
     */
    @Test
    public void rotatesVector() {
        final int dim = 2;
        final Vect first = Mockito.mock(Vect.class);
        final Vect second = Mockito.mock(Vect.class);
        Mockito.when(first.coords()).thenReturn(RotateVectTest.scalars(dim));
        Mockito.when(second.coords()).thenReturn(RotateVectTest.scalars(dim));
        final InnerProduct pdt = new Dot();
        final double error = 1.e-6;
        Degrees angle = new Degrees.Default(Math.random());
        MatcherAssert.assertThat(
            pdt.angle(
                first, new RotateVect(first, angle)
            ).resolve(pdt).doubleValue(),
            Matchers.closeTo(angle.resolve(pdt).doubleValue(), error)
        );
        angle = new Degrees.Default(Math.random());
        MatcherAssert.assertThat(
            pdt.angle(
                second, new RotateVect(second, angle)
            ).resolve(pdt).doubleValue(),
            Matchers.closeTo(angle.resolve(pdt).doubleValue(), error)
        );
    }

    /**
     * {@link RotateVect} coordinates cannot be evaluated when
     * the field is not a metric space field.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void errorsWhenEvaluatingCoordinates() {
        this.thrown.expect(UnsupportedOperationException.class);
        final Vect first = Mockito.mock(Vect.class);
        Mockito.when(first.coords()).thenReturn(RotateVectTest.scalars(2));
        new RotateVect(
            first, Math.random()
        ).coords()[0].value(Mockito.mock(Field.class));
    }

    /**
     * Mocks an array of {@link Scalar} with a given length.
     * @param length Array length
     * @return An array of scalars
     */
    private static Scalar[] scalars(final int length) {
        final Scalar[] result = new Scalar[length];
        for (int idx = 0; idx < result.length; ++idx) {
            result[idx] = RotateVectTest.scalar(Math.random());
        }
        return result;
    }

    /**
     * Returns a default scalar of the double.
     * @param num A double
     * @return A scalar
     */
    private static Scalar scalar(final double num) {
        return new Scalar.Default<Double>(num);
    }
}
