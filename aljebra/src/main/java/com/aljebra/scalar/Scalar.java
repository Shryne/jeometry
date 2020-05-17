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
package com.aljebra.scalar;

import com.aljebra.field.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Scalar interface.
 * @param <T> scalar types
 * @since 0.1
 */
public interface Scalar<T> {

    /**
     * Return the actual value of the scalar.
     * @param field Scalar field
     * @return An object representing the scalar
     */
    T value(Field<T> field);

    /**
     * Adds the given scalars to this scalar.
     * @param operands Scalars to add
     * @return A scalar defining the addition
     */
    default Scalar<T> add(final List<? extends Scalar<T>> operands) {
        final List<Scalar<T>> ops = new ArrayList<>(operands);
        ops.add(this);
        return new Add<T>(ops);
    }

    /**
     * Adds the given scalar to this scalar.
     * @param operand Scalar to add
     * @return A scalar defining the addition
     */
    default Scalar<T> add(final Scalar<T> operand) {
        return new Add<T>(Arrays.asList(operand, this));
    }

    /**
     * Multiplies the given scalars to this scalar.
     * @param operands Scalars to multiply
     * @return A scalar defining the multiplication
     */
    default Scalar<T> mult(final List<? extends Scalar<T>> operands) {
        final List<Scalar<T>> ops = new ArrayList<>(operands);
        ops.add(this);
        return new Multiplication<T>(ops);
    }

    /**
     * Multiplies the given scalar to this scalar.
     * @param operand Scalar to multiply
     * @return A scalar defining the multiplication
     */
    default Scalar<T> mult(final Scalar<T> operand) {
        return new Multiplication<T>(operand, this);
    }

    /**
     * Minimal representation of a scalar holding a reference to an object.
     * @param <R> Holded object type.
     * @since 0.1
     */
    @EqualsAndHashCode
    @ToString(includeFieldNames = false)
    class Default<R> implements Scalar<R> {
        /**
         * Wrapped object.
         */
        private final R origin;

        /**
         * Constructor.
         * @param num Wrapped object.
         */
        public Default(final R num) {
            this.origin = num;
        }

        @Override
        public R value(final Field<R> field) {
            return this.origin;
        }
    }
}
