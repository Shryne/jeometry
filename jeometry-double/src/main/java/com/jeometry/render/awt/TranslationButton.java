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

import javax.swing.JButton;

/**
 * AWT Button that triggers surface translation.
 * @since 0.4
 */
public final class TranslationButton extends JButton {

    /**
     * Serial version ID.
     */
    private static final long serialVersionUID = 4212678788815808659L;

    /**
     * Vertical translation amount.
     */
    private final double ytrans;

    /**
     * Horizontal translation amount.
     */
    private final double xtrans;

    /**
     * Ctor.
     * @param caption Text on the JButton
     * @param xtrans X translation Amount to trigger
     * @param ytrans Y translation Amount to trigger
     */
    public TranslationButton(final String caption, final double xtrans,
        final double ytrans) {
        super(caption);
        this.xtrans = xtrans;
        this.ytrans = ytrans;
    }

    /**
     * Adds a listener to translate the given drawable.
     * @param drawable Surface to translate
     * @return This button
     */
    public JButton react(final AwtDrawableSurface drawable) {
        this.addMouseListener(new Translate(this.xtrans, this.ytrans, drawable));
        return this;
    }

}
