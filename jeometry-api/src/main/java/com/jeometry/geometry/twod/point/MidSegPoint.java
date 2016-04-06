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
package com.jeometry.geometry.twod.point;

import com.jeometry.geometry.twod.segment.Segment;
import com.jeometry.model.algebra.field.OrderedField;
import com.jeometry.model.algebra.scalar.Add;
import com.jeometry.model.algebra.scalar.Division;
import com.jeometry.model.algebra.scalar.Scalar;
import com.jeometry.model.algebra.vector.Minus;
import com.jeometry.model.algebra.vector.Sum;
import com.jeometry.model.algebra.vector.Times;
import com.jeometry.model.algebra.vector.Vect;
import lombok.ToString;

/**
 * A point defined by belonging to a segment. The point is fixed upon
 * construction, which means that a modification to the underlying segment does
 * not ensure that this point is still inside the segment.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ToString(callSuper = true)
public final class MidSegPoint extends XyPoint {

    /**
     * Constructor.
     * @param seg The segment to belong to
     * @param field Field for scalar randomization
     */
    public MidSegPoint(final Segment seg, final OrderedField<?> field) {
        this(MidSegPoint.vector(seg, field));
    }

    /**
     * Constructor.
     * @param vector Point belonging to the segment
     */
    private MidSegPoint(final Vect vector) {
        super(vector.coords()[0], vector.coords()[1]);
    }

    /**
     * Builds a vector belonging to the segment.
     * @param seg The segment to belong to
     * @param field Field for scalar randomization
     * @return A point belonging to the segment
     */
    private static Vect vector(final Segment seg, final OrderedField<?> field) {
        final Scalar one = field.multIdentity();
        final Scalar half = new Division(one, new Add(one, one));
        return new Sum(
            new Times(new Minus(seg.start(), seg.end()), half), seg.start()
        );
    }

}
