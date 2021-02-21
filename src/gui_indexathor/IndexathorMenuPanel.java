package gui_indexathor;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author alvar
 */
public class IndexathorMenuPanel extends JPanel implements MouseInputListener {

    private Color bg = new Color(28, 28, 28);
    private Color fg = new Color(255, 255, 255);

    private JFrame frame;
    private JLabel lblMinimize;
    private JLabel lblClose;
    
    private int idFrame = 0;
    
    private IndexathorMenuItem imi;

    public IndexathorMenuPanel(int lblClosePosX, int lblMinPosX, IndexathorListCell ilc) {

        lblMinimize = new JLabel();
        lblClose = new JLabel();
        /*
            Creates two labels which both of them have a mous listener for closing the window or minimizing it.
        */
        lblMinimize.setFont(new java.awt.Font("Perpetua Titling MT", 0, 14)); // NOI18N
        lblMinimize.setBackground(bg);
        lblMinimize.setForeground(fg);
        lblMinimize.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMinimize.setText("__");
        lblMinimize.setToolTipText("");
        lblMinimize.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lblMinimize.setOpaque(true);
        lblMinimize.addMouseListener(listenerMin);

        lblClose.setFont(new java.awt.Font("Perpetua Titling MT", 0, 14)); // NOI18N
        lblClose.setBackground(bg);
        lblClose.setForeground(fg);
        lblClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblClose.setText("X");
        lblClose.setOpaque(true);
        lblClose.addMouseListener(listenerClose);

        this.addComponent(lblMinimize, lblMinPosX, 0, 30, 30);
        this.addComponent(lblClose, lblClosePosX, 0, 30, 30);

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        
        imi = new IndexathorMenuItem(ilc);
    }
    //This Method set the frame where whre the user is clicking 
    public void setFrameIndexathorMenuPanel(JFrame aFrame,int idFrame) {
        this.frame = aFrame;
        this.idFrame = idFrame;
    }
    //Sets the top bar menu properties
    public void createIndexPanel(int posX, int posY, int width, int height) {
        this.setBackground(bg);
        this.setLocation(posX, posY);
        this.setSize(width, height);
        this.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

    }
    //Adds a component to the top menu bar
    public JComponent addComponent(JComponent c, int posX, int posY, int width, int height) {
        super.add(c);
        c.setLocation(posX, posY);
        c.setSize(width, height);
        return c;
    }
    //Mouse Listener for lblMinimize
    private MouseAdapter listenerMin = new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            lblMinimizeMouseClicked(evt);
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
            lblMinimizeMouseEntered(evt);
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            lblMinimizeMouseExited(evt);
        }
    };
    //Mouse Listener for lblClose
    private MouseAdapter listenerClose = new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            lblCloseMouseClicked(evt);
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
            lblCloseMouseEntered(evt);
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
            lblCloseMouseExited(evt);
        }
    };

    static Point compCoords = null;

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        //Gets the position of the mouse when clicking
        compCoords = e.getPoint();
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        //Sets the position to null when mouse release
        compCoords = null;
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
        //Get the location of the mouse
        Point currCoords = e.getLocationOnScreen();
        //Sets the frame given in the method before and move it into the mouse pointer
        frame.setLocation(currCoords.x - compCoords.x, currCoords.y - compCoords.y);

    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {

    }
    
    //Changes the Label background color
    private void lblCloseMouseExited(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        Color exited = new Color(28, 28, 28);
        lblClose.setBackground(exited);
    }
    //Changes the Label background color
    private void lblCloseMouseEntered(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        Color entered = new Color(70, 73, 75);
        lblClose.setBackground(entered);
    }
    
    private void lblCloseMouseClicked(java.awt.event.MouseEvent evt) {
        //Close the program if is the main screen or close just a window if is another
        if(idFrame== 1){
            System.exit(0);
        }else{
            Color exited = new Color(28, 28, 28);
            lblClose.setBackground(exited);
            frame.dispose();
        }
        
    }
    //Changes the Label background color
    private void lblMinimizeMouseExited(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        Color exited = new Color(28, 28, 28);
        lblMinimize.setBackground(exited);
    }
    //Changes the Label background color
    private void lblMinimizeMouseEntered(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        Color entered = new Color(70, 73, 75);
        lblMinimize.setBackground(entered);
    }
    //Minimize the frame given above
    private void lblMinimizeMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        frame.setState(frame.ICONIFIED);
    }
    

}
