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

import com.jeometry.geometry.twod.Shape;
import com.jeometry.geometry.twod.line.Line;
import com.jeometry.geometry.twod.line.LineAnalytics;
import com.jeometry.model.algebra.field.Field;
import com.jeometry.model.decimal.Decimal;
import java.awt.Graphics2D;

/**
 * Awt Line painter that draws a line on an AWT graphics.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class AwtLine extends AbstractAwtPaint {

    /**
     * Ctor.
     * @param field Field for scalar operations
     */
    public AwtLine(final Field<Double> field) {
        super(field, Line.class);
    }

    /**
     * Ctor.
     */
    public AwtLine() {
        this(new Decimal());
    }

    @Override
    public void draw(final Shape renderable, final Graphics2D graphics,
        final AwtContext context) {
        final Line line = (Line) renderable.renderable();
        final LineAnalytics analytics = new LineAnalytics(line, this.field());
        if (analytics.vertical()) {
            this.vertical(graphics, line, context);
        } else {
            this.regular(graphics, analytics, context);
        }
        
    }

    /**
     * Draws a regular (non-vertical) line.
     * @param graphics Awt Graphics to draw upon
     * @param analytics Analytics of the line to draw
     * @param context AwtContext
     */
    private void regular(final Graphics2D graphics,
        final LineAnalytics analytics, final AwtContext context) {
        final int width = context.width();
        final int height = context.height();
        final int scale = context.scale();
        final Double xcenter = context.center().dblx();
        final Double ycenter = context.center().dbly();
        final Double slope = this.field().actual(analytics.slope());
        final Double intercept = this.field().actual(analytics.intercept());
        final int yzero = (int) (
            height / 2d - (
                (scale * xcenter - width / 2d) * slope
                + scale * intercept
                - ycenter * scale
            )
        );
        final int ywidth = (int) (
            height / 2d - (
                (width / 2d + scale * xcenter) * slope
                + scale * intercept
                - ycenter * scale
            )
        );
        graphics.drawLine(0, yzero, width, ywidth);
    }

    /**
     * Draws a vertical line.
     * @param graphics Awt Graphics to draw upon
     * @param line Line to draw
     * @param context AwtContext
     */
    private void vertical(final Graphics2D graphics, final Line line,
        final AwtContext context) {
        final int width = context.width();
        final int height = context.height();
        final int scale = context.scale();
        final Double xcenter = context.center().dblx();
        final int xline = (int)(
            width / 2d
            + scale * (this.field().actual(line.point().coords()[0])-xcenter)
        );
        graphics.drawLine(xline, 0, xline, height);
    }

}
