import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;

public class Final_Assignment extends JFrame implements ActionListener{
    private final JButton Redraw_circle;
    public static JTextField x_1, y_1, radius_1, x_2, y_2, radius_2, No_Yes;
    private JLabel Label;
    public String change_drag;
    public  int cursX, cursY;// to get where we point at
    public double xx1 = 0, yy1= 0, radiuss1= 0, xx2= 0, yy2= 0, radiuss2= 0; //a convert of Jtextfield to make the variable double
    circle c = new circle();
    draggable d = new draggable();

    public static void main(String[] args) {
        Final_Assignment frame = new Final_Assignment();
        frame.setSize(400,450);
        frame.setTitle("Final Assignment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);//locate the window to center
    }

    public Final_Assignment() {
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

            JPanel panel_2a = new JPanel();
            panel_2a.add(Label = new JLabel("Enter circle 1 info:"));
            panel_2a.setBorder(new EmptyBorder(20, 0, 0, 0));

            JPanel panel_2b = new JPanel();
            panel_2b.add(Label = new JLabel("Enter circle 2 info:"));
            panel_2b.setBorder(new EmptyBorder(20, 0, 0, 0));

            JPanel panel_2c = new JPanel(new GridLayout(3, 2));
            panel_2c.add(Label = new JLabel("Center x:"));
            panel_2c.add(x_1);
            panel_2c.add(Label = new JLabel("Center y:"));
            panel_2c.add(y_1);
            panel_2c.add(Label = new JLabel("Radius:"));
            panel_2c.add(radius_1);
            panel_2c.setBorder(new EmptyBorder(0, 20, 0, 20));



            JPanel panel_2d = new JPanel(new GridLayout(3, 2));
            panel_2d.add(Label = new JLabel("Center x:"));
            panel_2d.add(x_2);
            panel_2d.add(Label = new JLabel("Center y:"));
            panel_2d.add(y_2);
            panel_2d.add(Label = new JLabel("Radius:"));
            panel_2d.add(radius_2);
            panel_2d.setBorder(new EmptyBorder(0, 20, 0, 20));

            JPanel panel_center = new JPanel(new GridLayout(2, 2));
            panel_center.add(panel_2a);
            panel_center.add(panel_2b);
            panel_center.add(panel_2c);
            panel_center.add(panel_2d);

            JPanel panel_centerLast = new JPanel();
            panel_centerLast.add(panel_center);
            JPanel panel_last = new JPanel();
            panel_last.add(Redraw_circle);

            JPanel panel_sl = new JPanel();
            panel_sl.setLayout(new BoxLayout(panel_sl,BoxLayout.PAGE_AXIS));//make a new line for button Redraw_circle
            panel_sl.add(panel_center);
            panel_sl.add(panel_last);
            add(panel_sl, BorderLayout.SOUTH);

            //center
            add(c, BorderLayout.CENTER);
            c.addMouseMotionListener(d); //MouseMotionListener is informed whenever you move or drag mouse and
        // it is informed against MouseEvent
            c.addMouseListener(d); //MouseListener is informed whenever you change the state of mouse and
        // It is informed against MouseEvent
            Redraw_circle.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean true_false;
        try {
            xx1 = Double.parseDouble(x_1.getText());
            xx2 = Double.parseDouble(x_2.getText());
            yy1 = Double.parseDouble(y_1.getText());
            yy2 = Double.parseDouble(y_2.getText());
            radiuss1 = Double.parseDouble(radius_1.getText());
            radiuss2 = Double.parseDouble(radius_2.getText());
        } catch (NumberFormatException m) {
            JOptionPane.showMessageDialog(null, "Must enter numbers!", "Warning",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        //if Redraw_circle is click
        if (e.getSource() == Redraw_circle) {
            true_false = check_intercept(xx1, xx2, yy1, yy2, radiuss1, radiuss2);
            //get true false from check_intercept method
            if (true_false) {
                No_Yes.setText("Yes");
            }
            else {
                No_Yes.setText("No");
            }
            repaint(); //repaint the draw
        }
    }

    //method to check if the circle is intercept
    public boolean check_intercept(double x1, double x2, double y1, double y2, double rr1, double rr2) {
        //find the distance between the centers for both x and y
        double fx = x2 - x1;
        double fy = y2 - y1;
        double radius_sum = rr1 + rr2;
        double distSQ = Math.hypot(fy, fx); //basically is (fx * fx) + (fy * fy) which is returns sqrt(fx^2 + fy^2)

        //use if else to check if the circle is intersects
        if (distSQ <= Math.abs(radius_sum)) //absolute value to ensure it's not negative number
        {
            //if circle does not intercept return true
            return true;
        }
        else
        {
            //if intercept return false
            return false;
        }
    }

    class draggable extends MouseAdapter { // class to drag the circle
        int x1, x2, r1;
        int y1, y2, r2;

        public void mousePressed(MouseEvent m) {
            this.x1 = (int) xx1;
            this.x2 = (int) xx2;
            this.r1 = (int) radiuss1;
            this.y1 = (int) yy1;
            this.y2 = (int) yy2;
            this.r2 = (int) radiuss2;
            //numbers of coordinates x and y when we press
            Point point = m.getPoint();
            int x = m.getX();
            int y = m.getY();
            double c_1 = Math.hypot(point.x - x1, point.y - y1);//to get the distance center of the circle 1
            double c_2 = Math.hypot(point.x - x2, point.y - y2);//to get the distance center of the circle 2
            //basically is (point.x - x1 * point.y - y1) + (point.x - x1 * point.y - y1) which is returns sqrt(x^2 + y^2)

            if (Math.pow(c_2, 2) < Math.pow(r2, 2)) { //is c_2^2 and r2^2 // if r2 power 2 is bigger than distance center 2 power 2
                change_drag = "false"; //condition if we are inside circle 2
                cursX = x - x2;//if didn't do like this the cursor will stable at one place at that circle no matter where we click
                cursY = y - y2;
            }
            else if (Math.pow(c_1, 2) < Math.pow(r1, 2)) { //is c_1^2 and r1^2 if r1 power 2 is bigger than distance center 1 power 2
                change_drag = "true"; //condition if we are inside circle 1
                cursX = x - x1;//if didn't do like this the cursor will stable at one place at that circle no matter where we click
                cursY = y - y1; // to know the offset between the clicking and the drawing
                // to know where the drawing should happened
                //the pointer will change if this formula is not implement drawing will automatically goes to the top left of circle
            }
            else {
                change_drag = "null";// will prevent the change_drag to remain true or false so it cannot be drag when we click empty canvas
            }
        }
        public void mouseReleased(MouseEvent m) {
            change_drag = "null";// will prevent the change_drag to remain true or false so it cannot be drag when we click empty canvas
        }
        public void mouseClicked(MouseEvent m) {
            change_drag = "null";// will prevent the change_drag to remain true or false so it cannot be drag when we click empty canvas
        }
        public void mouseDragged(MouseEvent m) {
            //number of coordinates x and y when we dragged
            int x = m.getX();
            int y = m.getY();

            if (change_drag.equals("true")) {
                xx1 = x - cursX;//if didn't do like this the cursor will stable at one place at that circle no matter where we click
                yy1 = y - cursY;
            }
            else if (change_drag.equals("false")){//if didn't do like this the cursor will stable at one place at that circle no matter where we click
                xx2 = x - cursX;
                yy2 = y - cursY;
            }

            x_1.setText(Integer.toString((int)xx1));
            x_2.setText(Integer.toString((int)xx2));
            y_1.setText(Integer.toString((int)yy1));
            y_2.setText(Integer.toString((int)yy2));
            boolean tf = check_intercept(xx1, xx2, yy1, yy2, radiuss1, radiuss2);
            if (tf) {
                No_Yes.setText("Yes");
            }
            else {
                No_Yes.setText("No");
            }
            repaint(); //repaint the draw
        }
    }

    class circle extends JPanel { //the circle we draw
        public circle() {}

        public void paintComponent(Graphics g) {
            super.paintComponent(g);//call the superclass methods to ensure the component is properly displayed

            int diameter = (int) radiuss1 * 2;
            int diameter2 = (int) radiuss2 * 2;
            g.setColor(Color.BLACK);
            g.drawOval((int)(xx1 - radiuss1), (int)(yy1 - radiuss1), diameter, diameter);
            g.setColor(Color.BLACK);
            g.drawOval((int)(xx2 -radiuss2), (int)(yy2 - radiuss2), diameter2, diameter2);
        }
    }
}

