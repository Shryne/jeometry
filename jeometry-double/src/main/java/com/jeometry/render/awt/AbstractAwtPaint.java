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
package com.jeometry.render.awt;

import com.jeometry.geometry.twod.RenderSupport;
import com.jeometry.geometry.twod.Renderable;
import com.jeometry.geometry.twod.Renderer;
import com.jeometry.geometry.twod.Shape;
import com.jeometry.model.algebra.field.Field;
import java.awt.Graphics2D;
import java.util.Arrays;

/**
 * Represents an abstract AWT renderer accepting an AWT {@link Graphics2D}
 * to draw with and an {@link AwtContext} describing the context for drawing.
 * This renderer relies on {@link RenderSupport} and is built with classes
 * to support.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public abstract class AbstractAwtPaint implements Renderer {

    /**
     * Field for scalar operations.
     */
    private final Field<Double> fld;

    /**
     * {@link Graphics2D} to draw on.
     */
    private Graphics2D graphics;

    /**
     * {@link AwtContext} describing drawing context.
     */
    private AwtContext context;

    /**
     * Supported classes that can be drawn.
     */
    private final Class<?>[] clazz;

    /**
     * Ctor.
     * @param field Field for scalar operations
     * @param clazz Supported classes
     */
    public AbstractAwtPaint(final Field<Double> field,
        final Class<?>... clazz) {
        this.fld = field;
        this.clazz = Arrays.copyOf(clazz, clazz.length);
    }

    /**
     * Sets current {@link Graphics2D} to draw on.
     * @param graphic Graphics to set
     */
    public final void setGraphics(final Graphics2D graphic) {
        this.graphics = graphic;
    }

    /**
     * Sets current {@link AwtContext} for drawing.
     * @param ctx Passed {@link AwtContext} to set
     */
    public final void setContext(final AwtContext ctx) {
        this.context = ctx;
    }

    @Override
    public final void render(final Shape renderable) {
        new RenderSupport(
            new Renderer() {
                @Override
                public void render(final Shape renderable) {
                    AbstractAwtPaint.this.draw(
                        renderable, AbstractAwtPaint.this.graphics,
                        AbstractAwtPaint.this.context
                    );
                }
            },
            this.clazz
        ).render(renderable);
    }

    /**
     * Gives the scalar field.
     * @return The field
     */
    protected final Field<Double> field() {
        return this.fld;
    }

    /**
     * Draws given {@link Renderable}.
     * @param renderable Renderable to draw
     * @param graphic AWT {@link Graphics2D} to draw
     * @param ctx Drawing {@link AwtContext}
     */
    protected abstract void draw(Shape renderable, Graphics2D graphic,
        AwtContext ctx);
}
