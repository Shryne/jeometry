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
package com.jeometry.twod.point;

import com.aljebra.scalar.Diff;
import com.aljebra.scalar.Division;
import com.aljebra.scalar.Scalar;
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
public final class LineIntersectPoint implements Vect {

    /**
     * First line.
     */
    private final Line first;

    /**
     * Second line.
     */
    private final Line second;

    /**
     * Constructor.
     * @param first First line
     * @param second Second line
     */
    public LineIntersectPoint(final Line first, final Line second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Scalar[] coords() {
        final Scalar abscissa = this.abscissa();
        return new Scalar[] {abscissa, this.ordinate(abscissa)};
    }

    /**
     * Calculates the ordinate of the intersecting point given its the abscissa.
     * @param abcissa Intersecting point abscissa
     * @return Scalar representing the ordinate
     */
    private Scalar ordinate(final Scalar abcissa) {
        return new LineAnalytics(this.first).vertical().ifElse(
            new LinePointOrdinate(this.second, abcissa),
            new LinePointOrdinate(this.first, abcissa)
        );
    }

    /**
     * Calculates abscissa of the intersecting point.
     * @return Scalar representing the abscissa
     */
    private Scalar abscissa() {
        final LineAnalytics fst = new LineAnalytics(this.first);
        final LineAnalytics snd = new LineAnalytics(this.second);
        return new Intersecting(this.first, this.second).ifElse(
            fst.vertical().ifElse(
                this.first.point().coords()[0],
                snd.vertical().ifElse(
                    this.second.point().coords()[0],
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
