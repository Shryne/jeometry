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
package com.aljebra.field.impl.doubles;

import com.aljebra.scalar.Add;
import com.aljebra.scalar.Multiplication;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.FixedVector;
import com.aljebra.vector.Vect;
import java.util.Random;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 * Tests for {@link Dot}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class DotTest {

    /**
     * Max scalar array length to generate(mock).
     */
    private static final int COORDS_LENGTH = 10;

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * {@link Dot} calculates product.
     */
    @Test
    public void calculatesProduct() {
        final Vect vecta = Mockito.mock(Vect.class);
        final Vect vectb = Mockito.mock(Vect.class);
        final Scalar[] coords = DotTest.scalars();
        Mockito.when(vectb.coords()).thenReturn(coords);
        Mockito.when(vecta.coords()).thenReturn(coords);
        final Scalar[] multis = new Scalar[coords.length];
        for (int idx = 0; idx < coords.length; ++idx) {
            multis[idx] = DotTest.square(coords[idx]);
        }
        MatcherAssert.assertThat(
            new Dot().product(vecta, vectb),
            Matchers.equalTo(new Add(multis))
        );
    }

    /**
     * {@link Dot} throws exception if the two vectors don't have
     * the same dimension when calculating product.
     */
    @Test
    public void errorsProductWhenNotSameSize() {
        this.thrown.expect(IllegalArgumentException.class);
        final Vect vecta = Mockito.mock(Vect.class);
        final Vect vectb = Mockito.mock(Vect.class);
        final Scalar[] acoords = DotTest.scalars();
        final Scalar[] bcoords = DotTest.scalars(acoords.length + 1);
        Mockito.when(vectb.coords()).thenReturn(bcoords);
        Mockito.when(vecta.coords()).thenReturn(acoords);
        new Dot().product(vecta, vectb);
    }

    /**
     * {@link Dot} calculates angl.
     */
    @Test
    public void calculatesAngle() {
        final Vect vecta = DotTest.vect(1., 0.);
        final double pifourth = Math.PI / 4;
        final Dot dot = new Dot();
        final double error = 1.e-5;
        MatcherAssert.assertThat(
            dot.angle(vecta, DotTest.vect(0., -1.)).resolve(dot).doubleValue(),
            Matchers.closeTo(-Math.PI / 2, error)
        );
        MatcherAssert.assertThat(
            dot.angle(vecta, DotTest.vect(-1., 0.)).resolve(dot).doubleValue(),
            Matchers.closeTo(Math.PI, error)
        );
        MatcherAssert.assertThat(
            dot.angle(vecta, DotTest.vect(0., 1.)).resolve(dot).doubleValue(),
            Matchers.closeTo(Math.PI / 2, error)
        );
        MatcherAssert.assertThat(
            dot.angle(vecta, DotTest.vect(1., 1.)).resolve(dot).doubleValue(),
            Matchers.closeTo(pifourth, error)
        );
    }

    /**
     * {@link Dot} throws exception if the two vectors don't have
     * the same dimension when calculating angle.
     */
    @Test
    public void errorsAngleWhenNotSameSize() {
        this.thrown.expect(IllegalArgumentException.class);
        final Vect vecta = Mockito.mock(Vect.class);
        final Vect vectb = Mockito.mock(Vect.class);
        final Scalar[] acoords = DotTest.scalars();
        final Scalar[] bcoords = DotTest.scalars(acoords.length + 1);
        Mockito.when(vectb.coords()).thenReturn(bcoords);
        Mockito.when(vecta.coords()).thenReturn(acoords);
        new Dot().angle(vecta, vectb);
    }

    /**
     * Gives the square of a scalar.
     * @param oper The scalar to square
     * @return A {@link Multiplication} object representing the square
     */
    private static Multiplication square(final Scalar oper) {
        return new Multiplication(oper, oper);
    }

    /**
     * Mocks an array of {@link Scalar} with a random length.
     * @return An array of scalars.
     */
    private static Scalar[] scalars() {
        return DotTest.scalars(new Random().nextInt(DotTest.COORDS_LENGTH));
    }

    /**
     * Mocks an array of {@link Scalar} with the given length.
     * @param length Array length to generate
     * @return An array of scalars.
     */
    private static Scalar[] scalars(final int length) {
        final Scalar[] result = new Scalar[length];
        for (int idx = 0; idx < result.length; ++idx) {
            result[idx] = Mockito.mock(Scalar.class);
        }
        return result;
    }

    /**
     * Builds a 2D vector with the given doubles.
     * @param xcoor Vector x coordinate
     * @param ycoor Vector y coordinate
     * @return A 2D vector
     */
    private static Vect vect(final double xcoor, final double ycoor) {
        return new FixedVector(
            new Scalar.Default<Double>(xcoor), new Scalar.Default<Double>(ycoor)
        );
    }
}
