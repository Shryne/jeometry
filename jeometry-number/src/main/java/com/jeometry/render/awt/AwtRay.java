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
import com.jeometry.geometry.twod.line.LineAnalytics;
import com.jeometry.geometry.twod.line.RayLine;
import com.jeometry.geometry.twod.point.XyPoint;
import com.jeometry.geometry.twod.ray.Ray;
import com.jeometry.model.algebra.field.Field;
import com.jeometry.model.decimal.Decimal;
import java.awt.Graphics2D;

/**
 * Awt Ray painter that draws a ray on an AWT graphics.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class AwtRay extends AbstractAwtPaint {

    /**
     * Ctor.
     * @param field Field for scalar operations
     */
    public AwtRay(final Field<Double> field) {
        super(field, Ray.class);
    }

    /**
     * Ctor.
     */
    public AwtRay() {
        this(new Decimal());
    }

    @Override
    public void draw(final Shape renderable, final Graphics2D graphics,
        final AwtContext context) {
        final Ray ray = (Ray) renderable.renderable();
        final LineAnalytics analytics = new LineAnalytics(new RayLine(ray), this.field());
        if (analytics.vertical()) {
            this.vertical(graphics, ray, context);
        } else {
            this.regular(graphics, analytics, context, ray);
        }
        
    }

    /**
     * Draws a regular (non-vertical) ray.
     * @param graphics Awt Graphics to draw upon
     * @param analytics Analytics of the ray to draw
     * @param context AwtContext
     * @param ray Ray to draw
     */
    private void regular(final Graphics2D graphics,
        final LineAnalytics analytics, final AwtContext context, final Ray ray) {
        final int width = context.width();
        final int height = context.height();
        final int scale = context.scale();
        final Field<Double> field = this.field();
        final XyPoint origin = (XyPoint) ray.origin();
        final Double xcenter = context.center().dblx();
        final Double ycenter = context.center().dbly();
        final Double xdir = field.actual(ray.direction().coords()[0]);
        final Double slope = field.actual(analytics.slope());
        final Double intercept = field.actual(analytics.intercept());
        final int xorigin = (int) (width / 2d + scale * (field.actual(origin.xcoor()) - xcenter));
        final int yorigin = (int) (height / 2d - scale * (field.actual(origin.ycoor()) - ycenter));
        if (xdir > 0) {
            final int ywidth = (int) (
                height / 2d - (
                    (width / 2d + scale * xcenter) * slope
                    + scale * intercept
                    - ycenter * scale
                )
            );
            graphics.drawLine(xorigin, yorigin, width, ywidth);
        }else {
            final int yzer = (int) (
                height / 2d - (
                    (scale * xcenter - width / 2d) * slope
                    + scale * intercept
                    - ycenter * scale
                )
            );
            graphics.drawLine(xorigin, yorigin, 0, yzer);
        }
    }

    /**
     * Draws a vertical ray.
     * @param graphics Awt Graphics to draw upon
     * @param ray Ray to draw
     * @param context AwtContext
     */
    private void vertical(final Graphics2D graphics, final Ray ray,
        final AwtContext context) {
        final int width = context.width();
        final int height = context.height();
        final int scale = context.scale();
        final Field<Double> field = this.field();
        final Double xcenter = context.center().dblx();
        final Double ycenter = context.center().dbly();
        final XyPoint origin = (XyPoint) ray.origin();
        final int xorigin = (int) (width / 2d + scale * (field.actual(origin.xcoor()) - xcenter));
        final int yorigin = (int) (height / 2d - scale * (field.actual(origin.ycoor()) - ycenter));
        if (field.actual(ray.direction().coords()[1]) > 0) {
            graphics.drawLine(xorigin, yorigin, xorigin, 0);
        } else {
            graphics.drawLine(xorigin, yorigin, xorigin, height);
        }
    }

}
