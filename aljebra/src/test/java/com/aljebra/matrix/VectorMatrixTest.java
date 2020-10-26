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

import com.aljebra.metric.scalar.Product;
import com.aljebra.scalar.Scalar;
import com.aljebra.scalar.Scalar.Default;
import com.aljebra.scalar.mock.Scalars;
import com.aljebra.vector.FixedVector;
import com.aljebra.vector.Vect;
import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
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
     * {@link VectorMatrix} can return lines and columns.
     */
    @Test
    public void returnsLinesAndColumns() {
        final Default<String> scalara = new Scalar.Default<>("a");
        final Default<String> scalarb = new Scalar.Default<>("b");
        final Default<String> scalarc = new Scalar.Default<>("c");
        final Default<String> scalard = new Scalar.Default<>("d");
        final Matrix<String> matrix = new VectorMatrix<>(
            Arrays.asList(scalara, scalarb, scalarc, scalard)
        );
        MatcherAssert.assertThat(
            matrix.line(1), Matchers.equalTo(new Scalar[] {scalara, scalarb, scalarc, scalard})
        );
        int idx = 0;
        MatcherAssert.assertThat(
            matrix.column(++idx), Matchers.equalTo(new Scalar[] {scalara})
        );
        MatcherAssert.assertThat(
            matrix.column(++idx), Matchers.equalTo(new Scalar[] {scalarb})
        );
        MatcherAssert.assertThat(
            matrix.column(++idx), Matchers.equalTo(new Scalar[] {scalarc})
        );
        MatcherAssert.assertThat(
            matrix.column(++idx), Matchers.equalTo(new Scalar[] {scalard})
        );
    }

    /**
     * {@link VectorMatrix} can apply transformation.
     */
    @Test
    public void appliesTransformation() {
        final int cols = 4;
        final Matrix<Object> matrix = new VectorMatrix<>(
            new Scalars<>(cols)
        );
        final Vect<Object> input = new FixedVector<>(new Scalars<>(cols));
        final Scalar<Object> expected = VectorMatrixTest.pdt(
            input, Arrays.asList(matrix.line(1))
        );
        MatcherAssert.assertThat(
            matrix.apply(input).coords()[0], Matchers.equalTo(expected)
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

    /**
     * Gives a scalar representing the product value of the given vector
     * by a coordinate array.
     * @param input Input vector
     * @param scalars Scalar list by which to operate the dot product
     * @return A {@link Scalar}, value of the product
     */
    private static Scalar<Object> pdt(final Vect<Object> input,
        final List<Scalar<Object>> scalars) {
        return new Product<Object>(input, new FixedVector<Object>(scalars));
    }
}
