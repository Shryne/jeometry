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
package com.jeometry.twod.line;

import com.aljebra.vector.Vect;
import com.jeometry.twod.point.RandomPoint;
import java.util.Arrays;
import java.util.Iterator;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link Polygon}.
 * @since 0.1
 */
public final class PolygonTest {

    /**
     * {@link Polygon} builds a polygon with the passed points.
     */
    @Test
    public void buildPolygonWithPoints() {
        final RandomPoint<Object> pointa = new RandomPoint<>();
        final RandomPoint<Object> pointb = new RandomPoint<>();
        final RandomPoint<Object> pointc = new RandomPoint<>();
        final RandomPoint<Object> pointd = new RandomPoint<>();
        final Polyline<Object> poly = new Polygon<>(Arrays.asList(pointa, pointb, pointc, pointd));
        final Iterator<Vect<Object>> points = poly.points().iterator();
        MatcherAssert.assertThat(
            points.next(), Matchers.equalTo(pointa)
        );
        MatcherAssert.assertThat(
            points.next(), Matchers.equalTo(pointb)
        );
        MatcherAssert.assertThat(
            points.next(), Matchers.equalTo(pointc)
        );
        MatcherAssert.assertThat(
            points.next(), Matchers.equalTo(pointd)
        );
        MatcherAssert.assertThat(
            points.next(), Matchers.equalTo(pointa)
        );
    }
}
