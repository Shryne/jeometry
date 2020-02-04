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
package com.jeometry.twod.point;

import com.aljebra.scalar.Diff;
import com.aljebra.scalar.Division;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.FixedVector;
import com.aljebra.vector.Vect;
import com.jeometry.twod.line.Line;
import com.jeometry.twod.line.analytics.Intersecting;
import com.jeometry.twod.line.analytics.LineAnalytics;
import com.jeometry.twod.line.analytics.LinePointOrdinate;
import lombok.ToString;

/**
 * Two lines intersection point.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ToString
public final class LineIntersectPoint extends XyPoint {

    /**
     * Constructor.
     * @param first First line
     * @param second Second line
     */
    public LineIntersectPoint(final Line first, final Line second) {
        super(LineIntersectPoint.vector(first, second));
    }

    /**
     * Builds a vector corresponding to the intersection point.
     * @param first First line
     * @param second Second line
     * @return The intersection point
     */
    private static Vect vector(final Line first, final Line second) {
        final Scalar abscissa = LineIntersectPoint.abscissa(first, second);
        return new FixedVector(
            abscissa, LineIntersectPoint.ordinate(first, second, abscissa)
        );
    }

    /**
     * Calculates the ordinate of the intersecting point given its the abscissa.
     * @param first First line
     * @param second Second line
     * @param abcissa Intersecting point abscissa
     * @return Scalar representing the ordinate
     */
    private static Scalar ordinate(final Line first, final Line second,
        final Scalar abcissa) {
        return new LineAnalytics(first).vertical().ifElse(
            new LinePointOrdinate(second, abcissa),
            new LinePointOrdinate(first, abcissa)
        );
    }

    /**
     * Calculates abscissa of the intersecting point.
     * @param first First line
     * @param second Second line
     * @return Scalar representing the abscissa
     */
    private static Scalar abscissa(final Line first, final Line second) {
        final LineAnalytics fst = new LineAnalytics(first);
        final LineAnalytics snd = new LineAnalytics(second);
        return new Intersecting(first, second).ifElse(
            fst.vertical().ifElse(
                first.point().coords()[0],
                snd.vertical().ifElse(
                    second.point().coords()[0],
                    new Division(
                        new Diff(snd.intercept(), fst.intercept()),
                        new Diff(fst.slope(), snd.slope())
                    )
                )
            ),
            new IllegalStateException(
                "Undefined intersecting point for two parallel lines."
            )
        );
    }

}
