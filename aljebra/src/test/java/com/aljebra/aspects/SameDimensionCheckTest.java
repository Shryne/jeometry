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
package com.aljebra.aspects;

import com.aljebra.matrix.Matrix;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.Vect;
import java.util.Random;
import org.aspectj.lang.JoinPoint;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

/**
 * Tests for {@link SameDimensionCheck}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class SameDimensionCheckTest {

    /**
     * Max random integer.
     */
    private static final int INT_RANDOM = 10;

    /**
     * Junit rule for expected exceptions.
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * SameDimensionCheck throws exception when vectors
     * don't have the same size.
     */
    @Test
    public void throwsExceptionForDifferentVectorsSize() {
        this.thrown.expect(IllegalArgumentException.class);
        final JoinPoint join = Mockito.mock(JoinPoint.class);
        final Vect vct = SameDimensionCheckTest.vect();
        final Vect other = SameDimensionCheckTest.vect(vct.coords().length + 1);
        Mockito.when(join.getArgs()).thenReturn(new Object[]{vct, other});
        new SameDimensionCheck().constructor(join);
    }

    /**
     * SameDimensionCheck throws exception when matrices
     * don't have the same size.
     */
    @Test
    public void throwsExceptionForDifferentMatricesSize() {
        this.thrown.expect(IllegalArgumentException.class);
        final JoinPoint join = Mockito.mock(JoinPoint.class);
        final Matrix mat = SameDimensionCheckTest.matrix();
        final int refline = mat.lines();
        final int refcol = mat.columns();
        final Matrix other = SameDimensionCheckTest.matrix(refline, refcol);
        final Matrix third = SameDimensionCheckTest.matrix(refline + 1, refcol);
        Mockito.when(join.getArgs()).thenReturn(
            new Object[]{mat, other, third}
        );
        new SameDimensionCheck().constructor(join);
    }

    /**
     * SameDimensionCheck accepts vectors with same size.
     */
    @Test
    public void acceptsVectorsWithSameSize() {
        final JoinPoint join = Mockito.mock(JoinPoint.class);
        final Vect vct = SameDimensionCheckTest.vect();
        final Vect other = SameDimensionCheckTest.vect(vct.coords().length);
        final Vect third = SameDimensionCheckTest.vect(vct.coords().length);
        Mockito.when(join.getArgs()).thenReturn(
            new Object[]{vct, other, third}
        );
        new SameDimensionCheck().constructor(join);
    }

    /**
     * SameDimensionCheck accepts matrices with same size.
     */
    @Test
    public void acceptsMatricesWithSameSize() {
        final JoinPoint join = Mockito.mock(JoinPoint.class);
        final Matrix mat = SameDimensionCheckTest.matrix();
        final int refline = mat.lines();
        final int refcol = mat.columns();
        final Matrix other = SameDimensionCheckTest.matrix(refcol, refline);
        final Matrix third = SameDimensionCheckTest.matrix(refcol, refline);
        Mockito.when(join.getArgs()).thenReturn(
            new Object[]{mat, other, third}
        );
        new SameDimensionCheck().constructor(join);
    }

    /**
     * Mocks a vector with the given dimension.
     * @param length Dimension of the given vector
     * @return A mocked vector
     */
    private static Vect vect(final int length) {
        final Vect mock = Mockito.mock(Vect.class);
        Mockito.when(mock.coords()).thenReturn(new Scalar[length]);
        return mock;
    }

    /**
     * Mocks a vector with a random dimension.
     * @return A mocked vector
     */
    private static Vect vect() {
        return SameDimensionCheckTest.vect(
            new Random().nextInt(SameDimensionCheckTest.INT_RANDOM)
        );
    }

    /**
     * Mocks a matrix with the given lines and columns count.
     * @param col Columns count
     * @param line Lines count
     * @return A mocked matrix
     */
    private static Matrix matrix(final int col, final int line) {
        final Matrix mock = Mockito.mock(Matrix.class);
        Mockito.when(mock.lines()).thenReturn(line);
        Mockito.when(mock.columns()).thenReturn(col);
        return mock;
    }

    /**
     * Mocks a matrix with random dimensions.
     * @return A mocked matrix
     */
    private static Matrix matrix() {
        return SameDimensionCheckTest.matrix(
            new Random().nextInt(SameDimensionCheckTest.INT_RANDOM),
            new Random().nextInt(SameDimensionCheckTest.INT_RANDOM)
        );
    }
}
