/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package WebService;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import Model.Song;
import Model.SongDAO;
import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;

/**
 * REST Web Service
 *
 * @author davi.oliveira
 */
@Path("/songs")
public class GenericResource {
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public GenericResource() {
    }
    /**
     * Retrieves representation of an instance of WebService.GenericResource
     * @return an instance of java.lang.String
     */
    
    @POST
    @Path("/create")
    @Consumes("application/json")
    public void create(String json) {
        Gson gson = new Gson();
        Song song = gson.fromJson(json, Song.class);
        SongDAO.create(song);
    }
    
    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(String json) {
        Gson gson = new Gson();
        Song song = gson.fromJson(json, Song.class);
        SongDAO.update(song);
    }
    
    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") int id ) {
        Song song = SongDAO.read(id);
        SongDAO.delete(song);
    }
    
    @GET
    @Path("/read/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String read(@PathParam("id") int id ) {
        Song song = SongDAO.read(id);
        Gson g = new Gson();
        String json = g.toJson(song);
        return json;
    }
 
    @GET
    @Path("/read")
    @Produces(MediaType.APPLICATION_JSON)
    public String read() {
        List<Song> songs = SongDAO.readAll();
        Gson g = new Gson();
        String json = g.toJson(songs);
        return json;
    }
}
