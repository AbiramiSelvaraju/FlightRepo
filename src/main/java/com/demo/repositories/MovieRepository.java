package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
	
	public List<Movie> findByDirector(String d);

//	@Query(value="select * from movie where rating > ?1", nativeQuery=true)
	@Query(value="select m from Movie m where m.rating > ?1")
	public List<Movie> filterMovies(double rating);

//	public List<T> findAll();
//	public T findById(int id);
//	public T save(T m);
//	
}


//interface BookRepository {
//
//	public List<Book> findAllBooks();
//	public Book findBookById(int id);
//	public Book saveBook(Book m);
//	
//}