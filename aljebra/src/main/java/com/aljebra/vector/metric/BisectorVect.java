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
package com.aljebra.vector.metric;

import com.aljebra.field.Field;
import com.aljebra.field.MetricSpaceField;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.FixedVector;
import com.aljebra.vector.Vect;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A vector defined as bisecting two other vectors.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = true)
public final class BisectorVect extends FixedVector {

    /**
     * Constructor.
     * @param first First vector
     * @param second Second vector
     */
    public BisectorVect(final Vect first, final Vect second) {
        super(BisectorVect.coords(first, second));
    }

    /**
     * Calculates coordinates of the bisector vector.
     * @param first First vector
     * @param second Second vector
     * @return Scalar array of the bisector vector coordinates
     */
    private static Scalar[] coords(final Vect first, final Vect second) {
        final Scalar[] result = new Scalar[first.coords().length];
        for (int idx = 0; idx < result.length; ++idx) {
            result[idx] = BisectorVect.coord(first, second, idx);
        }
        return result;
    }

    /**
     * Gives a scalar corresponding to a given single coordinate
     * of the bisector vector.
     * @param first First vector
     * @param second Second vector
     * @param dim Coordinate index
     * @return A scalar corresponding to a coordinate of the bisector vector
     */
    private static Scalar coord(final Vect first, final Vect second,
        final int dim) {
        return new Scalar() {
            @Override
            public <T> T value(final Field<T> field) {
                if (field instanceof MetricSpaceField<?>) {
                    final InnerProduct pdt =
                        ((MetricSpaceField<T>) field).product();
                    return field.actual(
                        pdt.rot(
                            first, pdt.angle(first, second).doubleValue() / 2
                        ).coords()[dim]
                    );
                } else {
                    throw new UnsupportedOperationException(
                        String.format(
                            "Field %s is not a metric space field", field
                        )
                    );
                }
            }
        };
    }

}
