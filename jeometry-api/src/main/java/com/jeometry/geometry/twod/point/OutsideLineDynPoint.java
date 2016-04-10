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

import com.aljebra.field.Field;
import com.aljebra.scalar.Add;
import com.aljebra.scalar.Diff;
import com.aljebra.scalar.Division;
import com.aljebra.scalar.Multiplication;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.Vect;
import com.jeometry.geometry.twod.line.Line;
import lombok.ToString;

/**
 * A point defined by not belonging to a line.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ToString(of = {"line", "xvalue", "yvalue"})
public final class OutsideLineDynPoint implements Vect {

    /**
     * The line that this point should not belong to.
     */
    private final Line line;
    /**
     * X coordinate.
     */
    private Scalar xvalue;
    /**
     * Y coordinate.
     */
    private Scalar yvalue;

    /**
     * Field for scalar operations.
     */
    private final Field<?> field;

    /**
     * Constructor.
     * @param line The line to avoid belonging to
     * @param field Field for scalar operations
     */
    public OutsideLineDynPoint(final Line line, final Field<?> field) {
        super();
        this.line = line;
        this.field = field;
        this.xvalue = this.getXOutsideLine();
        this.yvalue = this.getYOutsideLine();
    }

    @Override
    public Scalar[] coords() {
        if (!this.check()) {
            this.xvalue = this.getXOutsideLine();
            this.yvalue = this.getYOutsideLine();
        }
        return new Scalar[] {this.xvalue, this.yvalue};
    }

    /**
     * Checks if this point is still outside the line.
     * @return True if this point is still outside the line
     */
    private boolean check() {
        final Vect dir = this.line.direction();
        boolean result = false;
        final Scalar zero = this.field.addIdentity();
        if (this.field.equals(dir.coords()[0], zero)) {
            result = !this.field.equals(this.xvalue, zero);
        } else {
            final Vect point = this.line.point();
            final Scalar slope = new Division(dir.coords()[1], dir.coords()[0]);
            final Scalar intercept = new Diff(
                point.coords()[1], new Multiplication(slope, point.coords()[0])
            );
            result = !this.field.equals(
                new Add(intercept, new Multiplication(slope, this.xvalue)),
                this.yvalue
            );
        }
        return result;
    }

    /**
     * Ensures generated X coordinate is outside line.
     * @return X coordinate
     */
    private Scalar getXOutsideLine() {
        final Vect dir = this.line.direction();
        Scalar candidate = this.field.random();
        if (this.field.equals(dir.coords()[0], this.field.addIdentity())) {
            candidate = this.field.other(this.field.addIdentity());
        }
        return candidate;
    }

    /**
     * Ensures generated Y coordinate is outside line.
     * @return Y coordinate
     */
    private Scalar getYOutsideLine() {
        Scalar candidate = this.field.random();
        final Vect dir = this.line.direction();
        if (!this.field.equals(dir.coords()[0], this.field.addIdentity())) {
            final Vect point = this.line.point();
            final Scalar slope = new Division(dir.coords()[1], dir.coords()[0]);
            final Scalar intercept = new Diff(
                point.coords()[1], new Multiplication(slope, point.coords()[0])
            );
            candidate = this.field.other(
                new Add(intercept, new Multiplication(slope, this.xvalue))
            );
        }
        return candidate;
    }
}
