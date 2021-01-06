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

import com.jeometry.twod.style.Style;
import com.jeometry.twod.style.impl.DefaultStyle;
import java.util.Optional;
import lombok.ToString;

/**
 * Represents a Shape. A shape is a {@link Renderable} with rendering options.
 * @param <T> renderable type
 * @since 0.1
 */
@ToString(includeFieldNames = false)
public final class Shape<T extends Renderable> {

    /**
     * Renderable.
     */
    private final T renderable;

    /**
     * Renderable name.
     */
    private final Optional<String> symbol;

    /**
     * Renderable style.
     */
    private final Style options;

    /**
     * Ctor.
     * @param renderable Renderable
     * @param symbol Renderable name (or empty optional)
     * @param style Renderable style
     */
    public Shape(final T renderable, final Optional<String> symbol,
        final Style style) {
        this.renderable = renderable;
        this.symbol = symbol;
        this.options = style;
    }

    /**
     * Ctor. Builds a named renderable with a default style.
     * @param renderable Renderable
     * @param symbol Renderable name
     */
    public Shape(final T renderable, final String symbol) {
        this(renderable, Optional.of(symbol), new DefaultStyle());
    }

    /**
     * Ctor. Builds an anonymous renderable with a the given style.
     * @param renderable Renderable
     * @param style Renderable style
     */
    public Shape(final T renderable, final Style style) {
        this(renderable, Optional.empty(), style);
    }

    /**
     * Ctor. Builds an anonymous renderable with a default style.
     * @param renderable Renderable
     */
    public Shape(final T renderable) {
        this(renderable, Optional.empty(), new DefaultStyle());
    }

    /**
     * Accessor for the renderable.
     * @return The renderable
     */
    public T renderable() {
        return this.renderable;
    }

    /**
     * Accessor for the renderable name.
     * @return The renderable name
     */
    public Optional<String> name() {
        return this.symbol;
    }

    /**
     * Checks if the renderable is anonymous (with no name).
     * @return True if the renderable is anonymous
     */
    public boolean anonymous() {
        return !this.symbol.isPresent();
    }

    /**
     * Accessor for the renderable style.
     * @return The renderable style.
     */
    public Style style() {
        return this.options;
    }
}
