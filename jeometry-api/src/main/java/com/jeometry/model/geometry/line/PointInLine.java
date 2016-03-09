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
import com.jeometry.model.algebra.scalar.Scalar;
import com.jeometry.model.algebra.vector.Sum;
import com.jeometry.model.algebra.vector.Times;
import com.jeometry.model.algebra.vector.Vect;

/**
 * A point defined by belonging to a line.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class PointInLine implements Vect {

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
     * @param field Field for scalar randomization
     */
    public PointInLine(final Line line, final Field<?> field) {
        super();
        this.line = line;
        this.factor = field.random();
    }

	@Override
	public Scalar[] coors() {
		return new Scalar[]{
			new Sum(new Times(this.line.direction(), this.factor), this.line.point()).coors()[0],
			new Sum(new Times(this.line.direction(), this.factor), this.line.point()).coors()[1]
		};
	}

}
