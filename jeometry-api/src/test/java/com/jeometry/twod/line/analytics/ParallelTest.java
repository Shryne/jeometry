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
package com.jeometry.twod.line.analytics;

import com.aljebra.field.impl.doubles.Decimal;
import com.jeometry.twod.line.Line;
import com.jeometry.twod.line.PtDirLine;
import com.jeometry.twod.line.RandomLine;
import com.jeometry.twod.point.DifferentPoint;
import com.jeometry.twod.point.RandomPoint;
import com.jeometry.twod.point.VertPoint;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link Parallel}.
 * @since 0.1
 */
public final class ParallelTest {

    /**
     * {@link Parallel} resolves to true if the two lines are vertical.
     */
    @Test
    public void resolvesTrueIfBothVertical() {
        MatcherAssert.assertThat(
            new Parallel(
                new PtDirLine(new RandomPoint(), new VertPoint()),
                new PtDirLine(new RandomPoint(), new VertPoint())
            ).resolve(new Decimal()),
            Matchers.is(true)
        );
    }

    /**
     * {@link Parallel} resolves to false if only one line is vertical.
     */
    @Test
    public void resolvesFalseIfOneVertical() {
        MatcherAssert.assertThat(
            new Parallel(
                new PtDirLine(new RandomPoint(), new VertPoint()),
                new PtDirLine(
                    new RandomPoint(), new DifferentPoint(new VertPoint())
                )
            ).resolve(new Decimal()),
            Matchers.is(false)
        );
    }

    /**
     * {@link Parallel} resolves to false when lines have different directions.
     */
    @Test
    public void resolvesFalseIfDifferentDirs() {
        final Line any = new RandomLine();
        MatcherAssert.assertThat(
            new Parallel(
                any,
                new PtDirLine(
                    new RandomPoint(), new DifferentPoint(any.direction())
                )
            ).resolve(new Decimal()),
            Matchers.is(false)
        );
    }

    /**
     * {@link Parallel} resolves to true when lines have same directions.
     */
    @Test
    public void resolvesTrueIfSameDirs() {
        final Line any = new RandomLine();
        MatcherAssert.assertThat(
            new Parallel(
                any, new PtDirLine(new RandomPoint(), any.direction())
            ).resolve(new Decimal()),
            Matchers.is(true)
        );
    }
}
