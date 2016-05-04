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
package com.aljebra.scalar;

import com.aljebra.field.Field;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Scalar interface.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Scalar {

    /**
     * Return the actual value of the scalar.
     * @param field Scalar field
     * @param <T> Scalar object type
     * @return An object representing the scalar
     */
    <T> T value(final Field<T> field);

    /**
     * Adds the given scalars to this scalar.
     * @param operands Scalars to add
     * @return A scalar defining the addition
     */
    default Scalar add(final Scalar... operands) {
        final Scalar[] ops = new Scalar[operands.length + 1];
        System.arraycopy(operands, 0, ops, 0, operands.length);
        ops[operands.length] = this;
        return new Add(ops);
    }

    /**
     * Multiplies the given scalars to this scalar.
     * @param operands Scalars to multiply
     * @return A scalar defining the multiplication
     */
    default Scalar mult(final Scalar... operands) {
        final Scalar[] ops = new Scalar[operands.length + 1];
        System.arraycopy(operands, 0, ops, 0, operands.length);
        ops[operands.length] = this;
        return new Multiplication(ops);
    }

    /**
     * Minimal representation of a scalar holding a reference to an object.
     * @author Hamdi Douss (douss.hamdi@gmail.com)
     * @version $Id$
     * @param <T> Holded object type.
     * @since 0.1
     */
    @EqualsAndHashCode
    @ToString(includeFieldNames = false)
    class Default<T> implements Scalar {
        /**
         * Wrapped object.
         */
        private final T origin;

        /**
         * Constructor.
         * @param num Wrapped object.
         */
        public Default(final T num) {
            this.origin = num;
        }

        /**
         * Gives the object representing the scalar.
         * @return The wrapped object
         */
        public final T value() {
            return this.origin;
        }

        @Override
        public <R> R value(final Field<R> field) {
            return field.actual(this);
        }
    }
}
