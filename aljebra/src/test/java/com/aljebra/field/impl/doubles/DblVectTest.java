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
package com.aljebra.field.impl.doubles;

import com.aljebra.scalar.Scalar;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link DblVect}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class DblVectTest {

    /**
     * DblVect returns right coordinates.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void returnsCoordinates() {
        final double coora = Math.random();
        final double coorb = Math.random();
        final double coorc = Math.random();
        final double coord = Math.random();
        final double coore = Math.random();
        final DblVect vect = new DblVect(coora, coorb, coorc, coord, coore);
        MatcherAssert.assertThat(
            vect.coords(),
            Matchers.array(
                Matchers.equalTo(new Scalar.Default<>(coora)),
                Matchers.equalTo(new Scalar.Default<>(coorb)),
                Matchers.equalTo(new Scalar.Default<>(coorc)),
                Matchers.equalTo(new Scalar.Default<>(coord)),
                Matchers.equalTo(new Scalar.Default<>(coore))
            )
        );
    }
}
