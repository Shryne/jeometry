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
package com.jeometry.twod.style.impl;

import com.jeometry.twod.style.Fill;
import com.jeometry.twod.style.Stroke;
import com.jeometry.twod.style.Style;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link DefaultStyle}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class DefaultStyleTest {

    /**
     * {@link DefaultStyle} gives a default stroke and a default fill styles.
     */
    @Test
    public void defaultsToTransparentAndDefaultStroke() {
        final Style style = new DefaultStyle();
        MatcherAssert.assertThat(
            style.fillStyle(), Matchers.instanceOf(TransparentFill.class)
        );
        MatcherAssert.assertThat(
            style.strokeStyle(), Matchers.instanceOf(DefaultStroke.class)
        );
    }

    /**
     * {@link DefaultStyle} accepts fill style and only defaults stroke style.
     */
    @Test
    public void acceptsFillOverride() {
        final Fill fill = Mockito.mock(Fill.class);
        final Style style = new DefaultStyle(fill);
        MatcherAssert.assertThat(style.fillStyle(), Matchers.equalTo(fill));
        MatcherAssert.assertThat(
            style.strokeStyle(), Matchers.instanceOf(DefaultStroke.class)
        );
    }

    /**
     * {@link DefaultStyle} accepts stroke style and only defaults fill style.
     */
    @Test
    public void acceptsStrokeOverride() {
        final Stroke stroke = Mockito.mock(Stroke.class);
        final Style style = new DefaultStyle(stroke);
        MatcherAssert.assertThat(style.strokeStyle(), Matchers.equalTo(stroke));
        MatcherAssert.assertThat(
            style.fillStyle(), Matchers.instanceOf(TransparentFill.class)
        );
    }
}
