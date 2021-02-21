package gui_indexathor;

import indexathor.InsertSong;
import java.awt.Color;
import javax.swing.JFileChooser;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 *
 * @author alvar
 */
public class MainScreen extends javax.swing.JFrame {

    private IndexathorListCell ilc = new IndexathorListCell();
    private IndexathorMenuBar imnb = new IndexathorMenuBar(ilc);
    private IndexathorMenuPanel imp = new IndexathorMenuPanel(920, 890, ilc);

    public MainScreen() {

        initComponents();
        this.setLocationRelativeTo(null);
        UIDefaults uiDefaults = UIManager.getDefaults();
        uiDefaults.put("activeCaption", new javax.swing.plaf.ColorUIResource(Color.gray));
        uiDefaults.put("activeCaptionText", new javax.swing.plaf.ColorUIResource(Color.white));
        this.setDefaultLookAndFeelDecorated(true);
        //Add into a the top menu Bar a MenuBar component at the given position with the given sizes
        imp.addComponent(imnb, 0, 0, 60, 32);
        
        //This call a method that creates the JPanel for the top menu bar
        imp.createIndexPanel(0, 0, 950, 30);
        //This call a method for setting the frame where the top menu bar is at.
        imp.setFrameIndexathorMenuPanel(this, 1);
        this.getContentPane().add(imp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 30));
        
        //Change the scrollPanel background color
        scrollPanelRight.getViewport().setBackground(new Color(33, 33, 33));
        scrollPanelRight.getViewport().setOpaque(true);
        this.setScrollBarColor();
        
        //Creates the list at the scrollPane given
        ilc.createList(scrollPanelLeft);
        
        //Creates the table at the scrollPane given
        ilc.createTable(scrollPanelRight);

