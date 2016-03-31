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
package com.jeometry.render.awt;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Mouse listener translating drawable surface when clicking
 * control buttons.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class Translate extends MouseAdapter {

    /**
     * X axis translation.
     */
    private final double xtrans;

    /**
     * Y axis translation.
     */
    private final double ytrans;

    /**
     * AwtDrawableSurface.
     */
    private final AwtDrawableSurface drawable;

    /**
     * Ctor.
     * @param xtrans X axis translation
     * @param ytrans Y axis translation
     * @param drawable Drawable surface
     */
    Translate(final double xtrans, final double ytrans,
        final AwtDrawableSurface drawable) {
        super();
        this.xtrans = xtrans;
        this.ytrans = ytrans;
        this.drawable = drawable;
    }

    @Override
    public void mouseClicked(final MouseEvent event) {
        this.drawable.translate(this.xtrans, this.ytrans);
        this.drawable.repaint();
    }
}
