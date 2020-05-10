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
package com.jeometry.twod.style.impl;

import com.jeometry.twod.style.Dash;
import com.jeometry.twod.style.Stroke;
import com.jeometry.twod.style.Style;
import java.awt.Color;
import java.util.Random;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link StrokeStyle}.
 * @since 0.1
 */
public final class StrokeStyleTest {

    /**
     * {@link StrokeStyle} defines a default fill style.
     */
    @Test
    public void defaultsToTransparentFill() {
        final Color color = Mockito.mock(Color.class);
        final Random rnd = new Random();
        final float width = rnd.nextFloat();
        final Dash[] values = Dash.values();
        final Dash pattern = values[rnd.nextInt(values.length)];
        final Style style = new StrokeStyle(color, pattern, width);
        MatcherAssert.assertThat(
            style.fillStyle(), Matchers.instanceOf(TransparentFill.class)
        );
    }

    /**
     * {@link StrokeStyle} defines a stroke style that responds with
     * correct width, pattern and color.
     */
    @Test
    public void definesStroke() {
        final Color color = Mockito.mock(Color.class);
        final Random rnd = new Random();
        final float width = rnd.nextFloat();
        final Dash[] values = Dash.values();
        final Dash pattern = values[rnd.nextInt(values.length)];
        final Stroke stroke = new StrokeStyle(color, pattern, width).strokeStyle();
        MatcherAssert.assertThat(stroke.width(), Matchers.equalTo(width));
        MatcherAssert.assertThat(stroke.dash(), Matchers.equalTo(pattern));
        MatcherAssert.assertThat(stroke.color(), Matchers.equalTo(color));
    }

}
