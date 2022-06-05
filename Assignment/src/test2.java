import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;

public class test2 extends JFrame implements ActionListener{
    private JButton Redraw_circle;
    public static JTextField x_1, y_1, radius_1, x_2, y_2, radius_2, No_Yes;
    private JLabel Label;
    public static boolean drag_circle_1;// a true false that detect if the circle is drag
    public static int cursX, cursY;// cursor x and cursor y
    public static double xx1, yy1, radiuss1, xx2, yy2, radiuss2;
    circle c = new circle();
    draggable d = new draggable();

    public static void main(String[] args) {
        test2 frame = new test2();
        frame.setSize(320,360);
        frame.setTitle("Final Assignment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public test2() {
        No_Yes = new JTextField(3);
        No_Yes.setEditable(false); //to let the No_Yes variable cannot be edited.
        No_Yes.setOpaque(false); //background transparent
        No_Yes.setBorder(new EmptyBorder(0, 0, 0, 0)); //to let the border disappear

        x_1 = new JTextField("50",1);
        y_1 = new JTextField("50",1);
        x_2 = new JTextField("160",1);
        y_2 = new JTextField("50",1);
        radius_1 = new JTextField("50",1);
        radius_2 = new JTextField("50",1);
        Redraw_circle = new JButton("Redraw Circle");

        JPanel panel_1 = new JPanel();
        panel_1.add(Label = new JLabel("Two circles intersect? "));
        panel_1.add(No_Yes);

        add(panel_1, BorderLayout.NORTH);
        //center

        circle Circle = new circle();
        add(Circle, BorderLayout.CENTER);

        //south

        JPanel panel_2a = new JPanel();
        panel_2a.add(Label = new JLabel("Enter circle 1 info:"));
        panel_2a.setBorder(new EmptyBorder(20, 0, 0, 0));

        JPanel panel_2b = new JPanel(new GridLayout(3, 2));
        panel_2b.add(Label = new JLabel("Center x:"));
        panel_2b.add(x_1);
        panel_2b.add(Label = new JLabel("Center y:"));
        panel_2b.add(y_1);
        panel_2b.add(Label = new JLabel("Radius:"));
        panel_2b.add(radius_1);
        panel_2b.setBorder(new EmptyBorder(0, 20, 0, 20));

        JPanel panel_21 = new JPanel();
        panel_21.add(Label = new JLabel("Enter circle 2 info:"));
        panel_21.setBorder(new EmptyBorder(20, 0, 0, 0));

        JPanel panel_2c = new JPanel(new GridLayout(3, 2));
        panel_2c.add(Label = new JLabel("Center x:"));
        panel_2c.add(x_2);
        panel_2c.add(Label = new JLabel("Center y:"));
        panel_2c.add(y_2);
        panel_2c.add(Label = new JLabel("Radius:"));
        panel_2c.add(radius_2);
        panel_2c.setBorder(new EmptyBorder(0, 20, 0, 20));

        JPanel panel_center = new JPanel(new GridLayout(2, 2));
        panel_center.add(panel_2a);
        panel_center.add(panel_21);
        panel_center.add(panel_2b);
        panel_center.add(panel_2c);


        JPanel panel_centerLast = new JPanel();
        panel_centerLast.add(panel_center);

        JPanel panel_last = new JPanel();
        panel_last.add(Redraw_circle);


        JPanel panel_sl = new JPanel();
        panel_sl.setLayout(new BoxLayout(panel_sl,BoxLayout.PAGE_AXIS));//make a new line for button
        panel_sl.add(panel_center);
        panel_sl.add(panel_last);
        add(panel_sl, BorderLayout.SOUTH);
        c.addMouseMotionListener(d);
        c.addMouseListener(d);
        Redraw_circle.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean true_false;
        xx1 = Double.parseDouble(x_1.getText());
        xx2 = Double.parseDouble(x_2.getText());
        yy1 = Double.parseDouble(y_1.getText());
        yy2 = Double.parseDouble(y_2.getText());
        radiuss1 = Double.parseDouble(radius_1.getText());
        radiuss2 = Double.parseDouble(radius_2.getText());
        if (e.getSource() == Redraw_circle) {
            true_false = check_intercept(xx1, xx2, yy1, yy2, radiuss1, radiuss2);
            //get true false from check_intercept method

            if (true_false) {
                No_Yes.setText("Yes");
            }
            else if (!true_false) {
                No_Yes.setText("No");
            }
            repaint();
        }

    }

    //method to check if the circle is intercept
    public boolean check_intercept(double x1, double x2, double y1, double y2, double rr1, double rr2) {
        //find the distance between the centers for both x and y
        double fx = x2 - x1;
        double fy = y2 - y1;
        //find the straight line of both centers
        double r1 = rr1;
        double r2 = rr2;
        double radius_sum = r1 + r2;
        //use if else to check if the circle is intersects
        if (Math.hypot(fx, fy) <= radius_sum)
        {
            //circle does not intercept
            return true;
        }
        else
        {
            //circle intercepted
            return false;
        }
    }
}

class circle extends JPanel{
    private int x;
    private int y;
    private int r;
    private int x2;
    private int y2;
    private int r2;
    private int xx, yy, xx2, yy2;

