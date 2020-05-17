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

package com.aljebra.scalar.mock;

import com.aljebra.scalar.Random;
import com.aljebra.scalar.Scalar;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Scalar iterator. Usage in testing purposes as a generator for scalars.
 * @param <T> scalar types
 * @since 0.3
 */
public final class Scalars<T> implements Iterable<Scalar<T>> {

    /**
     * List of scalars.
     */
    private final List<Scalar<T>> lst;

    /**
     * Ctor.
     * @param dim List size
     */
    public Scalars(final int dim) {
        this.lst = Scalars.list(dim);
    }

    @Override
    public Iterator<Scalar<T>> iterator() {
        return this.lst.iterator();
    }

    /**
     * Builds a list with the given dimension and the passed Scalar.
     * @param dim List size
     * @param <T> scalar types
     * @return A list with given size and with a repeating element
     */
    private static <T> List<Scalar<T>> list(final int dim) {
        final List<Scalar<T>> result = new ArrayList<>(dim);
        for (int idx = 0; idx < dim; ++idx) {
            result.add(new Random<>());
        }
        return result;
    }
}
