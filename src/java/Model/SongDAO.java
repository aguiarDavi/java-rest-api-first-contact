/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davi.oliveira
 */
public class SongDAO {
    
    public static Connection getConnection() {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/CRUD_DAPI", "postgres", "postgres");
        } catch (Exception e) {
            System.out.println(e);
        }
        return c;
    }
    
    //MÉTODO PARA A CRIAÇÃO DE UMA MÚSICA
    public static int create(Song s) {
        int status = 0;
        try {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("INSERT INTO song(title, artist, album, genre) values (?,?,?,?)");
            ps.setString(1, s.getTitle());
            ps.setString(2, s.getArtist());
            ps.setString(3, s.getAlbum());
            ps.setString(4, s.getGenre());
            status = ps.executeUpdate();
                
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return status;
    }
    
    //MÉTODO PARA ATUALIZAR UMA MÚSICA JÁ EXISTENTE
    public static int update(Song s) {
        int status = 0;
        try {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("UPDATE song SET title=?, artist=?, album=?, genre=? WHERE id=?");
            ps.setString(1, s.getTitle());
            ps.setString(2, s.getArtist());
            ps.setString(3, s.getAlbum());
            ps.setString(4, s.getGenre());
            ps.setInt(5, s.getId());
            status = ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e);
        }
            
        return status;
    }
    
    //MÉTODO PARA EXCLUIR MÚSICA
    public static int delete(Song s) {
        int status = 0;
        try {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("DELETE FROM song WHERE id=?");
            ps.setInt(1, s.getId());
            status = ps.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return status;
    }
    
    //MÉTODO PARA LER TODAS AS MÚSICAS
    public static List<Song> readAll() {
        List<Song> songList = new ArrayList<Song>();
        try {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM song");
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Song s = new Song();
                s.setId(rs.getInt("id"));
                s.setTitle(rs.getString("title"));
                s.setArtist(rs.getString("artist"));
                s.setAlbum(rs.getString("album"));
                s.setGenre(rs.getString("genre"));
                songList.add(s);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return songList;
    }
    
    //MÉTODO PARA LER MÚSICA PELA ID
    public static Song read(int id) {
        Song s = null;
        try {
            Connection c = getConnection();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM song WHERE id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                s = new Song();
                s.setId(rs.getInt("id"));
                s.setTitle(rs.getString("title"));
                s.setArtist(rs.getString("artist"));
                s.setAlbum(rs.getString("album"));
                s.setGenre(rs.getString("genre"));
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return s;
    }
}
