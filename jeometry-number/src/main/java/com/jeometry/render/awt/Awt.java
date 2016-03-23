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

import com.jeometry.geometry.twod.Renderable;
import com.jeometry.main.Figure;
import com.jeometry.render.Output;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Represents an AWT JFrame {@link Output} drawing the figure on an AWT window.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class Awt extends JFrame implements Output {

    /**
     * Serial version ID.
     */
    private static final long serialVersionUID = 3449434902800801695L;

    /**
     * Translate increment/decrement amount.
     */
    private static final int TRANSLATE_AMOUNT = 2;

    /**
     * Panel border inset.
     */
    private static final int BORDER_INSET = 5;

    /**
     * List of {@link AbstractAwtPaint}s to paint shapes.
     */
    private List<AbstractAwtPaint> painters;

    /**
     * Reference to the figure to draw.
     */
    private Figure figure;

    /**
     * Drawable Panel.
     */
    private AwtDrawableSurface drawable;

    /**
     * Ctor. Builds a {@link JFrame} with a drawable surface and 4 control
     * buttons.
     */
    public Awt() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        final JPanel content = new JPanel();
        content.setBorder(
            new EmptyBorder(
                Awt.BORDER_INSET, Awt.BORDER_INSET,
                Awt.BORDER_INSET, Awt.BORDER_INSET
            )
        );
        setContentPane(content);
        content.setLayout(new BorderLayout(0, 0));
        this.drawable = new AwtDrawableSurface(this);
        content.add(this.drawable, BorderLayout.CENTER);
        final JPanel buttons = this.buttons();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        content.add(buttons, BorderLayout.EAST);
        this.painters = Awt.init();
    }

    @Override
    public void render(final Figure fig) {
        this.figure = fig;
        this.repaint();
        this.setVisible(true);
    }

    @Override
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        final Graphics2D surface = (Graphics2D) this.drawable.getGraphics();
        final RenderingHints hints = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        surface.setRenderingHints(hints);
        final AwtContext context = this.drawable.context();
        surface.setColor(Color.BLACK);
        for (final AbstractAwtPaint painter : this.painters) {
            painter.setGraphics(surface);
            painter.setContext(context);
            for (final Renderable shape : this.figure.getShapes()) {
                painter.render(shape);
            }
        }
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
    public Awt add(final AbstractAwtPaint painter) {
        this.painters.add(painter);
        return this;
    }

    /**
     * Initialize with default painters.
     * @return A list of default painters
     */
    private static List<AbstractAwtPaint> init() {
        final List<AbstractAwtPaint> result = new ArrayList<>(5);
        result.add(new AwtPoint());
        result.add(new AwtCircle());
        result.add(new AwtLine());
        return result;
    }

    /**
     * Initializes control buttons.
     * @return A {@link JPanel} containing control buttons
     */
    private JPanel buttons() {
        final JPanel buttons = new JPanel();
        final JButton ups = new JButton("up");
        buttons.add(ups);
        final JButton down = new JButton("down");
        buttons.add(down);
        final JButton right = new JButton("right");
        buttons.add(right);
        final JButton left = new JButton("left");
        buttons.add(left);
        final JButton zoomin = new JButton("zoomin");
        buttons.add(zoomin);
        final JButton zoomout = new JButton("zoomout");
        buttons.add(zoomout);
        ups.addMouseListener(new Translate(0, Awt.TRANSLATE_AMOUNT));
        down.addMouseListener(new Translate(0, -Awt.TRANSLATE_AMOUNT));
        right.addMouseListener(new Translate(Awt.TRANSLATE_AMOUNT, 0));
        left.addMouseListener(new Translate(-Awt.TRANSLATE_AMOUNT, 0));
        zoomin.addMouseListener(
            new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent event) {
                    Awt.this.drawable.zoomIn();
                    Awt.this.repaint();
                }
            }
        );
        zoomout.addMouseListener(
            new MouseAdapter() {
                @Override
                public void mouseClicked(final MouseEvent event) {
                    Awt.this.drawable.zoomOut();
                    Awt.this.repaint();
                }
            }
        );
        return buttons;
    }

    /**
     * Mouse listener translating drawable surface when clicking
     * control buttons.
     * @author Hamdi Douss (douss.hamdi@gmail.com)
     * @version $Id$
     * @since 0.1
     */
    private class Translate extends MouseAdapter {

        /**
         * X axis translation.
         */
        private final double xtrans;

        /**
         * Y axis translation.
         */
        private final double ytrans;

        /**
         * Ctor.
         * @param xtrans X axis translation
         * @param ytrans Y axis translation
         */
        Translate(final double xtrans, final double ytrans) {
            this.xtrans = xtrans;
            this.ytrans = ytrans;
        }

        @Override
        public void mouseClicked(final MouseEvent event) {
            Awt.this.drawable.translateX(this.xtrans);
            Awt.this.drawable.translateY(this.ytrans);
            Awt.this.repaint();
        }
    }

}
