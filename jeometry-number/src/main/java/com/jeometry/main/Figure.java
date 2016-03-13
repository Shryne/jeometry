package com.jeometry.main;

import com.jeometry.geometry.twod.LineAnalytics;
import com.jeometry.geometry.twod.line.Line;
import com.jeometry.model.algebra.field.Field;
import com.jeometry.model.algebra.scalar.Scalar;
import com.jeometry.model.algebra.vector.Vect;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class Figure extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 3190141606352419844L;
    private final static int SCALE = 50;
    List<Line> lines = new ArrayList<>();
    List<Vect> points = new ArrayList<>();
    private Field<Double> field;
    
    public Figure(Field<Double> field) {
        this.field = field;
    }
    public Figure add(Line line) {
        this.lines.add(line);
        return this;
    }

    public Figure add(Vect point) {
        this.points.add(point);
        return this;
    }
    
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        final int w  = this.getWidth();
        final int h  = this.getHeight();
        g2.clearRect(0, 0, w, h);
        g2.setColor(Color.RED);
        g2.drawLine(0, h/2, w, h/2);
        g2.drawLine(w/2, 0, w/2, h);
        g2.setColor(Color.BLACK);
        for (Line line : lines) {
            LineAnalytics analytics = new LineAnalytics(line, field);
            Scalar slope = analytics.slope();
            Scalar intercept = analytics.intercept();
            g2.drawLine(
                0, h/2 - (int)((-w/2)*field.actual(slope) + SCALE * field.actual(intercept)),
                w, h/2 - (int)(w/2*field.actual(slope) + SCALE * field.actual(intercept))
            );
        }
        final int size = 4;
        for (Vect point : points) {
            g2.drawRect(
                (int)(w/2 + SCALE * field.actual(point.coords()[0])) - size/2,
                (int)(h/2 - SCALE * field.actual(point.coords()[1])) - size/2,
                size, size
            );
        }
    }
    
    public Figure withSize(int width, int height) {
        super.setSize(SCALE * width, SCALE * height);
        return this;
    }
}
