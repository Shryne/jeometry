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

import com.jeometry.geometry.twod.line.Line;
import com.jeometry.geometry.twod.line.ParallelPassingByLine;
import com.jeometry.geometry.twod.line.PointDirectionLine;
import com.jeometry.geometry.twod.line.TwoPointsLine;
import com.jeometry.geometry.twod.point.PointInLine;
import com.jeometry.model.algebra.vector.Vect;
import com.jeometry.model.decimal.DblCircle;
import com.jeometry.model.decimal.DblPoint;
import com.jeometry.model.decimal.Decimal;
import com.jeometry.render.awt.Awt;

/**
 * Main testing class using {@link Awt} output.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Main {

    public static void main(String[] args) {
        DblPoint pointa = new DblPoint(1d, 2d);
        DblPoint origin = new DblPoint(0d, 0d);
        Vect dir = new DblPoint(2d, 0d);
        PointDirectionLine hor = new PointDirectionLine(dir, origin);
        PointInLine pointb = new PointInLine(hor, new Decimal());
        Line abline = new TwoPointsLine(pointa, pointb);
        ParallelPassingByLine lineb = new ParallelPassingByLine(pointa, hor);
        Figure figure = new Figure()
            .add(hor).add(abline).add(lineb).add(pointb).add(pointa).add(origin)
            .add(new DblCircle(5., pointa));
        final Awt awt = new Awt().withSize(50, 50);
        awt.render(figure);
        awt.setVisible(true);
    }

}
