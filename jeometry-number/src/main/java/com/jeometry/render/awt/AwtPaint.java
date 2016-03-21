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
public abstract class AwtPaint implements Renderer {

    /**
     * {@link Graphics2D} to draw on.
     */
    private Graphics2D graphics;

    /**
     * {@link AwtContext} describing drawing context.
     */
    private AwtContext context;

    /**
     * Field for scalar operations.
     */
    protected final Field<Double> field;

    /**
     * Supported classes that can be drawn.
     */
    private final Class<?>[] clazz;

    /**
     * Ctor.
     * @param field Field for scalar operations
     * @param clazz Supported classes
     */
    public AwtPaint(Field<Double> field, Class<?>... clazz) {
        this.field = field;
        this.clazz = Arrays.copyOf(clazz, clazz.length);
    }

    /**
     * Sets current {@link Graphics2D} to draw on.
     * @param graphics Graphics to set
     */
    public void setGraphics(final Graphics2D graphics) {
        this.graphics = graphics;
    }

    /**
     * Sets current {@link AwtContext} for drawing.
     * @param context {@link AwtContext} to set
     */
    public void setContext(AwtContext context) {
        this.context = context;        
    }
    
    @Override
    public void render(Renderable renderable) {
        new RenderSupport(new Renderer() {
            @Override
            public void render(Renderable renderable) {
                AwtPaint.this.draw(
                    renderable, AwtPaint.this.graphics, AwtPaint.this.context
                );                
            }
        }, this.clazz).render(renderable);        
    }

    /**
     * Draws given {@link Renderable}.
     * @param renderable Renderable to draw
     * @param graphics AWT {@link Graphics2D} to draw
     * @param context {@link AwtContext} for drawing
     */
    protected abstract void draw(Renderable renderable, Graphics2D graphics,
        AwtContext context);
}
