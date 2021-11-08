package com.example.Moivethymleaf;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@Controller
public class Controler {
    public FavMovies f = new FavMovies();

    @RequestMapping(value = "/recent", method = RequestMethod.POST)
    public String addFav(@ModelAttribute MovieDetails md, BindingResult errors, Model model) {

        moviesResource obj = new moviesResource();

        ArrayList<MovieDetails> arr = obj.fill_recent_movie();

        model.addAttribute("rm",arr);
        model.addAttribute("md",new MovieDetails());
        model.addAttribute("s","Latest Movies");

        this.f.add_favorites(md.getId());


/*
        for (int i =0 ;i<this.f.getLength();i++)
        {
            System.out.println("movie: "+this.f.getByIndex(i).getName());
            System.out.println("movie: "+this.f.getByIndex(i).getPoster());

        }

 */
        return "index";
    }


    @RequestMapping(value = "/Fav", method = RequestMethod.GET)
    public String FavPage(@RequestParam String ids,Model model) {


     //   System.out.println("what is id: "+ids);

        if (ids != "")
        {
            this.f.deleteById(new Integer(ids));
        }
        ArrayList<MovieDetails> fv = this.f.getAllFav();
        model.addAttribute("fv",fv);
        model.addAttribute("s","Favorite Movies");
       // System.out.println("here1");
        return "Fav_movie";
    }



    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchPage(@RequestParam String queries, Model model) {
     //   System.out.println("====inside SearchPage method====");
      //  System.out.println("query is : "+ queries);


        moviesResource obj = new moviesResource();

        ArrayList<MovieDetails> arr = obj.searchMovie(queries);

        model.addAttribute("rm",arr);
       model.addAttribute("md",new MovieDetails());
        model.addAttribute("queries","");
        model.addAttribute("s","Movies");


        return "search";
    }


    @GetMapping("/recent")
    public String getLatest(Model model)
    {
        moviesResource obj = new moviesResource();

        ArrayList<MovieDetails> arr = obj.fill_recent_movie();

        model.addAttribute("rm",arr);
        model.addAttribute("md",new MovieDetails());
        model.addAttribute("queries","");
        model.addAttribute("s","Latest Movies");
        return "index";
    }


}
