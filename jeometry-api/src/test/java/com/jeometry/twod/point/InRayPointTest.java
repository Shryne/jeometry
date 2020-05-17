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
package com.jeometry.twod.point;

import com.aljebra.field.Field;
import com.aljebra.field.impl.doubles.Decimal;
import com.aljebra.scalar.Scalar;
import com.jeometry.twod.line.RayLine;
import com.jeometry.twod.line.analytics.PointInLine;
import com.jeometry.twod.ray.PtsRay;
import com.jeometry.twod.ray.Ray;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link InRayPoint}.
 * @since 0.1
 */
public final class InRayPointTest {

    /**
     * {@link InRayPoint} constructs a point belonging to the ray.
     */
    @Test
    public void buildsAPointInRay() {
        final Ray<Double> any = new PtsRay<>(new RandomPoint<>(), new RandomPoint<>());
        MatcherAssert.assertThat(
            InRayPointTest.belongs(new InRayPoint<>(any), any),
            Matchers.is(true)
        );
    }

    /**
     * {@link InRayPoint} constructs a point belonging to a vertical ray.
     */
    @Test
    public void buildsAPointInVerticalRay() {
        final Ray<Double> any = new PtsRay<>(new RandomPoint<>(), new VertPoint<>());
        MatcherAssert.assertThat(
            InRayPointTest.belongs(new InRayPoint<>(any), any),
            Matchers.is(true)
        );
    }

    /**
     * {@link InRayPoint} toString prints underlying coordinates.
     */
    @Test
    public void toStringPrintsCoordinates() {
        final InRayPoint<Double> point = new InRayPoint<>(
            new PtsRay<>(new RandomPoint<>(), new RandomPoint<>())
        );
        MatcherAssert.assertThat(
            point.toString(), Matchers.allOf(
                Matchers.containsString(point.xcoor().toString()),
                Matchers.containsString(point.ycoor().toString())
            )
        );
    }

    /**
     * Checks if the given point belongs to the ray.
     * @param pnt Point
     * @param any Ray
     * @return True if the point belongs to the ray
     */
    private static boolean belongs(final XyPoint<Double> pnt, final Ray<Double> any) {
        final Field<Double> dec = new Decimal();
        final double xcoor = pnt.xcoor().value(dec);
        final double ycoor = pnt.ycoor().value(dec);
        final Scalar<Double>[] coords = any.origin().coords();
        final boolean inline = new PointInLine<>(pnt, new RayLine<>(any)).resolve(dec);
        final Scalar<Double>[] dircoords = any.direction().coords();
        final double ydir = dircoords[1].value(dec);
        final double yorigin = coords[1].value(dec);
        final boolean ybetween = ydir > 0 && ycoor >= yorigin
            || ydir < 0 && ycoor <= yorigin;
        final double xorigin = coords[0].value(dec);
        final double xdir = dircoords[0].value(dec);
        final boolean xbetween = xdir > 0 && xcoor >= xorigin
            || xdir < 0 && xcoor <= xorigin;
        return inline && xbetween && ybetween;
    }
}
