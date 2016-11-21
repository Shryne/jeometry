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
package com.jeometry.twod.scalar;

import com.aljebra.field.Field;
import com.aljebra.metric.scalar.Norm;
import com.aljebra.scalar.Scalar;
import com.jeometry.twod.point.SegVect;
import com.jeometry.twod.segment.Segment;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A scalar representing the length of a segment (in a metric system).
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@EqualsAndHashCode
@ToString(includeFieldNames = false)
public final class SegmentLength implements Scalar {

    /**
     * Segment.
     */
    private final Segment seg;

    /**
     * Constructor.
     * @param seg Segment for which to calculate the length
     */
    public SegmentLength(final Segment seg) {
        this.seg = seg;
    }
    @Override
    public <T> T value(final Field<T> field) {
        return new Norm(new SegVect(this.seg)).value(field);
    }

}
