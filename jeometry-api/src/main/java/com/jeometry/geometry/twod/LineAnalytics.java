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
package com.jeometry.geometry.twod;

import com.jeometry.geometry.twod.line.Line;
import com.jeometry.model.algebra.field.Field;
import com.jeometry.model.algebra.scalar.Diff;
import com.jeometry.model.algebra.scalar.Division;
import com.jeometry.model.algebra.scalar.Multiplication;
import com.jeometry.model.algebra.scalar.Scalar;
import com.jeometry.model.algebra.vector.Vect;

/**
 * A class calculating slope, y-intercept of a {@link Line}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class LineAnalytics {

    /**
     * Line for which to calculate properties.
     */
    private Line line;

    /**
     * Field of scalars to calculate line properties against.
     */
    private Field<?> field;

    /**
     * Constructor.
     * @param line The line to avoid belonging to
     * @param field Field for scalar operations
     */
    public LineAnalytics(final Line line, final Field<?> field) {
        this.line = line;
        this.field = field;
    }

    /**
     * Calculates Line slope. Throws a runtime exception
     * if the line is vertical (i.e Line direction has a zero coordinate
     * on X-axis).
     * @return Slope scalar
     */
    public Scalar slope() {
        if (!this.vertical()) {
            final Vect dir = this.line.direction();
            return new Division(dir.coords()[1], dir.coords()[0]);
        }
        throw new IllegalStateException("Line has infinite slope.");
    }

    /**
     * Calculates Line y-intercept. Throws a runtime exception
     * if the line is vertical (i.e Line direction has a zero coordinate
     * on X-axis).
     * @return Y-intercept scalar
     */
    public Scalar intercept() {
        if (!this.vertical()) {
            final Vect point = this.line.point();
            final Scalar slope = this.slope();
            return new Diff(
                point.coords()[1], new Multiplication(slope, point.coords()[0])
            );
        }
        throw new IllegalStateException(
            "Line has infinite slope. Could not calculate y-intercept."
        );
    }

    public boolean vertical() {
        final Vect dir = this.line.direction();
        return this.field.equals(dir.coords()[0], this.field.addIdentity());
    }
    
}
