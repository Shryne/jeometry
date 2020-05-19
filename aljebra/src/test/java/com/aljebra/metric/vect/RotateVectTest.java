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

import com.aljebra.field.impl.doubles.Dot;
import com.aljebra.field.mock.SpyField;
import com.aljebra.metric.InnerProduct;
import com.aljebra.metric.angle.Degrees;
import com.aljebra.scalar.Scalar;
import com.aljebra.scalar.mock.Scalars;
import com.aljebra.vector.FixedVector;
import com.aljebra.vector.Vect;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
        final Vect<Double> first = new FixedVector<>(new Scalars<>(dim));
        final Vect<Double> second = new FixedVector<>(new Scalars<>(dim));
        final InnerProduct<Double> pdt = new Dot();
        final double error = 1.e-6;
        Degrees<Double> angle = new Degrees.Default<>(Math.random());
        MatcherAssert.assertThat(
            pdt.angle(
                first, new RotateVect<>(first, angle)
            ).resolve(pdt).doubleValue(),
            Matchers.closeTo(angle.resolve(pdt).doubleValue(), error)
        );
        angle = new Degrees.Default<>(Math.random());
        MatcherAssert.assertThat(
            pdt.angle(
                second, new RotateVect<>(second, angle)
            ).resolve(pdt).doubleValue(),
            Matchers.closeTo(angle.resolve(pdt).doubleValue(), error)
        );
    }

    /**
     * {@link RotateVect} coordinates cannot be evaluated when
     * the field is not a metric space field.
     */
    @Test
    public void errorsWhenEvaluatingCoordinates() {
        this.thrown.expect(UnsupportedOperationException.class);
        final Vect<Double> first = new FixedVector<>(new Scalars<>(2));
        new RotateVect<>(
            first, Math.random()
        ).coords()[0].value(new SpyField<>(new Double(0.), new Double(1.)));
    }

    /**
     * {@link RotateVect} toString prints coordinates.
     */
    @Test
    public void printsCoords() {
        final Vect<Double> vect = new RotateVect<>(
            new FixedVector<>(new Scalars<>(2)), Math.random()
        );
        for (final Scalar<Double> scalar : vect.coords()) {
            MatcherAssert.assertThat(
                vect.toString(), Matchers.containsString(scalar.toString())
            );
        }
    }

}
