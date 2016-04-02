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
package com.jeometry.aspects;

import com.jeometry.model.algebra.matrix.Matrix;
import com.jeometry.model.algebra.vector.Vect;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
     * Estimated same type arguments count.
     */
    private static final int ARGS_SIZE = 5;

    /**
     * Arguments types to check for size.
     */
    private final Class<?>[] clazz = {Vect.class, Matrix.class};

    /**
     * Checks if arguments of matrix or scalar arguments have the same size.
     * @param jpoint Join Point
     */
    @Before("execution(@com.jeometry.aspects.DimensionsEqual *.new(..))")
    public void check(final JoinPoint jpoint) {
        final Object[] args = jpoint.getArgs();
        final Map<Class<?>, List<Object>> argmap = new HashMap<>();
        for (int idx = 0; idx < this.clazz.length; ++idx) {
            argmap.put(
                this.clazz[idx],
                new ArrayList<>(SameDimensionCheck.ARGS_SIZE)
            );
        }
        for (int arg = 0; arg < args.length; ++arg) {
            for (int idx = 0; idx < this.clazz.length; ++idx) {
                if (this.clazz[idx].isAssignableFrom(args[arg].getClass())) {
                    argmap.get(this.clazz[idx]).add(args[arg]);
                }
            }
        }
        for (int idx = 0; idx < this.clazz.length; ++idx) {
            final List<Object> list = argmap.get(this.clazz[idx]);
            if (list != null && !list.isEmpty()) {
                this.validate(list);
            }
        }
    }

    /**
     * Validates a list of objects to have the same size.
     * @param list The list of objects to validate
     */
    private void validate(final List<Object> list) {
        final Object first  = list.get(0);
        if (first instanceof Vect) {
            final int dim = ((Vect) first).coords().length;
            for (final Object vector : list) {
                this.check((Vect) vector, dim);
            }
        }
    }

    /**
     * Checks if a vector has the given dimension.
     * @param vector The vector
     * @param dim Dimension
     */
    private void check(final Vect vector, final int dim) {
        if (vector.coords().length != dim) {
            throw new IllegalArgumentException();
        }
    }
}
