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
package com.jeometry.model.decimal;

import com.jeometry.geometry.twod.point.XyVector;
import com.jeometry.model.algebra.scalar.Scalar;

/**
 * Represents a 2D vector defined by (x,y) double coordinates.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class DblPoint extends XyVector {

    /**
     * Constructor.
     * @param xcoor X coordinate
     * @param ycoor Y coordinate
     */
    public DblPoint(final Double xcoor, final Double ycoor) {
        super(new Scalar.Default<>(xcoor), new Scalar.Default<>(ycoor));
    }

    /**
     * Convenience method to return the double value of x-coordinate.
     * @return X coordinate double value
     */
    @SuppressWarnings("unchecked")
    public Double dblx() {
        return ((Scalar.Default<Double>) this.xcoor()).value();
    }

    /**
     * Convenience method to return the double value of y-coordinate.
     * @return Y coordinate double value
     */
    @SuppressWarnings("unchecked")
    public Double dbly() {
        return ((Scalar.Default<Double>) this.ycoor()).value();
    }

    /**
     * Convenience method to set a double as a x coordinate.
     * @param xcoor X coordinate to set
     */
    public void setDblX(final Double xcoor) {
        this.setX(new Scalar.Default<>(xcoor));
    }

    /**
     * Convenience method to set a double as a y coordinate.
     * @param ycoor Y coordinate to set
     */
    public void setDblY(final Double ycoor) {
        this.setY(new Scalar.Default<>(ycoor));
    }

}
