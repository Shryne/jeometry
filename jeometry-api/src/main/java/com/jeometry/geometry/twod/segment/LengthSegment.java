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

import com.aljebra.scalar.Scalar;
import com.aljebra.vector.Times;
import com.aljebra.vector.Vect;
import com.aljebra.vector.metric.Normalized;
import com.jeometry.geometry.twod.point.SegVect;
import lombok.ToString;

/**
 * A random segment defined by its length.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ToString(includeFieldNames = false)
public final class LengthSegment implements Segment {

    /**
     * Generated segment.
     */
    private final Segment seg;

    /**
     * Constructor.
     * @param length Segment length
     */
    public LengthSegment(final Scalar length) {
        this.seg = LengthSegment.generate(length);
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
     * Generates a random segment with this length.
     * @param length Segment length
     * @return A segment with the given length
     */
    private static Segment generate(final Scalar length) {
        final Segment seg = new RandomSegment();
        return new PtVectSegment(
            seg.start(), new Times(new Normalized(new SegVect(seg)), length)
        );
    }

}
