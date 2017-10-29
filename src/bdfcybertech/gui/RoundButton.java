/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.plaf.basic.*;
import java.awt.event.*;

/**
 *
 * @author MUSTAFA
 */
class RoundButton extends JButton {
    public RoundButton() {
        this(null, null);
    }
    public RoundButton(Icon icon) {
        this(null, icon);
    }
    public RoundButton(String text) {
        this(text, null);
    }
    public RoundButton(Action a) {
        this();
        setAction(a);
    }
    public RoundButton(String text, Icon icon) {
        setModel(new DefaultButtonModel());
        init(text, icon);
        if(icon==null) {
            return;
        }
        int iw = Math.max(icon.getIconWidth(), icon.getIconHeight());
        int sw = 1;
        setBorder(BorderFactory.createEmptyBorder(sw,sw,sw,sw));
        Dimension dim = new Dimension(iw+sw+sw, iw+sw+sw);
        setPreferredSize(dim);
        setMaximumSize(dim);
        setMinimumSize(dim);
        setBackground(Color.BLACK);
        setContentAreaFilled(false);
        setFocusPainted(false);
        //setVerticalAlignment(SwingConstants.TOP);
        setAlignmentY(Component.TOP_ALIGNMENT);
        initShape();
    }
    protected Shape shape, base;
    protected void initShape() {
        if(!getBounds().equals(base)) {
            Dimension s = getPreferredSize();
            base = getBounds();
            shape = new Ellipse2D.Float(0, 0, s.width-1, s.height-1);
        }
    }
    @Override
    protected void paintBorder(Graphics g) {
        initShape();
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        //g2.setStroke(new BasicStroke(1.0f));
        g2.draw(shape);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }
    @Override
    public boolean contains(int x, int y) {
        initShape();
        return shape.contains(x, y);
    }

    RoundButton b;
    private void doEvery(){
        ImageIcon i = new ImageIcon(getClass().getResource("images/001.png"));
        b = new RoundButton(i);
        JFrame m = new JFrame("Test");
        m.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        m.setSize(100, 100);
        m.getContentPane().add(b);
        m.setVisible(true);
    }

    public static void main(String []args){

        new RoundButton().doEvery();
    }
}

class RoundImageButtonUI extends BasicButtonUI{
    protected Shape shape, base;
    @Override
    protected void installDefaults(AbstractButton b) {
        super.installDefaults(b);
        clearTextShiftOffset();
        defaultTextShiftOffset = 0;
        Icon icon = b.getIcon();
        if(icon==null) return;
        int iw = Math.max(icon.getIconWidth(), icon.getIconHeight());
        int sw = 1;
        b.setBorder(BorderFactory.createEmptyBorder(sw,sw,sw,sw));
        b.setContentAreaFilled(false);
        b.setFocusPainted(false);
        b.setOpaque(false);
        b.setBackground(Color.BLACK);
        Dimension dim = new Dimension(iw+sw+sw, iw+sw+sw);
        b.setPreferredSize(dim);
        b.setMaximumSize(dim);
        b.setMinimumSize(dim);
        //b.setVerticalAlignment(SwingConstants.TOP);
        b.setAlignmentY(Component.TOP_ALIGNMENT);
        initShape(b);
    }
    @Override
    protected void installListeners(AbstractButton b) {
        BasicButtonListener listener = new BasicButtonListener(b) {
            @Override public void mousePressed(MouseEvent e) {
                AbstractButton b = (AbstractButton) e.getSource();
                initShape(b);
                if(shape.contains(e.getX(), e.getY())) {
                    super.mousePressed(e);
                }
            }
            @Override public void mouseEntered(MouseEvent e) {
                if(shape.contains(e.getX(), e.getY())) {
                    super.mouseEntered(e);
                }
            }
            @Override public void mouseMoved(MouseEvent e) {
                if(shape.contains(e.getX(), e.getY())) {
                    super.mouseEntered(e);
                }else{
                    super.mouseExited(e);
                }
            }
        };
        if(listener != null) {
            b.addMouseListener(listener);
            b.addMouseMotionListener(listener);
            b.addFocusListener(listener);
            b.addPropertyChangeListener(listener);
            b.addChangeListener(listener);
        }
    }
    @Override
    public void paint(Graphics g, JComponent c) {
        super.paint(g, c);
        Graphics2D g2 = (Graphics2D)g;
        initShape(c);
        //Border
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(c.getBackground());
        //g2.setStroke(new BasicStroke(1.0f));
        g2.draw(shape);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
    }
    private void initShape(JComponent c) {
        if(!c.getBounds().equals(base)) {
            Dimension s = c.getPreferredSize();
            base = c.getBounds();
            shape = new Ellipse2D.Float(0, 0, s.width-1, s.height-1);
        }
    }
}
