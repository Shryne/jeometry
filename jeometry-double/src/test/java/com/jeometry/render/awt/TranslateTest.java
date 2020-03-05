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

import com.jeometry.model.decimal.DblPoint;
import java.awt.event.MouseEvent;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests for {@link Translate}.
 * @since 0.1
 */
public final class TranslateTest {

    /**
     * {@link Translate} can translate to a new center.
     */
    @Test
    public void translatesCenter() {
        final AwtDrawableSurface surface = new AwtDrawableSurface();
        final DblPoint center = surface.context().center();
        final Double xcoor = center.dblx();
        final Double ycoor = center.dbly();
        final double amountx = Math.random();
        final double amounty = Math.random();
        final Translate mouse = new Translate(amountx, amounty, surface);
        mouse.mouseClicked(Mockito.mock(MouseEvent.class));
        MatcherAssert.assertThat(
            surface.context().center(),
            Matchers.equalTo(new DblPoint(xcoor + amountx, ycoor + amounty))
        );
    }

}
