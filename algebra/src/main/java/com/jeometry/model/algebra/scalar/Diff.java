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
 * A scalar represented as the difference between two scalars.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class Diff implements Scalar {
    /**
     * First operand.
     */
    private final Scalar foperand;

    /**
     * Second operand.
     */
    private final Scalar soperand;

    /**
     * Constructor.
     * @param first First operand (minuend)
     * @param second Second operand (subtrahend)
     */
    public Diff(final Scalar first, final Scalar second) {
        this.foperand = first;
        this.soperand = second;
    }

    /**
     * Gives first operand (minuend).
     * @return The first operand of the difference.
     */
    public Scalar first() {
        return this.foperand;
    }

    /**
     * Gives second operand (subtrahend).
     * @return The second operand of the difference.
     */
    public Scalar second() {
        return this.soperand;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
            + ((this.foperand == null) ? 0 : this.foperand.hashCode());
        result = prime * result
            + ((this.soperand == null) ? 0 : this.soperand.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Diff other = (Diff) obj;
        if (this.foperand == null) {
            if (other.foperand != null) {
                return false;
            }
        } else if (!this.foperand.equals(other.foperand)) {
            return false;
        }
        if (this.soperand == null) {
            if (other.soperand != null) {
                return false;
            }
        } else if (!this.soperand.equals(other.soperand)) {
            return false;
        }
        return true;
    }
}
