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
package com.jeometry.twod.line;

import com.aljebra.scalar.AddIdentity;
import com.aljebra.vector.Vect;
import com.jeometry.twod.point.PtReflectionPoint;
import com.jeometry.twod.point.XyPoint;
import lombok.ToString;

/**
 * A line defined as the reflection of a line, across a given point.
 * @since 0.1
 */
@ToString(includeFieldNames = false)
public final class PtReflectionLine extends PtDirLine {

    /**
     * Constructor.
     * @param center Reflection center
     * @param line The line to reflect
     */
    public PtReflectionLine(final Vect center, final Line line) {
        super(new PtReflectionPoint(center, line.point()), line.direction());
    }

    /**
     * Constructor. Builds the reflection line across the origin.
     * @param input The line to reflect
     */
    public PtReflectionLine(final Line input) {
        this(new XyPoint(new AddIdentity(), new AddIdentity()), input);
    }

}
