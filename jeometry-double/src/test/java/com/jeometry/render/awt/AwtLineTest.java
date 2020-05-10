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
import com.jeometry.twod.Shape;
import com.jeometry.twod.angle.VectsAngle;
import com.jeometry.twod.line.Line;
import com.jeometry.twod.mock.SpyAngle;
import java.awt.Graphics2D;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link AwtLine}.
 * @since 0.1
 */
public final class AwtLineTest {

    /**
     * {@link AwtLine} renders lines.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void rendersLines() {
        final Line<Double> line = Mockito.mock(Line.class);
        Mockito.when(line.point()).thenReturn(new DblPoint(0., 0.));
        Mockito.when(line.direction()).thenReturn(new DblPoint(1., 1.));
        final AwtLine painter = new AwtLine(new Decimal());
        painter.setContext(new AwtDrawableSurface().context());
        painter.setGraphics(Mockito.mock(Graphics2D.class));
        painter.render(new Shape(line));
        Mockito.verify(line, Mockito.atLeastOnce()).direction();
        Mockito.verify(line, Mockito.atLeastOnce()).point();
    }

    /**
     * {@link AwtLine} renders vertical lines.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void rendersVertical() {
        final Line<Double> line = Mockito.mock(Line.class);
        Mockito.when(line.point()).thenReturn(new DblPoint(0., 0.));
        Mockito.when(line.direction()).thenReturn(new DblPoint(0., 1.));
        final AwtLine painter = new AwtLine(new Decimal());
        painter.setContext(new AwtDrawableSurface().context());
        painter.setGraphics(Mockito.mock(Graphics2D.class));
        painter.render(new Shape(line));
        Mockito.verify(line).direction();
        Mockito.verify(line).point();
    }

    /**
     * {@link AwtLine} does not render other renderables.
     */
    @Test
    public void doesNotRenderOthers() {
        final SpyAngle<Double> render = new SpyAngle<>(
            new VectsAngle<>(new DblPoint(0., 0.), new DblPoint(1., 0.), new DblPoint(0., 1.))
        );
        final AwtLine painter = new AwtLine(new Decimal());
        painter.setContext(new AwtDrawableSurface().context());
        painter.setGraphics(Mockito.mock(Graphics2D.class));
        painter.render(new Shape(render));
        MatcherAssert.assertThat(render.origined(), Matchers.equalTo(false));
    }

}
