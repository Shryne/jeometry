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

import com.aljebra.scalar.Random;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.Sum;
import com.aljebra.vector.Times;
import com.aljebra.vector.Vect;
import com.jeometry.geometry.twod.line.Line;
import lombok.ToString;

/**
 * A point defined by belonging to a line. The point is dynamic regarding
 * to the passed line, which means that it is ensured that this point remains
 * belonging to the line even if modifications occur on the line.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ToString(includeFieldNames = false)
public final class InLinePoint implements Vect {

    /**
     * The line to belong to.
     */
    private final Line line;

    /**
     * A random scalar.
     */
    private final Scalar factor;

    /**
     * Constructor.
     * @param line The line to belong to
     */
    public InLinePoint(final Line line) {
        this.line = line;
        this.factor = new Random();
    }

    @Override
    public Scalar[] coords() {
        return new Scalar[] {
            new Sum(
                new Times(this.line.direction(), this.factor), this.line.point()
            ).coords()[0],
            new Sum(
                new Times(this.line.direction(), this.factor), this.line.point()
            ).coords()[1],
        };
    }

}
