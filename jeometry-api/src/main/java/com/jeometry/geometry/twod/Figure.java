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
package com.jeometry.geometry.twod;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a figure composed of shapes to output.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class Figure {

    /**
     * Shapes list.
     */
    private final List<Shape> shaps = new ArrayList<>(10);

    /**
     * Adds a shape to the figure.
     * @param shape The shape to add
     * @return This figure instance
     */
    public Figure add(final Shape shape) {
        this.shaps.add(shape);
        return this;
    }

    /**
     * Adds an anonymous renderable to the figure.
     * @param shape The renderable to add
     * @return This figure instance
     */
    public Figure add(final Renderable shape) {
        this.shaps.add(new Shape(shape));
        return this;
    }

    /**
     * Adds an named renderable to the figure.
     * @param shape The renderable to add
     * @param name The renderable name
     * @return This figure instance
     */
    public Figure add(final Renderable shape, final String name) {
        this.shaps.add(new Shape(shape, name));
        return this;
    }

    /**
     * Accessor for the figure shapes.
     * @return The list of the shapes
     */
    public List<Shape> shapes() {
        return this.shaps;
    }
}
