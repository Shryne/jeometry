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
package com.jeometry.model.geometry.line;

import com.jeometry.model.algebra.field.Field;
import com.jeometry.model.algebra.scalar.Add;
import com.jeometry.model.algebra.scalar.Diff;
import com.jeometry.model.algebra.scalar.Division;
import com.jeometry.model.algebra.scalar.Multiplication;
import com.jeometry.model.algebra.scalar.Scalar;
import com.jeometry.model.algebra.vector.Vect;

/**
 * A point defined by not belonging to a line.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class PointOutsideLine implements Vect {

    /**
     * The line that this point should not belong to.
     */
    private final Line line;
    /**
     * X coordinate.
     */
    private final Scalar xvalue;
    /**
     * Y coordinate.
     */
    private final Scalar yvalue;

    /**
     * Constructor.
     * @param line The line to avoid belonging to
     * @param field Field for scalar operations
     */
    public PointOutsideLine(final Line line, final Field<?> field) {
        super();
        this.line = line;
        this.xvalue = this.getXOutsideLine(field);
        this.yvalue = this.getYOutsideLine(field);
    }

    @Override
    public Scalar[] coors() {
        return new Scalar[] {this.xvalue, this.yvalue};
    }

    /**
     * Ensures generated X coordinate is outside line.
     * @param field Field for scalar operations
     * @return X coordinate
     */
    private Scalar getXOutsideLine(final Field<?> field) {
        final Vect dir = this.line.direction();
        Scalar candidate = field.random();
        if (field.equals(dir.coors()[0], field.addIdentity())) {
            candidate = field.other(field.addIdentity());
        }
        return candidate;
    }

    /**
     * Ensures generated Y coordinate is outside line.
     * @param field Field for scalar operations
     * @return Y coordinate
     */
    private Scalar getYOutsideLine(final Field<?> field) {
        Scalar candidate = field.random();
        final Vect dir = this.line.direction();
        if (!field.equals(dir.coors()[0], field.addIdentity())) {
            final Vect point = this.line.point();
            final Scalar slope = new Division(dir.coors()[1], dir.coors()[0]);
            final Scalar intercept = new Diff(
                point.coors()[1], new Multiplication(slope, point.coors()[0])
            );
            candidate = field.other(
                new Add(intercept, new Multiplication(slope, this.xvalue))
            );
        }
        return candidate;
    }
}
