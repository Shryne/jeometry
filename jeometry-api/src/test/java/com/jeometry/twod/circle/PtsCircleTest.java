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
package com.jeometry.twod.circle;

import com.aljebra.field.Field;
import com.aljebra.field.impl.doubles.Decimal;
import com.aljebra.metric.scalar.Norm;
import com.aljebra.vector.Minus;
import com.jeometry.twod.point.RandomPoint;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link PtsCircle}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class PtsCircleTest {

    /**
     * {@link PtsCircle} builds a circle with correct center and point.
     */
    @Test
    public void buildsCircle() {
        final RandomPoint center = new RandomPoint();
        final RandomPoint point = new RandomPoint();
        final Field<Double> dec = new Decimal();
        final Circle circle = new PtsCircle(center, point);
        MatcherAssert.assertThat(
            circle.center(), Matchers.equalTo(center)
        );
        final double error = 1.e-6;
        MatcherAssert.assertThat(
            dec.actual(circle.radius()),
            Matchers.closeTo(
                dec.actual(new Norm(new Minus(point, center))), error
            )
        );
    }
}
