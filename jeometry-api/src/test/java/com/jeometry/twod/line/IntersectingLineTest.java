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
package com.jeometry.twod.line;

import com.aljebra.field.impl.doubles.Decimal;
import com.aljebra.vector.Vect;
import com.jeometry.twod.line.analytics.Intersecting;
import com.jeometry.twod.line.analytics.PointInLine;
import com.jeometry.twod.point.RandomPoint;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link IntersectingLine}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class IntersectingLineTest {

    /**
     * {@link IntersectingLine} can build an intersecting line.
     */
    @Test
    public void buildsIntersectingLine() {
        final Line line = new RandomLine();
        MatcherAssert.assertThat(
            new Intersecting(
                line, new IntersectingLine(line)
            ).resolve(new Decimal()),
            Matchers.equalTo(true)
        );
    }

    /**
     * {@link IntersectingLine} can build an intersecting line
     * passing by a point.
     */
    @Test
    public void buildsIntersectingLinePassingByPoint() {
        final Line line = new RandomLine();
        final Vect point = new RandomPoint();
        final IntersectingLine intersecting = new IntersectingLine(line, point);
        MatcherAssert.assertThat(
            new Intersecting(line, intersecting).resolve(new Decimal()),
            Matchers.equalTo(true)
        );
        MatcherAssert.assertThat(
            new PointInLine(point, intersecting).resolve(new Decimal()),
            Matchers.equalTo(true)
        );
    }

}
