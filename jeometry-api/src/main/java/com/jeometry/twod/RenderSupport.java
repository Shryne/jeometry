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

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A {@link Renderer} wrapper class. {@link RenderSupport} wraps a renderer
 * and relies on passed classes to delegates rendering iff the renderable is
 * supported (subclass/subinterface of passed classes).
 * @since 0.1
 */
public final class RenderSupport implements Renderer {

    /**
     * Supported classes.
     */
    private final Set<Class<?>> supported;

    /**
     * Wrapped renderer.
     */
    private final Renderer origin;

    /**
     * Ctor.
     * @param origin Wrapped renderer
     * @param clazz Supported classes
     */
    public RenderSupport(final Renderer origin, final Class<?>... clazz) {
        this.supported = new HashSet<>(Arrays.asList(clazz));
        this.origin = origin;
    }

    @Override
    public void render(final Shape renderable) {
        if (this.supports(renderable)) {
            this.origin.render(renderable);
        }
    }

    /**
     * Checks if the renderable is supported.
     * @param renderable Renderable
     * @return True if the renderable is supported
     */
    private boolean supports(final Shape renderable) {
        boolean result = false;
        for (final Class<?> clazz : this.supported) {
            if (clazz.isAssignableFrom(renderable.renderable().getClass())) {
                result = true;
                break;
            }
        }
        return result;
    }

}
