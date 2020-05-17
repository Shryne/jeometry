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
package com.aljebra.metric.angle;

import com.aljebra.metric.InnerProduct;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import java.util.Arrays;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A {@link Degrees} implementation defined as the sum of a {@link Degrees} set.
 * @param <T> scalar types
 * @since 0.1
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode
public final class Sum<T> implements Degrees<T> {

    /**
     * Sum operands.
     */
    private final Multiset<Degrees<T>> operands;

    /**
     * Constructor.
     * @param operands Sum operands
     */
    public Sum(final Iterable<Degrees<T>> operands) {
        this.operands = HashMultiset.create(operands);
    }

    /**
     * Constructor. Build sum with two degrees
     * @param first First operand
     * @param second Second operand
     */
    public Sum(final Degrees<T> first, final Degrees<T> second) {
        this(Arrays.asList(first, second));
    }

    @Override
    public Number resolve(final InnerProduct<T> product) {
        Double sum = 0.;
        for (final Degrees<T> angle : this.operands) {
            sum += angle.resolve(product).doubleValue();
        }
        return sum;
    }

}
