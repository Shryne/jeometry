package com.jeometry.render;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.BoxLayout;

public class Test extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Test frame = new Test();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Test() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));
        
        JPanel drawable = new JPanel();
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
    }

}
