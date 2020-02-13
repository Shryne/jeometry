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
package com.jeometry.twod;

import com.jeometry.twod.line.Line;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link RenderSupport}.
 * @since 0.1
 */
public final class RenderSupportTest {

    /**
     * {@link RenderSupport} delegates to wrapped renderer when shape class
     * is supported.
     */
    @Test
    public void delegatesToWrappedWhenSupported() {
        final Renderer rend = Mockito.mock(Renderer.class);
        final Shape shape = new Shape(Mockito.mock(Line.class));
        final RenderSupport renderer = new RenderSupport(rend, Line.class);
        renderer.render(shape);
        Mockito.verify(rend).render(shape);
    }

    /**
     * {@link RenderSupport} does nothing when shape class is not supported.
     */
    @Test
    public void ignoresUnsupportedShape() {
        final Renderer rend = Mockito.mock(Renderer.class);
        final Shape shape = new Shape(Mockito.mock(Renderable.class));
        final RenderSupport renderer = new RenderSupport(rend, Line.class);
        renderer.render(shape);
        Mockito.verify(rend, Mockito.never()).render(shape);
    }

}
