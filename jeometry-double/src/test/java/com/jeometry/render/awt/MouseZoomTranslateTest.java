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

import com.jeometry.model.decimal.DblPoint;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link MouseZoomTranslate}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class MouseZoomTranslateTest {

    /**
     * {@link MouseZoomTranslate} drags to a new center when the user drags
     * the mouse.
     */
    @Test
    public void dragsCenter() {
        final AwtDrawableSurface surface = new AwtDrawableSurface();
        final MouseZoomTranslate mouse = new MouseZoomTranslate(surface);
        final MouseEvent pressed = Mockito.mock(MouseEvent.class);
        final int startx = 50;
        final int starty = 60;
        Mockito.when(pressed.getX()).thenReturn(startx);
        Mockito.when(pressed.getY()).thenReturn(starty);
        final MouseEvent dragged = Mockito.mock(MouseEvent.class);
        final int dragx = 100;
        final int dragy = 50;
        Mockito.when(dragged.getX()).thenReturn(dragx);
        Mockito.when(dragged.getY()).thenReturn(dragy);
        mouse.mousePressed(pressed);
        mouse.mouseDragged(dragged);
        final double scale = surface.context().scale();
        MatcherAssert.assertThat(
            surface.context().center(),
            Matchers.equalTo(
                new DblPoint((startx - dragx) / scale, (dragy - starty) / scale)
            )
        );
    }

    /**
     * {@link MouseZoomTranslate} zooms in and out when mouse wheel is rotated.
     */
    @Test
    public void zoomsInAndOut() {
        final AwtDrawableSurface surface = new AwtDrawableSurface();
        final MouseZoomTranslate mouse = new MouseZoomTranslate(surface);
        final MouseWheelEvent wheel = Mockito.mock(MouseWheelEvent.class);
        Mockito.when(wheel.getWheelRotation()).thenReturn(-1);
        final double scale = surface.context().scale();
        mouse.mouseWheelMoved(wheel);
        MatcherAssert.assertThat(
            surface.context().scale(),
            Matchers.greaterThan(scale)
        );
        Mockito.when(wheel.getWheelRotation()).thenReturn(1);
        mouse.mouseWheelMoved(wheel);
        mouse.mouseWheelMoved(wheel);
        MatcherAssert.assertThat(
            surface.context().scale(),
            Matchers.lessThan(scale)
        );
    }
}
