package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        String response= movieService.addMovie(movie);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director){
        String response= movieService.addDirector(director);
        return new ResponseEntity(response,HttpStatus.CREATED);
    }

    @PutMapping("/add-movie-director-pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam String m,@RequestParam String d){
        String response=movieService.addMovieDirectorPair(m,d);
        return new ResponseEntity(response,HttpStatus.CREATED);
    }

    @GetMapping("/get-movie-by-name/{movieName}")
    public ResponseEntity getMovieByName(@PathVariable String movieName){
        return new ResponseEntity(movieService.getMovieByName(movieName),HttpStatus.FOUND);
    }

    @GetMapping("/get-director-by-name/{directorName}")
    public ResponseEntity getDirectorByName(@PathVariable String directorName){
        return new ResponseEntity(movieService.getDirectorByName(directorName),HttpStatus.FOUND);
    }

    @GetMapping("/get-movies-by-director-name/{directorName}")
    public ResponseEntity getMoviesByDirectorName(@PathVariable String directorName){
        return new ResponseEntity(movieService.getMoviesByDirectorName(directorName),HttpStatus.FOUND);
    }

    @GetMapping("/find-all-movies")
    public ResponseEntity findAllMovies(){
        return new ResponseEntity(movieService.findAllMovies(),HttpStatus.FOUND);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam String d){
        return new ResponseEntity(movieService.deleteDirectorByName(d),HttpStatus.OK);
    }

    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        return new ResponseEntity(movieService.deleteAllDirectors(),HttpStatus.OK);
    }
}
