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
package com.jeometry.model.decimal;

import com.aljebra.field.impl.doubles.Decimal;
import com.aljebra.scalar.Scalar;
import com.jeometry.twod.point.XyPoint;

/**
 * Represents a 2D vector defined by (x,y) double coordinates.
 * @since 0.1
 */
public final class DblPoint extends XyPoint<Double> {

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
    public Double dblx() {
        return DblPoint.value(this.xcoor());
    }

    /**
     * Convenience method to return the double value of y-coordinate.
     * @return Y coordinate double value
     */
    public Double dbly() {
        return DblPoint.value(this.ycoor());
    }

    /**
     * Returns actual double value.
     * @param scalar Scalar
     * @return Double value
     */
    private static Double value(final Scalar<Double> scalar) {
        return scalar.value(new Decimal());
    }
}
