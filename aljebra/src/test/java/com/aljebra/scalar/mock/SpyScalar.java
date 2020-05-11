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

package com.aljebra.scalar.mock;

import com.aljebra.field.Field;
import com.aljebra.scalar.Scalar;
import java.util.Optional;

/**
 * Scalar decorator with spying capabilities on the value method calls.
 * It holds the last {@link Field} passed when calling value method as an optional. The optional
 * is empty if the method was never called.
 * @param <T> scalar types
 * @since 0.1
 */
public final class SpyScalar<T> implements Scalar<T> {

    /**
     * An optional holding the last field passed as parameter when calling value method.
     * The optional is empty if the method was never called.
     */
    private Optional<Field<T>> fld;

    /**
     * Decorated scalar.
     */
    private final Scalar<T> org;

    /**
     * Ctor.
     * @param origin The scalar to decorate.
     */
    public SpyScalar(final Scalar<T> origin) {
        this.org = origin;
        this.fld = Optional.empty();
    }

    @Override
    public T value(final Field<T> field) {
        this.fld = Optional.of(field);
        return this.org.value(field);
    }

    /**
     * Accessor for the last field passed when calling value method.
     * @return The field
     */
    public Optional<Field<T>> field() {
        return this.fld;
    }
}
