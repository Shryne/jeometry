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
package com.jeometry.twod.line.analytics;

import com.aljebra.scalar.Scalar;
import com.aljebra.scalar.condition.Predicate;
import com.jeometry.twod.line.Line;
import lombok.EqualsAndHashCode;

/**
 * Convenience class to regroup line analytics predicates and scalars like
 * vertical, slope and y-intercept.
 * @param <T> scalar types
 * @since 0.1
 */
@EqualsAndHashCode
public final class LineAnalytics<T> {

    /**
     * Line to analyze.
     */
    private final Line<T> line;

    /**
     * Constructor.
     * @param line Line for which to calculate analytics
     */
    public LineAnalytics(final Line<T> line) {
        this.line = line;
    }

    /**
     * Calculates the line slope.
     * @return A scalar representing the line slope
     */
    public Scalar<T> slope() {
        return new Slope<T>(this.line);
    }

    /**
     * Calculates the line y-intercept.
     * @return A scalar representing the line y-intercept
     */
    public Scalar<T> intercept() {
        return new Intercept<T>(this.line);
    }

    /**
     * Checks if the line is vertical.
     * @return A predicate for the line verticality
     */
    public Predicate<T> vertical() {
        return new Vertical<>(this.line);
    }

    /**
     * Calculates the ordinate of the line point by its abcissa.
     * @param abcissa Point abcissa
     * @return A scalar representing the ordinate
     */
    public Scalar<T> ordinate(final Scalar<T> abcissa) {
        return new LinePointOrdinate<>(this.line, abcissa);
    }
}
