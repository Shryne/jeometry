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
package com.jeometry.geometry.twod.point;

import com.jeometry.geometry.twod.Renderable;
import com.jeometry.model.algebra.scalar.Scalar;
import com.jeometry.model.algebra.vector.FixedVector;
import lombok.ToString;

/**
 * Represents a 2D vector defined by (x,y) coordinates.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ToString(callSuper = true)
public class XyVector extends FixedVector implements Renderable {

    /**
     * Constructor.
     * @param xcoor X coordinate
     * @param ycoor Y coordinate
     */
    public XyVector(final Scalar xcoor, final Scalar ycoor) {
        super(xcoor, ycoor);
    }

    /**
     * Gives the X coordinate.
     * @return X coordinate of the point
     */
    public final Scalar xcoor() {
        return this.coords()[0];
    }

    /**
     * Gives the Y coordinate.
     * @return Y coordinate of the point
     */
    public final Scalar ycoor() {
        return this.coords()[1];
    }

    /**
     * Modifies X coordinate of the vector.
     * @param xcor New X coordinate
     */
    public final void setX(final Scalar xcor) {
        this.setCoor(0, xcor);
    }

    /**
     * Modifies Y Coordinate of the vector.
     * @param ycor New Y coordinate
     */
    public final void setY(final Scalar ycor) {
        this.setCoor(1, ycor);
    }

}
