package com.example.Moivethymleaf;



import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.json.JSONObject;

import javax.ws.rs.core.Response;
import java.util.*;


import org.json.JSONArray;


public class moviesResource {

    public HashMap<Integer, String> get_genre() {
        HashMap<Integer, String> genre_obj = new HashMap<Integer, String>();


        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("https://api.themoviedb.org/3/genre/movie/list?api_key=81b213f239e14e0b3343a9a35bbaaa14");
        Response response = target.request().get();
        String jsonString = response.readEntity(String.class);

        JSONObject obj = new JSONObject(jsonString);

        JSONArray array = obj.getJSONArray("genres");

        int ky = 0;
        String vlue = "";

        for (int i = 0; i < array.length(); i++) {
            vlue = array.getJSONObject(i).getString("name");
            ky = array.getJSONObject(i).getInt("id");

            genre_obj.put(ky, vlue);


        }

        Set set = genre_obj.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();

        }


        return genre_obj;
    }

    public ArrayList<MovieDetails> fill_recent_movie() {

        ArrayList<MovieDetails> latest_mov = new ArrayList<MovieDetails>();


        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("https://api.themoviedb.org/3/movie/now_playing?api_key=81b213f239e14e0b3343a9a35bbaaa14&page=1");
        Response response = target.request().get();
        String jsonString = response.readEntity(String.class);

        JSONObject obj = new JSONObject(jsonString);


        HashMap<Integer, String> genre = get_genre();


        JSONArray arr = obj.getJSONArray("results");
        int pgs = obj.getInt("total_pages");
       //System.out.println("total pages : " + pgs);
        MovieDetails temp;
        JSONArray g_temp;
        String concat_genre = "";
        for (int j = 1; j <= pgs; j++) {
            ////System.out.println(obj);
            target = client.target("https://api.themoviedb.org/3/movie/now_playing?api_key=81b213f239e14e0b3343a9a35bbaaa14&page=" + j);
            response = target.request().get();
            jsonString = response.readEntity(String.class);

            obj = new JSONObject(jsonString);


            arr = obj.getJSONArray("results");


            for (int i = 0; i < arr.length(); i++) {
                //System.out.println(arr.getJSONObject(i));
                String title = arr.getJSONObject(i).getString("title");
                String description = arr.getJSONObject(i).getString("overview");
                int id  = arr.getJSONObject(i).getInt("id");
                Object poster = arr.getJSONObject(i).get("poster_path");
                g_temp = arr.getJSONObject(i).getJSONArray("genre_ids");



                for (int g = 0; g < g_temp.length(); g++) {
                    concat_genre += genre.get(g_temp.getInt(g)) + " - ";
                }


                temp = new MovieDetails();

                temp.setGenres(concat_genre);
                temp.setName(title);
                temp.setDes(description);

                if(poster !=JSONObject.NULL)
                {
                    //System.out.println(poster);
                    temp.setPoster((String)poster);
                }

                temp.setId(id);

                latest_mov.add(temp);

                concat_genre = "";
            }

        }

        return latest_mov;
    }


    public ArrayList<MovieDetails> searchMovie(String queries) {
       // System.out.print("===== search movie method ===== ");
        ArrayList<MovieDetails> search_mo = new ArrayList<MovieDetails>();

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("https://api.themoviedb.org/3/search/movie?api_key=81b213f239e14e0b3343a9a35bbaaa14&page=1&query="+ queries);
        Response response = target.request().get();
        String jsonString = response.readEntity(String.class);

        JSONObject obj = new JSONObject(jsonString);
        //System.out.println(obj);
        HashMap<Integer, String> genre1 = get_genre();

        JSONArray arr2 = obj.getJSONArray("results");
        int pgs1 = obj.getInt("total_pages");
        //System.out.println("total pages : " + pgs1);
        MovieDetails temp1 ;

        JSONArray g_temp;
        String concat_genre = "";

        for (int j = 1; j <= pgs1; j++) {

            //System.out.println(obj);
            target = client.target("https://api.themoviedb.org/3/search/movie?api_key=81b213f239e14e0b3343a9a35bbaaa14&page=" + j + "&query=" + queries);
            response = target.request().get();
            jsonString = response.readEntity(String.class);

            obj = new JSONObject(jsonString);

            arr2 = obj.getJSONArray("results");


            for (int i = 0; i < arr2.length(); i++) {
                String title = arr2.getJSONObject(i).getString("title");
                String description = arr2.getJSONObject(i).getString("overview");
                int id  = arr2.getJSONObject(i).getInt("id");
                Object poster = arr2.getJSONObject(i).get("poster_path");
                g_temp = arr2.getJSONObject(i).getJSONArray("genre_ids");


                for (int g = 0; g < g_temp.length(); g++) {
                    concat_genre += genre1.get(g_temp.getInt(g)) + " - ";
                }


                temp1 = new MovieDetails();

                temp1.setGenres(concat_genre);
                temp1.setName(title);
                temp1.setDes(description);
                if(poster !=JSONObject.NULL)
                {
                    //System.out.println(poster);
                    temp1.setPoster((String)poster);
                }

                temp1.setId(id);

                search_mo.add(temp1);

                concat_genre = "";
            }


        }

        //System.out.println("----to check if ARRAY WORKS ---------");
        for (int i = 0; i < search_mo.size(); i++) {
            //System.out.println(search_mo.get(i).getName());
        }

        return search_mo;
    }


}

