package com.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.demo.entities.Movie;
import com.demo.repositories.MovieRepository;

@Service
public class MovieService {
//	public MovieService() {
//		System.out.println("movie service is instantiated");
//	}
	
	@Autowired
	private MovieRepository repo;
	
	public List<Movie> getAllMovies(){
		return repo.findAll();
	}
	
	public Movie getMovieById(int id) {
		
		Optional<Movie> optional = repo.findById(id);
		
		if(optional.isPresent()) {
			return optional.get();
		} else {			
//			throw new RuntimeException();
			System.out.println("Movie with id "+id+" is not found in database.");
			return null;
		}
		
	}
	
	public Movie createMovie(Movie m) {
		return repo.save(m);
	}
	
	public List<Movie> findMoviesByDirector(String d) {
		return repo.findByDirector(d);
	}

	public List<Movie> filterMovies(double rating) {
		return repo.filterMovies(rating);
	}
	
	
	

	

}
