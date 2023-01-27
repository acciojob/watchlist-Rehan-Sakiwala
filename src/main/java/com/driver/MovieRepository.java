package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MovieRepository {

    Map<String,Movie> movieDB=new HashMap<>();
    Map<String,Director> directorDB=new HashMap<>();
    Map<String, List<String>> pairDB=new HashMap<>();

    public String addMovie(Movie movie){
        String name=movie.getName();
        movieDB.put(name,movie);
        return "Added Successfully";
    }

    public String addDirector(Director director){
        String name=director.getName();
        directorDB.put(name,director);
        return "Added Successfully";
    }

    public String addMovieDirectorPair(String movieName,String directorName){
        if(!movieDB.containsKey(movieName) || !directorDB.containsKey(directorName))
            return "Movie or Director doesn't exist in the list, proceed to add first";
        List<String> movieList=pairDB.getOrDefault(directorName,new ArrayList<>());
        if(movieList.contains(movieName))
            return "Movie already exist";

        movieList.add(movieName);
        pairDB.put(directorName,movieList);
        return "Pair added successfully";
    }

    public Movie getMovieByName(String movieName){
            return movieDB.get(movieName);
    }

    public Director getDirectorByName(String directorName){
        return directorDB.get(directorName);
    }

    public List getMoviesByDirectorName(String directorName){
        List<String> movieList=pairDB.get(directorName);
        return movieList;
    }

    public List findAllMovies(){
        List<String> movieList=new ArrayList<>();
        for(String temp : movieDB.keySet()){
            movieList.add(temp);
        }
        return movieList;
    }

    public String deleteDirectorByName(String directorName){
        if(!directorDB.containsKey(directorName)) return "No director found";

        List<String> movieList=pairDB.get(directorName);
        for(String movie:movieList){
            movieDB.remove(movie);
        }

        pairDB.remove(directorName);

        directorDB.remove(directorName);

        return "Deleted Successfully";
    }

    public String deleteAllDirectors(){
        for(String D : pairDB.keySet()){
            List<String> dl=new ArrayList<>();
            dl=pairDB.get(D);
            for(String movie:dl){
                if(movieDB.containsKey(movie))
                    movieDB.remove(movie);

            }
            pairDB.remove(D);
        }

        for(String D: directorDB.keySet()){
            directorDB.remove(D);
        }

        return "Deleted successfully";
    }

}
