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
import java.util.Random;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link Decimal}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class DecimalTest {

    /**
     * AbstractField returns a different scalar.
     */
    @Test
    public void returnsDifferentScalar() {
        final Decimal field = new Decimal();
        final Scalar scalar = new Scalar.Default<Double>(
            new Random().nextDouble()
        );
        MatcherAssert.assertThat(
            "Generated scalar should be different than the passed one",
            !field.equals(scalar, field.other(scalar))
        );
    }

    /**
     * AbstractField calculates actual value for default scalar implementation.
     */
    @Test
    public void calculatesActualValueForDefault() {
        final Decimal field = new Decimal();
        final Scalar.Default<?> scalar = Mockito.mock(Scalar.Default.class);
        MatcherAssert.assertThat(
            field.actual(scalar), Matchers.equalTo(scalar.value())
        );
        Mockito.verify(scalar, Mockito.never()).value(Mockito.any());
    }

    /**
     * AbstractField delegates actual value calculations
     * for other scalar implementation.
     */
    @Test
    public void delegatesActualValueForScalars() {
        final Decimal field = new Decimal();
        final Scalar scalar = Mockito.mock(Scalar.class);
        field.actual(scalar);
        Mockito.verify(scalar).value(Mockito.eq(field));
    }
}
