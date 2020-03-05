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
package com.jeometry.twod.arc;

import com.aljebra.scalar.Scalar;
import com.aljebra.vector.Vect;
import com.jeometry.twod.circle.Circle;
import lombok.ToString;

/**
 * Arc implementation class describing an arc by a circle a starting
 * and an ending angle.
 * @since 0.1
 */
@ToString
public class RadAngleArc implements Arc {

    /**
     * Arc radius.
     */
    private final Scalar rdx;

    /**
     * Arc center.
     */
    private final Vect cntr;

    /**
     * Arc starting angle.
     */
    private final Number starting;

    /**
     * Arc ending angle.
     */
    private final Number ending;

    /**
     * Ctor.
     * @param circle Circle arc
     * @param start Arc starting angle
     * @param end Arc ending angle
     */
    public RadAngleArc(final Circle circle, final Number start,
        final Number end) {
        this.rdx = circle.radius();
        this.cntr = circle.center();
        this.starting = start;
        this.ending = end;
    }

    @Override
    public final Vect center() {
        return this.cntr;
    }

    @Override
    public final Scalar radius() {
        return this.rdx;
    }

    @Override
    public final Number start() {
        return this.starting;
    }

    @Override
    public final Number end() {
        return this.ending;
    }

}
