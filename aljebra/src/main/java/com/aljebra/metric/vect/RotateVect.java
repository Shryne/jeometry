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
package com.aljebra.metric.vect;

import com.aljebra.field.MetricSpaceField;
import com.aljebra.metric.angle.Degrees;
import com.aljebra.metric.scalar.MetricScalar;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.FixedVector;
import com.aljebra.vector.Vect;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A vector defined as the rotation image of a vector by an angle.
 * @since 0.1
 */
@ToString(includeFieldNames = false)
@EqualsAndHashCode(callSuper = true)
public final class RotateVect extends FixedVector {

    /**
     * Constructor.
     * @param vector Vector to rotate
     * @param angle Rotation angle
     */
    public RotateVect(final Vect vector, final Number angle) {
        this(vector, new Degrees.Default(angle));
    }

    /**
     * Constructor.
     * @param vector Vector to rotate
     * @param angle Rotation angle
     */
    public RotateVect(final Vect vector, final Degrees angle) {
        super(RotateVect.coords(vector, angle));
    }

    /**
     * Calculates coordinates of the rotated vector.
     * @param vector Vector to rotate
     * @param angle Rotation angle
     * @return Scalar array of the rotated vector coordinates
     */
    private static Scalar[] coords(final Vect vector, final Degrees angle) {
        final Scalar[] result = new Scalar[vector.coords().length];
        for (int idx = 0; idx < result.length; ++idx) {
            result[idx] = RotateVect.coord(vector, angle, idx);
        }
        return result;
    }

    /**
     * Gives a scalar corresponding to a given single coordinate
     * of the rotated vector.
     * @param vector Vector to rotate
     * @param angle Rotation angle
     * @param dim Coordinate index
     * @return A scalar corresponding to a coordinate of the rotated vector
     */
    private static Scalar coord(final Vect vector, final Degrees angle,
        final int dim) {
        return new MetricScalar() {
            @Override
            public <T> T value(final MetricSpaceField<T> field) {
                return field.actual(
                    field.product().rot(vector, angle).coords()[dim]
                );
            }
        };
    }

}
