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
import java.util.Arrays;
import java.util.function.BinaryOperator;

/**
 * Abstract Field implementation based on {@link Default} implementation
 * for returning actual scalar objects.
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

    @SuppressWarnings("unchecked")
    @Override
    public final T actual(final Scalar scalar) {
        T result = null;
        if (scalar instanceof Scalar.Default<?>) {
            result = ((Scalar.Default<T>) scalar).value();
        }
        if (scalar instanceof Add) {
            result = this.actual(this.calculate((Add) scalar));
        }
        if (scalar instanceof Multiplication) {
            result = this.actual(this.calculate((Multiplication) scalar));
        }
        if (scalar instanceof Diff) {
            final Diff diff = (Diff) scalar;
            final T inverse = this.addition().inverse(
                this.actual(diff.second())
            );
            result = this.actual(
                this.calculate(
                    new Add(diff.first(), new Scalar.Default<>(inverse))
                )
            );
        }
        if (scalar instanceof Division) {
            final Division div = (Division) scalar;
            final T inverse = this.multiplication().inverse(
                this.actual(div.second())
            );
            result = this.actual(
                this.calculate(
                    new Multiplication(
                        div.first(), new Scalar.Default<>(inverse)
                    )
                )
            );
        }
        return result;
    }

    @Override
    public final Scalar addIdentity() {
        return new Scalar.Default<T>(this.addition().neutral());
    }

    @Override
    public final Scalar multIdentity() {
        return new Scalar.Default<T>(this.multiplication().neutral());
    }

    /**
     * Returns the field addition operation.
     * @return A {@link FieldAddition} object
     */
    protected abstract FieldAddition<T> addition();

    /**
     * Returns the field multiplication operation.
     * @return A {@link FieldMultiplication} object
     */
    protected abstract FieldMultiplication<T> multiplication();

    /**
     * Calculates the passed {@link Add} and returns a scalar
     * representing the result.
     * @param add The addition scalar
     * @return A scalar representing the addition result
     */
    private Scalar calculate(final Add add) {
        final Scalar[] ops = add.operands();
        final FieldAddition<T> addition = this.addition();
        final T result = Arrays.stream(ops).map(this::actual).reduce(
            addition.neutral(),
            new BinaryOperator<T>() {
                @Override
                public T apply(final T operand, final T second) {
                    return addition.add(operand, second);
                }
            }
        );
        return new Scalar.Default<T>(result);
    }

    /**
     * Calculates the passed {@link Multiplication} and returns a scalar
     * representing the result.
     * @param mult The multiplication scalar
     * @return A scalar representing the multiplication result
     */
    private Scalar calculate(final Multiplication mult) {
        final Scalar[] ops = mult.operands();
        final FieldMultiplication<T> multip = this.multiplication();
        final T result = Arrays.stream(ops).map(this::actual).reduce(
            multip.neutral(),
            new BinaryOperator<T>() {
                @Override
                public T apply(final T operand, final T second) {
                    return multip.multiply(operand, second);
                }
            }
        );
        return new Scalar.Default<T>(result);
    }

}
