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
import com.jeometry.geometry.twod.circle.Circle;
import com.jeometry.model.algebra.field.Field;
import com.jeometry.model.algebra.vector.Vect;
import com.jeometry.model.decimal.Decimal;
import java.awt.Graphics2D;

/**
 * Awt Circle painter that draws a circle on an AWT graphics.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class AwtCircle extends AbstractAwtPaint {

    /**
     * Ctor.
     * @param field Field for scalar operations
     */
    public AwtCircle(final Field<Double> field) {
        super(field, Circle.class);
    }

    /**
     * Ctor.
     */
    public AwtCircle() {
        this(new Decimal());
    }

    @Override
    public void draw(final Shape renderable, final Graphics2D graphics,
        final AwtContext context) {
        final Circle circle = (Circle) renderable.renderable();
        final Vect point = circle.center();
        final int width = context.width();
        final int height = context.height();
        final int scale = context.scale();
        final int size = (int) (scale * this.field().actual(circle.radius()));
        final Double xcoor = context.center().dblx();
        final Double ycoor = context.center().dbly();
        final int xpoint = (int) (
            width / 2 + scale * this.field().actual(point.coords()[0]) - size
            - xcoor * scale
        );
        final int ypoint = (int) (
            height / 2 - scale * this.field().actual(point.coords()[1]) - size
            + ycoor * scale
        );
        graphics.drawOval(xpoint, ypoint, 2 * size, 2 * size);
    }

}
