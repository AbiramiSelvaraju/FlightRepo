package com.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entities.Movie;
import com.demo.services.MovieService;

@RestController
@CrossOrigin({"https://hoppscotch.io"})
@RequestMapping("/v1/api")
public class MovieController {

	@Autowired
	private MovieService service;

	@GetMapping("/movies")
	public List<Movie> getAllMovies(){
		return service.getAllMovies();
	}

	@GetMapping("/movies/{id}")
	public Movie getMovieById(@PathVariable int id) {
		return service.getMovieById(id);
	}

	@PostMapping("/movies")
	public Movie createMovie(@RequestBody Movie m) {
		return service.createMovie(m);
	}

	@GetMapping("/movies/search")
	public List<Movie> findMoviesByDirector(@RequestParam String director) {
		return service.findMoviesByDirector(director);
	}

	@GetMapping("/movies/filter")
	public List<Movie> filterMovies(@RequestParam double rating) {
		return service.filterMovies(rating);
	}



	// delete
	// update

}