        //Creates two labels with the information of the JTable at the panel given
        ilc.setSongsInPlayList(panelSubMenu);
        ilc.setSongsDuration(panelSubMenu);

    }

    private void setScrollBarColor() {
        /*
            This method will modifies the scrollbars of the scrollPane written above.
        */

        Color bg = new Color(28, 28, 28);
        Color fg = new Color(77, 77, 78);
        //Sets the vertical scrollbar background into the color given, in this case black
        scrollPanelLeft.getVerticalScrollBar().setBackground(bg);

        //Sets the vertical scrollbar item background into the color given, in this case gray
        scrollPanelLeft.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = fg;
            }
        });
        //Sets the horizontal scrollbar background into the color given, in this case black
        scrollPanelLeft.getHorizontalScrollBar().setBackground(bg);

        //Sets the horizontal scrollbar item background into the color given, in this case gray
        scrollPanelLeft.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = fg;
            }
        });
        //Sets the vertical scrollbar background into the color given, in this case black
        scrollPanelRight.getVerticalScrollBar().setBackground(bg);
        //Sets the vertical scrollbar item background into the color given, in this case gray
        scrollPanelRight.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = fg;
            }
        });
        //Sets the horizontal scrollbar background into the color given, in this case black
        scrollPanelRight.getHorizontalScrollBar().setBackground(bg);
        //Sets the horizontal scrollbar item background into the color given, in this case gray
        scrollPanelRight.getHorizontalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = fg;
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSubMenu = new javax.swing.JPanel();
        btnNewIndex = new javax.swing.JButton();
        lblPlayListSongs = new javax.swing.JLabel();
        lblPlayListDuration = new javax.swing.JLabel();
        btnCreatePlayList = new javax.swing.JButton();
        panelTopMenu = new javax.swing.JPanel();
        lblSong = new javax.swing.JLabel();
        lblArtist = new javax.swing.JLabel();
        lblSongDuration = new javax.swing.JLabel();
        btnAddAll = new javax.swing.JButton();
        btnClearPlayList = new javax.swing.JButton();
        btnClearGenres = new javax.swing.JButton();
        scrollPanelLeft = new javax.swing.JScrollPane();
        scrollPanelRight = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(950, 710));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelSubMenu.setBackground(new java.awt.Color(33, 33, 33));
        panelSubMenu.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(0, 0, 0)));
        panelSubMenu.setVerifyInputWhenFocusTarget(false);
        panelSubMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNewIndex.setBackground(new java.awt.Color(51, 51, 51));
        btnNewIndex.setForeground(new java.awt.Color(255, 255, 255));
        btnNewIndex.setText("New Index");
        btnNewIndex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewIndexActionPerformed(evt);
            }
        });
        panelSubMenu.add(btnNewIndex, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 15, -1, -1));

        lblPlayListSongs.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayListSongs.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPlayListSongs.setText("PLAYLIST SONGS:");
        panelSubMenu.add(lblPlayListSongs, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 120, -1));

        lblPlayListDuration.setForeground(new java.awt.Color(255, 255, 255));
        lblPlayListDuration.setText("PLAYLIST DURATION:");
        panelSubMenu.add(lblPlayListDuration, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, 140, -1));

        btnCreatePlayList.setBackground(new java.awt.Color(51, 51, 51));
        btnCreatePlayList.setForeground(new java.awt.Color(255, 255, 255));
        btnCreatePlayList.setText("New PlayList");
        btnCreatePlayList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreatePlayListActionPerformed(evt);
            }
        });
        panelSubMenu.add(btnCreatePlayList, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 15, -1, -1));

        getContentPane().add(panelSubMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 650, 950, 60));

        panelTopMenu.setBackground(new java.awt.Color(33, 33, 33));
        panelTopMenu.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(0, 0, 0)));
        panelTopMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblSong.setForeground(new java.awt.Color(255, 255, 255));
        lblSong.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSong.setText("SONG");
        panelTopMenu.add(lblSong, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 15, 110, -1));

        lblArtist.setForeground(new java.awt.Color(255, 255, 255));
        lblArtist.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblArtist.setText("ARTIST");
        panelTopMenu.add(lblArtist, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 15, 90, -1));

        lblSongDuration.setForeground(new java.awt.Color(255, 255, 255));
        lblSongDuration.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSongDuration.setText("DURATION");
        panelTopMenu.add(lblSongDuration, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 15, 120, 20));

        btnAddAll.setBackground(new java.awt.Color(51, 51, 51));
        btnAddAll.setForeground(new java.awt.Color(255, 255, 255));
        btnAddAll.setText("Add All");
        btnAddAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddAllActionPerformed(evt);
            }
        });
        panelTopMenu.add(btnAddAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        btnClearPlayList.setBackground(new java.awt.Color(51, 51, 51));
        btnClearPlayList.setForeground(new java.awt.Color(255, 255, 255));
        btnClearPlayList.setText("Clear PlayList");
        btnClearPlayList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearPlayListActionPerformed(evt);
            }
        });
        panelTopMenu.add(btnClearPlayList, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, -1));

        btnClearGenres.setBackground(new java.awt.Color(51, 51, 51));
        btnClearGenres.setForeground(new java.awt.Color(255, 255, 255));
        btnClearGenres.setText("View All");
        btnClearGenres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearGenresActionPerformed(evt);
            }
        });
        panelTopMenu.add(btnClearGenres, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        getContentPane().add(panelTopMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 950, 50));

        scrollPanelLeft.setBackground(new java.awt.Color(51, 51, 51));
        scrollPanelLeft.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 1, new java.awt.Color(0, 0, 0)));
        getContentPane().add(scrollPanelLeft, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 270, 570));

        scrollPanelRight.setBorder(null);
        getContentPane().add(scrollPanelRight, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 680, 570));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewIndexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewIndexActionPerformed

        /*
            This method creates a FileChooser component when the button assigned is clicked.
            The user only can select a Directory, where their songs are stored,instaed of files.
        */
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        
        /*
            If the users hits the button "accept" in the FileChooser component will insert the songs into the DB.
            After that will call a method for realoading the JList where all songs are shown.
        */
        
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            InsertSong insertS = new InsertSong();
            insertS.SetSongUrl(chooser.getSelectedFile().getAbsolutePath());
            insertS.insert();
            ilc.loadSongs();
            ilc.reloadListElements();
        }
    }//GEN-LAST:event_btnNewIndexActionPerformed

    private void btnClearPlayListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearPlayListActionPerformed
        /*
            This method is attached to a JButton that will clear all elements in the JTable component and sended back into the JList component.
            After that will call another method that shows all the songs stored in the JTable.
        */
        ilc.removeAll();
        ilc.setSongsInPlayList(panelSubMenu);
        ilc.setSongsDuration(panelSubMenu);
    }//GEN-LAST:event_btnClearPlayListActionPerformed

    private void btnAddAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddAllActionPerformed
        /*
            This method is attached to a JButton that will clear all elements in the JList component and sended into the JTable component.
        After that will call another method that shows all the songs stored in the JTable.
        */
        ilc.addAll();
        ilc.setSongsInPlayList(panelSubMenu);
        ilc.setSongsDuration(panelSubMenu);
    }//GEN-LAST:event_btnAddAllActionPerformed

    private void btnCreatePlayListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreatePlayListActionPerformed
        /*
            Creates a new screen that shows all playLsit stored and for adding a new playList file.
        */
        new IndexathorNewFilePanel(ilc).panelNewPlayList();
    }//GEN-LAST:event_btnCreatePlayListActionPerformed

    private void btnClearGenresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearGenresActionPerformed
        /*
            Sets the JList component to show all songsGenres
        */
        ilc.loadSongs();
        ilc.reloadListElements();
    }//GEN-LAST:event_btnClearGenresActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddAll;
    private javax.swing.JButton btnClearGenres;
    private javax.swing.JButton btnClearPlayList;
    private javax.swing.JButton btnCreatePlayList;
    private javax.swing.JButton btnNewIndex;
    private javax.swing.JLabel lblArtist;
    private javax.swing.JLabel lblPlayListDuration;
    private javax.swing.JLabel lblPlayListSongs;
    private javax.swing.JLabel lblSong;
    private javax.swing.JLabel lblSongDuration;
    private javax.swing.JPanel panelSubMenu;
    private javax.swing.JPanel panelTopMenu;
    private javax.swing.JScrollPane scrollPanelLeft;
    private javax.swing.JScrollPane scrollPanelRight;
    // End of variables declaration//GEN-END:variables
}
