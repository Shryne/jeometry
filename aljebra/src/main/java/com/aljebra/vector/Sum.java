/*
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

import com.aljebra.scalar.Add;
import com.aljebra.scalar.Scalar;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A vector represented as the sum of a set of vectors.
 * @param <T> scalar types
 * @since 0.1
 */
@EqualsAndHashCode
@ToString(includeFieldNames = false)
public final class Sum<T> implements Vect<T> {

    /**
     * Sum operands.
     */
    private final Multiset<Vect<T>> operands;

    /**
     * Constructor.
     * @param operands Sum operands
     */
    public Sum(final Iterable<Vect<T>> operands) {
        this.operands = HashMultiset.create(operands);
    }

    @Override
    public Scalar<T>[] coords() {
        final Scalar<T>[] coords = this.operands.iterator().next().coords();
        final int dim = coords.length;
        final Scalar<T>[] result = Arrays.copyOf(coords, dim);
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
    private Add<T> dimension(final int dim) {
        final List<Scalar<T>> coor = new ArrayList<>(this.operands.size());
        for (final Vect<T> oper : this.operands) {
            coor.add(oper.coords()[dim]);
        }
        return new Add<T>(coor);
    }
}
