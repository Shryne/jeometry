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
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Control buttons panel.
 * @since 0.1
 */
public final class Buttons extends JPanel {

    /**
     * Serial version ID.
     */
    private static final long serialVersionUID = -7413652639513091330L;

    /**
     * Translate increment/decrement amount.
     */
    private static final int TRANSLATE_AMOUNT = 2;

    /**
     * Drawable Panel.
     */
    private final AwtDrawableSurface drawable;

    /**
     * Ctor.
     * @param drawable The drawable surface
     */
    public Buttons(final AwtDrawableSurface drawable) {
        super();
        this.drawable = drawable;
    }

    /**
     * Builds the component.
     * @return This buttons JPanel
     */
    public JPanel init() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(this.button("up", 0, Buttons.TRANSLATE_AMOUNT));
        this.add(this.button("down", 0, -Buttons.TRANSLATE_AMOUNT));
        this.add(this.button("right", Buttons.TRANSLATE_AMOUNT, 0));
        this.add(this.button("left", -Buttons.TRANSLATE_AMOUNT, 0));
        final JButton zoomin = new JButton("zoomin");
        this.add(zoomin);
        final JButton zoomout = new JButton("zoomout");
        this.add(zoomout);
        zoomin.addMouseListener(
            new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent event) {
                    Buttons.this.drawable.zoomIn();
                    Buttons.this.drawable.repaint();
                }
            }
        );
        zoomout.addMouseListener(
            new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent event) {
                    Buttons.this.drawable.zoomOut();
                    Buttons.this.drawable.repaint();
                }
            }
        );
        return this;
    }

    /**
     * Builds a translation JButton.
     * @param caption Text on the JButton
     * @param xtrans X translation Amount to trigger
     * @param ytrans Y translation Amount to trigger
     * @return JButton
     */
    private JButton button(final String caption, final double xtrans,
        final double ytrans) {
        final JButton button = new JButton(caption);
        button.addMouseListener(new Translate(xtrans, ytrans, this.drawable));
        return button;
    }

}
