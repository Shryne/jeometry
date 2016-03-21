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

/**
 * Class representing Awt drawable surface.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class AwtContext {

    /**
     * Width of drawable surface. 
     */
    final private int wdth;

    /**
     * Height of drawable surface.
     */
    final private int hght;
    
    /**
     * Scale/Zoom of drawable surface.
     */
    final private int scle;

    /**
     * Ctor.
     * @param width Width of drawable surface.
     * @param height Height of drawable surface.
     * @param scale Zoom of drawable surface.
     */
    public AwtContext(final int width, final int height, final int scale) {
        this.wdth = width;
        this.hght = height;
        this.scle = scale;
    }

    /**
     * Accessor for the height.
     * @return Height of drawable surface
     */
    public int height() {
        return hght;
    }

    /**
     * Accessor for the width.
     * @return Width of drawable surface
     */
    public int width() {
        return wdth;
    }

    /**
     * Accessor for the scale.
     * @return Scale of drawable surface
     */
    public int scale() {
        return scle;
    }
}
