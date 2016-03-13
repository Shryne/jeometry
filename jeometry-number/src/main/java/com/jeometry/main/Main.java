package com.jeometry.main;

import com.jeometry.geometry.twod.PointInLine;
import com.jeometry.geometry.twod.XyVector;
import com.jeometry.geometry.twod.line.ParallelPassingByLine;
import com.jeometry.geometry.twod.line.PointDirectionLine;
import com.jeometry.model.algebra.field.Field;
import com.jeometry.model.algebra.scalar.Scalar;
import com.jeometry.model.algebra.vector.Minus;
import com.jeometry.model.scalar.Decimal;

public class Main {

    public static void main(String[] args) {
        final Field<Double> f = new Decimal();
        XyVector pointa = new XyVector(
            new Scalar.Default<Double>(1.), new Scalar.Default<Double>(2.)
        );
        XyVector origin = new XyVector(
            new Scalar.Default<Double>(0.), new Scalar.Default<Double>(0.)
        );
        XyVector dir = new XyVector(
            new Scalar.Default<Double>(2.), new Scalar.Default<Double>(0.)
        );
        PointDirectionLine hor = new PointDirectionLine(dir, origin);
        PointInLine pointb = new PointInLine(hor, f);
        PointDirectionLine abline = new PointDirectionLine(
            new Minus(pointa, pointb), pointb
        );
        ParallelPassingByLine lineb = new ParallelPassingByLine(pointa, hor);
        Figure figure = new Figure(f)
            .add(hor).add(pointa).add(pointb).add(abline).add(lineb)
            .withSize(50, 50);
        figure.setVisible(true);

    }

}
