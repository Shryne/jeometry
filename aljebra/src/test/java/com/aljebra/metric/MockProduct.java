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
package com.aljebra.metric;

import com.aljebra.metric.angle.Degrees;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.Vect;

/**
 * Mock InnerProduct with spying (verifying) capabilities on the methods angle, norm and product.
 * It holds the parameters with which the methods were last called as optionals.
 * If the optional is empty, that means the method was never called.
 * @param <T> scalar types
 * @since 0.1
 */
public final class MockProduct<T> implements InnerProduct<T> {

    @Override
    public Scalar<T> product(final Vect<T> first, final Vect<T> second) {
        return null;
    }

    @Override
    public Degrees angle(final Vect<T> first, final Vect<T> second) {
        return null;
    }

    @Override
    public Scalar<T> norm(final Vect<T> vect) {
        return null;
    }

    @Override
    public Vect<T> rot(final Vect<T> vect, final Degrees angle) {
        return null;
    }

}
