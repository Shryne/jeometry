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
package com.jeometry.twod.arc;

import com.aljebra.scalar.Scalar;
import com.aljebra.vector.Vect;
import com.jeometry.twod.Renderable;

/**
 * Arc interface describing an arc by a center, a radius, a starting angle
 * and an ending angle.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Arc extends Renderable {

    /**
     * Gives the center of the arc.
     * @return Arc center
     */
    Vect center();

    /**
     * Gives the arc radius.
     * @return Arc radius
     */
    Scalar radius();

    /**
     * Gives the arc starting angle measure (as defined according
     * to trigonometric circle).
     * @return Arc starting angle
     */
    Number start();

    /**
     * Gives the arc ending angle measure (as defined according
     * to trigonometric circle).
     * @return Arc ending angle
     */
    Number end();

}
