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
package com.jeometry.main;

import com.jeometry.geometry.twod.Figure;
import com.jeometry.geometry.twod.angle.ThreePtsAngle;
import com.jeometry.geometry.twod.line.Line;
import com.jeometry.geometry.twod.line.ParallelPassingByLine;
import com.jeometry.geometry.twod.line.TwoPointsLine;
import com.jeometry.geometry.twod.point.PointInLine;
import com.jeometry.geometry.twod.ray.PtDirRay;
import com.jeometry.geometry.twod.segment.TwoPtsSeg;
import com.jeometry.model.decimal.DblPoint;
import com.jeometry.model.decimal.Decimal;
import com.jeometry.render.awt.Awt;

/**
 * Main testing class using {@link Awt} output.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class Main {

    /**
     * Private constructor.
     */
    private Main() {
    }

    /**
     * Main start method.
     * @param args Unused args
     */
    public static void main(final String... args) {
        final DblPoint pointa = new DblPoint(1d, 2d);
        final DblPoint pointp = new DblPoint(1d, -2d);
        final DblPoint pointc = new DblPoint(-3d, 1d);
        final DblPoint pointd = new DblPoint(0d, 5d);
        final DblPoint pointe = new DblPoint(-4d, 2d);
        final DblPoint pointk = new DblPoint(5d, 0d);
        final DblPoint origin = new DblPoint(0d, 0d);
        final TwoPointsLine hor = new TwoPointsLine(pointk, origin);
        final PointInLine pointb = new PointInLine(hor, new Decimal());
        final Line abline = new TwoPointsLine(pointa, pointb);
        final Line apline = new TwoPointsLine(pointa, pointp);
        final Line lineb = new ParallelPassingByLine(pointa, hor);
        final Figure figure = new Figure()
            .add(hor).add(abline).add(lineb).add(pointb).add(pointa).add(apline)
            .add(origin).add(new PtDirRay(pointa, new DblPoint(3.0, 3.0)))
            .add(new PtDirRay(new DblPoint(0., 3.0), new DblPoint(-1.0, -10.0)))
            .add(new PtDirRay(new DblPoint(0., -3.0), new DblPoint(3.0, -2.0)))
        .add(new ThreePtsAngle(pointc, pointd, pointe))
        .add(new TwoPtsSeg(origin, pointb)).add(new TwoPtsSeg(pointk, pointe));
        final Awt awt = new Awt().withSize(50, 50);
        awt.render(figure);
        awt.setVisible(true);
    }

}
