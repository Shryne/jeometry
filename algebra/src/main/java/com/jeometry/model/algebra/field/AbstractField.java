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
package com.jeometry.model.algebra.field;

import com.jeometry.model.algebra.scalar.Add;
import com.jeometry.model.algebra.scalar.Diff;
import com.jeometry.model.algebra.scalar.Division;
import com.jeometry.model.algebra.scalar.Multiplication;
import com.jeometry.model.algebra.scalar.Scalar;

/**
 * Abstract Field implementation based on {@link Default} implementation
 * for returning actual objects.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @param <T> The actual objects constituting the field.
 * @since 0.1
 */
public abstract class AbstractField<T> implements Field<T> {

    @Override
    public final Scalar other(final Scalar scalar) {
        Scalar result = this.random();
        while (this.equals(result, scalar)) {
            result = this.random();
        }
        return result;
    }

    @Override
    public boolean equals(final Scalar scalar, final Scalar other) {
        final T first = this.actual(scalar);
        final T second = this.actual(other);
        return first.equals(second);
    }

    @SuppressWarnings("unchecked")
    @Override
    public final T actual(final Scalar scalar) {
        T result = null;
        if (Scalar.Default.class.isAssignableFrom(scalar.getClass())) {
            result = ((Scalar.Default<T>) scalar).value();
        }
        if (Add.class.isAssignableFrom(scalar.getClass())) {
            result = this.actual(this.calculate((Add) scalar));
        }
        if (Multiplication.class.isAssignableFrom(scalar.getClass())) {
            result = this.actual(this.calculate((Multiplication) scalar));
        }
        if (Diff.class.isAssignableFrom(scalar.getClass())) {
            result = this.actual(this.calculate((Diff) scalar));
        }
        if (Division.class.isAssignableFrom(scalar.getClass())) {
            result = this.actual(this.calculate((Division) scalar));
        }
        return result;
    }

    /**
     * Calculates the passed {@link Add} and returns a scalar
     * representing the result.
     * @param add The addition scalar
     * @return A scalar representing the addition result
     */
    protected abstract Scalar calculate(final Add add);

    /**
     * Calculates the passed {@link Multiplication} and returns a scalar
     * representing the result.
     * @param mult The multiplication scalar
     * @return A scalar representing the multiplication result
     */
    protected abstract Scalar calculate(final Multiplication mult);

    /**
     * Calculates the passed {@link Division} and returns a scalar
     * representing the result.
     * @param div The division scalar
     * @return A scalar representing the division result
     */
    protected abstract Scalar calculate(final Division div);

    /**
     * Calculates the passed {@link Diff} and returns a scalar
     * representing the result.
     * @param diff The difference scalar
     * @return A scalar representing the difference result
     */
    protected abstract Scalar calculate(final Diff diff);

}
