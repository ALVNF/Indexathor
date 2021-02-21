package gui_indexathor;

import java.awt.Color;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 *
 * @author alvar
 */
public class IndexathorNewFilePanel {
    
    /*
        This class is for creating a new screen where the user can see all the playlist that he have and where new playList can be added. 
    */

    private JFrame menuItemFrame;
    private IndexathorListCell ilc ;
    private Color bg = new Color(33, 33, 33); //Black
    private Color fg = new Color(255, 255, 255); //Dark Gray

    public IndexathorNewFilePanel(IndexathorListCell ilc) {
        this.ilc = ilc;
    }

    protected void panelNewPlayList() {

        if (this.menuItemFrame == null) { // If the frame exits instead of been created will show the existing frame
            JPanel panel = new JPanel();
            JScrollPane scrollPane = new JScrollPane();
            
            scrollPane.getViewport().setBackground(new Color(33, 33, 33));
            scrollPane.getViewport().setOpaque(true);
            scrollPane.setViewportView(listOfPlayList());
            this.setScrollBarColor(scrollPane);
            
            
            JLabel title = new JLabel();

            title.setText("WRITE PLAYLIST NAME");
            title.setForeground(fg);
            
            JLabel lblPlayList = new JLabel();

            lblPlayList.setText("ALL PLAYLISTS");
            lblPlayList.setForeground(fg);

            JTextField fileName = new JTextField();

            fileName.setBackground(bg);
            fileName.setForeground(fg);

            JButton accept = new JButton();

            accept.setBackground(new java.awt.Color(51, 51, 51));
            accept.setForeground(new java.awt.Color(255, 255, 255));
            accept.setText("Accept");
            accept.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    //Call the method that creates the playListFile
                    ilc.createM3U(fileName.getText());
                    //Close this window
                    menuItemFrame.dispose();
                    //Calls the method that reloads the JList
                    ilc.reloadListElements();
                    //Calls the method that clear the JTable
                    ilc.removeAll();
                    //Once the user accept and the file is created, the playList will be executend into the VLC media player and played
                    try {
                        ProcessBuilder pb = new ProcessBuilder("C:\\Program Files\\VideoLAN\\VLC\\vlc.exe", "./PlayList/" + fileName.getText() + ".m3u");
                        pb.start();
                    } catch (IOException ex) {
                        Logger.getLogger(IndexathorNewFilePanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //This method sets the Labels with the data of the JTable, in this case to 0 because the JTable is clean
                    ilc.callSongsLabels();
                    //Clear the Text Input
                    fileName.setText("");

                }
            });
            
            //Place all the elements created before into the Panel.
            panel.setBackground(bg);
            panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
            panel.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 50, 160, 30));
            panel.add(fileName, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 90, 120, 30));
            panel.add(lblPlayList, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 5, 120, 30));
            panel.add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 35, 180, 200));
            panel.add(accept, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 160, -1, -1));
            
            //Creates a new frame where the componets will be stored
            menuItemFrame = new JFrame();

            IndexathorMenuPanel imp = new IndexathorMenuPanel(470, 440, ilc);
            imp.setFrameIndexathorMenuPanel(menuItemFrame, 2);

            menuItemFrame.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
            menuItemFrame.getRootPane().setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
            imp.createIndexPanel(0, 0, 200, 30);

            menuItemFrame.getContentPane().add(imp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 30));
            menuItemFrame.getContentPane().add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 500, 270));

            menuItemFrame.setSize(500, 300);
            menuItemFrame.setLocationRelativeTo(null);

            menuItemFrame.revalidate();
            menuItemFrame.repaint();
            menuItemFrame.setUndecorated(true);
            menuItemFrame.setVisible(true);

        } else {
            menuItemFrame.setVisible(true);
            menuItemFrame.requestFocus();
        }

    }
    /*
        This method returns a JList where will be stored all the playList files. 
    */
    private JList listOfPlayList() {

        DefaultListModel listModel = new DefaultListModel();
        String[] palyLists = ilc.fileFilter();
        JList list;
        for (String pathname : palyLists) {
            // Print the names of files and directories
            listModel.addElement(pathname);

        }

        list = new JList(listModel);
        list.setCellRenderer(new DefaultListCellRenderer());
        list.setBackground(bg);
        list.setForeground(fg);

        list.setOpaque(true);
        list.revalidate();
        list.repaint();
        list.setVisible(true);

        return list;
    }
    /*
        This method will modifies the scrollbars of the scrollPane written above.
    */
    private void setScrollBarColor(JScrollPane spanel) {
        Color bg = new Color(28, 28, 28);
        Color fg = new Color(77, 77, 78);

        spanel.getVerticalScrollBar().setBackground(bg);

        spanel.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = fg;
            }
        });

        spanel.getHorizontalScrollBar().setBackground(bg);
        spanel.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = fg;
            }
        });

    }

}
