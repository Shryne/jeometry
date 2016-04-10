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
package com.jeometry.geometry.twod.line;

import com.aljebra.field.Field;
import com.aljebra.scalar.Diff;
import com.aljebra.scalar.Division;
import com.aljebra.scalar.Multiplication;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.Vect;
import com.jeometry.geometry.twod.point.XyPoint;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link LineAnalytics}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class LineAnalyticsTest {

    /**
     * {@link LineAnalytics}can calculate simple analytics.
     */
    @Test
    public void calculateSimpleSlope() {
        final Scalar one = Mockito.mock(Scalar.class);
        final Scalar two = Mockito.mock(Scalar.class);
        final Scalar three = Mockito.mock(Scalar.class);
        final Scalar four = Mockito.mock(Scalar.class);
        final Vect dir = new XyPoint(one, two);
        final Vect point = new XyPoint(three, four);
        final PtDirLine line = new PtDirLine(point, dir);
        final Field<?> field = Mockito.mock(Field.class);
        final LineAnalytics analytics = new LineAnalytics(line, field);
        final Scalar slope = analytics.slope();
        final Scalar intercept = analytics.intercept();
        MatcherAssert.assertThat(
            slope, Matchers.equalTo(new Division(two, one))
        );
        MatcherAssert.assertThat(
            intercept, Matchers.equalTo(
                new Diff(four, new Multiplication(slope, three))
            )
        );
    }
}
