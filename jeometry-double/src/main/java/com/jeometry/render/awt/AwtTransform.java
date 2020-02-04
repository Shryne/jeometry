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
import com.aljebra.vector.Vect;
import com.jeometry.model.decimal.DblPoint;
import com.jeometry.twod.point.XyPoint;
import java.awt.Point;

/**
 * Class transforming double coordinates to Awt graphics coordinates.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class AwtTransform {

    /**
     * Awt context.
     */
    private final AwtContext ctx;

    /**
     * Field for scalar operations.
     */
    private final Field<Double> field;

    /**
     * Ctor.
     * @param ctx Awt context
     */
    public AwtTransform(final AwtContext ctx) {
        this(ctx, new Decimal());
    }

    /**
     * Ctor.
     * @param ctx Awt context
     * @param field Field for scalar operations
     */
    public AwtTransform(final AwtContext ctx, final Field<Double> field) {
        this.ctx = ctx;
        this.field = field;
    }

    /**
     * Applies transformation.
     * @param point DblPoint input
     * @return AWT Point
     */
    public Point transform(final DblPoint point) {
        final double scale = this.ctx.scale();
        return new Point(
            (int) (
                this.ctx.width() / 2d
                + scale * point.dblx() - this.ctx.center().dblx() * scale
            ),
            (int) (
                this.ctx.height() / 2d
                - scale * point.dbly() + this.ctx.center().dbly() * scale
            )
        );
    }

    /**
     * Applies transformation.
     * @param point DblPoint input
     * @return AWT Point
     */
    public Point transform(final XyPoint point) {
        return this.transform(
            new DblPoint(
                this.field.actual(point.xcoor()),
                this.field.actual(point.ycoor())
            )
        );
    }

    /**
     * Applies transformation.
     * @param point Vector input
     * @return AWT Point
     */
    public Point transform(final Vect point) {
        return this.transform(
            new DblPoint(
                this.field.actual(point.coords()[0]),
                this.field.actual(point.coords()[1])
            )
        );
    }

    /**
     * Applies inverse transformation.
     * @param point DblPoint input
     * @return AWT Point
     */
    public DblPoint inverse(final Point point) {
        final double scale = this.ctx.scale();
        final DblPoint center = this.ctx.center();
        return new DblPoint(
            (point.x - this.ctx.width() / 2d) / scale + center.dblx(),
            (this.ctx.height() / 2d - point.y) / scale + center.dbly()
        );
    }
}
