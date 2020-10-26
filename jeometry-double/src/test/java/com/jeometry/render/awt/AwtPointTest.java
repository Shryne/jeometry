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
package com.jeometry.render.awt;

import com.aljebra.field.impl.doubles.Decimal;
import com.jeometry.twod.Shape;
import com.jeometry.twod.mock.SpyLine;
import com.jeometry.twod.point.RandomPoint;
import com.jeometry.twod.style.Dash;
import com.jeometry.twod.style.impl.StrokeStyle;
import java.awt.Color;
import java.awt.Graphics2D;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link AwtPoint}.
 * @since 0.1
 */
public final class AwtPointTest {

    /**
     * {@link AwtPoint} renders points.
     */
    @Test
    public void rendersPoints() {
        final AwtPoint painter = new AwtPoint(new Decimal());
        final Graphics2D graphics = Mockito.mock(Graphics2D.class);
        painter.render(
            new Shape<>(new RandomPoint<>()),
            new AwtDrawableSurface().context(),
            graphics
        );
        Mockito.verify(graphics).drawRect(
            Mockito.anyInt(), Mockito.anyInt(),
            Mockito.anyInt(), Mockito.anyInt()
        );
    }

    /**
     * {@link AwtPoint} uses right color.
     */
    @Test
    public void usesRightColor() {
        final AwtPoint painter = new AwtPoint(new Decimal());
        final Graphics2D graphics = Mockito.mock(Graphics2D.class);
        painter.render(
            new Shape<>(
                new RandomPoint<>(),
                new StrokeStyle(Color.CYAN, Dash.SOLID, 1.f)
            ),
            new AwtDrawableSurface().context(), graphics
        );
        Mockito.verify(graphics).drawRect(
            Mockito.anyInt(), Mockito.anyInt(),
            Mockito.anyInt(), Mockito.anyInt()
        );
        Mockito.verify(graphics).setColor(Color.CYAN);
    }

    /**
     * {@link AwtPoint} does not render other renderables.
     */
    @Test
    public void doesNotRenderOthers() {
        final SpyLine<Double> render = new SpyLine<>();
        final AwtPoint painter = new AwtPoint(new Decimal());
        painter.render(
            new Shape<>(render),
            new AwtDrawableSurface().context(),
            Mockito.mock(Graphics2D.class)
        );
        MatcherAssert.assertThat(render.directioned(), Matchers.equalTo(false));
        MatcherAssert.assertThat(render.pointed(), Matchers.equalTo(false));
    }

}
