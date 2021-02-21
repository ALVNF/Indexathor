package gui_indexathor;

import indexathor.InsertSong;
import indexathor.Songs;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author alvar
 */
public class IndexathorMenuItem extends JMenuItem {

    private Color bg = new Color(28, 28, 28);
    private Color fg = new Color(255, 255, 255);

    private JFrame menuItemFrame;
    private IndexathorListCell ilc;

    public IndexathorMenuItem(IndexathorListCell ilc) {
        this.ilc = ilc;
    }

    
    //Sets the name for the new menuItem
    public void setMenuItemText(String text) {
        this.setText(text);
    }
    /*
        This method creates a new screen when he clicks on the menuItem
    */
    private void functionIndexMenuSongGenre() {
        if (this.menuItemFrame == null) {

            menuItemFrame = new JFrame();
            IndexathorMenuPanel imp = new IndexathorMenuPanel(470, 440, this.ilc);
            imp.setFrameIndexathorMenuPanel(menuItemFrame, 2);

            menuItemFrame.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
            menuItemFrame.getRootPane().setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 0, 0)));
            imp.createIndexPanel(0, 0, 500, 30);

            menuItemFrame.getContentPane().add(imp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 30));

            this.drawTable();

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
    //This method draws a Jtable where the user can select the genre of the songs that he want to add into the Playlist.
    public void drawTable() {
        JScrollPane spanel = new JScrollPane();
        Color bg2 = new Color(34, 34, 34);
        Color selectedBg = new Color(77, 77, 78);//Dark Gray
        Color selectedFg = new Color(255, 255, 255);//White
        
        //Creates a model that is selectable but not editable
        IndexathorTableModel indexModel = new IndexathorTableModel(new Object[][]{}, new String[]{
            "", "", ""
        }
        );

        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(bg);
        //Creates the table with some properties
        JTable table = new JTable();

        table.setModel(indexModel);
        table.setFocusable(true);
        table.setShowVerticalLines(true);

        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(false);
        table.setCellSelectionEnabled(true);

        table.setBackground(bg2);
        //Change the header background Color
        for (int i = 0; i < table.getModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        table.setForeground(fg);
        table.setSelectionBackground(selectedBg);
        table.setSelectionForeground(selectedFg);
        table.setOpaque(true);
        table.revalidate();
        table.repaint();
        table.setVisible(true);
        
        InsertSong is = new InsertSong();
        ArrayList genres = is.getGenres();
        
        //This will create a new Row in the JTable only if there are more Genres avalaible for adding it.
        int genresIndex = 0;
        for (int i = 0; i < (genres.size()-(genres.size()%3))/3; i++) {
            indexModel.addRow(new Object[]{genres.get(genresIndex),genres.get(genresIndex+1),genres.get(genresIndex+2)});
            genresIndex+=3;
        }
        if(genres.size() % 3 == 1){
            indexModel.addRow(new Object[]{genres.get(genres.size()-1)});
        }else if(genres.size() % 3 == 2){
            indexModel.addRow(new Object[]{genres.get(genres.size()-1), genres.get(genres.size()-2)});
        }

        spanel.getViewport().setBackground(new Color(33, 33, 33));
        spanel.getViewport().setOpaque(true);
        spanel.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));
        spanel.setViewportView(table);
        menuItemFrame.getContentPane().add(spanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 500, 300));
        
        //When the user selects a genre in the table, this method will change the JList in the main Screen and will show only the songs with that genre selected
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                int column = table.getSelectedColumn();
                
                InsertSong is = new InsertSong();
                
                ArrayList<Songs> songs = (ArrayList<Songs>)is.orderByGenre(table.getValueAt(row, column).toString());
                
                ilc.setArrSongs(songs);
                ilc.fillHashMap();
                ilc.reloadListElements();
                
                
                
                table.setSelectionMode(0);
            }

        });

    }

    public void callFunctions(int id) {

        if (id == 3) {
            functionIndexMenuSongGenre();
        } else {
            System.out.println("Error calling Function");
        }
    }

}
