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
package com.jeometry.twod.segment;

import com.aljebra.field.Field;
import com.aljebra.field.impl.doubles.Decimal;
import com.aljebra.vector.VectEquals;
import com.jeometry.twod.point.PtReflectionPoint;
import com.jeometry.twod.point.RandomPoint;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link PtReflectionSegment}.
 * @since 0.1
 */
public final class PtReflectionSegmentTest {

    /**
     * {@link PtReflectionSegment} can build the reflection segment given
     * the reflection center.
     */
    @Test
    public void buildsReflection() {
        final RandomPoint<Double> start = new RandomPoint<>();
        final RandomPoint<Double> end = new RandomPoint<>();
        final RandomPoint<Double> across = new RandomPoint<>();
        final Segment<Double> result = new PtReflectionSegment<>(
            across, new PtsSegment<>(start, end)
        );
        final Field<Double> dec = new Decimal();
        MatcherAssert.assertThat(
            new VectEquals<>(
                result.start(), new PtReflectionPoint<>(across, start)
            ).resolve(dec),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new VectEquals<>(
                result.end(), new PtReflectionPoint<>(across, end)
            ).resolve(dec),
            Matchers.is(true)
        );
    }

    /**
     * {@link PtReflectionSegment} can build the reflection segment
     * across the origin if not given the center.
     */
    @Test
    public void buildsReflectionAcrossOrigin() {
        final RandomPoint<Double> start = new RandomPoint<>();
        final RandomPoint<Double> end = new RandomPoint<>();
        final Segment<Double> result = new PtReflectionSegment<>(
            new PtsSegment<>(start, end)
        );
        final Field<Double> dec = new Decimal();
        MatcherAssert.assertThat(
            new VectEquals<>(
                result.start(), new PtReflectionPoint<>(start)
            ).resolve(dec),
            Matchers.is(true)
        );
        MatcherAssert.assertThat(
            new VectEquals<>(
                result.end(), new PtReflectionPoint<>(end)
            ).resolve(dec),
            Matchers.is(true)
        );
    }

}
