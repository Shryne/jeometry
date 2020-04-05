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
package com.jeometry.twod.point;

import com.aljebra.scalar.AddIdentity;
import com.aljebra.vector.Minus;
import com.aljebra.vector.Sum;
import com.aljebra.vector.Vect;
import java.util.Arrays;
import lombok.ToString;

/**
 * A point defined as the reflection of a point, across another given point.
 * @param <T> scalar types
 * @since 0.1
 */
@ToString
public final class PtReflectionPoint<T> extends XyPoint<T> {

    /**
     * Constructor.
     * @param center Reflection center
     * @param input The point to reflect
     */
    public PtReflectionPoint(final Vect<T> center, final Vect<T> input) {
        super(new Sum<T>(Arrays.asList(center, new Minus<T>(center, input))));
    }

    /**
     * Constructor. Builds the reflection point across the origin.
     * @param input The point to reflect
     */
    public PtReflectionPoint(final Vect<T> input) {
        this(new XyPoint<T>(new AddIdentity<>(), new AddIdentity<>()), input);
    }

}
