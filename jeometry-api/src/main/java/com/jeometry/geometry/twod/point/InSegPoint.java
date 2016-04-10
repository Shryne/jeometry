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

import com.aljebra.field.OrderedField;
import com.aljebra.scalar.Scalar;
import com.aljebra.vector.Minus;
import com.aljebra.vector.Sum;
import com.aljebra.vector.Times;
import com.aljebra.vector.Vect;
import com.jeometry.geometry.twod.segment.Segment;
import lombok.ToString;

/**
 * A point defined by belonging to a segment.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
@ToString(callSuper = true)
public final class InSegPoint implements Vect {

    /**
     * A random scalar.
     */
    private final Scalar factor;

    /**
     * The segment to belong to.
     */
    private final Segment seg;

    /**
     * Constructor.
     * @param seg The segment to belong to
     * @param field Field for scalar randomization
     */
    public InSegPoint(final Segment seg, final OrderedField<?> field) {
        this.factor = field.between(field.addIdentity(), field.multIdentity());
        this.seg = seg;
    }

    @Override
    public Scalar[] coords() {
        return new Sum(
            new Times(new Minus(this.seg.end(), this.seg.start()), this.factor),
            this.seg.start()
        ).coords();
    }

}
