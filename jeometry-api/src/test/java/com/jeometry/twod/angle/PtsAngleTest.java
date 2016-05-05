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
package com.jeometry.twod.angle;

import com.aljebra.field.Field;
import com.aljebra.field.impl.doubles.Decimal;
import com.aljebra.vector.Minus;
import com.aljebra.vector.Vect;
import com.jeometry.twod.line.RayLine;
import com.jeometry.twod.line.analytics.PointInLine;
import com.jeometry.twod.point.RandomPoint;
import com.jeometry.twod.ray.PtsRay;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link PtsAngle}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class PtsAngleTest {

    /**
     * {@link PtsAngle} builds an angle with the given points.
     */
    @Test
    public void buildsPointsAngle() {
        final Vect origin = new RandomPoint();
        final Vect startpt = new RandomPoint();
        final Vect endpt = new RandomPoint();
        final Angle angle = new PtsAngle(origin, startpt, endpt);
        MatcherAssert.assertThat(angle.origin(), Matchers.equalTo(origin));
        MatcherAssert.assertThat(
            angle.start(), Matchers.equalTo(new Minus(startpt, origin))
        );
        MatcherAssert.assertThat(
            angle.end(), Matchers.equalTo(new Minus(endpt, origin))
        );
        final Field<Double> dec = new Decimal();
        MatcherAssert.assertThat(
            new PointInLine(
                startpt, new RayLine(new PtsRay(origin, startpt))
            ).resolve(dec),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new PointInLine(
                endpt, new RayLine(new PtsRay(origin, endpt))
            ).resolve(dec),
            Matchers.is(true)
        );
    }

}
