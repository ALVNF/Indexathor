package gui_indexathor;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.event.MouseInputListener;

/**
 *
 * @author alvar
 */
public class IndexathorMenu extends JMenu implements MouseInputListener{
    
    /*
        This class will create the menu which contains the menuItem Select Genre
    */
    
    
    private Color bg = new Color(28,28,28);
    private Color fg = new Color(255,255,255);

    private IndexathorMenuItem imiGenre;
    
    public IndexathorMenu(IndexathorListCell ilc){
        this.addMouseListener(this); 
        this.addMouseMotionListener(this);  
        imiGenre = new IndexathorMenuItem(ilc);
    }
    
    public void createIndexMenu(){
        this.setText("···");
        this.setFont(new Font("Perpetua Titling MT", Font.BOLD, 18));
        this.setBackground(bg);
        this.setForeground(fg);
       
     
        this.setOpaque(true);
        this.createIndexMenuItem();
    }
    
    public void createIndexMenuItem(){
        imiGenre.setMenuItemText("Musical Genres");
        this.add(imiGenre);

        imiGenre.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    imiGenre.callFunctions(3);
                }
            }
        );
        
    }
    


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
    //Change the background color to Dark gray
    @Override
    public void mouseEntered(MouseEvent e) {
        Color entered = new Color(70,73,75);
        this.setBackground(entered);
    }
    //Change the background color to black
    @Override
    public void mouseExited(MouseEvent e) {
        Color exited = new Color(28,28,28);
        this.setBackground(exited);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
    
}
