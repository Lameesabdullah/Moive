package com.example.Moivethymleaf;


import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.ws.rs.core.Response;
import java.util.ArrayList;


public class FavMovies {
    private ArrayList<MovieDetails> add_fav ;


    public FavMovies()
    {
        this.add_fav = new ArrayList<MovieDetails>();
    }


    public void add_favorites (int  id) {


        String uri= "https://api.themoviedb.org/3/movie/"+id+"?api_key=81b213f239e14e0b3343a9a35bbaaa14";

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(uri);
        Response response = target.request().get();
        String jsonString = response.readEntity(String.class);

        JSONObject obj = new JSONObject(jsonString);

       // System.out.println(obj);

        String title = obj.getString("title");

        String overview = obj.getString("overview");

        JSONArray g_array = obj.getJSONArray("genres");

        Object poster = obj.get("poster_path");



        String concagt_genre="";
        for(int i=0;i<g_array.length();i++)
        {
            if (i+1 ==g_array.length())
            {
                concagt_genre+=g_array.getJSONObject(i).getString("name");
            }
            else
            {
                concagt_genre+=g_array.getJSONObject(i).getString("name")+" - ";
            }
        }



        MovieDetails temp_obj = new MovieDetails();


        temp_obj.setId(id);
        temp_obj.setGenres(concagt_genre);
        temp_obj.setDes(overview);
        temp_obj.setName(title);
        if(poster !=JSONObject.NULL)
        {
            //System.out.println(poster);
            temp_obj.setPoster((String)poster);
        }


        this.add_fav.add(temp_obj);




    }

    public ArrayList<MovieDetails> getAllFav()
    {
        return this.add_fav;
    }
    public MovieDetails getByIndex(int idx)
    {
        return this.add_fav.get(idx);
    }

    public int getLength()
    {

        return this.add_fav.size();
    }

    public void deleteByIndex(int idx)

    {
        this.add_fav.remove(idx);
    }

    public void deleteById(int id)
    {
        int index=-1;
        String name="";
        for(int i=0;i<this.add_fav.size();i++)
        {
            if(id ==this.add_fav.get(i).getId())
            {
                deleteByIndex(i);
            }
        }
    }
}

