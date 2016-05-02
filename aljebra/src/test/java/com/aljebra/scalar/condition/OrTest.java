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
package com.aljebra.scalar.condition;

import com.aljebra.field.Field;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link Or}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class OrTest {

    /**
     * {@link Or} returns true if at least one predicate resolves to true.
     */
    @Test
    public void resolvesToTrueWhenOnePredicateTrue() {
        MatcherAssert.assertThat(
            new Or(
                OrTest.positive(), OrTest.negative(), OrTest.negative()
            ).resolve(Mockito.mock(Field.class)),
            Matchers.is(true)
        );
    }

    /**
     * {@link Or} resolves to false if all predicates resolve to false.
     */
    @Test
    public void resolvesToFalseWhenAllPredicatesFalse() {
        MatcherAssert.assertThat(
            new Or(
                OrTest.negative(), OrTest.negative(), OrTest.negative()
            ).resolve(Mockito.mock(Field.class)),
            Matchers.is(false)
        );
    }

    /**
     * Returns an always true predicate.
     * @return A predicate always resolving to true
     */
    private static Predicate positive() {
        return new Predicate() {
            @Override
            public boolean resolve(final Field<?> field) {
                return true;
            }
        };
    }

    /**
     * Returns an always false predicate.
     * @return A predicate always resolving to false
     */
    private static Predicate negative() {
        return new Predicate() {
            @Override
            public boolean resolve(final Field<?> field) {
                return false;
            }
        };
    }
}
