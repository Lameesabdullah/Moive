package com.example.Moivethymleaf;



public class MovieDetails  {



    private int id;
    private String name;
    private String poster;
    private String des;
    private String  genres;


    public MovieDetails( ){


    }
    public int getId() {
        return id;
    }

    public void setId(int id)
    {
        this.id=id;
    }



    public MovieDetails(String name, String poster, String des, String  genres ){
        this.name=name;
        this.poster = poster;
        this.des=des;
        this.genres=genres;
        this.id=id;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }
}


