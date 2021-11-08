package com.example.Moivethymleaf;


public class Demo {

    public static void main(String [] args)
    {

      //  System.out.println("from demo we talk");

        moviesResource obj = new moviesResource();

        obj.fill_recent_movie();



        System.exit(0);

        FavMovies fv = new FavMovies();

        fv.add_favorites(436969);
        fv.add_favorites(436979);
        fv.add_favorites(435969);


        fv.deleteByIndex(0);

        for (int i =0 ;i<fv.getLength();i++)
        {
           // System.out.println(fv.getByIndex(i).getName());
        }

        //obj.fill_recent_movie();
        //.searchMovie("fight");
    }

}
