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
import com.jeometry.geometry.twod.point.XyVector;
import com.jeometry.geometry.twod.ray.Ray;
import com.jeometry.model.algebra.field.Field;
import com.jeometry.model.algebra.scalar.Scalar;
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
        final Scalar slope = analytics.slope();
        final Scalar intercept = analytics.intercept();
        final int width = context.width();
        final int height = context.height();
        final int scale = context.scale();
        final XyVector origin = (XyVector) ray.origin();
        final Double xcoor = context.center().dblx();
        final Double ycoor = context.center().dbly();
        final Double xdir = this.field().actual(ray.direction().coords()[0]);
        final Double dblslope = this.field().actual(slope);
        final Double dblintercept = this.field().actual(intercept);
        final Double xorigin = this.field().actual(origin.xcoor());
        final Double yorigin = this.field().actual(origin.ycoor());
        final int xzero = width/2 + (int) (scale * (xorigin - xcoor));
        final int yzero = height/2 - (int) (scale * (yorigin - ycoor));
        if (xdir > 0) {
        final int ywidth = height / 2
            - (int) (
                (width / 2 + scale * xcoor) * dblslope
                + scale * dblintercept
                - ycoor * scale
            );
        
        graphics.drawLine( xzero,yzero, width, ywidth);
        }else {
            final int yzer = height / 2
                - (int) (
                    (scale * xcoor - width / 2) * dblslope
                    + scale * dblintercept
                    - ycoor * scale
                );
            
            graphics.drawLine( xzero,yzero, 0, yzer);
        }
    }

}
