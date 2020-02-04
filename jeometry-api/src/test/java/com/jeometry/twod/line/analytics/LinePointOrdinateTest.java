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
package com.jeometry.twod.line.analytics;

import com.aljebra.field.impl.doubles.Decimal;
import com.aljebra.scalar.Scalar;
import com.jeometry.twod.line.Line;
import com.jeometry.twod.line.PtDirLine;
import com.jeometry.twod.point.DifferentPoint;
import com.jeometry.twod.point.RandomPoint;
import com.jeometry.twod.point.VertPoint;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for {@link LinePointOrdinate}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class LinePointOrdinateTest {

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * {@link LinePointOrdinate} calculates ordinate.
     */
    @Test
    public void calculatesOrdinate() {
        final Line line = new PtDirLine(
            new RandomPoint(), new DifferentPoint(new VertPoint())
        );
        final double error = 1.e-6;
        final Decimal dec = new Decimal();
        final Scalar abscissa = new RandomPoint().xcoor();
        final Double startx = line.point().coords()[0].value(dec);
        final Double starty = line.point().coords()[1].value(dec);
        final Double dirx = line.direction().coords()[0].value(dec);
        final Double diry = line.direction().coords()[1].value(dec);
        MatcherAssert.assertThat(
            new LinePointOrdinate(line, abscissa).value(dec),
            Matchers.closeTo(
                diry / dirx * abscissa.value(dec)
                + starty - startx * diry / dirx,
                error
            )
        );
    }

    /**
     * {@link LinePointOrdinate} throws exception on evaluation
     * if the line is vertical and the abscissa is outside the line.
     */
    @Test
    public void errorsWhenVerticalLineAndOutsideAbscissa() {
        this.thrown.expect(IllegalStateException.class);
        final RandomPoint point = new RandomPoint();
        final Line line = new PtDirLine(point, new VertPoint());
        new LinePointOrdinate(
            line, new DifferentPoint(point).xcoor()
        ).value(new Decimal());
    }

    /**
     * {@link LinePointOrdinate} can be evaluated
     * if the line is vertical and the abscissa is in the line.
     */
    @Test
    public void evaluatesWhenVerticalLineAndInsideAbscissa() {
        final RandomPoint point = new RandomPoint();
        final Line line = new PtDirLine(point, new VertPoint());
        new LinePointOrdinate(line, point.xcoor()).value(new Decimal());
    }
}
