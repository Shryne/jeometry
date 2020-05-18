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
package com.jeometry.render;

import com.jeometry.model.decimal.DblPoint;
import java.awt.Dimension;
import java.util.Random;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;

/**
 * Tests for {@link Surface}.
 * @since 0.1
 */
public final class SurfaceTest {

    /**
     * {@link Surface} gives access to properties.
     */
    @Test
    public void givesAccessToPoperties() {
        final int width = new Random().nextInt();
        final int height = new Random().nextInt();
        final double scale = Math.random();
        final double xcoor = Math.random();
        final double ycoor = Math.random();
        final Surface ctx = new Surface(
            new Dimension(width, height), scale, new DblPoint(xcoor, ycoor)
        );
        MatcherAssert.assertThat(
            ctx.center(), Matchers.equalTo(new DblPoint(xcoor, ycoor))
        );
        MatcherAssert.assertThat(
            ctx.height(), Matchers.equalTo(height)
        );
        MatcherAssert.assertThat(
            ctx.width(), Matchers.equalTo(width)
        );
        MatcherAssert.assertThat(
            ctx.scale(), Matchers.equalTo(scale)
        );
    }

}
