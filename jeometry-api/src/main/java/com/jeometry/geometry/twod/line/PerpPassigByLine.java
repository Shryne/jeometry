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
package com.jeometry.geometry.twod.line;

import com.jeometry.model.algebra.field.Field;
import com.jeometry.model.algebra.scalar.Diff;
import com.jeometry.model.algebra.vector.FixedVector;
import com.jeometry.model.algebra.vector.Vect;
import lombok.ToString;

/**
 * A line defined by being perpendicular to another line,
 * and passing by a given point.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ToString(includeFieldNames = false)
public final class PerpPassigByLine implements Line {
    /**
     * The line direction.
     */
    private final Vect dir;

    /**
     * A point by which this line passes.
     */
    private final Vect pnt;

    /**
     * Constructor.
     * @param perp The line to be parallel to
     * @param point Point to pass by
     * @param field Field for scalar randomization
     */
    public PerpPassigByLine(final Line perp, final Vect point,
        final Field<?> field) {
        super();
        this.dir = new FixedVector(
            perp.direction().coords()[1],
            new Diff(field.addIdentity(), perp.direction().coords()[0])
        );
        this.pnt = point;
    }

    @Override
    public Vect direction() {
        return this.dir;
    }

    @Override
    public Vect point() {
        return this.pnt;
    }

}
