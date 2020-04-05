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
package com.aljebra.field.impl.doubles;

import com.aljebra.metric.angle.Degrees;
import com.aljebra.scalar.Add;
import com.aljebra.scalar.Multiplication;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.Vect;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link Dot}.
 * @since 0.1
 */
public final class DotTest {

    /**
     * Max scalar array length to generate(mock).
     */
    private static final int COORDS_LENGTH = 10;

    /**
     * {@link Dot} can calculate vector product.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void calculatesProduct() {
        final Vect<Double> vecta = Mockito.mock(Vect.class);
        final Vect<Double> vectb = Mockito.mock(Vect.class);
        final Scalar<Double>[] coords = DotTest.scalars(DotTest.COORDS_LENGTH);
        Mockito.when(vectb.coords()).thenReturn(coords);
        Mockito.when(vecta.coords()).thenReturn(coords);
        final List<Scalar<Double>> multis = new ArrayList<>(coords.length);
        for (final Scalar<Double> coord : coords) {
            multis.add(DotTest.square(coord));
        }
        MatcherAssert.assertThat(
            new Dot().product(vecta, vectb),
            Matchers.equalTo(new Add<Double>(multis))
        );
    }

    /**
     * {@link Dot} can calculate angle between two vectors.
     */
    @Test
    public void calculatesAngle() {
        final Vect<Double> vecta = new DblVect(1., 0.);
        final double pifourth = Math.PI / 4;
        final Dot dot = new Dot();
        final double error = 1.e-6;
        MatcherAssert.assertThat(
            dot.angle(vecta, new DblVect(0., -1.)).resolve(dot).doubleValue(),
            Matchers.closeTo(-Math.PI / 2, error)
        );
        MatcherAssert.assertThat(
            dot.angle(vecta, new DblVect(-1., 0.)).resolve(dot).doubleValue(),
            Matchers.closeTo(Math.PI, error)
        );
        MatcherAssert.assertThat(
            dot.angle(vecta, new DblVect(0., 1.)).resolve(dot).doubleValue(),
            Matchers.closeTo(Math.PI / 2, error)
        );
        MatcherAssert.assertThat(
            dot.angle(vecta, new DblVect(1., 1.)).resolve(dot).doubleValue(),
            Matchers.closeTo(pifourth, error)
        );
    }

    /**
     * {@link Dot} can calculate rotation.
     */
    @Test
    public void calculatesRotation() {
        final Vect<Double> vecta = new DblVect(1., 0.);
        final Degrees pifourth = new Degrees.Default(Math.PI / 4);
        final Dot dot = new Dot();
        final Decimal field = new Decimal();
        final double error = 1.e-6;
        Scalar<Double>[] image = dot.rot(vecta, pifourth).coords();
        MatcherAssert.assertThat(
            field.actual(image[0]), Matchers.closeTo(Math.sqrt(2) / 2, error)
        );
        MatcherAssert.assertThat(
            field.actual(image[1]), Matchers.closeTo(Math.sqrt(2) / 2, error)
        );
        image = dot.rot(vecta, new Degrees.Default(0)).coords();
        MatcherAssert.assertThat(
            field.actual(image[0]), Matchers.closeTo(1., error)
        );
        MatcherAssert.assertThat(
            field.actual(image[1]), Matchers.closeTo(0., error)
        );
        image = dot.rot(vecta, new Degrees.Default(Math.PI / 2)).coords();
        MatcherAssert.assertThat(
            field.actual(image[0]), Matchers.closeTo(0., error)
        );
        MatcherAssert.assertThat(
            field.actual(image[1]), Matchers.closeTo(1., error)
        );
        image = dot.rot(vecta, new Degrees.Default(Math.PI)).coords();
        MatcherAssert.assertThat(
            field.actual(image[0]), Matchers.closeTo(-1., error)
        );
        MatcherAssert.assertThat(
            field.actual(image[1]), Matchers.closeTo(0., error)
        );
    }

    /**
     * {@link Dot} can calculate norm.
     */
    @Test
    public void calculatesNorm() {
        final Decimal field = new Decimal();
        final Dot dot = new Dot();
        MatcherAssert.assertThat(
            field.actual(dot.norm(new DblVect(1., 0.))),
            Matchers.equalTo(1.)
        );
        MatcherAssert.assertThat(
            field.actual(dot.norm(new DblVect(0., 0.))),
            Matchers.equalTo(0.)
        );
        MatcherAssert.assertThat(
            field.actual(dot.norm(new DblVect(1., 1.))),
            Matchers.equalTo(Math.sqrt(2.))
        );
        final double expected = Math.sqrt(8);
        final double mintwo = -2.;
        MatcherAssert.assertThat(
            field.actual(dot.norm(new DblVect(mintwo, mintwo))),
            Matchers.equalTo(expected)
        );
    }

    /**
     * Gives the square of a scalar.
     * @param oper The scalar to square
     * @return A {@link Multiplication} object representing the square
     */
    @SuppressWarnings("unchecked")
    private static Multiplication<Double> square(final Scalar<Double> oper) {
        return new Multiplication<Double>(oper, oper);
    }

    /**
     * Mocks an array of {@link Scalar} with the given length.
     * @param length Array length to generate
     * @return An array of scalars.
     */
    @SuppressWarnings("unchecked")
    private static Scalar<Double>[] scalars(final int length) {
        final Scalar<Double>[] result = new Scalar[length];
        for (int idx = 0; idx < result.length; ++idx) {
            result[idx] = Mockito.mock(Scalar.class);
        }
        return result;
    }

}
