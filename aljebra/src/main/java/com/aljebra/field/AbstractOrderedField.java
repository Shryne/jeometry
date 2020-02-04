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
package com.aljebra.field;

import com.aljebra.scalar.Scalar;

/**
 * Abstract Ordered Field implementation based on {@link OrderedRandom}
 * for scalar randomization.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @param <T> The actual objects constituting the field.
 * @since 0.1
 */
public abstract class AbstractOrderedField<T> extends
    AbstractField<T> implements OrderedField<T> {

    /**
     * Randomizer.
     */
    private final OrderedRandom<T> random;

    /**
     * Constructor.
     * @param add Field addition
     * @param mul Field multiplication
     * @param random Ordered randomizer implementation
     */
    public AbstractOrderedField(final FieldAddition<T> add,
        final FieldMultiplication<T> mul, final OrderedRandom<T> random) {
        super(add, mul);
        this.random = random;
    }

    @Override
    public final Scalar between(final Scalar lower, final Scalar upper) {
        final T min = this.actual(lower);
        final T max = this.actual(upper);
        return new Scalar.Default<T>(this.random.between(min, max));
    }

    @Override
    public final Scalar greater(final Scalar lower) {
        return new Scalar.Default<T>(this.random.greater(this.actual(lower)));
    }

    @Override
    public final Scalar lower(final Scalar upper) {
        return new Scalar.Default<T>(this.random.lower(this.actual(upper)));
    }

}
