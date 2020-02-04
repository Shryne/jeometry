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

import com.aljebra.vector.Vect;
import com.jeometry.twod.line.Line;
import com.jeometry.twod.line.PerpLine;
import com.jeometry.twod.line.PtDirLine;
import lombok.ToString;

/**
 * A point defined as the projection of a point, onto a line.
 * The projection is parallel to the given direction.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ToString
public final class ProjectionPoint extends XyPoint {

    /**
     * Constructor.
     * @param line Line to project onto
     * @param dir Projection direction
     * @param input The point to project
     */
    public ProjectionPoint(final Line line, final Vect dir, final Vect input) {
        super(new LineIntersectPoint(new PtDirLine(input, dir), line));
    }

    /**
     * Constructor. Builds the projection point parallel to a given line.
     * @param line Line to project onto
     * @param dir Projection direction line
     * @param input The point to project
     */
    public ProjectionPoint(final Line line, final Line dir, final Vect input) {
        this(line, dir.direction(), input);
    }

    /**
     * Constructor. Builds the orthogonal projection point.
     * @param line Line to project onto
     * @param input The point to orthogonally project on the line
     */
    public ProjectionPoint(final Line line, final Vect input) {
        this(line, new PerpLine(line), input);
    }

}
