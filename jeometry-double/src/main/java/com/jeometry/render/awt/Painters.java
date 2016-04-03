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
package com.jeometry.render.awt;

import java.util.ArrayList;
import java.util.List;

/**
 * A holding class of the default list of {@link AbstractAwtPaint}.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class Painters extends ArrayList<AbstractAwtPaint> {

    /**
     * Serial version ID.
     */
    private static final long serialVersionUID = -6863539915215768631L;

    /**
     * Default painters.
     */
    private final transient List<AbstractAwtPaint> def;

    /**
     * Ctor.
     */
    public Painters() {
        super();
        this.def = this.init();
    }

    /**
     * Accessor for the default AWT painters.
     * @return A list of default painters
     */
    public List<AbstractAwtPaint> defaults() {
        return this.def;
    }

    /**
     * Initializes the list of the default painters.
     * @return A list of default painters
     */
    private List<AbstractAwtPaint> init() {
        final int size = 6;
        final List<AbstractAwtPaint> defaults = new ArrayList<>(size);
        defaults.add(new AwtPoint());
        defaults.add(new AwtCircle());
        defaults.add(new AwtLine());
        defaults.add(new AwtRay());
        defaults.add(new AwtAngle());
        defaults.add(new AwtSegment());
        this.addAll(defaults);
        return defaults;
    }
}
