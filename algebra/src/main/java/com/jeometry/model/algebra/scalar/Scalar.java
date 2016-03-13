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
package com.jeometry.model.algebra.scalar;

/**
 * Scalar interface. An abstract (annontation-like) to represent a scalar.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Scalar {

    /**
     * Minimal representation of a scalar holding a reference to an object.
     * @author Hamdi Douss (douss.hamdi@gmail.com)
     * @version $Id$
     * @param <T> Holded object type.
     * @since 0.1
     */
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
        public final int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result
                + ((this.origin == null) ? 0 : this.origin.hashCode());
            return result;
        }

        @Override
        public final boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Default<?> other = (Default<?>) obj;
            if (this.origin == null) {
                if (other.origin != null) {
                    return false;
                }
            } else if (!this.origin.equals(other.origin)) {
                return false;
            }
            return true;
        }
    }
}
