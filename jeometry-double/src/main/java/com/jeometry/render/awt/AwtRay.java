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

import com.aljebra.field.Field;
import com.aljebra.field.impl.Decimal;
import com.jeometry.geometry.twod.Shape;
import com.jeometry.geometry.twod.line.LineAnalytics;
import com.jeometry.geometry.twod.line.RayLine;
import com.jeometry.geometry.twod.ray.Ray;
import com.jeometry.model.decimal.DblPoint;
import java.awt.Graphics2D;
import java.awt.Point;

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
        final LineAnalytics analytics = new LineAnalytics(
            new RayLine(ray), this.field()
        );
        if (analytics.vertical()) {
            this.vertical(graphics, ray, context);
        } else {
            this.regular(graphics, context, ray);
        }
    }

    /**
     * Draws a regular (non-vertical) ray.
     * @param graphics Awt Graphics to draw upon
     * @param ctxt AwtContext
     * @param ray Ray to draw
     */
    private void regular(final Graphics2D graphics, final AwtContext ctxt,
        final Ray ray) {
        final LineAnalytics analytics = new LineAnalytics(
            new RayLine(ray), this.field()
        );
        final int width = ctxt.width();
        final Field<Double> field = this.field();
        final Double xdir = field.actual(ray.direction().coords()[0]);
        final Double slope = field.actual(analytics.slope());
        final Double intercept = field.actual(analytics.intercept());
        final AwtTransform transform = new AwtTransform(ctxt);
        final Point origin = transform.transform(ray.origin());
        final Double limit;
        if (xdir > 0) {
            limit = transform.inverse(new Point(width, 0)).dblx();
        } else {
            limit = transform.inverse(new Point(0, 0)).dblx();
        }
        final Point end = transform.transform(
            new DblPoint(limit, limit * slope + intercept)
        );
        graphics.drawLine(origin.x, origin.y, end.x, end.y);
    }

    /**
     * Draws a vertical ray.
     * @param graphics Awt Graphics to draw upon
     * @param ray Ray to draw
     * @param context AwtContext
     */
    private void vertical(final Graphics2D graphics, final Ray ray,
        final AwtContext context) {
        final Field<Double> field = this.field();
        final AwtTransform transform = new AwtTransform(context);
        final Point origin = transform.transform(ray.origin());
        if (field.actual(ray.direction().coords()[1]) > 0) {
            graphics.drawLine(origin.x, origin.y, origin.x, 0);
        } else {
            graphics.drawLine(origin.x, origin.y, origin.x, context.height());
        }
    }

}
