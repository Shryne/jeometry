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

import com.jeometry.model.decimal.DblPoint;
import java.awt.Dimension;

/**
 * Class representing Awt drawable surface properties: width, height, center
 * and scale(zoom).
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class AwtContext {

    /**
     * Width of drawable surface.
     */
    private final int wdth;

    /**
     * Height of drawable surface.
     */
    private final int hght;

    /**
     * Scale/Zoom of drawable surface.
     */
    private final double scle;

    /**
     * Point on which drawable surface is centered.
     */
    private final DblPoint cntr;

    /**
     * Ctor.
     * @param dim Dimensions of drawable surface.
     * @param scale Zoom of drawable surface.
     * @param center Point on which drawable surface is centered
     */
    public AwtContext(final Dimension dim, final double scale,
        final DblPoint center) {
        this.wdth = dim.width;
        this.hght = dim.height;
        this.scle = scale;
        this.cntr = center;
    }

    /**
     * Accessor for the height.
     * @return Height of drawable surface
     */
    public int height() {
        return this.hght;
    }

    /**
     * Accessor for the width.
     * @return Width of drawable surface
     */
    public int width() {
        return this.wdth;
    }

    /**
     * Accessor for the scale.
     * @return Scale of drawable surface
     */
    public double scale() {
        return this.scle;
    }

    /**
     * Accessor for the center.
     * @return Scale of drawable surface
     */
    public DblPoint center() {
        return this.cntr;
    }

}
