package com.demo.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	private String director;
	private double rating;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", director=" + director + ", rating=" + rating + "]";
	}
	public Movie(int id, String title, String director, double rating) {
		this(title, director, rating);
		this.id = id;
	}
	public Movie(String title, String director, double rating) {
		super();
		this.title = title;
		this.director = director;
		this.rating = rating;
	}
	public Movie() {
		super();
	}	
	
}
