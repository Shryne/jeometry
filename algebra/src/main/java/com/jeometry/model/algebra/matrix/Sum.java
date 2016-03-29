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
package com.jeometry.model.algebra.matrix;

import com.jeometry.model.algebra.scalar.Scalar;
import com.jeometry.model.algebra.vector.FixedVector;
import com.jeometry.model.algebra.vector.Vect;
import lombok.ToString;

/**
 * A Matrix represented as the sum of a set of matrices.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ToString
public final class Sum implements Matrix {

    /**
     * Sum operands.
     */
    private final Matrix[] operands;

    /**
     * Constructor.
     * @param operands Sum operands
     */
    public Sum(final Matrix... operands) {
        this.operands = operands;
    }

    @Override
    public Scalar[] coords() {
        final Vect[] results = new Vect[this.operands.length];
        for (int i = 0; i < this.operands.length; ++i) {
            results[i] = new FixedVector(this.operands[i].coords());
        }
        return new com.jeometry.model.algebra.vector.Sum(results).coords();
    }

    @Override
    public Scalar[] column(int i) {
        final Vect[] cols = new Vect[this.operands.length];
        for (int op = 0; op < this.operands.length; ++op) {
            cols[op] = new FixedVector(this.operands[op].column(i));
        }
        return new com.jeometry.model.algebra.vector.Sum(cols).coords();
    }

    @Override
    public Scalar[] line(int j) {
        final Vect[] lines = new Vect[this.operands.length];
        for (int i = 0; i < this.operands.length; ++i) {
            lines[i] = new FixedVector(this.operands[i].line(j));
        }
        return new com.jeometry.model.algebra.vector.Sum(lines).coords();
    }

    @Override
    public Vect apply(Vect input) {
        final Vect[] results = new Vect[this.operands.length];
        for (int i = 0; i < this.operands.length; ++i) {
            results[i] = this.operands[i].apply(input);
        }
        return new com.jeometry.model.algebra.vector.Sum(results);
    }

    @Override
    public Integer columns() {
        return this.operands[0].columns();
    }

    @Override
    public Integer lines() {
        return this.operands[0].lines();
    }
}
