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

import com.aljebra.field.Field;
import com.aljebra.field.impl.doubles.Decimal;
import com.jeometry.model.decimal.DblPoint;
import com.jeometry.render.awt.style.AwtStroke;
import com.jeometry.twod.Shape;
import com.jeometry.twod.line.PtDirLine;
import com.jeometry.twod.style.impl.DefaultStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Awt Axis painter.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class AxisPaint {

    /**
     * Scalar field.
     */
    private Field<Double> field;

    /**
     * Awt Context.
     */
    private AwtContext ctx;

    /**
     * Ctor.
     * @param field Field for scalar operations
     * @param ctx Awt Context
     */
    public AxisPaint(final Field<Double> field, final AwtContext ctx) {
        this.field = field;
        this.ctx = ctx;
    }

    /**
     * Ctor.
     * @param ctx Awt Context
     */
    public AxisPaint(final AwtContext ctx) {
        this(new Decimal(), ctx);
    }

    /**
     * Paints X and Y Axis on the given graphics.
     * @param graphics Awt graphics
     */
    public void paint(final Graphics2D graphics) {
        final DblPoint origin = new DblPoint(0., 0.);
        final Shape xaxis = new Shape(
            new PtDirLine(origin, new DblPoint(1.0, 0.))
        );
        final Shape yaxis = new Shape(
            new PtDirLine(origin, new DblPoint(0., 1.))
        );
        final Color color = graphics.getColor();
        graphics.setStroke(new AwtStroke(new DefaultStroke()));
        graphics.setColor(Color.RED);
        new AwtLine(this.field).draw(xaxis, graphics, this.ctx);
        new AwtLine(this.field).draw(yaxis, graphics, this.ctx);
        graphics.setColor(color);
    }

}
