package gui_indexathor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JMenuBar;

/**
 *
 * @author alvar
 */
public class IndexathorMenuBar extends JMenuBar{

    private IndexathorMenu im;
    private Color bg = new Color(28,28,28);
    
    /*
        This class just creates a MenuBar which will have a Black background color, and will be added into the top bar menu
    */
    
    public IndexathorMenuBar(IndexathorListCell ilc) {
        im = new IndexathorMenu(ilc);
        im.createIndexMenu();
        this.add(im);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(bg);
        g2d.fillRect(0, 0, getWidth(), getHeight());

    }
    

    
    
    
}
