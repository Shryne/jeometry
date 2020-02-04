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
package com.jeometry.twod.segment;

import com.aljebra.metric.scalar.Norm;
import com.aljebra.metric.vect.Normalized;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.Times;
import com.aljebra.vector.Vect;
import com.jeometry.twod.point.RandomPoint;
import com.jeometry.twod.point.SegVect;
import lombok.ToString;

/**
 * A random segment defined by its length. The length is defined by a scalar, or
 * by a segment, in which case this segment will be congruent
 * to the given segment.
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
     * Constructor. Builds a segment congruent to the given segment.
     * @param seg Segment to be congruent to
     */
    public LengthSegment(final Segment seg) {
        this(new RandomPoint(), seg);
    }

    /**
     * Constructor. Builds a segment congruent to the given segment and having
     * the passed point as one of its extremities.
     * @param start Segment extremity
     * @param seg Segment to be congruent to
     */
    public LengthSegment(final Vect start, final Segment seg) {
        this(start, new Norm(new SegVect(seg)));
    }

    /**
     * Constructor. Builds a segment with the given length.
     * @param length Segment length
     */
    public LengthSegment(final Scalar length) {
        this(new RandomPoint(), length);
    }

    /**
     * Constructor. Builds a segment with the given length and having
     * the passed point as one of its extremities.
     * @param start Segment extremity
     * @param length Segment length
     */
    public LengthSegment(final Vect start, final Scalar length) {
        this(start, new RandomPoint(), length);
    }

    /**
     * Constructor. Builds a segment with the given length, having the given
     * direction and having the passed point as one of its extremities.
     * @param strt Segment extremity
     * @param dir Segment direction
     * @param length Segment length
     */
    public LengthSegment(final Vect strt, final Vect dir, final Scalar length) {
        this.seg = new PtVectSegment(
            strt, new Times(new Normalized(dir), length)
        );
    }

    @Override
    public Vect start() {
        return this.seg.start();
    }

    @Override
    public Vect end() {
        return this.seg.end();
    }

}
