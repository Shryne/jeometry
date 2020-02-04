/**
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
package com.jeometry.model.decimal;

import com.aljebra.scalar.Scalar;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link DblPoint}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class DblPointTest {

    /**
     * {@link DblPoint} accepts double coordinates.
     */
    @Test
    public void acceptsDoubleRadius() {
        final double xcoor = Math.random();
        final double ycoor = Math.random();
        final DblPoint point = new DblPoint(xcoor, ycoor);
        MatcherAssert.assertThat(
            point.xcoor(),
            Matchers.equalTo(new Scalar.Default<Double>(xcoor))
        );
        MatcherAssert.assertThat(
            point.ycoor(),
            Matchers.equalTo(new Scalar.Default<Double>(ycoor))
        );
        MatcherAssert.assertThat(
            point.dblx(), Matchers.equalTo(xcoor)
        );
        MatcherAssert.assertThat(
            point.dbly(),
            Matchers.equalTo(ycoor)
        );
    }

}
