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
package com.aljebra.field.impl.doubles;

import com.aljebra.scalar.Scalar;
import com.aljebra.vector.FixedVector;
import java.util.ArrayList;
import java.util.List;

/**
 * A convenient class for double vectors.
 * @since 0.1
 */
public final class DblVect extends FixedVector<Double> {

    /**
     * Constructor.
     * @param coords Double vector coordinates
     */
    public DblVect(Double... coords) {
        super(DblVect.wrap(coords));
    }

    /**
     * Wraps double array into scalar array.
     * @param coords Double array
     * @return A scalar array wrapping the doubles
     */
    private static List<Scalar<Double>> wrap(final Double... coords) {
        final List<Scalar<Double>> result = new ArrayList<>(coords.length);
        for (final Double coor : coords) {
            result.add(DblVect.wrap(coor));
        }
        return result;
    }

    /**
     * Wraps a double into a scalar.
     * @param num A double
     * @return A scalar wrapping the double
     */
    private static Scalar<Double> wrap(final Double num) {
        return new Scalar.Default<Double>(num);
    }

}
