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

import com.jeometry.geometry.twod.Renderable;
import com.jeometry.main.Figure;
import com.jeometry.render.Output;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Represents an AWT JFrame {@link Output} drawing the figure on an AWT window.
 * @author Hamdi Douss (douss.hamdi@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Awt extends JFrame implements Output {

    /**
     * Serial version ID.
     */
    private static final long serialVersionUID = 3449434902800801695L;

    /**
     * Scale of the drawable surface.
     */
    private int scale = 10;

    /**
     * List of {@link AwtPaint}s to paint shapes.
     */
    private List<AwtPaint> painters = new ArrayList<>();

    /**
     * Reference to the figure to draw.
     */
    private Figure figure;

    /**
     * Drawable Panel.
     */
    private JPanel drawable;

    /**
     * Ctor. Builds a {@link JFrame} with a drawable surface and 4 control
     * buttons.
     */
    public Awt() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        this.drawable = new JPanel();
        contentPane.add(drawable, BorderLayout.CENTER);
        JPanel buttons = new JPanel();
        contentPane.add(buttons, BorderLayout.EAST);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        JButton up = new JButton("up");
        buttons.add(up);
        JButton down = new JButton("down");
        buttons.add(down);
        JButton right = new JButton("right");
        buttons.add(right);
        JButton left = new JButton("left");
        buttons.add(left);
        JButton zoomin = new JButton("zoomin");
        buttons.add(zoomin);
        JButton zoomout = new JButton("zoomout");
        buttons.add(zoomout);
        this.painters = Awt.init();
    }

    @Override
    public void render(final Figure figure) {
        this.figure = figure;
        this.repaint();
        this.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) drawable.getGraphics();
        RenderingHints rh = new RenderingHints(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHints(rh);
        final int width  = drawable.getWidth();
        final int height  = drawable.getHeight();
        g2.clearRect(0, 0, width, height);
        g2.setColor(Color.RED);
        g2.drawLine(0, height/2, width, height/2);
        g2.drawLine(width/2, 0, width/2, height);
        g2.setColor(Color.BLACK);
        AwtContext context = new AwtContext(width, height, scale); 
        for (AwtPaint painter : this.painters) {
            painter.setGraphics(g2);
            painter.setContext(context);
            for (Renderable shape : this.figure.getShapes()) {
                painter.render(shape);
            }
        }
    }
    
    /**
     * Modifies drawable surface size, in coordinates relative size.
     * @param width Width to set in coordinates unit
     * @param height Height to set in coordinates unit
     * @return This awt reference
     */
    public Awt withSize(int width, int height) {
        drawable.setSize(scale * width, scale * height);
        return this;
    }

    /**
     * Adds an {@link AwtPaint} to the registered painters.
     * @param painter
     * @return
     */
    public Awt add(AwtPaint painter) {
        this.painters.add(painter);
        return this;
    }

    /**
     * Initialize with default painters.
     * @return A list of default painters
     */
    private static List<AwtPaint> init() {
        List<AwtPaint> result = new ArrayList<>();
        result.add(new AwtPoint());
        result.add(new AwtCircle());
        result.add(new AwtLine());
        return result;
    }

}
