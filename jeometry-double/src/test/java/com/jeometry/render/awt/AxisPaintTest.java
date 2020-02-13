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

import com.jeometry.model.decimal.DblPoint;
import com.jeometry.render.awt.style.AwtStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.Random;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link AxisPaint}.
 * @since 0.1
 */
public final class AxisPaintTest {

    /**
     * {@link AxisPaint} preserves Graphics color.
     */
    @Test
    public void preservesGraphicsColor() {
        final AwtContext ctx = AxisPaintTest.context();
        final Graphics2D graphics = Mockito.mock(Graphics2D.class);
        Mockito.when(graphics.getColor()).thenReturn(Color.BLUE);
        new AxisPaint(ctx).paint(graphics);
        Mockito.verify(graphics).setColor(Color.BLUE);
    }

    /**
     * {@link AxisPaint} uses default awt stroke.
     */
    @Test
    public void overridesGraphicsStroke() {
        final AwtContext ctx = AxisPaintTest.context();
        final Graphics2D graphics = Mockito.mock(Graphics2D.class);
        new AxisPaint(ctx).paint(graphics);
        Mockito.verify(graphics).setStroke(Mockito.isA(AwtStroke.class));
    }

    /**
     * {@link AxisPaint} draws lines on graphics.
     */
    @Test
    public void drawsLines() {
        final AwtContext ctx = AxisPaintTest.context();
        final Graphics2D graphics = Mockito.mock(Graphics2D.class);
        new AxisPaint(ctx).paint(graphics);
        Mockito.verify(graphics, Mockito.atLeast(2)).drawLine(
            Mockito.anyInt(), Mockito.anyInt(),
            Mockito.anyInt(), Mockito.anyInt()
        );
    }

    /**
     * Builds a random awt context.
     * @return A random {@link AwtContext}
     */
    private static AwtContext context() {
        return new AwtContext(
            new Dimension(new Random().nextInt(), new Random().nextInt()),
            Math.random(),
            new DblPoint(Math.random(), Math.random())
        );
    }
}
