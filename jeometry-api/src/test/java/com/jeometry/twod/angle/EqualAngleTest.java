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
package com.jeometry.twod.angle;

import com.aljebra.field.impl.doubles.Dot;
import com.aljebra.metric.angle.VectsDegrees;
import com.aljebra.vector.Vect;
import com.jeometry.twod.point.RandomPoint;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link EqualAngle}.
 * @since 0.1
 */
public final class EqualAngleTest {

    /**
     * {@link EqualAngle} builds an angle equal to the passed angle.
     */
    @Test
    public void buildsEqualAngle() {
        final Angle<Double> ref = new VectsAngle<>(
            new RandomPoint<>(), new RandomPoint<>(), new RandomPoint<>()
        );
        final Angle<Double> angle = new EqualAngle<>(ref);
        final double error = 1.e-6;
        MatcherAssert.assertThat(
            new VectsDegrees<>(
                ref.start(), ref.end()
            ).resolve(new Dot()).doubleValue(),
            Matchers.closeTo(
                new VectsDegrees<>(
                    angle.start(), angle.end()
                ).resolve(new Dot()).doubleValue(),
                error
            )
        );
    }

    /**
     * {@link EqualAngle} builds an angle equal to the passed angle,
     * with the given origin and starting vector.
     */
    @Test
    public void buildsEqualAngleWithOriginAndStart() {
        final Angle<Double> ref = new VectsAngle<>(
            new RandomPoint<>(), new RandomPoint<>(), new RandomPoint<>()
        );
        final Vect<Double> origin = new RandomPoint<>();
        final Vect<Double> start = new RandomPoint<>();
        final Angle<Double> angle = new EqualAngle<>(origin, start, ref);
        final double error = 1.e-6;
        MatcherAssert.assertThat(
            new VectsDegrees<>(
                ref.start(), ref.end()
            ).resolve(new Dot()).doubleValue(),
            Matchers.closeTo(
                new VectsDegrees<>(
                    angle.start(), angle.end()
                ).resolve(new Dot()).doubleValue(),
                error
            )
        );
        MatcherAssert.assertThat(angle.origin(), Matchers.equalTo(origin));
        MatcherAssert.assertThat(angle.start(), Matchers.equalTo(start));
    }

    /**
     * {@link EqualAngle} toString prints origin and starting vectors.
     */
    @Test
    public void printsAttributes() {
        final Angle<Double> ref = new VectsAngle<>(
            new RandomPoint<>(), new RandomPoint<>(), new RandomPoint<>()
        );
        final Vect<Double> origin = new RandomPoint<>();
        final Vect<Double> start = new RandomPoint<>();
        final Angle<Double> angle = new EqualAngle<>(origin, start, ref);
        MatcherAssert.assertThat(
            angle.toString(), Matchers.containsString(origin.toString())
        );
        MatcherAssert.assertThat(
            angle.toString(), Matchers.containsString(start.toString())
        );
    }
}
