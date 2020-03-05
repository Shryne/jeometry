/*
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
package com.jeometry.twod.style.impl;

import com.jeometry.twod.style.Fill;
import com.jeometry.twod.style.Stroke;

/**
 * Default style class. A {@link FixedStyle} that defaults to
 * {@link TransparentFill} and {@link DefaultStroke}.
 * @since 0.1
 */
public final class DefaultStyle extends FixedStyle {

    /**
     * Ctor. Builds a style with the passed stroke style and the
     * {@link TransparentFill} fill style.
     * @param stroke Stroke style
     */
    public DefaultStyle(final Stroke stroke) {
        super(stroke, new TransparentFill());
    }

    /**
     * Ctor. Builds a style with the passed fill style and the
     * {@link DefaultStroke} stroke style.
     * @param fill Fill style
     */
    public DefaultStyle(final Fill fill) {
        super(new DefaultStroke(), fill);
    }

    /**
     * Ctor. Builds a style with a {@link DefaultStroke} and a
     * {@link FixedColorFill}.
     */
    public DefaultStyle() {
        this(new DefaultStroke());
    }

}
