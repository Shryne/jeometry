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
 * A scalar represented as the division of a scalar by another scalar.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class Division implements Scalar {
    /**
     * First operand.
     */
    private final Scalar dividend;

    /**
     * Second operand.
     */
    private final Scalar divisor;

    /**
     * Constructor.
     * @param first First operand (dividend)
     * @param second Second operand (divisor)
     */
    public Division(final Scalar first, final Scalar second) {
        this.dividend = first;
        this.divisor = second;
    }

    /**
     * Gives first operand (dividend).
     * @return The first operand of the division.
     */
    public Scalar first() {
        return this.dividend;
    }

    /**
     * Gives second operand (divisor).
     * @return The second operand of the sum.
     */
    public Scalar second() {
        return this.divisor;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
            + ((dividend == null) ? 0 : dividend.hashCode());
        result = prime * result
            + ((divisor == null) ? 0 : divisor.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Division other = (Division) obj;
        if (dividend == null) {
            if (other.dividend != null)
                return false;
        } else if (!dividend.equals(other.dividend))
            return false;
        if (divisor == null) {
            if (other.divisor != null)
                return false;
        } else if (!divisor.equals(other.divisor))
            return false;
        return true;
    }

}
