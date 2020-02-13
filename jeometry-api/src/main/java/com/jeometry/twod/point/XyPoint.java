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
package com.jeometry.twod.point;

import com.aljebra.scalar.Scalar;
import com.aljebra.vector.FixedVector;
import com.aljebra.vector.Vect;
import com.jeometry.twod.Renderable;
import lombok.ToString;

/**
 * Represents a 2D vector defined by (x,y) coordinates.
 * @since 0.1
 */
@ToString(callSuper = true)
public class XyPoint extends FixedVector implements Renderable {

    /**
     * Constructor.
     * @param xcoor X coordinate
     * @param ycoor Y coordinate
     */
    public XyPoint(final Scalar xcoor, final Scalar ycoor) {
        super(xcoor, ycoor);
    }

    /**
     * Constructor. Projects the given vector on X and Y axis.
     * @param vector Input vector
     */
    public XyPoint(final Vect vector) {
        super(vector.coords()[0], vector.coords()[1]);
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

}
