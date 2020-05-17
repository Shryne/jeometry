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
package com.aljebra.field.mock;

import com.aljebra.field.OrderedRandom;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * Mock OrderedRandom with spying (verifying) capabilities on the methods between, greater
 * and lower.
 * It holds the parameters with which the methods were last called as optionals.
 * If the optional is empty, that means the method was never called.
 * @param <T> scalar types
 * @since 0.3
 */
public final class MkOrderedRandom<T> implements OrderedRandom<T> {

    /**
     * A list of subsequent returned values for between method.
     */
    private final Optional<List<T>> forced;

    /**
     * An iterator iterating over forced values to return for between method.
     */
    private Optional<Iterator<T>> iter;

    /**
     * An optional holding the last scalars passed as parameter when calling between method.
     * The optional is empty if the method was never called.
     */
    private Optional<List<T>> bet;

    /**
     * An optional holding the last scalar passed as parameter when calling greater method.
     * The optional is empty if the method was never called.
     */
    private Optional<T> great;

    /**
     * An optional holding the last scalar passed as parameter when calling lower method.
     * The optional is empty if the method was never called.
     */
    private Optional<T> low;

    /**
     * Default constructor with no forced returned values for between method.
     */
    public MkOrderedRandom() {
        this(Optional.empty());
    }

    /**
     * Ctor.
     * @param forced List of subsequent values to be returned by between method. If the between
     *  method is called more times than this list has elements, arbitrary values will be returned.
     */
    public MkOrderedRandom(final Optional<List<T>> forced) {
        this.forced = forced;
        this.iter = Optional.empty();
        this.bet = Optional.empty();
        this.great = Optional.empty();
        this.low = Optional.empty();
    }

    /**
     * Accessor for the last scalars passed when calling between method.
     * @return An optional probably containing two scalars, or empty if the method was never called
     */
    public Optional<List<T>> between() {
        return this.bet;
    }

    /**
     * Accessor for the last scalar passed when calling greater method.
     * @return An optional probably containing a scalar, or empty if the method was never called
     */
    public Optional<T> greater() {
        return this.great;
    }

    /**
     * Accessor for the last scalar passed when calling lower method.
     * @return An optional probably containing a scalar, or empty if the method was never called
     */
    public Optional<T> lower() {
        return this.low;
    }

    @Override
    public T between(final T lower, final T upper) {
        this.bet = Optional.of(Arrays.asList(lower, upper));
        T result = lower;
        if (this.forced.isPresent() && !this.iter.isPresent()) {
            this.iter = Optional.of(this.forced.get().iterator());
        }
        if (this.iter.isPresent()) {
            final Iterator<T> iterator = this.iter.get();
            if (iterator.hasNext()) {
                result = iterator.next();
            }
        }
        return result;
    }

    @Override
    public T greater(final T lower) {
        this.great = Optional.of(lower);
        return lower;
    }

    @Override
    public T lower(final T upper) {
        this.low = Optional.of(upper);
        return upper;
    }

}
