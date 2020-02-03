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
package com.jeometry.render.awt.style;

import com.jeometry.twod.style.Dash;
import com.jeometry.twod.style.Stroke;
import java.awt.BasicStroke;

/**
 * An implementation of {@link BasicStroke} based on {@link Stroke}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class AwtStroke extends BasicStroke {

    /**
     * Float array constant representing dashed line in Awt {@link BasicStroke}
     * format.
     */
    private static final float[] DASHED = {8};

    /**
     * Float array constant representing dotted line in Awt {@link BasicStroke}
     * format.
     */
    private static final float[] DOTTED = {1, 4};

    /**
     * Float array constant representing solid line in Awt {@link BasicStroke}
     * format.
     */
    private static final float[] SOLID = null;

    /**
     * Ctor. Builds a {@link BasicStroke} with the passed {@link Stroke}.
     * @param stroke Stroke from which to build an AWT Stroke
     */
    public AwtStroke(final Stroke stroke) {
        super(
            stroke.width(), BasicStroke.CAP_SQUARE, BasicStroke.CAP_ROUND, 1.0f,
            AwtStroke.dash(stroke.dash()), 0
        );
    }

    /**
     * Converts the {@link Dash} enum value to a float array respecting AWT dash
     * pattern format.
     * @param dash Dash to convert.
     * @return Awt dash pattern as float array
     */
    private static float[] dash(final Dash dash) {
        final float[] result;
        if (Dash.DASHED.equals(dash)) {
            result = AwtStroke.DASHED;
        } else {
            if (Dash.DOTTED.equals(dash)) {
                result = AwtStroke.DOTTED;
            } else {
                result = AwtStroke.SOLID;
            }
        }
        return result;
    }

}
