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
import com.jeometry.geometry.twod.line.Line;
import com.jeometry.geometry.twod.line.LineAnalytics;
import com.jeometry.model.algebra.field.Field;
import com.jeometry.model.algebra.scalar.Scalar;
import java.awt.Graphics2D;

/**
 * Awt Line painter that draws a line on an AWT graphics.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class AwtLine extends AwtPaint {

    /**
     * Ctor.
     * @param field Field for scalar operations
     */
    public AwtLine(Field<Double> field) {
        super(field, Line.class);
    }

    @Override
    protected void draw(final Renderable renderable, final Graphics2D graphics,
        final AwtContext context) {
        final Line line = (Line) renderable;
        final LineAnalytics analytics = new LineAnalytics(line , this.field);
        final Scalar slope = analytics.slope();
        final Scalar intercept = analytics.intercept();
        final int width = context.width();
        final int height = context.height();
        final int scale = context.scale();
        final int yzero = height/2
            - (int)(
                (-width/2) * this.field.actual(slope)
                + scale * this.field.actual(intercept)
            );
        final int ywidth = height/2
            - (int)(
                width/2 * this.field.actual(slope)
                + scale * this.field.actual(intercept)
            );
        graphics.drawLine(0, yzero, width, ywidth);
    }

}
