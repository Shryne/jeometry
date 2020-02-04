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
package com.aljebra.aspects;

import com.aljebra.matrix.Matrix;
import com.aljebra.vector.Vect;
import java.util.HashMap;
import java.util.Map;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Aspect class to check for same dimensions of vectors and matrices.
 * If a method or a constructor is annotated with {@link DimensionsEqual}
 * annotation, its vectors or matrices arguments will be checked if they
 * have the same size. If it is not the case an {@link IllegalArgumentException}
 * will be thrown.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@Aspect
public final class SameDimensionCheck {

    /**
     * Arguments types to check for size.
     */
    private final Class<?>[] clazz = {Vect.class, Matrix.class};

    /**
     * Checks if method arguments of matrix or vector types have the same size.
     * @param jpoint Join Point
     */
    @Before("execution(@DimensionsEqual * *(..))")
    public void method(final JoinPoint jpoint) {
        this.constructor(jpoint);
    }

    /**
     * Checks if constructor arguments of matrix or vector types have
     * the same size.
     * @param jpoint Join Point
     */
    @Before("execution(@DimensionsEqual *.new(..))")
    public void constructor(final JoinPoint jpoint) {
        final Object[] args = jpoint.getArgs();
        final Map<Class<?>, Object> ref = new HashMap<>();
        for (int arg = 0; arg < args.length; ++arg) {
            for (int idx = 0; idx < this.clazz.length; ++idx) {
                final Class<?> type = args[arg].getClass().getComponentType();
                if (type != null && this.clazz[idx].isAssignableFrom(type)) {
                    SameDimensionCheck.validate(args[arg]);
                }
                if (!this.clazz[idx].isAssignableFrom(args[arg].getClass())) {
                    continue;
                }
                final Object reference = ref.get(this.clazz[idx]);
                if (reference == null) {
                    ref.put(this.clazz[idx], args[arg]);
                    continue;
                }
                SameDimensionCheck.validate(reference, args[arg]);
            }
        }
    }

    /**
     * Validates an array argument.
     * @param object The array
     */
    private static void validate(final Object object) {
        if (object instanceof Object[]) {
            final Object[] array = (Object[]) object;
            for (int idx = 1; idx < array.length; ++idx) {
                SameDimensionCheck.validate(array[0], array[idx]);
            }
        }
    }

    /**
     * Validates an argument against a reference. If the argument does not
     * have the same dimensions as the reference, it throws an
     * {@link IllegalArgumentException}.
     * @param reference Reference object
     * @param object Argument to validate
     */
    private static void validate(final Object reference, final Object object) {
        if (object instanceof Vect && reference instanceof Vect) {
            SameDimensionCheck.validate((Vect) reference, (Vect) object);
        }
        if (object instanceof Matrix && reference instanceof Matrix) {
            SameDimensionCheck.validate((Matrix) reference, (Matrix) object);
        }
    }

    /**
     * Validates a vector argument against a vector reference.
     * If the argument does not have the same dimension as the reference,
     * it throws an {@link IllegalArgumentException}.
     * @param reference Reference vector
     * @param object Argument to validate
     */
    private static void validate(final Vect reference, final Vect object) {
        if (reference.coords().length != object.coords().length) {
            throw new IllegalArgumentException(
                String.format(
                    "Vectors should have same dimension: %s dim %d, %s dim %d.",
                    reference, reference.coords().length,
                    object, object.coords().length
                )
            );
        }
    }

    /**
     * Validates a matrix argument against a matrix reference.
     * If the argument does not have the same columns and lines count
     * as the reference, it throws an {@link IllegalArgumentException}.
     * @param reference Reference matrix
     * @param object Argument to validate
     */
    private static void validate(final Matrix reference, final Matrix object) {
        if (reference.columns() != object.columns()
            || reference.lines() != object.lines()) {
            throw new IllegalArgumentException(
                String.format(
                    "Matrices should have same size: %s %dx%d, %s dim %dx%d.",
                    reference, reference.lines(), reference.columns(),
                    object, object.lines(), object.columns()
                )
            );
        }
    }
}
