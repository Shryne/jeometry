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

import com.jeometry.model.decimal.DblPoint;
import com.jeometry.render.Surface;
import com.jeometry.twod.Figure;
import com.jeometry.twod.Shape;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.JPanel;

/**
 * AWT JPanel representing the drawing surface.
 * @since 0.1
 */
public final class AwtDrawableSurface extends JPanel {

    /**
     * Serial version ID.
     */
    private static final long serialVersionUID = 2741492657820010553L;

    /**
     * Zoom increment/decrement amount.
     */
    private static final double ZOOM_AMOUNT = 1.5;

    /**
     * Initial zoom value.
     */
    private static final double ZOOM_START = 10;

    /**
     * Scale of the drawable surface.
     */
    private double scale;

    /**
     * List of {@link AbstractAwtPaint}s to paint shapes.
     */
    private final transient List<AbstractAwtPaint> painters;

    /**
     * Reference to the figure to draw.
     */
    private transient Figure figure;

    /**
     * Drawable Panel center.
     */
    private transient DblPoint center;

    /**
     * Ctor. Builds a {@link JPanel} as a drawable surface.
     */
    public AwtDrawableSurface() {
        super();
        this.center = new DblPoint(0., 0.);
        this.scale = AwtDrawableSurface.ZOOM_START;
        this.painters = new Painters().defaults();
    }

    /**
     * Inits listeners on the drawable surface.
     */
    public void mouseReact() {
        final MouseAdapter listener = new MouseZoomTranslate(this);
        this.addMouseMotionListener(listener);
        this.addMouseListener(listener);
        this.addMouseWheelListener(listener);
    }

    @Override
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        final int width = this.getWidth();
        final int height = this.getHeight();
        graphics.clearRect(0, 0, width, height);
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);
        if (graphics instanceof Graphics2D) {
            final Graphics2D surface = (Graphics2D) graphics;
            surface.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            );
            final Surface context = this.context();
            surface.setColor(Color.BLACK);
            for (final AbstractAwtPaint painter : this.painters) {
                painter.setGraphics(surface);
                painter.setContext(context);
                for (final Shape<?> shape : this.figure) {
                    painter.render(shape);
                }
            }
            this.axis(surface);
        }
    }

    /**
     * Gives the {@link Surface}.
     * @return Drawing {@link Surface}
     */
    public Surface context() {
        return new Surface(
            new Dimension(this.getWidth(), this.getHeight()),
            this.scale, this.center
        );
    }

    /**
     * Zooms in the drawable surface.
     */
    public void zoomIn() {
        this.scale *= AwtDrawableSurface.ZOOM_AMOUNT;
    }

    /**
     * Zooms out the drawable surface.
     */
    public void zoomOut() {
        this.scale /= AwtDrawableSurface.ZOOM_AMOUNT;
    }

    /**
     * Translates the center of the drawable surface by the given amount
     * on X and Y Axis.
     * @param amountx Amount to translate by on X-Axis
     * @param amounty Amount to translate by on Y-Axis
     */
    public void translate(final double amountx, final double amounty) {
        this.center = new DblPoint(
            this.center.dblx() + amountx, this.center.dbly() + amounty
        );
    }

    /**
     * Modifies drawable surface size, in coordinates relative size.
     * @param width Width to set in coordinates unit
     * @param height Height to set in coordinates unit
     * @return This drawable surface
     */
    public AwtDrawableSurface withSize(final int width, final int height) {
        this.setSize((int) this.scale * width, (int) this.scale * height);
        return this;
    }

    /**
     * Adds an {@link AbstractAwtPaint} to the registered painters.
     * @param painter Painter to add
     */
    public void add(final AbstractAwtPaint painter) {
        this.painters.add(painter);
    }

    /**
     * Sets the figure to draw.
     * @param fig The figure to draw
     */
    public void setFigure(final Figure fig) {
        this.figure = fig;
    }

    /**
     * Draws X-axis and Y-axis.
     * @param graphics AWT graphics to draw into
     */
    private void axis(final Graphics2D graphics) {
        new AxisPaint(this.context()).paint(graphics);
    }

}
