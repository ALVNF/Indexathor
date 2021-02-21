package gui_indexathor;

import indexathor.InsertSong;
import indexathor.Songs;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author alvar
 */
public class IndexathorListCell {
    
    /*
        This class contains the core of the projects where the JList and JTable with the songs are stored and sended to other classes
    */

    private final Color selectedBg = new Color(77, 77, 78);//Darck Gray
    private final Color selectedFg = new Color(255, 255, 255);//White

    private final Color bg = new Color(28, 28, 28);//Black
    private final Color fg = new Color(255, 255, 255);//White

    private DefaultListModel listModel = new DefaultListModel(); //Model for the JList
    private IndexathorTableModel indexModel; //Model for the JTable

    private JList list;
    private JTable table;
    private JPanel panel = new JPanel();

    private HashMap<Integer, Songs> songs = new HashMap<>();//Map which contains Songs Objects
    private ArrayList<Songs> arraySongs = new ArrayList(); //Array List where all songs in the JList are stored
    private ArrayList<Songs> deletedSongs = new ArrayList(); //Array List where all songs in the JTable are stored
    private JScrollPane scrollPanel;

    public IndexathorListCell() {
        this.loadSongs();
    }

    protected void setArrSongs(ArrayList<Songs> songs) {
        this.arraySongs = songs;
    }
    //Loads the songs that are stored into the JList
    protected void loadSongs() {
        InsertSong is = new InsertSong();
        arraySongs = is.getSongs();
        this.fillHashMap();
    }
    //Fill the Map with songs Objects stored in the JList
    protected void fillHashMap() {
        songs.clear();
        for (int i = 0; i < arraySongs.size(); i++) {
            songs.put(i, (Songs) arraySongs.get(i));
        }
    }
    //Repaint the JList component
    protected void reloadListElements() {
        listModel.removeAllElements();
        for (int i : songs.keySet()) {
            listModel.addElement(songs.get(i).getFileName());
        }
        new IndexathorMenuItem(this).drawTable();

    }
    //Getter of songs
    protected ArrayList<Songs> getSongs() {
        return new InsertSong().getSongs();
    }



