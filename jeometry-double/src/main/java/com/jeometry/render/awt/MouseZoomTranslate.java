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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * Mouse listener translating drawable surface when dragging, and zooming
 * drawable surface when moving mouse wheel.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
class MouseZoomTranslate extends MouseAdapter {

    /**
     * X start dragging position.
     */
    private int startx;

    /**
     * Y start dragging position.
     */
    private int starty;

    /**
     * Parent {@link Awt} instance.
     */
    private final AwtDrawableSurface awt;

    /**
     * Ctor.
     * @param awt Parent {@link Awt} instance
     */
    MouseZoomTranslate(final AwtDrawableSurface awt) {
        super();
        this.awt = awt;
    }

    @Override
    public void mousePressed(final MouseEvent event) {
        this.startx = event.getX();
        this.starty = event.getY();
    }

    @Override
    public void mouseDragged(final MouseEvent event) {
        final double scale = (double) this.awt.context().scale();
        this.awt.translate(
            (this.startx - event.getX()) / scale,
            (event.getY() - this.starty) / scale
        );
        this.startx = event.getX();
        this.starty = event.getY();
        this.awt.repaint();
    }

    @Override
    public void mouseWheelMoved(final MouseWheelEvent event) {
        if (event.getWheelRotation() < 0) {
            this.awt.zoomIn();
        } else {
            this.awt.zoomOut();
        }
        this.awt.repaint();
    }

}
