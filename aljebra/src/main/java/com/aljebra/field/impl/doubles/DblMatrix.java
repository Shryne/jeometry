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

import com.aljebra.matrix.FixedMatrix;
import com.aljebra.scalar.Scalar;
import java.util.Arrays;

/**
 * A convenient class for double matrices.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class DblMatrix extends FixedMatrix {

    /**
     * Constructor.
     * @param lines Matrix lines count
     * @param cols Matrix columns count
     * @param coords Double matrix coordinates
     */
    public DblMatrix(final int lines, final int cols, final Double... coords) {
        super(lines, cols, DblMatrix.wrap(coords));
    }

    /**
     * Wraps double array into Scalar array.
     * @param coords Double array
     * @return A scalar array wrapping the doubles
     */
    private static Scalar[] wrap(final Double... coords) {
        return Arrays.stream(coords).map(
            i -> new Scalar.Default<Double>(i)
        ).toArray(Scalar[]::new);
    }

}
