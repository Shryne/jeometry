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
package com.aljebra.vector;

import com.aljebra.field.Field;
import com.aljebra.scalar.Scalar;
import com.aljebra.scalar.condition.Equals;
import com.aljebra.scalar.condition.Predicate;

/**
 * A predicate to determine if two vectors are equals.
 * @param <T> scalar types
 * @since 0.1
 */
public final class VectEquals<T> implements Predicate<Field<T>> {

    /**
     * First vector.
     */
    private final Vect<T> first;

    /**
     * Second vector.
     */
    private final Vect<T> second;

    /**
     * Constructor.
     * @param first First vector
     * @param second Second vector
     */
    public VectEquals(final Vect<T> first, final Vect<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean resolve(final Field<T> field) {
        final Scalar<T>[] fcoords = this.first.coords();
        final Scalar<T>[] scoords = this.second.coords();
        return fcoords.length == scoords.length
            && VectEquals.arraysEqual(fcoords, scoords, field);
    }

    /**
     * Checks if passed scalar arrays are one to one equals.
     * @param first First scalars array
     * @param second Second scalars array
     * @param field Scalars field
     * @param <T> scalar types
     * @return True if the two arrays are equals
     */
    private static <T> boolean arraysEqual(final Scalar<T>[] first,
        final Scalar<T>[] second, final Field<T> field) {
        boolean result = true;
        for (int idx = 0; idx < second.length; ++idx) {
            if (!VectEquals.scalarEqual(first[idx], second[idx], field)) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Checks if passed scalars are equals.
     * @param first First scalar
     * @param second Second scalar
     * @param field Scalars field
     * @param <T> scalar types
     * @return True if the two scalars are equals
     */
    private static <T> boolean scalarEqual(final Scalar<T> first, final Scalar<T> second,
        final Field<T> field) {
        return new Equals<>(first, second).resolve(field);
    }

}
