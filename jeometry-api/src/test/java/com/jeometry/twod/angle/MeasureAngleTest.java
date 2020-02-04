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
package com.jeometry.twod.angle;

import com.aljebra.field.impl.doubles.Dot;
import com.aljebra.metric.angle.VectsDegrees;
import com.aljebra.vector.Vect;
import com.jeometry.twod.point.RandomPoint;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link MeasureAngle}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class MeasureAngleTest {

    /**
     * {@link MeasureAngle} builds an angle with the given measure.
     */
    @Test
    public void buildsMeasureAngle() {
        final double measure = Math.random();
        final Angle angle = new MeasureAngle(measure);
        final double error = 1.e-6;
        MatcherAssert.assertThat(
            new VectsDegrees(
                angle.start(), angle.end()
            ).resolve(new Dot()).doubleValue(),
            Matchers.closeTo(measure, error)
        );
    }

    /**
     * {@link MeasureAngle} builds an angle with the given measure,
     * the given origin and starting vector.
     */
    @Test
    public void buildsMeasureAngleWithOriginAndStart() {
        final Vect origin = new RandomPoint();
        final Vect start = new RandomPoint();
        final double measure = Math.random();
        final Angle angle = new MeasureAngle(origin, start, measure);
        final double error = 1.e-6;
        MatcherAssert.assertThat(
            new VectsDegrees(
                angle.start(), angle.end()
            ).resolve(new Dot()).doubleValue(),
            Matchers.closeTo(measure, error)
        );
        MatcherAssert.assertThat(angle.origin(), Matchers.equalTo(origin));
        MatcherAssert.assertThat(angle.start(), Matchers.equalTo(start));
    }
}
