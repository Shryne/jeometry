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
package com.jeometry.render.awt;

import com.aljebra.field.Field;
import com.jeometry.render.RenderSupport;
import com.jeometry.render.Renderer;
import com.jeometry.render.Surface;
import com.jeometry.render.awt.style.AwtStroke;
import com.jeometry.twod.Renderable;
import com.jeometry.twod.Shape;
import com.jeometry.twod.style.Stroke;
import java.awt.Graphics2D;

/**
 * Represents an abstract AWT renderer accepting an AWT {@link Graphics2D} to
 * draw with and an {@link Surface} describing the context for drawing. This
 * renderer relies on {@link RenderSupport} and is built with
 * the set of classes that this renderer can draw.
 * @param <T> renderable type
 * @since 0.1
 */
public abstract class AbstractAwtPaint<T extends Renderable> implements Renderer {

    /**
     * Field for scalar operations.
     */
    private final Field<Double> fld;

    /**
     * Supported class that can be drawn.
     */
    private final Class<?> clazz;

    /**
     * Ctor.
     * @param field Field for scalar operations
     * @param clazz Supported class
     */
    public AbstractAwtPaint(final Field<Double> field,
        final Class<?> clazz) {
        this.fld = field;
        this.clazz = clazz;
    }

    @Override
    public final void render(final Shape<?> renderable, final Surface context,
        final Graphics2D graphics) {
        new RenderSupport(
            new Renderer() {
                @SuppressWarnings("unchecked")
                @Override
                public void render(final Shape<?> renderable, final Surface context,
                    final Graphics2D graphics) {
                    final Stroke stroke = renderable.style().stroke();
                    graphics.setStroke(new AwtStroke(stroke));
                    graphics.setColor(stroke.color());
                    AbstractAwtPaint.this.draw(
                        ((Class<Shape<T>>) (Class<?>) Shape.class).cast(renderable), graphics,
                        context
                    );
                }
            },
            this.clazz
        ).render(renderable, context, graphics);
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
     * @param ctx Drawing {@link Surface}
     */
    protected abstract void draw(Shape<T> renderable, Graphics2D graphic,
        Surface ctx);
}
