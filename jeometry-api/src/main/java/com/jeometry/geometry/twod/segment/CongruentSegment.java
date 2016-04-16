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
package com.jeometry.geometry.twod.segment;

import com.aljebra.scalar.Norm;
import com.aljebra.vector.Vect;
import com.jeometry.geometry.twod.point.SegVect;
import lombok.ToString;

/**
 * A segment defined by being congruent to another segment.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ToString(includeFieldNames = false)
public final class CongruentSegment implements Segment {

    /**
     * Generated segment.
     */
    private final Segment seg;

    /**
     * Constructor.
     * @param seg Segment to be congruent to
     */
    public CongruentSegment(final Segment seg) {
        this.seg = CongruentSegment.congruent(seg);
    }

    @Override
    public Vect start() {
        return this.seg.start();
    }

    @Override
    public Vect end() {
        return this.seg.end();
    }

    /**
     * Generates a segment congruent to the given segment.
     * @param seg Segment to be congruent to
     * @return Random segement congruent to the given segment
     */
    private static Segment congruent(final Segment seg) {
        return new LengthSegment(new Norm(new SegVect(seg)));
    }

}
