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
package com.jeometry.render.awt;

import com.aljebra.field.impl.doubles.Decimal;
import com.aljebra.scalar.Scalar;
import com.jeometry.twod.Shape;
import com.jeometry.twod.line.Line;
import com.jeometry.twod.point.XyPoint;
import java.awt.Graphics2D;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link AwtPoint}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class AwtPointTest {

    /**
     * {@link AwtPoint} renders points.
     */
    @Test
    public void rendersPoints() {
        final XyPoint point = new XyPoint(
            new Scalar.Default<Double>(Math.random()),
            new Scalar.Default<Double>(Math.random())
        );
        final AwtPoint painter = new AwtPoint(new Decimal());
        painter.setContext(new AwtDrawableSurface().context());
        final Graphics2D graphics = Mockito.mock(Graphics2D.class);
        painter.setGraphics(graphics);
        painter.render(new Shape(point));
        Mockito.verify(graphics).drawRect(
            Mockito.anyInt(), Mockito.anyInt(),
            Mockito.anyInt(), Mockito.anyInt()
        );
    }

    /**
     * {@link AwtPoint} does not render other renderables.
     */
    @Test
    public void doesNotRenderOthers() {
        final Line render = Mockito.mock(Line.class);
        final AwtPoint painter = new AwtPoint(new Decimal());
        painter.setContext(new AwtDrawableSurface().context());
        painter.setGraphics(Mockito.mock(Graphics2D.class));
        painter.render(new Shape(render));
        Mockito.verify(render, Mockito.never()).point();
        Mockito.verify(render, Mockito.never()).direction();
    }

}
