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
package com.jeometry.twod.point;

import com.aljebra.scalar.Add;
import com.aljebra.scalar.MultIdentity;
import com.aljebra.scalar.MultInverse;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.Minus;
import com.aljebra.vector.Sum;
import com.aljebra.vector.Times;
import com.aljebra.vector.Vect;
import com.jeometry.twod.segment.Segment;
import lombok.ToString;

/**
 * A point defined by being a segment midpoint.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ToString(callSuper = true)
public final class MidSegPoint implements Vect {

    /**
     * The segment to midpoint.
     */
    private final Segment seg;

    /**
     * Constructor.
     * @param seg The segment to belong to
     */
    public MidSegPoint(final Segment seg) {
        this.seg = seg;
    }

    @Override
    public Scalar[] coords() {
        return MidSegPoint.vector(this.seg).coords();
    }

    /**
     * Builds a vector belonging to the segment.
     * @param seg The segment to belong to
     * @return A point belonging to the segment
     */
    private static Vect vector(final Segment seg) {
        final Scalar half = new MultInverse(
            new Add(new MultIdentity(), new MultIdentity())
        );
        return new Sum(
            new Times(new Minus(seg.end(), seg.start()), half), seg.start()
        );
    }

}
