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
package com.jeometry.model;

import com.jeometry.model.scalar.Add;
import com.jeometry.model.scalar.Diff;
import com.jeometry.model.scalar.Division;
import com.jeometry.model.scalar.Multiplication;
import com.jeometry.model.scalar.Scalar;
import com.jeometry.model.scalar.ScalarSupplier;

/**
 * A point defined by not belonging to a line.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @since 0.1
 */
public final class PointOutsideLine implements Vector {

    /**
     * The line that this point should not belong to.
     */
    private Line line;
    /**
     * X coordinate.
     */
    private Scalar xcoor;
    /**
     * Y coordinate.
     */
    private Scalar ycoor;

    /**
     * Constructor.
     * @param line The line to avoid belonging to
     * @param supp Scalar supplier for scalar operations
     */
    public PointOutsideLine(final Line line, final ScalarSupplier supp) {
        super();
        this.line = line;
        this.xcoor = this.getXOutsideLine(supp);
        this.ycoor = this.getYOutsideLine(supp);
    }

    @Override
    public Scalar x() {
        return this.xcoor;
    }

    @Override
    public Scalar y() {
        return this.ycoor;
    }

    /**
     * Ensures generated X coordinate is outside line.
     * @param supp Scalar supplier for scalar operations
     * @return X coordinate
     */
    private Scalar getXOutsideLine(final ScalarSupplier supp) {
        final Vector dir = this.line.direction();
        Scalar candidate = supp.random();
        if (supp.equals(dir.x(), supp.addIdentity())) {
            candidate = supp.other(supp.addIdentity());
        }
        return candidate;
    }

    /**
     * Ensures generated Y coordinate is outside line.
     * @param supp Scalar supplier for scalar operations
     * @return Y coordinate
     */
    private Scalar getYOutsideLine(final ScalarSupplier supp) {
        Scalar candidate = supp.random();
        final Vector dir = this.line.direction();
        if (!supp.equals(dir.x(), supp.addIdentity())) {
            final Vector point = this.line.point();
            final Scalar slope = new Division(dir.y(), dir.x());
            final Scalar intercept = new Diff(
                point.y(), new Multiplication(slope, point.x())
            );
            candidate = supp.other(
                new Add(intercept, new Multiplication(slope, this.xcoor))
            );
        }
        return candidate;
    }
}