    public circle() {

    }

    public void paintComponent(Graphics g) {
            super.paintComponent(g);//call the superclass methods to ensure the component is properly displayed

            this.x = (int) test2.xx1;
            this.y = (int) test2.yy1;
            this.r = (int) test2.radiuss1;
            this.x2 = (int) test2.xx2;
            this.y2 = (int) test2.yy2;
            this.r2 = (int) test2.radiuss2;

            int diameter = r * 2;
            int diameter2 = r2 * 2;
            g.setColor(Color.BLACK);
            g.drawOval(x - r, y - r, diameter, diameter);
            g.setColor(Color.BLACK);
            g.drawOval(x2 - r2, y2 - r2, diameter2, diameter2);

            test2.x_1.setText(Integer.toString(x));
            test2.y_1.setText(Integer.toString(y));
            test2.x_2.setText(Integer.toString(x2));
            test2.y_2.setText(Integer.toString(y2));
        }
}

class draggable extends MouseAdapter {
    int x, xx, xx2;
    int y, yy, yy2;
    circle c = new circle();

    public void mousePressed(MouseEvent m) {
        //numbers of coordinates x and y when we press
        x = m.getX();
        y = m.getY();
        this.xx = (int) test2.xx1;
        this.yy = (int) test2.yy1;
        this.xx2 = (int) test2.xx2;
        this.yy2 = (int) test2.yy2;

        if (x >= xx2 && x < xx2 + 100 && y >= yy2 && y < yy2) {
            test2.drag_circle_1 = false;
            test2.cursX = x - xx2;
            test2.cursY = x - yy2;
        }
        else if (x >= xx && x < xx + 100 && y >= yy && y < yy) {
            test2.drag_circle_1 = true;
            test2.cursX = x - xx;
            test2.cursY = x - yy;
        }
    }

    public void mouseDragged(MouseEvent m) {
        //numbers of coordinates x and y when we press
        x = m.getX();
        y = m.getY();
        this.xx = (int) test2.xx1;
        this.yy = (int) test2.yy1;
        this.xx2 = (int) test2.xx2;
        this.yy2 = (int) test2.yy2;

        if (test2.drag_circle_1) {
            xx = x - test2.cursX;
            yy = y - test2.cursY;
        }
        else{
            xx2 = x - test2.cursX;
            yy2 = y - test2.cursY;
        }

        c.repaint();

        test2.x_1.setText(Integer.toString(xx));
        test2.x_2.setText(Integer.toString(xx2));
        test2.y_1.setText(Integer.toString(yy));
        test2.y_2.setText(Integer.toString(yy2));
    }
}


