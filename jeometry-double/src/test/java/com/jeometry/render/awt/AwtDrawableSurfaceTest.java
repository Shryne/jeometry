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
import com.jeometry.model.decimal.DblPoint;
import com.jeometry.twod.Figure;
import com.jeometry.twod.Shape;
import com.jeometry.twod.line.Line;
import com.jeometry.twod.mock.SpyLine;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.util.concurrent.CountDownLatch;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link AwtDrawableSurface}.
 * @since 0.1
 */
public final class AwtDrawableSurfaceTest {

    /**
     * {@link AwtDrawableSurface} can add mouse listener.
     */
    @Test
    public void addsMouseListener() {
        final AwtDrawableSurface surface = new AwtDrawableSurface();
        final int size = surface.getListeners(MouseListener.class).length;
        surface.mouseReact();
        MatcherAssert.assertThat(
            surface.getListeners(MouseListener.class).length,
            Matchers.greaterThan(size)
        );
    }

    /**
     * {@link AwtDrawableSurface} can zoom in.
     */
    @Test
    public void zoomsIn() {
        final AwtDrawableSurface surface = new AwtDrawableSurface();
        final double scale = surface.context().scale();
        surface.zoomIn();
        MatcherAssert.assertThat(
            surface.context().scale(), Matchers.greaterThan(scale)
        );
    }

    /**
     * {@link AwtDrawableSurface} can zoom out.
     */
    @Test
    public void zoomsOut() {
        final AwtDrawableSurface surface = new AwtDrawableSurface();
        final double scale = surface.context().scale();
        surface.zoomOut();
        MatcherAssert.assertThat(
            surface.context().scale(), Matchers.lessThan(scale)
        );
    }

    /**
     * {@link AwtDrawableSurface} can translate its center.
     */
    @Test
    public void translatesCenter() {
        final AwtDrawableSurface surface = new AwtDrawableSurface();
        final DblPoint center = surface.context().center();
        final Double xcoor = center.dblx();
        final Double ycoor = center.dbly();
        final double amountx = Math.random();
        final double amounty = Math.random();
        surface.translate(amountx, amounty);
        MatcherAssert.assertThat(
            surface.context().center(),
            Matchers.equalTo(new DblPoint(xcoor + amountx, ycoor + amounty))
        );
    }

    /**
     * {@link AwtDrawableSurface} adds a painter, and delegates figure rendering
     * to this painter.
     * @throws InterruptedException If fails.
     */
    @Test
    public void addsPainter() throws InterruptedException {
        final AwtDrawableSurface surface = new AwtDrawableSurface();
        final CountDownLatch latch = new CountDownLatch(1);
        final AbstractAwtPaint painter = new AbstractAwtPaint(
            new Decimal(), Line.class
        ) {
            @Override
            protected void draw(final Shape renderable,
                final Graphics2D graphic, final AwtContext ctx) {
                ((Line<?>) renderable.renderable()).direction();
                latch.countDown();
            }
        };
        final SpyLine<Double> line = new SpyLine<>();
        final Figure figure = new Figure().add(line);
        surface.add(painter);
        surface.setFigure(figure);
        surface.paint(Mockito.mock(Graphics2D.class));
        latch.await();
        MatcherAssert.assertThat(line.directioned(), Matchers.equalTo(true));
    }
}
