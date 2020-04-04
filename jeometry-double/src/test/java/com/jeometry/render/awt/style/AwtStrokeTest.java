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
package com.jeometry.render.awt.style;

import com.jeometry.twod.style.Dash;
import com.jeometry.twod.style.impl.FixedStroke;
import java.awt.Color;
import java.util.Random;
import java.util.stream.IntStream;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link AwtStroke}.
 * @since 0.1
 */
public final class AwtStrokeTest {

    /**
     * {@link AwtStroke} respects width.
     */
    @Test
    public void givesCorrectWidth() {
        final float width = new Random().nextFloat();
        final AwtStroke stroke = new AwtStroke(
            new FixedStroke(Color.BLACK, Dash.SOLID, width)
        );
        MatcherAssert.assertThat(
            stroke.getLineWidth(), Matchers.equalTo(width)
        );
    }

    /**
     * {@link AwtStroke} converts solid style.
     */
    @Test
    public void convertsSolidPattern() {
        final AwtStroke solid = new AwtStroke(
            new FixedStroke(Color.BLACK, Dash.SOLID, 1)
        );
        MatcherAssert.assertThat(
            AwtStrokeTest.objectify(solid.getDashArray()),
            Matchers.arrayContaining(1f, 0f)
        );
    }

    /**
     * {@link AwtStroke} converts dashed style.
     */
    @Test
    public void convertsDashedPattern() {
        final AwtStroke dashed = new AwtStroke(
            new FixedStroke(Color.BLACK, Dash.DASHED, 1)
        );
        final float[] pattern = dashed.getDashArray();
        MatcherAssert.assertThat(pattern.length, Matchers.equalTo(1));
    }

    /**
     * {@link AwtStroke} converts dotted style.
     */
    @Test
    public void convertsDottedPattern() {
        final AwtStroke dotted = new AwtStroke(
            new FixedStroke(Color.BLACK, Dash.DOTTED, 1)
        );
        final float[] pattern = dotted.getDashArray();
        MatcherAssert.assertThat(pattern.length, Matchers.equalTo(2));
        MatcherAssert.assertThat(pattern[0], Matchers.equalTo(1f));
        MatcherAssert.assertThat(pattern[1], Matchers.greaterThan(2f));
    }

    /**
     * Transforms a primitive float array and returns an Object Float array.
     * @param array Input
     * @return Float array
     */
    private static Float[] objectify(final float... array) {
        return IntStream.range(0, array.length).mapToObj(
            i -> new Float(array[i])
        ).toArray(Float[]::new);
    }
}
