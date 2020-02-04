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
package com.jeometry.twod.style.impl;

import com.jeometry.twod.style.Dash;
import com.jeometry.twod.style.Stroke;
import java.awt.Color;
import java.util.Random;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link FixedStroke}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class FixedStrokeTest {

    /**
     * {@link FixedStroke} responds with correct width, pattern and color.
     */
    @Test
    public void respondsWithCorrectAttributes() {
        final Color color = Mockito.mock(Color.class);
        final Random rnd = new Random();
        final float width = rnd.nextFloat();
        final Dash[] values = Dash.values();
        final Dash pattern = values[rnd.nextInt(values.length)];
        final Stroke stroke = new FixedStroke(color, pattern, width);
        MatcherAssert.assertThat(stroke.width(), Matchers.equalTo(width));
        MatcherAssert.assertThat(stroke.dash(), Matchers.equalTo(pattern));
        MatcherAssert.assertThat(stroke.color(), Matchers.equalTo(color));
    }

    /**
     * {@link FixedStroke} responds with 1px, solid, black stroke when built
     * with default constructor.
     */
    @Test
    public void respondsWithDefaultAttributes() {
        final Stroke stroke = new FixedStroke();
        MatcherAssert.assertThat(stroke.width(), Matchers.equalTo(1f));
        MatcherAssert.assertThat(stroke.dash(), Matchers.equalTo(Dash.SOLID));
        MatcherAssert.assertThat(stroke.color(), Matchers.equalTo(Color.BLACK));
    }
}
