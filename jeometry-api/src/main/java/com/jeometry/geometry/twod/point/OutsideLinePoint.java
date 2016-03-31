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

import com.jeometry.geometry.twod.line.Line;
import com.jeometry.model.algebra.field.Field;
import com.jeometry.model.algebra.scalar.Add;
import com.jeometry.model.algebra.scalar.Diff;
import com.jeometry.model.algebra.scalar.Division;
import com.jeometry.model.algebra.scalar.Multiplication;
import com.jeometry.model.algebra.scalar.Scalar;
import com.jeometry.model.algebra.vector.Vect;
import lombok.ToString;

/**
 * A point defined by not belonging to a line. The point is fixed upon
 * construction, which means that a modification to the underlying line does
 * not ensure that this point is still outside the line.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ToString(callSuper = true)
public final class OutsideLinePoint extends XyPoint {

    /**
     * Constructor.
     * @param line The line to avoid belonging to
     * @param field Field for scalar operations
     */
    public OutsideLinePoint(final Line line, final Field<?> field) {
        this(OutsideLinePoint.getXOutsideLine(line, field), line, field);
    }

    /**
     * Constructor.
     * @param xcoor X coordinate of the point
     * @param line The line to avoid belonging to
     * @param field Field for scalar operations
     */
    private OutsideLinePoint(final Scalar xcoor, final Line line,
        final Field<?> field) {
        super(xcoor, OutsideLinePoint.getYOutsideLine(line, xcoor, field));
    }

    /**
     * Ensures generated X coordinate is outside line.
     * @param line Line to be outside to
     * @param field Field for scalar operations
     * @return X coordinate
     */
    private static Scalar getXOutsideLine(final Line line,
        final Field<?> field) {
        final Vect dir = line.direction();
        Scalar candidate = field.random();
        if (field.equals(dir.coords()[0], field.addIdentity())) {
            candidate = field.other(field.addIdentity());
        }
        return candidate;
    }

    /**
     * Ensures generated Y coordinate is outside line.
     * @param line Line to be outside to
     * @param xcoor Calculated X coordinate
     * @param field Field for scalar operations
     * @return Y coordinate
     */
    private static Scalar getYOutsideLine(final Line line, final Scalar xcoor,
        final Field<?> field) {
        Scalar candidate = field.random();
        final Vect dir = line.direction();
        if (!field.equals(dir.coords()[0], field.addIdentity())) {
            final Vect point = line.point();
            final Scalar slope = new Division(dir.coords()[1], dir.coords()[0]);
            final Scalar intercept = new Diff(
                point.coords()[1], new Multiplication(slope, point.coords()[0])
            );
            candidate = field.other(
                new Add(intercept, new Multiplication(slope, xcoor))
            );
        }
        return candidate;
    }
}
