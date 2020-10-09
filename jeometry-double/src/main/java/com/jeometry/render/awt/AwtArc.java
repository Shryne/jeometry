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

import com.aljebra.field.Field;
import com.aljebra.field.impl.doubles.Decimal;
import com.jeometry.render.Surface;
import com.jeometry.render.Transform;
import com.jeometry.twod.Shape;
import com.jeometry.twod.arc.Arc;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 * Awt Arc painter that draws an arc on an AWT graphics.
 * @since 0.4
 */
public final class AwtArc extends AbstractAwtPaint<Arc<Double>> {

    /**
     * Ctor.
     * @param field Field for scalar operations
     */
    public AwtArc(final Field<Double> field) {
        super(field, Arc.class);
    }

    /**
     * Ctor.
     */
    public AwtArc() {
        this(new Decimal());
    }

    @Override
    public void draw(final Shape<Arc<Double>> renderable, final Graphics2D graphics,
        final Surface context) {
        final Arc<Double> arc = renderable.renderable();
        final int size = (int) (context.scale() * this.field().actual(
            arc.radius()
        ));
        final Point center = new Transform(context)
            .transform(arc.center());
        final int start = AwtArc.degrees(arc.start());
        final int end = AwtArc.degrees(arc.end());
        graphics.drawArc(center.x - size, center.y - size, 2 * size, 2 * size, start, start - end);
    }

    /**
     * Converts radian angle to degrees angle.
     * @param radians Angle to convert
     * @return Conversion result
     */
    private static int degrees(final Number radians) {
        final Double degs = Double.valueOf(Math.toDegrees(radians.doubleValue()));
        return degs.intValue();
    }

}
