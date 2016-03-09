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
package com.jeometry.geometry.twod.line;

import com.jeometry.model.algebra.vector.Vect;

/**
 * A line defined by a point to pass by and a direction.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class PointDirectionLine implements Line {

    /**
     * Direction.
     */
    private Vect dir;

    /**
     * Point belonging to the line.
     */
    private Vect pnt;

    /**
     * Constructor.
     * @param direction Direction of the line
     * @param point Point belonging to the line
     */
    public PointDirectionLine(final Vect direction, final Vect point) {
        super();
        this.dir = direction;
        this.pnt = point;
    }

    @Override
    public Vect direction() {
        return this.dir;
    }

    /**
     * Modifies the direction of the line.
     * @param direction New direction of the line
     */
    public void setDirection(final Vect direction) {
        this.dir = direction;
    }

    @Override
    public Vect point() {
        return this.pnt;
    }

    /**
     * Modifies the point that should belong to the line.
     * @param point New point to pass by.
     */
    public void setPoint(final Vect point) {
        this.pnt = point;
    }
}
