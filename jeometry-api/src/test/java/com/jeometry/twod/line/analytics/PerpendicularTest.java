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
package com.jeometry.twod.line.analytics;

import com.aljebra.field.impl.doubles.Decimal;
import com.jeometry.twod.line.Line;
import com.jeometry.twod.line.ParallelLine;
import com.jeometry.twod.line.PerpLine;
import com.jeometry.twod.line.RandomLine;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link Perpendicular}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class PerpendicularTest {

    /**
     * {@link Perpendicular} resolves to true when lines are perpendicular.
     */
    @Test
    public void resolvesTrueIfPerp() {
        final Line any = new RandomLine();
        MatcherAssert.assertThat(
            new Perpendicular(any, new PerpLine(any)).resolve(new Decimal()),
            Matchers.is(true)
        );
    }

    /**
     * {@link Perpendicular} resolves to false when lines are parallel.
     */
    @Test
    public void resolvesfalseIfParallel() {
        final Line any = new RandomLine();
        MatcherAssert.assertThat(
            new Perpendicular(
                any, new ParallelLine(any)
            ).resolve(new Decimal()),
            Matchers.is(false)
        );
    }

    /**
     * {@link Perpendicular} can accept lines or vectors.
     */
    @Test
    public void acceptsLinesOrVects() {
        final Line any = new RandomLine();
        MatcherAssert.assertThat(
            new Perpendicular(
                any.direction(), new PerpLine(any).direction()
            ).resolve(new Decimal()),
            Matchers.is(true)
        );
    }
}
