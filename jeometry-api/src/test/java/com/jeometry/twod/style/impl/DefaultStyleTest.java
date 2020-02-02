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

import com.jeometry.twod.style.Dash;
import com.jeometry.twod.style.Stroke;
import com.jeometry.twod.style.Style;
import java.awt.Color;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

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
    public void buildsDefaultStyle() {
        final Style style = new DefaultStyle();
        MatcherAssert.assertThat(
            style.fillStyle().color().getAlpha(), Matchers.equalTo(0)
        );
        final Stroke stroke = style.strokeStyle();
        MatcherAssert.assertThat(stroke.color(), Matchers.equalTo(Color.BLACK));
        MatcherAssert.assertThat(stroke.width(), Matchers.equalTo(1.f));
        MatcherAssert.assertThat(stroke.dash(), Matchers.equalTo(Dash.SOLID));
    }
}
