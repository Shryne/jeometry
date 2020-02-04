/**
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
package com.aljebra.vector;

import com.aljebra.aspects.DimensionsEqual;
import com.aljebra.scalar.Add;
import com.aljebra.scalar.Scalar;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.Arrays;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A vector represented as the sum of a set of vectors.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@EqualsAndHashCode
@ToString(includeFieldNames = false)
public final class Sum implements Vect {

    /**
     * Sum operands.
     */
    private final Multiset<Vect> operands;

    /**
     * Constructor.
     * @param operands Sum operands
     */
    @DimensionsEqual
    public Sum(final Vect... operands) {
        this.operands = HashMultiset.create(Arrays.asList(operands));
    }

    @Override
    public Scalar[] coords() {
        final int dim = this.operands.iterator().next().coords().length;
        final Scalar[] result = new Scalar[dim];
        for (int axis = 0; axis < dim; ++axis) {
            result[axis] = this.dimension(axis);
        }
        return result;
    }

    /**
     * Calculates the sum of the operands coordinates over a dimension.
     * @param dim Given dimension
     * @return An {@link Add} object representing the sum
     */
    private Add dimension(final int dim) {
        final Scalar[] coor = new Scalar[this.operands.size()];
        int idx = 0;
        for (final Vect oper : this.operands) {
            coor[idx] = oper.coords()[dim];
            ++idx;
        }
        return new Add(coor);
    }
}
