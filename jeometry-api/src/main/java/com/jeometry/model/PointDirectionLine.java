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
package com.jeometry.model;

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
    private Vector direction;

    /**
     * Point belonging to the line.
     */
    private Vector point;

    /**
     * Constructor.
     * @param direction Direction of the line
     * @param point Point belonging to the line
     */
    public PointDirectionLine(final Vector direction, final Vector point) {
        super();
        this.direction = direction;
        this.point = point;
    }

    @Override
    public Vector direction() {
        return this.direction;
    }

    /**
     * Modifies the direction of the line.
     * @param dir New direction of the line
     */
    public void setDirection(final Vector dir) {
        this.direction = dir;
    }

    @Override
    public Vector point() {
        return this.point;
    }

    /**
     * Modifies the point that should belong to the line.
     * @param pnt New point to pass by.
     */
    public void setPoint(final Vector pnt) {
        this.point = pnt;
    }
}
