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

import com.jeometry.render.Output;
import com.jeometry.twod.Figure;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Represents an AWT JFrame {@link Output} drawing the figure on an AWT window.
 * @since 0.1
 */
public final class Awt extends JFrame implements Output {

    /**
     * Serial version ID.
     */
    private static final long serialVersionUID = 3449434902800801695L;

    /**
     * Panel border inset.
     */
    private static final int BORDER_INSET = 5;

    /**
     * Drawable Panel.
     */
    private final AwtDrawableSurface drawable;

    /**
     * Whether Components has benn initiated.
     */
    private boolean initiated;

    /**
     * Ctor. Builds a {@link JFrame} with a drawable surface and 4 control
     * buttons.
     */
    public Awt() {
        this(new AwtDrawableSurface());
    }

    /**
     * Ctor. Builds a {@link JFrame} with the passed drawable surface and 4 control
     * buttons.
     * @param drawable Awt drawable surface
     */
    public Awt(final AwtDrawableSurface drawable) {
        super();
        this.drawable = drawable;
    }

    @Override
    public void render(final Figure fig) {
        if (!this.initiated) {
            this.init();
        }
        this.drawable.setFigure(fig);
        this.repaint();
        this.setVisible(true);
    }

    /**
     * Modifies drawable surface size, in coordinates relative size.
     * @param width Width to set in coordinates unit
     * @param height Height to set in coordinates unit
     * @return This awt reference
     */
    public Awt withSize(final int width, final int height) {
        this.drawable.withSize(width, height);
        return this;
    }

    /**
     * Adds an {@link AbstractAwtPaint} to the registered painters.
     * @param painter Painter to add
     * @return This awt reference
     */
    public Awt add(final AbstractAwtPaint<?> painter) {
        this.drawable.add(painter);
        return this;
    }

    /**
     * Builds the component.
     */
    private void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final int posx = 100;
        final int posy = 100;
        final int width = 450;
        final int height = 300;
        this.setBounds(posx, posy, width, height);
        final JPanel content = new JPanel();
        content.setBorder(
            new EmptyBorder(
                Awt.BORDER_INSET, Awt.BORDER_INSET,
                Awt.BORDER_INSET, Awt.BORDER_INSET
            )
        );
        this.setContentPane(content);
        content.setLayout(new BorderLayout(0, 0));
        this.drawable.mouseReact();
        content.add(this.drawable, BorderLayout.CENTER);
        content.add(new Buttons(this.drawable).init(), BorderLayout.EAST);
        this.initiated = true;
    }

}
