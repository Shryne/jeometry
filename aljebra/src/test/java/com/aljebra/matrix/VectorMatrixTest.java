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
package com.aljebra.matrix;

import com.aljebra.scalar.Scalar;
import com.aljebra.scalar.mock.Scalars;
import com.aljebra.vector.FixedVector;
import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.Random;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link VectorMatrix}.
 * @since 0.1
 */
public final class VectorMatrixTest {

    /**
     * Max random integer.
     */
    private static final int INT_RANDOM = 10;

    /**
     * {@link VectorMatrix} returns right coordinates.
     */
    @Test
    public void calculatesCoordinates() {
        final int dim = 1 + new Random().nextInt(VectorMatrixTest.INT_RANDOM);
        final Iterable<Scalar<Object>> coorsa = new Scalars<>(dim);
        final Matrix<Object> scalarmat = new VectorMatrix<>(coorsa);
        final Matrix<Object> vectmat = new VectorMatrix<>(
            new FixedVector<Object>(coorsa)
        );
        MatcherAssert.assertThat(scalarmat.lines(), Matchers.equalTo(1));
        MatcherAssert.assertThat(scalarmat.columns(), Matchers.equalTo(dim));
        MatcherAssert.assertThat(
            Arrays.asList(scalarmat.coords()), Matchers.equalTo(Lists.newArrayList(coorsa))
        );
        MatcherAssert.assertThat(vectmat.lines(), Matchers.equalTo(1));
        MatcherAssert.assertThat(vectmat.columns(), Matchers.equalTo(dim));
        MatcherAssert.assertThat(
            Arrays.asList(vectmat.coords()), Matchers.equalTo(Lists.newArrayList(coorsa))
        );
    }

    /**
     * {@link VectorMatrix} instances are considered equals if they have the
     * same coordinates.
     */
    @Test
    public void considersEqualsIfSameCoordinates() {
        final Iterable<Scalar<Object>> coords = new Scalars<>(
            1 + new Random().nextInt(VectorMatrixTest.INT_RANDOM)
        );
        MatcherAssert.assertThat(
            new VectorMatrix<Object>(coords), Matchers.equalTo(new VectorMatrix<Object>(coords))
        );
    }

    /**
     * {@link VectorMatrix} instances have same hashcode if they have the same
     * coordinates.
     */
    @Test
    public void sameHashCodeIfSameCoordinates() {
        final Iterable<Scalar<Object>> coords = new Scalars<>(
            1 + new Random().nextInt(VectorMatrixTest.INT_RANDOM)
        );
        MatcherAssert.assertThat(
            new VectorMatrix<Object>(coords).hashCode(),
            Matchers.equalTo(new VectorMatrix<Object>(coords).hashCode())
        );
    }
}
