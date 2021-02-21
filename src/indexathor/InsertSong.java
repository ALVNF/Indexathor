/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package indexathor;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.transform.Transformers;

/**
 *
 * @author Josito
 */
public class InsertSong {
    private File f;
    private HashMap routes = new HashMap();
    private HashMap dbRoutes = new HashMap();
    private Date date = new Date();
    private SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    
    public InsertSong(){
        
    }
    
    
    public void SetSongUrl(String url){
        f = new File(url);
        this.getFiles(f);
        //System.out.println(routes);
        
    }

    private void getFiles(File parentFile){
        
        for(File files : parentFile.listFiles()){
            if(files.isDirectory()){
                this.getFiles(files);
            }else{
                if(files.getName().split("\\.")[1].equals("mp3")){
                    routes.put(files.getName(),files.getParent());
                }
            }
        }
        
    }
    
    public boolean insert(){
        boolean songInserted = true;
        File logFile = new File("log.txt");
        
        // Create a new session
        HibernateUtil.buildSessionFactory();
        try {
            
            //Open session
            HibernateUtil.openSessionAndBindToThread();

            //Variable to controlate version
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            String hql = "FROM Songs";
            List<Songs> result = session.createQuery(hql).list();
            
            //Remove same songs of our HashMap
            for (Object object : result) {
                Songs song = (Songs) object;
                routes.remove(song.getFileName());
            }
            
            //Close session and thread
            HibernateUtil.closeSessionAndUnbindFromThread();
            
            for (Object key : routes.keySet()) {
                try {
                    //Start transacction, open session and thread again for each song
                    HibernateUtil.openSessionAndBindToThread();
                    session = HibernateUtil.getSessionFactory().getCurrentSession();
                    session.beginTransaction();
                    //Create a new songId
                    SongsId songId = new SongsId();
                    Mp3File mp3 = new Mp3File(routes.get(key)+"/"+key);
                    

                    //Create a ID3v2 to get metadata.
                    ID3v2 metadata = mp3.getId3v2Tag();
                    
                    System.out.println("Nombre de la cancion: " + key + " |||| Ruta: " + routes.get(key));
                    songId.setTitle(key.toString().split("\\.")[0]);
                    
                    if(metadata.getArtist() == null || metadata.getArtist().length()==0 ) songId.setArtist("Anonymous");
                    else songId.setArtist(metadata.getArtist());

                    int duration = (int) mp3.getLengthInSeconds();

                    //Create a new Songs
                    Songs song = new Songs();
                    song.setAlbum(metadata.getAlbum());
                    song.setYear(metadata.getYear());
                    song.setId(songId);
                    song.setDuration(duration);
                    song.setFilePath((String) routes.get(key));
                    song.setGenre(metadata.getGenreDescription());
                    song.setLyrics(metadata.getLyrics());
                    song.setFileName((String) key);
                    song.setCover(metadata.getAlbumImage());

                    //Save and upload to db
                    session.save(song);
                    session.getTransaction().commit();
                    session.clear();
                    
                    //Writter log
                    FileWriter writter = new FileWriter(logFile,true);
                    writter.write("Song - " + key + " - inserted at "+ format.format(date) +"\n");
                    writter.close();
                    
                } catch (IOException | UnsupportedTagException | InvalidDataException ex) {
                    Logger.getLogger(InsertSong.class.getName()).log(Level.SEVERE, null, ex);
                } finally{
                    //Close session and thread for each song
                    HibernateUtil.closeSessionAndUnbindFromThread();
                }
                
            }
        } catch (ConstraintViolationException e) {
            System.out.println("DUPLICATE SONG AND ARTIST");
            System.out.printf("MENSAJE: %s%n", e.getMessage());
            System.out.printf("COD ERROR: %d%n", e.getErrorCode());
            System.out.printf("ERROR SQL: %s%n",
            e.getSQLException().getMessage());
            songInserted = false;
        }

        return songInserted;
    }
    
    public ArrayList getSongs(){
        
        HibernateUtil.buildSessionFactory();
        //Open session
        HibernateUtil.openSessionAndBindToThread();

        //Variable to controlate version
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String hql = "FROM Songs";
        List<Songs> result = session.createQuery(hql).list();

        //Close session and thread
        HibernateUtil.closeSessionAndUnbindFromThread();
        ArrayList canciones = new ArrayList(result);

        return canciones;
    }
    
    public ArrayList getGenres(){
        HibernateUtil.buildSessionFactory();
        //Open session
        HibernateUtil.openSessionAndBindToThread();

        //Variable to controlate version
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String sql = "SELECT MIN(Genre) FROM songs WHERE Genre is not null GROUP BY Genre ";
        List<Songs> result = session.createSQLQuery(sql).list();

        //Close session and thread
        HibernateUtil.closeSessionAndUnbindFromThread();
        ArrayList generos = new ArrayList(result);

        return generos;
    }
    
    public ArrayList<Songs> orderByGenre(String genre){
        HibernateUtil.buildSessionFactory();
        //Open session
        HibernateUtil.openSessionAndBindToThread();

        //Variable to controlate version
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        String sql = "FROM Songs WHERE Genre = '"+genre+"'";
        List<Songs> result = session.createQuery(sql).list();

        //Close session and thread
        HibernateUtil.closeSessionAndUnbindFromThread();
        ArrayList<Songs> canciones = new ArrayList(result);
        
        return canciones;
    }

}
