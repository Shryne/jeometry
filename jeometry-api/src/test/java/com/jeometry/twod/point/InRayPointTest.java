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
package com.jeometry.twod.point;

import com.aljebra.field.Field;
import com.aljebra.field.impl.doubles.Decimal;
import com.aljebra.vector.Vect;
import com.jeometry.twod.line.analytics.Intercept;
import com.jeometry.twod.line.analytics.Slope;
import com.jeometry.twod.line.analytics.Vertical;
import com.jeometry.twod.ray.PtsRay;
import com.jeometry.twod.ray.Ray;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link InRayPoint}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class InRayPointTest {

    /**
     * {@link InRayPoint} constructs a point belonging to the ray.
     */
    @Test
    public void buildsAPointInRay() {
        final Ray any = new PtsRay(new RandomPoint(), new RandomPoint());
        MatcherAssert.assertThat(
            InRayPointTest.belongs(new InRayPoint(any), any),
            Matchers.is(true)
        );
    }

    /**
     * Checks if the given point belongs to the ray.
     * @param pnt Point
     * @param any Ray
     * @return True if the point belongs to the ray
     */
    private static boolean belongs(final Vect pnt, final Ray any) {
        final Field<Double> dec = new Decimal();
        final double xcoor = pnt.coords()[0].value(dec);
        final double ycoor = pnt.coords()[1].value(dec);
        final boolean result;
        final double xorigin = any.origin().coords()[0].value(dec);
        if (new Vertical(any).resolve(dec)) {
            final double ydir = any.direction().coords()[1].value(dec);
            final double yorigin = any.origin().coords()[1].value(dec);
            final boolean between = ydir > 0 && ycoor > yorigin
                || ydir < 0 && ycoor < yorigin;
            result = xorigin == xcoor && between;
        } else {
            final double xdir = any.direction().coords()[0].value(dec);
            final double error = 1.e-6;
            final boolean between = xdir > 0 && xcoor > xorigin
                || xdir < 0 && xcoor < xorigin;
            result = Math.abs(
                ycoor - xcoor * new Slope(any).value(dec)
                - new Intercept(any).value(dec)
            ) < error && between;
        }
        return result;
    }
}