    protected void callSongsLabels(){
        this.setSongsDuration(panel);
        this.setSongsInPlayList(panel);
    }
    //This method creates a JLabel which will show the total Duration of all songs stored at the JTable
    JLabel durationSongs = new JLabel();
    protected void setSongsDuration(JPanel panel) {

        this.panel = panel;
        int totalDuration = 0;
        for (int i = deletedSongs.size() - 1; i >= 0; i--) {
            totalDuration += deletedSongs.get(i).getDuration();
        }
        durationSongs.setText(String.valueOf(totalDuration));
        durationSongs.setForeground(fg);
        durationSongs.setVisible(true);
        panel.add(durationSongs, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 20, 50, 20));
        panel.validate();
    }
    //This method creates a JLabel which will show the total songs stored at the JTable
    JLabel numberSongs = new JLabel();
    protected void setSongsInPlayList(JPanel panel) {

        this.panel = panel;
        numberSongs.setText(String.valueOf(deletedSongs.size()));
        numberSongs.setForeground(fg);
        numberSongs.setVisible(true);
        panel.add(numberSongs, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 20, 50, 20));
        panel.validate();

    }
    //Creates a JList that shows all the data in the arrayList and print it into the scrollPane given
    public void createList(JScrollPane scrollPanel) {
        this.scrollPanel = scrollPanel;
        for (int i : songs.keySet()) {
            listModel.addElement(songs.get(i).getFileName());
        }

        list = new JList(listModel);
        list.setCellRenderer(new DefaultListCellRenderer());
        list.setBackground(bg);
        list.setForeground(fg);
        list.setSelectionBackground(selectedBg);
        list.setSelectionForeground(selectedFg);

        list.setOpaque(true);
        list.revalidate();
        list.repaint();
        list.setVisible(true);

        scrollPanel.setViewportView(list);
    }
    //Creates a JTable that shows all the data in the arrayList and print it into the scrollPane given
    public void createTable(JScrollPane scrollPanel) {

        indexModel = new IndexathorTableModel(new Object[][]{}, new String[]{
            "", "", ""
        }
        );
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(bg);

        table = new JTable();

        table.setModel(indexModel);
        table.setFocusable(false);
        table.setShowVerticalLines(false);

        table.setBackground(bg);
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
        this.moveElement();
        scrollPanel.setViewportView(table);

    }
    
    private void moveElement() {
        //Set a listener into the JList that will move the item selected by the user into the JTable
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (list.getSelectedIndex() != -1) {
                    IndexathorListCell.this.addElement();
                    listModel.removeElementAt(list.getSelectedIndex());
                    list.setSelectedIndex(-1);
                    setSongsDuration(panel);
                    setSongsInPlayList(panel);
                }
            }

        });
        //Set a listener into the JTable that will move the item selected by the user into the JList
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (table.getSelectedRow() != -1) {
                    IndexathorListCell.this.removeElement();
                    indexModel.removeRow(table.getSelectedRow());
                    setSongsDuration(panel);
                    setSongsInPlayList(panel);
                }
            }

        });

    }
    //This method will add a new Row with some songs properties in the JTable
    private void addElement() {
        for (int i : songs.keySet()) {
            if (list.getSelectedIndex() == i) {
                indexModel.addRow(new Object[]{songs.get(i).getFileName(), songs.get(i).getId().getArtist(), songs.get(i).getDuration() + " seg"});
            }
        }
        songs.clear();
        deletedSongs.add(arraySongs.get(list.getSelectedIndex()));
        arraySongs.remove(list.getSelectedIndex());
        this.fillHashMap();
    }
    //This method will add the removed element from JTable into JList only with the song name
    private void removeElement() {
        int row = table.getSelectedRow();
        TableModel tableValue = table.getModel();

        songs.clear();
        arraySongs.add(deletedSongs.get(table.getSelectedRow()));
        deletedSongs.remove(table.getSelectedRow());

        this.fillHashMap();
        listModel.add(list.getModel().getSize(), tableValue.getValueAt(row, 0).toString());

    }
    //This method will add any Song stored in the JList component
    protected void addAll() {
        for (int i = 0; i < arraySongs.size(); i++) {
            indexModel.addRow(new Object[]{arraySongs.get(i).getFileName(), arraySongs.get(i).getId().getArtist(), arraySongs.get(i).getDuration() + " seg"});
            deletedSongs.add(arraySongs.get(i));
        }
        listModel.removeAllElements();
        arraySongs.clear();
    }
    //This method will remove any Song stored in the JTable component
    protected void removeAll() {
        for (int i = deletedSongs.size() - 1; i >= 0; i--) {
            listModel.add(list.getModel().getSize(), deletedSongs.get(i).getFileName());
            indexModel.removeRow(i);
            arraySongs.add(deletedSongs.get(i));
        }
        deletedSongs.clear();
    }
    
    //This method creates a ne folder called PlayList and after that the M3U File with the name that the user gives and songs stored into the Jtable
    private File file;
    protected void createM3U(String name) {
        File fileDir = new File("PlayList");
        fileDir.mkdir();
        file = new File("PlayList/" + name + ".m3u");
        if (file.exists()){
            JOptionPane.showMessageDialog(null, "Ya existe una playlist con ese nombre"); //Message when file already exists
        } else {
            try {
                FileWriter writer = new FileWriter(file, true);
                writer.write("#EXTM3U\n");
                for (int i = 0; i < deletedSongs.size(); i++) {
                    Songs songs = deletedSongs.get(i);
                    writer.write("#EXTINF:" + songs.getDuration() + "," + songs.getId().getTitle() + "\n");
                    writer.write(songs.getFilePath() + "/" + songs.getFileName() + "\n\n");
                }
                writer.close();
                JOptionPane.showMessageDialog(null, "La playList se ha creado con éxito"); //Message when file is correctly created

            } catch (IOException ex) {
                Logger.getLogger(IndexathorListCell.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //This method shows all the PlayList that the user has created
    protected String[] fileFilter() {
        // Creates an array in which we will store the names of files and directories
        String[] pathnames;

        // Creates a new File instance by converting the given pathname string into an abstract pathname
        File f = new File("./PlayList");

        // Populates the array with names of files and directories
        pathnames = f.list();

        return pathnames;
    }

}
