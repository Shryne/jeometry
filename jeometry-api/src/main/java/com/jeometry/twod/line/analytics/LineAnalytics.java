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
package com.jeometry.twod.line.analytics;

import com.aljebra.scalar.Scalar;
import com.aljebra.scalar.condition.Predicate;
import com.jeometry.twod.line.Line;
import lombok.EqualsAndHashCode;

/**
 * Convenience class to regroup line analytics predicates and scalars like
 * vertical, slope and y-intercept.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@EqualsAndHashCode
public final class LineAnalytics {

    /**
     * Line to analyze.
     */
    private final Line line;

    /**
     * Constructor.
     * @param line Line for which to calculate analytics
     */
    public LineAnalytics(final Line line) {
        this.line = line;
    }

    /**
     * Calculates the line slope.
     * @return A scalar representing the line slope
     */
    public Scalar slope() {
        return new Slope(this.line);
    }

    /**
     * Calculates the line y-intercept.
     * @return A scalar representing the line y-intercept
     */
    public Scalar intercept() {
        return new Intercept(this.line);
    }

    /**
     * Checks if the line is vertical.
     * @return A predicate for the line verticality
     */
    public Predicate vertical() {
        return new Vertical(this.line);
    }

}
