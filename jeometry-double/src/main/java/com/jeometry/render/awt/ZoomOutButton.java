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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 * AWT Button that zooms out the surface.
 * @since 0.4
 */
public final class ZoomOutButton extends JButton {

    /**
     * Serial version ID.
     */
    private static final long serialVersionUID = 1196500784803227250L;

    /**
     * Ctor.
     * @param caption Text on the JButton
     */
    public ZoomOutButton(final String caption) {
        super(caption);
    }

    /**
     * Adds a listener to zooms out the given drawable.
     * @param drawable Surface to translate
     * @return This button
     */
    public JButton react(final AwtDrawableSurface drawable) {
        this.addMouseListener(new ZoomOutButton.Listener(drawable));
        return this;
    }

    /**
     * Mouse click listener to zoom out.
     * @since 0.4
     */
    private static final class Listener extends MouseAdapter {
        /**
         * Drawable surface.
         */
        private final AwtDrawableSurface drawable;

        /**
         * Constructor.
         * @param drawable Drawable surface
         */
        private Listener(final AwtDrawableSurface drawable) {
            this.drawable = drawable;
        }

        @Override
        public void mouseClicked(final MouseEvent event) {
            this.drawable.zoomOut();
            this.drawable.repaint();
        }
    }
}
