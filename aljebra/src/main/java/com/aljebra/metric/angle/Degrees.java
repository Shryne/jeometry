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
package com.aljebra.metric.angle;

import com.aljebra.metric.InnerProduct;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Degrees interface. Abstract representation of angles.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Degrees {

    /**
     * Return the actual value of the degrees.
     * @param product Related {@link InnerProduct}
     * @return A number representing the angle in radians
     */
    Number resolve(final InnerProduct product);

    /**
     * Determines if this angle is right.
     * @param product Related {@link InnerProduct}
     * @return True if the angle is right
     */
    default boolean right(final InnerProduct product) {
        return Double.valueOf(
            this.resolve(product).doubleValue() % Math.PI
        ).equals(Math.PI / 2);
    }

    /**
     * Determines if this angle is flat.
     * @param product Related {@link InnerProduct}
     * @return True if the angle is flat
     */
    default boolean flat(final InnerProduct product) {
        return this.resolve(product).doubleValue() % Math.PI == 0;
    }

    /**
     * Minimal representation of a degrees holding a reference to a number.
     * @author Hamdi Douss (douss.hamdi@gmail.com)
     * @version $Id$
     * @since 0.1
     */
    @EqualsAndHashCode
    @ToString(includeFieldNames = false)
    class Default implements Degrees {
        /**
         * Wrapped Number.
         */
        private final Number origin;

        /**
         * Constructor.
         * @param num Wrapped number.
         */
        public Default(final Number num) {
            this.origin = num;
        }

        @Override
        public Number resolve(final InnerProduct product) {
            return this.origin;
        }
    }
}
